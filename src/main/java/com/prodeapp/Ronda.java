package com.prodeapp;

import java.util.ArrayList;

public class Ronda {
    private String nro;
    private ArrayList<Partido> partidos;
    
    // Constructor
    public Ronda(String nro) {
      this.nro = nro;
      this.partidos = new ArrayList<Partido>();
    }

    // Método para calcular los puntos de la ronda
    public int puntos() {
      int puntos = 0;
      
      for (Partido partido : partidos) {
        puntos += partido.getResultado() == ResultadoEnum.GANADOR ? 3 : 
                  partido.getResultado() == ResultadoEnum.EMPATE ? 1 : 0;
      }
      
      return puntos;
    }
    
    // Getters y Setters
    public String getNro() {
      return nro;
    }
    
    public void setNro(String nro) {
      this.nro = nro;
    }
    
    public ArrayList<Partido> getPartidos() {
      return partidos;
    }
    
    public void setPartidos(ArrayList<Partido> partidos) {
      this.partidos = partidos;
    }


  }
  
