package com.prodeapp;

import java.util.ArrayList;

public class Equipo {
    private String nombre;
    private String descripcion;
    private ArrayList<Partido> partidosJugados;
    
    // Constructor
    public Equipo(String nombre, String descripcion) {
      this.nombre = nombre;
      this.descripcion = descripcion;
      this.partidosJugados = new ArrayList<Partido>();
    }
    
    // Getters y Setters
    public String getNombre() {
      return nombre;
    }
    
    public void setNombre(String nombre) {
      this.nombre = nombre;
    }
    
    public String getDescripcion() {
      return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
    }

    public ArrayList<Partido> getPartidosJugados() {
      return partidosJugados;
    }

    public void setPartidosJugados(ArrayList<Partido> partidosJugados) {
      this.partidosJugados = partidosJugados;
    }
  }
  