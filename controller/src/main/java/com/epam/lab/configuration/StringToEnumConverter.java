package com.epam.lab.configuration;

import com.epam.lab.dto.SortCriteriaDTO;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, SortCriteriaDTO> {
    @Override
    public SortCriteriaDTO convert(String source) {
        return SortCriteriaDTO.valueOf(source.toUpperCase());
    }
}