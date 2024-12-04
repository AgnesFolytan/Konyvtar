package com.example.konyvtar;

public class Konyvtar {
    private String cim;
    private String szerzo;
    private int oldalszam;

    public Konyvtar(String cim, String szerzo, int oldalszam) {
        this.cim = cim;
        this.szerzo = szerzo;
        this.oldalszam = oldalszam;
    }

    public String getCim() {
        return cim;
    }

    public String getSzerzo() {
        return szerzo;
    }

    public int getOldalszam() {
        return oldalszam;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public void setSzerzo(String szerzo) {
        this.szerzo = szerzo;
    }

    public void setOldalszam(int oldalszam) {
        this.oldalszam = oldalszam;
    }
}
