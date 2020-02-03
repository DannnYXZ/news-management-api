package com.epam.lab.configuration;

import com.epam.lab.dto.SortCriteria;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, SortCriteria> {
    @Override
    public SortCriteria convert(String source) {
        return SortCriteria.valueOf(source.toUpperCase());
    }
}