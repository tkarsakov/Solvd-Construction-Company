package com.solvd.construction.json.deserealizers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.solvd.construction.model.Country;
import com.solvd.construction.model.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class SupplierDeserializer extends StdDeserializer<Supplier> {
    private static final Logger LOGGER = LogManager.getLogger();

    public SupplierDeserializer() {
        this(null);
    }

    protected SupplierDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Supplier deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        if (node.get("supplier") == null) {
            LOGGER.warn("Root node 'supplier' not found in provided file");
            return null;
        }
        node = node.get("supplier");
        String supplierName = node.get("supplierName").asText();
        String supplierEmail = node.get("supplierEmail").asText();
        if (node.get("countryId") == null) {
            String countryName = node.get("country").get("countryName").asText();
            Long postalCode = node.get("country").get("postalCode").asLong();
            return new Supplier(supplierName, supplierEmail, new Country(countryName, postalCode));
        } else {
            Long countryId = node.get("countryId").asLong();
            return new Supplier(null, supplierName, supplierEmail, countryId);
        }
    }
}
