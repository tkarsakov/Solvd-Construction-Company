package com.solvd.construction.json.deserealizers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.solvd.construction.model.Client;
import com.solvd.construction.model.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ClientDeserializer extends StdDeserializer<Client> {
    private static final Logger LOGGER = LogManager.getLogger();

    public ClientDeserializer() {
        this(null);
    }

    protected ClientDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Client deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        Client client;
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        if (node.get("client") == null) {
            LOGGER.warn("Root node 'client' not found in provided file");
            return null;
        }
        node = node.get("client");
        return getModelFromRootNode(node);
    }

    public static Client getModelFromRootNode(JsonNode node) {
        String clientName = node.get("clientName").asText();
        String clientEmail = node.get("clientEmail").asText();
        if (node.get("countryId") == null) {
            String countryName = node.get("country").get("countryName").asText();
            Long postalCode = node.get("country").get("postalCode").asLong();
            return new Client(clientName, clientEmail, new Country(countryName, postalCode));
        } else {
            Long countryId = node.get("countryId").asLong();
            return new Client(null, clientName, clientEmail, countryId);
        }
    }
}
