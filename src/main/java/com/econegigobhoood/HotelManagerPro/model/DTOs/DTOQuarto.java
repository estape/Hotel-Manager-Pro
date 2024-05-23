package com.econegigobhoood.HotelManagerPro.model.DTOs;



public class DTOQuarto  {
    
    private int idQuarto;
    private int qtdCamaSolt;
    private int qtdCamacas;
    private int qtdbanheiro;
    private String infadd;
    private int idtpQuarto;
    

    public DTOQuarto(String Nome,String Rg, int Idade){
        
    }

    public DTOQuarto(int idQuarto,int qtdCamaSolt,int qtdCamacas, int qtdbanheiro,String infadd,int idtpQuarto){
        this.idQuarto    = idQuarto;
        this.qtdCamaSolt = qtdCamaSolt;
        this.qtdCamacas  = qtdCamacas;
        this.qtdbanheiro = qtdbanheiro;
        this.infadd      = infadd;
        this.idtpQuarto  = idtpQuarto;
    }

    public int getIdQuarto() {
        return idQuarto;
    }

    public int  setIdQuarto(int idQuarto) {
        return this.idQuarto = idQuarto;
    }

    public int getQtdCamaSolt() {
        return qtdCamaSolt;
    }

    public int setQtdCamaSolt(int qtdCamaSolt) {
        return this.qtdCamaSolt = qtdCamaSolt;
    }

    public int getQtdCamacas() {
        return qtdCamacas;
    }

    public int setQtdCamacas(int qtdCamacas) {
        return this.qtdCamacas = qtdCamacas;
    }

    public int getQtdbanheiro() {
        return qtdbanheiro;
    }

    public int setQtdbanheiro(int qtdbanheiro) {
        return this.qtdbanheiro = qtdbanheiro;
    }

    public String getInfadd() {
        return infadd;
    }

    public String setInfadd(String infadd) {
        return this.infadd = infadd;
    }

    public int getIdtpQuarto() {
        return idtpQuarto;
    }

    public int setIdtpQuarto(int idtpQuarto) {
        return this.idtpQuarto = idtpQuarto;
    }

}
