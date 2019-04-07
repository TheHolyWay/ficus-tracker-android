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

    @SerializedName("t_max")
    private float maxTemp;
    @SerializedName("sm_max")
    private float maxHum;
    @SerializedName("l_max")
    private float maxLight;

    @SerializedName("sm_min")
    private float minHum;
    @SerializedName("t_min")
    private float minTemp;
    @SerializedName("l_min")
    private float minLight;
    @SerializedName("sensor_data")
    private SensorData sensorData;

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public float getMaxHum() {
        return maxHum;
    }

    public void setMaxHum(float maxHum) {
        this.maxHum = maxHum;
    }

    public float getMaxLight() {
        return maxLight;
    }

    public void setMaxLight(float maxLight) {
        this.maxLight = maxLight;
    }

    public float getMinHum() {
        return minHum;
    }

    public void setMinHum(float minHum) {
        this.minHum = minHum;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public float getMinLight() {
        return minLight;
    }

    public void setMinLight(float minLight) {
        this.minLight = minLight;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations;
    }

    public List<String> getProblems() {
        return problems;
    }

    public void setProblems(List<String> problems) {
        this.problems = problems;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

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
}
