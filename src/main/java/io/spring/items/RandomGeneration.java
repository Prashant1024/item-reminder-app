package io.spring.items;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
@Service
public class RandomGeneration {

    public String randomNumberGenerator()
    {
        LocalDateTime now = LocalDateTime.now();        //LocalDateTime Object created
        int minute = 60 + now.getMinute();
        int second = now.getSecond();
        int millis = now.get(ChronoField.MILLI_OF_SECOND);
        int id = minute * second * millis;
        String str1 = Integer.toString(id);
        String finalId = str1.substring(0, 5);
        return finalId;
    }
}
