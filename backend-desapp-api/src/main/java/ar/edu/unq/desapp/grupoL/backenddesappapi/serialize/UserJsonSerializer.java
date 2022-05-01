package ar.edu.unq.desapp.grupoL.backenddesappapi.serialize;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class UserJsonSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(User user, JsonGenerator jgen, SerializerProvider serializers) throws IOException {
        jgen.writeStartObject();


        jgen.writeNumberField("id", user.getId());
        jgen.writeStringField("name", user.getName());
        jgen.writeStringField("surname", user.getSurname());
        jgen.writeStringField("mail", user.getEmail());
        jgen.writeStringField("address", user.getAddress());
        jgen.writeStringField("password", user.getPassword());
        jgen.writeStringField("cvu", user.getCvu());
        jgen.writeStringField("wallet", user.getWallet());
        jgen.writeStringField("score", user.getScore());
        jgen.writeObjectField("operationsNumber", user.getOperationsNumber());


       // serializeTransactions(jgen, user);
        jgen.writeEndObject();
    }

   /* private void serializeTransactions(JsonGenerator jgen, User user) throws IOException {
        jgen.writeFieldName("transactions");
        jgen.writeStartArray();
        user.getTransactions().stream().forEach(transaction -> {
            try {
                jgen.writeObject(transaction);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jgen.writeEndArray();
    }*/


}



