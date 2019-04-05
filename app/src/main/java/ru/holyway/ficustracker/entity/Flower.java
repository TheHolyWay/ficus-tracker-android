package ru.holyway.ficustracker.entity;

public class Flower {

    private String name;
    private int id;
    private int type;
    private float humidity;
    private float lighting;
    private float temperature;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getLighting() {
        return lighting;
    }

    public void setLighting(float lighting) {
        this.lighting = lighting;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
