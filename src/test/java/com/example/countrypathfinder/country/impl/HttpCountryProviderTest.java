package com.example.countrypathfinder.country.impl;

import com.example.countrypathfinder.country.Country;
import com.example.countrypathfinder.country.CountryProvider;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HttpCountryProviderTest {

    @Test
    void integrationTest() {
        //given:
        CountryProvider countryProvider = new HttpCountryProvider();
        //when:
        List<Country> countries = countryProvider.loadAll();
        //then:
        assertEquals(250, countries.size());
    }
}
