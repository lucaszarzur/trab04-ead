package br.com.utfpr.libraryfive.util;

import org.springframework.stereotype.Component;

@Component
public class FormatUtils {

    public Integer getIntegerValue(String stringValue) {
        return Integer.valueOf(stringValue);
    }
}
