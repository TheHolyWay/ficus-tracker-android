package ru.holyway.ficustracker.entity;

public class Flower {

    private String name;
    private int type;
    private int sensor;

    public Flower(String name, int type, int sensor) {
        this.name = name;
        this.type = type;
        this.sensor = sensor;
    }

    public String getName() {
        return name;
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

    public int getSensor() {
        return sensor;
    }

    public void setSensor(int sensor) {
        this.sensor = sensor;
    }
}
