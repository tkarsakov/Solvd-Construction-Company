package com.solvd.construction.xml.modeltags;

public enum SupplierTags {
    SUPPLIER_NAME_TAG("supplierName"),
    SUPPLIER_EMAIL_TAG("supplierEmail"),
    SUPPLIER_COUNTRY_ID_TAG("countryId");
    public final String TAG;

    SupplierTags(String TAG) {
        this.TAG = TAG;
    }
}
