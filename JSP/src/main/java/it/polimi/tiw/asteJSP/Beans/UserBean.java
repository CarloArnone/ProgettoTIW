package it.polimi.tiw.asteJSP.Beans;

public class UserBean {

    private int id;
    private String username;
    private String indirizzoSpedizione;
    private String lang;

    public UserBean(String username) {
        this.username = username;
    }

    public UserBean(int id, String username, String indirizzoSpedizione, String lang) {
        this.id = id;
        this.username = username;
        this.indirizzoSpedizione = indirizzoSpedizione;
        this.lang = lang;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getIndirizzoSpedizione() {
        return indirizzoSpedizione;
    }

    public String getLang() {
        return lang;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIndirizzoSpedizione(String indirizzoSpedizione) {
        this.indirizzoSpedizione = indirizzoSpedizione;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
