package com.solvd.construction.xml.modeltags;

public enum CountryTags {
    COUNTRY_NAME_TAG("countryName"),
    POSTAL_CODE_TAG("postalCode");
    public final String TAG;

    CountryTags(String TAG) {
        this.TAG = TAG;
    }
}
