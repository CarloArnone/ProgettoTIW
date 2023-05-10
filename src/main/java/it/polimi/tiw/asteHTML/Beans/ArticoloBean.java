package it.polimi.tiw.asteHTML.Beans;

public class ArticoloBean {
    private int id;
    private int idCreatore;
    private int idAsta;
    private int prezzo;
    private String name;
    private String image;
    private String descrizione;


    public ArticoloBean(int id, int idCreatore, int prezzo, String name, String image, String descrizione) {

        //Free Article
        this.id = id;
        this.idCreatore = idCreatore;
        this.prezzo = prezzo;
        this.name = name;
        this.image = image;
        this.descrizione = descrizione;
    }

    public ArticoloBean(int id, int idCreatore, int idAsta, int prezzo, String name, String image, String descrizione) {

        //In Auction Article
        this.id = id;
        this.idCreatore = idCreatore;
        this.idAsta = idAsta;
        this.prezzo = prezzo;
        this.name = name;
        this.image = image;
        this.descrizione = descrizione;
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

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
