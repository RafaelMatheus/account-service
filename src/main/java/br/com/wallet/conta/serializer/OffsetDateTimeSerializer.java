package br.com.wallet.conta.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {

    @Override
    public void serialize(final OffsetDateTime arg0, final JsonGenerator arg1, final SerializerProvider arg2) throws IOException {
        if (arg0 != null) {
            arg1.writeString(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(arg0));
        } else {
            arg1.writeNull();
        }
    }
}