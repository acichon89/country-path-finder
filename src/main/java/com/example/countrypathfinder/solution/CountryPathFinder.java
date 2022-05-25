package com.example.countrypathfinder.solution;

import java.util.List;
import java.util.Optional;

public interface CountryPathFinder {

    Optional<List<String>> search(String source, String destination);
}
