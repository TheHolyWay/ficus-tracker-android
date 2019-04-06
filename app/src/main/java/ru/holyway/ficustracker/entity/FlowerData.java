package ru.holyway.ficustracker.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlowerData {

    private int id;
    private String name;
    private String type;
    private List<String> recommendations;
    private List<String> problems;
    private List<String> warnings;

    public List<String> getRecommendations() {
        return recommendations;
    }

    public List<String> getProblems() {
        return problems;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    @SerializedName("sensor_data")
    private SensorData sensorData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SensorData getSensorData() {
        return sensorData;
    }

    public void setSensorData(SensorData sensorData) {
        this.sensorData = sensorData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations;
    }

    public void setProblems(List<String> problems) {
        this.problems = problems;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
}
