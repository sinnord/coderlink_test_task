package ru.sinnord.testtask.model;

public class RowItem {

    private Integer id;
    private String name;
    private String value;
    private String newValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "RowItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", newValue='" + newValue + '\'' +
                '}';
    }
}
