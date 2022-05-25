package com.example.countrypathfinder.solution.impl;

import com.example.countrypathfinder.country.Country;
import com.example.countrypathfinder.country.CountryProvider;
import com.example.countrypathfinder.dijkstra.Graph;
import com.example.countrypathfinder.dijkstra.Node;
import com.example.countrypathfinder.solution.CountryPathFinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
@Slf4j
class DijkstraPopulatedCountryPathFinder implements CountryPathFinder {

    private final CountryProvider countryProvider;
    private final Map<String, Graph> mapGraph = new HashMap<>();

    public DijkstraPopulatedCountryPathFinder(CountryProvider countryProvider) {
        this.countryProvider = countryProvider;
    }

    @PostConstruct
    public void reloadAllCountryDistances() {
        log.info("Started populating graphs from all countries...");
        List<Country> countries = countryProvider.loadAll();
        countries.forEach(country -> {
            Graph graph = new Graph();
            countries.forEach(c -> {
                Node node = new Node(c.getCca3());
                graph.addNode(node);
            });

            countries.forEach(c -> {
                graph.searchNode(c.getCca3()).ifPresent(node -> {
                    c.getBorders().forEach(border -> {
                        node.addDestination(graph.searchNode(border)
                                .orElseThrow(countryNotFound(border)), 1);
                    });
                });
            });
            graph.calculateShortestPathFromSource(graph.searchNode(country.getCca3()).orElseThrow(countryNotFound(country.getCca3())));
            mapGraph.put(country.getCca3(), graph);
        });
        log.info("Finished populating all countries' graphs!");
    }

    @Override
    public Optional<List<String>> search(String source, String destination) {
        return Optional.ofNullable(mapGraph.get(source)).flatMap(graph -> graph.searchNode(destination))
                .map(Node::getShortestPath)
                .map(list -> list.stream().map(Node::getName).collect(Collectors.toList()))
                .filter(list -> !list.isEmpty())
                .map(list -> {
                    list.add(destination);
                    return list;
                });
    }

    private Supplier<IllegalStateException> countryNotFound(String name) {
        return () -> new IllegalStateException(String.format("Cannot find country with given name %s", name));
    }
}
