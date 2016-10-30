package com.teddydeveloper.stardewvalleywiki.Models;

/**
 * Created by Matu on 29.10.2016.
 */

public enum DataFiles {
    ARTIFACTS("artifacts", "json/Artifacts.json"),
    RINGS("rings", "json/Rings.json");

    private String name;
    private String jsonFileLocation;

    DataFiles(String name, String jsonFileLoacation) {
        this.name = name;
        this.jsonFileLocation = jsonFileLoacation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJsonFileLocation() {
        return jsonFileLocation;
    }

    public void setJsonFileLocation(String jsonFileLocation) {
        this.jsonFileLocation = jsonFileLocation;
    }
}
