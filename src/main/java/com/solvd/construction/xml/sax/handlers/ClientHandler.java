package com.solvd.construction.xml.sax.handlers;

import com.solvd.construction.model.Client;
import com.solvd.construction.model.Country;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import static com.solvd.construction.xml.modeltags.ClientTags.*;
import static com.solvd.construction.xml.modeltags.CountryTags.COUNTRY_NAME_TAG;
import static com.solvd.construction.xml.modeltags.CountryTags.POSTAL_CODE_TAG;

public class ClientHandler extends DefaultHandler implements ModelHandler<Client> {
    private String currentTag;

    private final Client client = new Client(null, null, null);


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
        if (currentTag.equals(clientName.name())) {
            client.setClientName(new String(ch, start, length));
        } else if (currentTag.equals(clientEmail.name())) {
            client.setClientEmail(new String(ch, start, length));
        } else if (currentTag.equals(countryId.name())) {
            client.setCountryId(Long.valueOf(new String(ch, start, length)));
        } else if (currentTag.equals(COUNTRY_NAME_TAG.TAG)) {
            client.setCountry(new Country(new String(ch, start, length), null));
        } else if (currentTag.equals(POSTAL_CODE_TAG.TAG)) {
            client.getCountry().setPostalCode(Long.valueOf(new String(ch, start, length)));
        }
    }

    @Override
    public Client getModel() {
        return this.client;
    }
}
