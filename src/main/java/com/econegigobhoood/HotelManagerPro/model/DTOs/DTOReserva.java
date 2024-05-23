package com.econegigobhoood.HotelManagerPro.model.DTOs;

import java.sql.Date;


public class DTOReserva {
    private int idReserva;
    private float valorTotal;
    private Date dtReserva;
    private int idFuncionarios;
    private String Status;
    private int idHospede;


    public DTOReserva(int idReserva,float valorTotal,Date dtReserva,int idFuncionarios,String Status,int idHospede){
        this.idReserva = idReserva;
        this.valorTotal = valorTotal;
        this.dtReserva = dtReserva;
        this.idFuncionarios = idFuncionarios;
        this.Status = Status;
        this.idHospede = idHospede;
    }


    public int getIdReserva() {
        return idReserva;
    }


    public int setIdReserva(int idReserva) {
        return this.idReserva = idReserva;
    }


    public float getValorTotal() {
        return valorTotal;
    }


    public float setValorTotal(float valorTotal) {
        return this.valorTotal = valorTotal;
    }


    public Date getDtReserva() {
        return dtReserva;
    }


    public Date setDtReserva(Date dtReserva) {
        return this.dtReserva = dtReserva;
    }


    public int getIdFuncionarios() {
        return idFuncionarios;
    }


    public int setIdFuncionarios(int idFuncionarios) {
         return this.idFuncionarios = idFuncionarios;
    }


    public String getStatus() {
        return Status;
    }


    public String setStatus(String status) {
        return Status = status;
    }


    public int getIdHospede() {
        return idHospede;
    }


    public int setIdHospede(int idHospede) {
         return this.idHospede = idHospede;
    }


}
