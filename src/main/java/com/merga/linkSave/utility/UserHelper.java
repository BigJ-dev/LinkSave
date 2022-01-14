package com.merga.linkSave.utility;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class UserHelper {

    public static String getDateTimeString() {
        DateFormat formatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.now();
        Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        date.setTime(30L);
        return formatted.format(date);
    }

    public static Date getDateTimeObject() {
        LocalDateTime dateTime = LocalDateTime.now();
        Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        date.setTime(30L);
        return date;
    }

    public static URI getUri(String uriPath) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(uriPath).toUriString());
        return uri;
    }
}
