package ru.sinnord.testtask.model;

public class RowItem {

    private final Integer number;
    private final String name;
    private final String value;

    public RowItem(Integer number, String name, String value) {
        this.number = number;
        this.name = name;
        this.value = value;
    }

    public Integer getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
