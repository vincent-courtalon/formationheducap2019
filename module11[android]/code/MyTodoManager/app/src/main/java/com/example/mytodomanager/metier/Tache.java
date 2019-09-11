package com.example.mytodomanager.metier;

import android.graphics.Color;

public class Tache {
    private static final int[] PRIORITE_COLOR = {
            Color.LTGRAY,
            Color.CYAN,
            Color.GREEN,
            Color.MAGENTA,
            Color.YELLOW
    };

    private String titre;
    private String contexte;
    private int priorite;
    private boolean termine;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContexte() {
        return contexte;
    }

    public void setContexte(String contexte) {
        this.contexte = contexte;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public boolean isTermine() {
        return termine;
    }

    public void setTermine(boolean termine) {
        this.termine = termine;
    }

    public Tache(String titre, String contexte, int priorite, boolean termine) {
        this.titre = titre;
        this.contexte = contexte;
        this.priorite = priorite;
        this.termine = termine;
    }

    public int getPrioriteColor() {
        if (this.priorite < 1 ||this.priorite > PRIORITE_COLOR.length)
            return PRIORITE_COLOR[0];
        return PRIORITE_COLOR[this.priorite - 1];

    }
}
