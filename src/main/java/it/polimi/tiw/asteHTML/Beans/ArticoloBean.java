package it.polimi.tiw.asteHTML.Beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

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
        this.image = "data:image/jpeg;base64," + convertFileToBase64(new File(image));
        this.descrizione = descrizione;
    }

    public ArticoloBean(int id, int idCreatore, int idAsta, int prezzo, String name, String image, String descrizione) {

        //In Auction Article
        this.id = id;
        this.idCreatore = idCreatore;
        this.idAsta = idAsta;
        this.prezzo = prezzo;
        this.name = name;
        this.image = "data:image/jpeg;base64," + convertFileToBase64(new File(image));
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

    public static String convertFileToBase64(File file) {
        FileInputStream fileInputStream = null;
        byte[] fileBytes = new byte[(int) file.length()];
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException ignored) {
            return Base64.getEncoder().encodeToString(fileBytes);
        }
        try {
            fileInputStream.read(fileBytes);
        } catch (IOException ignored) {

        }
        try {
            fileInputStream.close();
        } catch (IOException ignored) {

        }
        return Base64.getEncoder().encodeToString(fileBytes);
    }
}
