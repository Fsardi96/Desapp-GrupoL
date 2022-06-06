package ar.edu.unq.desapp.grupoL.backenddesappapi.serialize;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class TransactionJsonSerializer extends JsonSerializer<Transaction> {

    @Override
    public void serialize(Transaction transaction, JsonGenerator jgen, SerializerProvider serializers) throws IOException {
        jgen.writeStartObject();

        jgen.writeNumberField("id", transaction.getId());
        jgen.writeStringField("dateAndTime", transaction.getDateAndTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        jgen.writeObjectField("crypto", transaction.getCrypto());
        jgen.writeStringField("transactionType", transaction.getTransactionType());
        jgen.writeObjectField("user", transaction.getUser());

        jgen.writeEndObject();
    }
}
