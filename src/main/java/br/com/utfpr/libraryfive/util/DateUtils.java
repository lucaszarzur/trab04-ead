package br.com.utfpr.libraryfive.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class DateUtils {

    public LocalDateTime getActualDate(){
        return LocalDateTime.now();
    }

    public LocalDateTime calculateDateToReturn(Integer days){
        return LocalDateTime.now().plusDays(days);
    }

    public Date convertDate(final String data) {

        String datePatternPtBr = "\\d{1,2}-\\d{1,2}-\\d{4}";
        String datePatternEn = "\\d{1,4}-\\d{1,2}-\\d{2}";

        String datePatternPtBrSlash = "\\d{1,2}/\\d{1,2}/\\d{4}";
        String datePatternEnSlash = "\\d{1,4}/\\d{1,2}/\\d{2}";

        SimpleDateFormat formatterPtBr = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatterEn = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat formatterPtBrSlash = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatterEnSlash = new SimpleDateFormat("yyyy/MM/dd");

        try {
            if (data.matches(datePatternPtBr)) {
                return formatterPtBr.parse(data);
            } else if (data.matches(datePatternPtBrSlash)) {
                return formatterPtBrSlash.parse(data);
            } else if (data.matches(datePatternEn)) {
                return formatterEn.parse(data);
            } else if (data.matches(datePatternEnSlash)) {
                return formatterEnSlash.parse(data);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Date convertLocalDateTimeToDate(LocalDateTime date) {
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }
}
