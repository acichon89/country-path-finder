package com.example.countrypathfinder.country.impl;

import com.example.countrypathfinder.country.Country;
import com.example.countrypathfinder.country.CountryProvider;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
class HttpCountryProvider implements CountryProvider {

    private static final String SOURCE = "https://raw.githubusercontent.com/mledoze/countries/master/countries.json";

    public List<Country> loadAll() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            List<Country> countries = Arrays.asList(mapper.readValue(new URL(SOURCE), Country[].class));
            log.info("Loaded {} countries from REST endpoint!", countries.size());
            return countries;
        } catch (IOException e) {
            log.error("Error while loading country list", e);
            return Collections.emptyList();
        }
    }
}
