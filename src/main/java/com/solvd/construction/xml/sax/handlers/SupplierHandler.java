package com.solvd.construction.xml.sax.handlers;

import com.solvd.construction.model.Country;
import com.solvd.construction.model.Supplier;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import static com.solvd.construction.xml.modeltags.CountryTags.COUNTRY_NAME_TAG;
import static com.solvd.construction.xml.modeltags.CountryTags.POSTAL_CODE_TAG;
import static com.solvd.construction.xml.modeltags.SupplierTags.*;

public class SupplierHandler extends DefaultHandler implements ModelHandler<Supplier> {
    private String currentTag;
    private final Supplier supplier = new Supplier(null, null, null);

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
        if (currentTag.equals(SUPPLIER_NAME_TAG.TAG)) {
            supplier.setSupplierName(new String(ch, start, length));
        } else if (currentTag.equals(SUPPLIER_EMAIL_TAG.TAG)) {
            supplier.setSupplierEmail(new String(ch, start, length));
        } else if (currentTag.equals(SUPPLIER_COUNTRY_ID_TAG.TAG)) {
            supplier.setCountryId(Long.valueOf(new String(ch, start, length)));
        } else if (currentTag.equals(COUNTRY_NAME_TAG.TAG)) {
            supplier.setCountry(new Country(new String(ch, start, length), null));
        } else if (currentTag.equals(POSTAL_CODE_TAG.TAG)) {
            supplier.getCountry().setPostalCode(Long.valueOf(new String(ch, start, length)));
        }
    }

    @Override
    public Supplier getModel() {
        return supplier;
    }
}
