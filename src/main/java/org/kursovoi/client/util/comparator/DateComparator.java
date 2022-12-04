package org.kursovoi.client.util.comparator;

import org.kursovoi.client.dto.CurrencyCourseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

@Component
public class DateComparator implements Comparator<CurrencyCourseDto> {

    @Override
    public int compare(CurrencyCourseDto o1, CurrencyCourseDto o2) {
        return LocalDate.parse(o1.getDate(), DateTimeFormatter.ISO_LOCAL_DATE)
                .compareTo(LocalDate.parse(o2.getDate(), DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
