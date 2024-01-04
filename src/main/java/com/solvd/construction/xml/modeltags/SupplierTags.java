package com.solvd.construction.xml.modeltags;

public enum SupplierTags {
    SUPPLIER_NAME_TAG("clientName"),
    SUPPLIER_EMAIL_TAG("clientEmail"),
    SUPPLIER_COUNTRY_ID_TAG("countryId");
    public final String TAG;

    SupplierTags(String TAG) {
        this.TAG = TAG;
    }
}
