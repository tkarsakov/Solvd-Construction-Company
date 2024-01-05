package com.solvd.construction.xml.sax.handlers;

import com.solvd.construction.model.Country;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import static com.solvd.construction.xml.modeltags.CountryTags.COUNTRY_NAME_TAG;
import static com.solvd.construction.xml.modeltags.CountryTags.POSTAL_CODE_TAG;

public class CountryHandler extends DefaultHandler implements ModelHandler<Country> {

    private String currentTag;
    private final Country country = new Country(null, null);

    public Country getModel() {
        return this.country;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTag = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        currentTag = ModelHandler.END_TAG;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentTag.equals(COUNTRY_NAME_TAG.TAG)) {
            country.setCountryName(new String(ch, start, length));
        } else if (currentTag.equals(POSTAL_CODE_TAG.TAG)) {
            country.setPostalCode(Long.valueOf(new String(ch, start, length)));
        }
    }
}
