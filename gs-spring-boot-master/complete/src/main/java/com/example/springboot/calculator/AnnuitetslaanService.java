package com.example.springboot.calculator;

import com.example.springboot.models.NedbetalingModel;
import com.example.springboot.services.DatoService;
import com.example.springboot.models.InnbetalingModel;

import java.time.LocalDate;

public class AnnuitetslaanService {

    DatoService datoService;

    public AnnuitetslaanService(){
        datoService = new DatoService();
    }

    public double regnUtMaanedligBetalingsBeloep(double antallAar,int terminerPerAar, double rente, double laanesum){
        if (antallAar == 0 || terminerPerAar == 0 || laanesum ==0)
            return -1;
        double maanedligBetaling = 0;
        double rentefot =  rente/terminerPerAar;
        double vekstFaktor = 1+rentefot;
        int antallTerminer = (int)(antallAar*terminerPerAar);
        maanedligBetaling = (rentefot/(1-Math.pow(vekstFaktor,-antallTerminer)))*laanesum;
        return maanedligBetaling;
    }

    private double regnUtRenteandel(double restbelop, double rentefot){
        return rentefot*restbelop;
    }

    private InnbetalingModel lagInnbetalingModel(double restGjeld, LocalDate dato, double InnbetalingModel, double gebyr, double renter, double total){
        InnbetalingModel betaling = new InnbetalingModel(restGjeld,dato, InnbetalingModel,gebyr,renter,total);
        return betaling;
    }


    public InnbetalingModel[] lagNedbetalingsPlan(NedbetalingModel nedbetalingModel){
        int antallTerminer = (int)(nedbetalingModel.getAntallAar()*nedbetalingModel.getTerminerPerAar());
        double maanedligBetaling = regnUtMaanedligBetalingsBeloep(nedbetalingModel.getAntallAar(),nedbetalingModel.getTerminerPerAar(),nedbetalingModel.getRente(),nedbetalingModel.getLaan());
        double restbelop = nedbetalingModel.getLaan();
        double rentefot = nedbetalingModel.getRente()/nedbetalingModel.getTerminerPerAar();
        double gebyr = 30;

        InnbetalingModel[] nedbetalingsplan = new InnbetalingModel[antallTerminer];
        for (int i = 0;i<antallTerminer;i++){
            LocalDate dato = datoService.getDateXMonthsFromDate(nedbetalingModel.getDato(), i);
            double rentedel = regnUtRenteandel(restbelop,rentefot);
            double avdrag = maanedligBetaling-rentedel;
            restbelop -= avdrag;
            nedbetalingsplan[i] = lagInnbetalingModel(restbelop,dato,avdrag,gebyr,rentedel,maanedligBetaling+gebyr);
        }
        return nedbetalingsplan;
    }
}
