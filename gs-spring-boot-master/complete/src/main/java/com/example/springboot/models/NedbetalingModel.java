package com.example.springboot.models;







import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Map;

public class NedbetalingModel {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dato;
    private double laan;
    private double rente;
    private int terminerPerAar;
    private double antallAar;

    @Override
    public String toString(){
        return "Dato: "+dato+"\nlaan: "+laan + "\nrente: " + rente + "\nterminerPerAar: "+terminerPerAar+"\nantallAar: "+antallAar+"\n";
    }

    public LocalDate getDato() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public double getLaan() {
        return laan;
    }

    public void setLaan(double laan) {
        this.laan = laan;
    }

    public double getRente() {
        return rente;
    }

    public void setRente(double rente) {
        this.rente = rente;
    }

    public int getTerminerPerAar() {
        return terminerPerAar;
    }

    public void setTerminerPerAar(int terminerPerAar) {
        this.terminerPerAar = terminerPerAar;
    }

    public double getAntallAar() {
        return antallAar;
    }

    public void setAntallAar(double antallAar) {
        this.antallAar = antallAar;
    }


}
