package com.econegigobhoood.HotelManagerPro.Model;

import java.sql.Date;

public class DTOHospedagem {
    private Date DtEntreda;
    private Date DtSaida;
    private int NumeroQuarto;
    private int IdCliente;

    public DTOHospedagem(){

    }

    public DTOHospedagem(Date DtEntreda,Date DtSaida, int NumeroQuarto,int IdCliente){
      this.DtEntreda = DtEntreda;
      this.DtSaida = DtSaida;
      this.NumeroQuarto = NumeroQuarto;
      this.IdCliente = IdCliente;
    }

    public Date getDtEntreda() {
        return DtEntreda;
    }

    public void setDtEntreda(Date dtEntreda) {
        DtEntreda = dtEntreda;
    }

    public Date getDtSaida() {
        return DtSaida;
    }

    public void setDtSaida(Date dtSaida) {
        DtSaida = dtSaida;
    }

    public int getNumeroQuarto() {
        return NumeroQuarto;
    }

    public void setNumeroQuarto(int numeroQuarto) {
        NumeroQuarto = numeroQuarto;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }
}
