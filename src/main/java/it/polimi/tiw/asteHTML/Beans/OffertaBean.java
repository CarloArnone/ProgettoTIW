package it.polimi.tiw.asteHTML.Beans;

import java.util.Date;

public class OffertaBean {
    int idCreatore;
    int idAsta;
    int prezzoOfferto;
    Date dataOfferta;

    public OffertaBean(int idCreatore, int idAsta, int prezzoOfferto, Date dataOfferta) {
        this.idCreatore = idCreatore;
        this.idAsta = idAsta;
        this.prezzoOfferto = prezzoOfferto;
        this.dataOfferta = dataOfferta;
    }


    public int getIdCreatore() {
        return idCreatore;
    }

    public void setIdCreatore(int idCreatore) {
        this.idCreatore = idCreatore;
    }

    public int getIdAsta() {
        return idAsta;
    }

    public void setIdAsta(int idAsta) {
        this.idAsta = idAsta;
    }

    public int getPrezzoOfferto() {
        return prezzoOfferto;
    }

    public void setPrezzoOfferto(int prezzoOfferto) {
        this.prezzoOfferto = prezzoOfferto;
    }

    public Date getDataOfferta() {
        return dataOfferta;
    }

    public void setDataOfferta(Date dataOfferta) {
        this.dataOfferta = dataOfferta;
    }
}
