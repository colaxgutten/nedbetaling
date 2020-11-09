package com.example.springboot.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class InnbetalingModel {
    private double restGjeld;
    private LocalDate dato;
    private double innbetaling;
    private double gebyr;
    private double renter;
    private double total;

    public InnbetalingModel(double restGjeld, LocalDate dato, double innbetaling, double gebyr, double renter, double total) {
        this.restGjeld = restGjeld;
        this.dato = dato;
        this.innbetaling = innbetaling;
        this.gebyr = gebyr;
        this.renter = renter;
        this.total = total;
    }

    @Override
    public String toString(){
        String format = "restGjeld: "+ getRestGjeld()+"\ndato: "+dato+"\ninnbetaling: "+innbetaling+"\ngebyr: "+gebyr+"\nrenter: "+renter+"\ntotal: "+total+"\n";
        return format;
    }

    public double getRestGjeld() {
        return restGjeld;
    }

    public void setRestGjeld(double restGjeld) {
        this.restGjeld = restGjeld;
    }

    public LocalDate getDato() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public double getInnbetaling() {
        return innbetaling;
    }

    public void setInnbetaling(double innbetaling) {
        this.innbetaling = innbetaling;
    }

    public double getGebyr() {
        return gebyr;
    }

    public void setGebyr(double gebyr) {
        this.gebyr = gebyr;
    }

    public double getRenter() {
        return renter;
    }

    public void setRenter(double renter) {
        this.renter = renter;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
