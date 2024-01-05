package com.solvd.construction.xml.jaxb.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.sql.Timestamp;

public class TimestampAdapter extends XmlAdapter<String, Timestamp> {

    @Override
    public Timestamp unmarshal(String s) throws Exception {
        String timestampPattern = "[0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}";
        if (s.matches(timestampPattern)) {
            return Timestamp.valueOf(s);
        } else {
            throw new Exception("Illegal format for Timestamp found in xml file");
        }
    }

    @Override
    public String marshal(Timestamp timestamp) throws Exception {
        return timestamp.toString();
    }
}
