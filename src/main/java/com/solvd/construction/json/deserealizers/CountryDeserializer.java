package com.solvd.construction.json.deserealizers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.solvd.construction.model.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class CountryDeserializer extends StdDeserializer<Country> {
    private static final Logger LOGGER = LogManager.getLogger();

    public CountryDeserializer() {
        this(null);
    }

    protected CountryDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Country deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        if (node.get("country") == null) {
            LOGGER.warn("Root node 'country' not found in provided file");
            return null;
        }
        node = node.get("country");
        return new Country(
                node.get("countryName").asText(),
                node.get("postalCode").asLong()
        );
    }
}
