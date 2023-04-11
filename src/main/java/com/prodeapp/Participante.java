package com.prodeapp;

import java.util.ArrayList;

public class Participante {
    private String nombre;
    private ArrayList<Pronostico> pronosticos;
    private int puntajeTotal;
    private int bonus;

    public Participante(String nombre) {
        this.nombre = nombre;
        this.pronosticos = new ArrayList<Pronostico>();
        this.puntajeTotal = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Pronostico> getPronosticos() {
        return pronosticos;
    }

    public void setPronosticos(ArrayList<Pronostico> pronosticos) {
        this.pronosticos = pronosticos;
    }

    public void addPronostico(Pronostico pronostico) {
        pronosticos.add(pronostico);
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal += puntajeTotal;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus += bonus;
    }
    
}
