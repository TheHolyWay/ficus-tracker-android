package ru.holyway.ficustracker.entity;

import com.google.gson.annotations.SerializedName;

public class FlowerData {

    private int id;
    private String name;
    private String type;

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
}
