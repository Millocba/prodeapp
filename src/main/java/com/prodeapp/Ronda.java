package com.prodeapp;

public class Ronda {
    private String nro;
    private Partido[] partidos;
    
    // Constructor
    public Ronda(String nro, Partido[] partidos) {
      this.nro = nro;
      this.partidos = partidos;
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
    
    public Partido[] getPartidos() {
      return partidos;
    }
    
    public void setPartidos(Partido[] partidos) {
      this.partidos = partidos;
    }
  }
  
