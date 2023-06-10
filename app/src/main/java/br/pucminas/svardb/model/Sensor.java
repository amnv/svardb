package br.pucminas.svardb.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


public class Sensor implements Serializable {
    private String name;
    private double value;
    private LocalDateTime date;

    public Sensor(String name, double value, LocalDateTime localDateTime) {
        this.name = name;
        this.value = value;
        this.date = localDateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
