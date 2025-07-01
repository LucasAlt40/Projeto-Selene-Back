package br.selene.projectseleneback.infra.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateHelper {
    public static LocalDateTime convertDateToLocalDateTime(Date date){
        return  date.toInstant()
                .atZone(ZoneId.of("UTC"))
                .toLocalDateTime();
    }
}
