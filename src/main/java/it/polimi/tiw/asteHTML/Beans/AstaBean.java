package it.polimi.tiw.asteHTML.Beans;

public class AstaBean {
    private int id;
    private int idVincitore;
    private int prezzoRaggiunto;
    private int rialzoMinimo;
    private int dataScadenza;
    private int oreRimantenti;
    private String indSped;

    public AstaBean(int id, int idVincitore, int prezzoRaggiunto, int rialzoMinimo, int dataScadenza, int oreRimantenti, String indSped) {
        this.id = id;
        this.idVincitore = idVincitore;
        this.prezzoRaggiunto = prezzoRaggiunto;
        this.rialzoMinimo = rialzoMinimo;
        this.dataScadenza = dataScadenza;
        this.oreRimantenti = oreRimantenti;
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

    public int getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(int dataScadenza) {
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
}
