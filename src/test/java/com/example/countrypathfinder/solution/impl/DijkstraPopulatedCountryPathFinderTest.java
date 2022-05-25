package com.example.countrypathfinder.solution.impl;

import com.example.countrypathfinder.country.Country;
import com.example.countrypathfinder.country.CountryProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DijkstraPopulatedCountryPathFinderTest {

    private DijkstraPopulatedCountryPathFinder pathFinder;

    @BeforeEach
    void setUp() {
        CountryProvider countryProvider = Mockito.mock(CountryProvider.class);
        when(countryProvider.loadAll()).thenReturn(mockedCountries());
        pathFinder = new DijkstraPopulatedCountryPathFinder(countryProvider);
        pathFinder.reloadAllCountryDistances();
    }

    @Test
    @DisplayName("should calculate the path")
    void shouldFindPath() {
        //given:
        //when:
        Optional<List<String>> result = pathFinder.search("CZE", "ROM");
        //then:
        assertTrue(result.isPresent());
        assertArrayEquals(new String[]{"CZE", "POL", "UKR", "ROM"}, result.get().toArray(new String[0]));
    }

    @Test
    @DisplayName("should return empty, because destination is unknown")
    void shouldReturnEmpty() {
        //given:
        //when:
        Optional<List<String>> result = pathFinder.search("POL", "NOR");
        //then:
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("should return empty, because there is no connection between source and target")
    void shouldReturnEmpty2() {
        //given:
        //when:
        Optional<List<String>> result = pathFinder.search("FRA", "ROM");
        //then:
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("should return empty, because source is unknown")
    void shouldReturnEmpty3() {
        //given:
        //when:
        Optional<List<String>> result = pathFinder.search("SWE", "CZE");
        //then:
        assertFalse(result.isPresent());
    }

    private List<Country> mockedCountries() {
        Country poland = new Country();
        poland.setCca3("POL");
        Country czechRepublic = new Country();
        czechRepublic.setCca3("CZE");
        Country ukraine = new Country();
        ukraine.setCca3("UKR");
        Country romania = new Country();
        romania.setCca3("ROM");
        poland.setBorders(asList("CZE", "UKR"));
        czechRepublic.setBorders(asList("POL"));
        ukraine.setBorders(asList("POL", "ROM"));
        romania.setBorders(asList("UKR"));
        Country france = new Country();
        france.setCca3("FRA");
        return asList(poland, czechRepublic, ukraine, romania, france);
    }
}
