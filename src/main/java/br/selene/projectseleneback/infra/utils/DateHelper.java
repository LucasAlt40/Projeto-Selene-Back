package br.selene.projectseleneback.infra.utils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateHelper {
    public static LocalDateTime convertDateToLocalDateTime(Date date){
        return  date.toInstant()
                .atZone(ZoneId.of("UTC"))
                .toLocalDateTime();
    }

    public static String formatToIso8601(LocalDateTime dateTime) {
        // Define o offset fixo de -3 horas
        ZoneOffset offset = ZoneOffset.of("-03:00");

        // Converte LocalDateTime para OffsetDateTime com o offset desejado
        OffsetDateTime offsetDateTime = dateTime.atOffset(offset);

        // Formata no padrão ISO_OFFSET_DATE_TIME (exemplo: 2023-08-14T19:09:10-03:00)
        return offsetDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
