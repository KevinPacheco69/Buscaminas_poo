package model;

import java.io.Serializable;

public class Casilla implements Serializable {

    private boolean mina;
    private boolean descubierta;
    private boolean marcada;
    private int adyacentes;

    public Casilla() {
        this.mina = false;
        this.descubierta = false;
        this.marcada = false;
        this.adyacentes = 0;
    }

    public boolean isMina() { return mina; }
    public void setMina(boolean mina) { this.mina = mina; }

    public boolean isDescubierta() { return descubierta; }
    public void setDescubierta(boolean descubierta) { this.descubierta = descubierta; }

    public boolean isMarcada() { return marcada; }
    public void setMarcada(boolean marcada) { this.marcada = marcada; }

    public int getAdyacentes() { return adyacentes; }
    public void setAdyacentes(int adyacentes) { this.adyacentes = adyacentes; }
}
