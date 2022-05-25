package com.example.countrypathfinder.country;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class Name {

    private String common;
    private String official;
    @JsonProperty("native")
    private Map<String, Spelling> _native;
}
