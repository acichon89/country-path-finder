package com.example.countrypathfinder.dijkstra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {

    @Getter
    private final String name;

    @Getter @Setter
    private List<Node> shortestPath = new LinkedList<>();
    @Getter @Setter
    private Integer distance = Integer.MAX_VALUE;
    @Getter @Setter
    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public Node(String name) {
        this.name = name;
    }

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                '}';
    }
}
