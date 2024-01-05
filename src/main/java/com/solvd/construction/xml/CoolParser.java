package com.solvd.construction.xml;

import com.solvd.construction.model.Model;
import com.solvd.construction.xml.sax.handlers.ModelHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class CoolParser<T extends DefaultHandler & ModelHandler<?>> {
    public static final Logger LOGGER = LogManager.getLogger();
    private final T handler;
    private final Schema schema;

    public CoolParser(T handler, String schemaLocation) {
        this.handler = handler;
        this.schema = CoolParser.createSchema(schemaLocation);
    }

    public Optional<Model> parse(String filename) {
        Optional<Model> optionalModel;
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setSchema(schema);
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(handler);
            xmlReader.setErrorHandler(new MyErrorHandler());
            xmlReader.parse(new File(filename).getAbsolutePath());
            optionalModel = Optional.of(handler.getModel());
            return optionalModel;
        } catch (ParserConfigurationException e) {
            LOGGER.warn("Exception while configuring parser");
            LOGGER.warn(e.getMessage());
            return Optional.empty();
        } catch (SAXException e) {
            LOGGER.warn("SAX exception");
            LOGGER.warn(e.getMessage());
            return Optional.empty();
        } catch (IOException e) {
            LOGGER.warn("Cannot open file {}", filename);
            LOGGER.warn(e.getMessage());
            return Optional.empty();
        }
    }

    private static Schema createSchema(String filename) {
        Schema schema = null;
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);
            schema = factory.newSchema(new File(filename));
        } catch (SAXException e) {
            LOGGER.warn("Error while trying to create parser schema");
            LOGGER.warn(e.getMessage());
        }
        return schema;
    }

    private static class MyErrorHandler implements ErrorHandler {

        private String getParseExceptionInfo(SAXParseException spe) {
            String systemId = spe.getSystemId();

            if (systemId == null) {
                systemId = "null";
            }

            return "URI=" + systemId + " Line="
                    + spe.getLineNumber() + ": " + spe.getMessage();
        }

        public void warning(SAXParseException spe) throws SAXException {
            LOGGER.info("Warning: " + getParseExceptionInfo(spe));
        }

        public void error(SAXParseException spe) throws SAXException {
            String message = "Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }

        public void fatalError(SAXParseException spe) throws SAXException {
            String message = "Fatal Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }
    }
}
