package com.prodeapp;

public class Partido {
    private Equipo equipo1;
    private Equipo equipo2;
    private int golesEquipo1;
    private int golesEquipo2;
    private ResultadoEnum resultado;
    private Ronda ronda;

    // Constructor
    public Partido(Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2, Ronda ronda) {
      this.equipo1 = equipo1;
      this.equipo2 = equipo2;
      this.golesEquipo1 = golesEquipo1;
      this.golesEquipo2 = golesEquipo2;
      this.resultado = calcularResultado();
      this.ronda = ronda;
    }
    
    // Método privado para calcular el resultado del partido
    private ResultadoEnum calcularResultado() {
      if (golesEquipo1 > golesEquipo2) {
        return ResultadoEnum.GANADOR;
      } else if (golesEquipo1 < golesEquipo2) {
        return ResultadoEnum.PERDEDOR;
      } else {
        return ResultadoEnum.EMPATE;
      }
    }
    
    // Getters y Setters
    public Equipo getEquipo1() {
      return equipo1;
    }
    
    public void setEquipo1(Equipo equipo1) {
      this.equipo1 = equipo1;
    }
    
    public Equipo getEquipo2() {
      return equipo2;
    }
    
    public void setEquipo2(Equipo equipo2) {
      this.equipo2 = equipo2;
    }
    
    public int getGolesEquipo1() {
      return golesEquipo1;
    }
    
    public void setGolesEquipo1(int golesEquipo1) {
      this.golesEquipo1 = golesEquipo1;
    }
    
    public int getGolesEquipo2() {
      return golesEquipo2;
    }
    
    public void setGolesEquipo2(int golesEquipo2) {
      this.golesEquipo2 = golesEquipo2;
    }
    
    public ResultadoEnum getResultado() {
      return resultado;
    }
    
    public void setResultado(ResultadoEnum resultado) {
      this.resultado = resultado;
    }

    public Ronda getRonda() {
      return ronda;
    }

    public int getRondaNro() {
      return Integer.parseInt(ronda.getNro());
    }

    public void setRonda(Ronda ronda) {
      this.ronda = ronda;
    }

    //Getter para extraer los equipos para comparar con los de pronosticos

    public String getEquipo1Nombre() {
      return equipo1.getNombre();
    }
    public String getEquipo2Nombre() {
      return equipo2.getNombre();
    }

    public String getEquipo1description() {
      return equipo1.getDescripcion();
    }
    public String getEquipo2description() {
      return equipo2.getDescripcion();
    }

  }
  
