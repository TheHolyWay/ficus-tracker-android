package ru.holyway.ficustracker.entity;

public class SensorData {

    private Integer serial;

    private float temperature;

    private int light;

    private int soilMoisture;

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public int getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(int soilMoisture) {
        this.soilMoisture = soilMoisture;
    }
}
