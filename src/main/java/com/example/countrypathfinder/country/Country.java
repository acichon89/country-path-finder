package com.example.countrypathfinder.country;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Country {
    private Name name;
    private List<String> tld = new ArrayList<>();
    private String cca2;
    private String ccn3;
    private String cca3;
    private String cioc;
    private boolean independent;
    private String status;
    private List<String> borders = new ArrayList<>();
}
