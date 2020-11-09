package com.example.springboot.services;

import com.example.springboot.models.NedbetalingModel;

import java.time.LocalDate;
import java.util.Map;

public class NedbetalingService {

    private double lanDefault = 1000000;
    private double antallAarDefault = 25;
    private int terminerPerAarDefault = 12;
    private double renteDefault = 3;
    private LocalDate datoDefault = LocalDate.parse("2020-01-01");
    private DatoService datoService;

    public NedbetalingService(){
        datoService = new DatoService();
    }

    public NedbetalingModel validateDataAndConstructNedbetalingModel(Map<String, Object> dataFromRequestBody){
        NedbetalingModel model = new NedbetalingModel();
        //laan
        if (dataFromRequestBody.containsKey("laanebelop")){
            try {
                double laan = ((Number) dataFromRequestBody.get("laanebelop")).doubleValue();
                model.setLaan(laan);
            } catch (ClassCastException e){
                //Bad input, should give appropriate message
                model.setLaan(lanDefault);
            }
        } else {
            model.setLaan(lanDefault);
        }
        //rente
        if (dataFromRequestBody.containsKey("nominellRente")){
            try {
                double rente = ((Number) dataFromRequestBody.get("nominellRente")).doubleValue();
                model.setRente(rente);
            } catch (ClassCastException e){
                //Bad input, should give appropriate message
                model.setRente(renteDefault);
            }
        } else {
            model.setRente(renteDefault);
        }
        //Dato
        if (dataFromRequestBody.containsKey("datoForsteInnbetaling") && dataFromRequestBody.containsKey("utlopsDato") ){
            try {
                String start = (String) dataFromRequestBody.get("datoForsteInnbetaling");
                String end = (String) dataFromRequestBody.get("utlopsDato");
                //should add format check on the strings
                double years = datoService.getYearsBetweenTwoDates(start,end);
                LocalDate dato = LocalDate.parse(start);
                model.setAntallAar(years);
                model.setDato(dato);
            } catch (ClassCastException e){
                //Bad input, should give appropriate message
                model.setAntallAar(antallAarDefault);
                model.setDato(datoDefault);
            }
        } else {
            model.setAntallAar(antallAarDefault);
            model.setDato(datoDefault);
        }
        model.setTerminerPerAar(terminerPerAarDefault);
        System.out.println(model);
        return model;
    }
}
