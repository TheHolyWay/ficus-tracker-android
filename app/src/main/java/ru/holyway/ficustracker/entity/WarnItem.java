package ru.holyway.ficustracker.entity;

public class WarnItem {

    private Type type;
    private String text;
    public WarnItem(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public enum Type {
        NOTIFY, WARNING, PROBLEM
    }
}
