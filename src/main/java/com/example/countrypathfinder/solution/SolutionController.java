package com.example.countrypathfinder.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolutionController {

    @Autowired
    private CountryPathFinder countryPathFinder;

    @GetMapping("/routing/{origin}/{destination}")
    public ResponseEntity<?> borderCrossing(@PathVariable("origin") String origin, @PathVariable("destination") String destination) {
        return countryPathFinder.search(origin, destination)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
