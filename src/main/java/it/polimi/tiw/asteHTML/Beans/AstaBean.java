package it.polimi.tiw.asteHTML.Beans;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AstaBean {
    private int id;
    private int idVincitore;
    private int prezzoRaggiunto;
    private int rialzoMinimo;
    private Date dataScadenza;
    private int oreRimantenti;
    private String indSped;


    public AstaBean(int id, int prezzoRaggiunto, int rialzoMinimo, Date dataScadenza, int oreRimantenti) {
        this.id = id;
        this.prezzoRaggiunto = prezzoRaggiunto;
        this.rialzoMinimo = rialzoMinimo;
        this.dataScadenza = dataScadenza;
        this.oreRimantenti = oreRimantenti;
        this.indSped = null;
    }

    public AstaBean(int id, int idVincitore, int prezzoRaggiunto, Date dataScadenza, String indSped) {
        this.id = id;
        this.idVincitore = idVincitore;
        this.prezzoRaggiunto = prezzoRaggiunto;
        this.dataScadenza = dataScadenza;
        this.indSped = indSped;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVincitore() {
        return idVincitore;
    }

    public void setIdVincitore(int idVincitore) {
        this.idVincitore = idVincitore;
    }

    public int getPrezzoRaggiunto() {
        return prezzoRaggiunto;
    }

    public void setPrezzoRaggiunto(int prezzoRaggiunto) {
        this.prezzoRaggiunto = prezzoRaggiunto;
    }

    public int getRialzoMinimo() {
        return rialzoMinimo;
    }

    public void setRialzoMinimo(int rialzoMinimo) {
        this.rialzoMinimo = rialzoMinimo;
    }

    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public int getOreRimantenti() {
        return oreRimantenti;
    }

    public void setOreRimantenti(int oreRimantenti) {
        this.oreRimantenti = oreRimantenti;
    }

    public String getIndSped() {
        return indSped;
    }

    public void setIndSped(String indSped) {
        this.indSped = indSped;
    }

    public String getRemainingTime(){
        if(oreRimantenti > 0){ //Data login maggiore Data termine asta
            return "0d 0h";
        }
        int timeRemaining = -oreRimantenti;
        int days = timeRemaining/24;
        int hours = timeRemaining - days*24;
        return days + "d " + hours + "h";
    }
}
