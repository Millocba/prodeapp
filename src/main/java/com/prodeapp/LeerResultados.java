package com.prodeapp;

import java.util.ArrayList;
import java.util.List;

public class LeerResultados {
    public static ArrayList<Partido> resultadoPartidos(List<String> lineas) {
        int i = 0;
        ArrayList<Partido> partidos = new ArrayList<>();
        //ArrayList<Ronda> rondas = new ArrayList<>();

        for(String line : lineas){
            
            //salta la primer linea
            if (i==0){
                i++;
                continue;
            }

            //separacion de valores
            String[] values = line.split(";");

            //crea objeto ronda
            
            Ronda ronda = new Ronda(values[0]);
            
            //crea los objetos equipos a partir de la lectura
            Equipo equipo1 = new Equipo(values[1],values[2]);
            //System.out.println(equipo1.getNombre()+equipo1.getDescripcion());
            int gol1 = Integer.parseInt(values[4]);
            int gol2 = Integer.parseInt(values[5]);
            Equipo equipo2 = new Equipo(values[6],values[7]);
            //System.out.println(equipo2.getNombre()+equipo2.getDescripcion());
            
            //crea el partido
            Partido partido = new Partido(equipo1,equipo2,gol1,gol2,ronda);
            //System.out.println(partido.getEquipo1Nombre()+partido.getEquipo1description());
            //System.out.println(partido.getEquipo2Nombre()+partido.getEquipo2description());

            //añade el nuevo partido al array de partidos
            partidos.add(partido);

        }
        
        //retorna los partidos leidos en el archivo
        return partidos;
        
    }
}
