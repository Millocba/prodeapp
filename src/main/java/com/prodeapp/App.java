package com.prodeapp;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Leer archivo de pronósticos
        try (BufferedReader br = new BufferedReader(new FileReader("Recursos/pronostico.csv"))) {
            String line;
            Partido[] partidos = new Partido[2];
            int i = 0;
            int lineCount = 0;
            String[] sele1 = new String[2];
            String[] sele2 = new String[2];
            int[] golSele1 = new int[2];
            int[] golSele2 = new int[2];

            ResultadoEnum resultado;

                // Leer archivo de resultados
            try (BufferedReader br1 = new BufferedReader(new FileReader("Recursos/resultados.csv"))) {
                String line1;
                int j = 0;
                while ((line1 = br1.readLine()) != null) {

                    if (j == 0) {
                        // saltar la primera línea
                        j++;
                        continue;
                    }

                    String[] values = line1.split(";");
                    sele1[j-1] = values[0];
                    sele2[j-1] = values[5];
                    golSele1[j-1] = Integer.parseInt(values[3]);
                    golSele2[j-1] = Integer.parseInt(values[4]);
                    
                    
                    /* if (golSele1[j-1] > golSele2[j-1]) {
                        resultado = ResultadoEnum.GANADOR;
                    } else if (golSele1[j-1] < golSele2[j-1]) {
                        resultado = ResultadoEnum.PERDEDOR;
                    } else {
                        resultado = ResultadoEnum.EMPATE;
                    } */
                    j++;
                    }
            } catch (IOException e) {
                System.out.println("Error al leer el archivo de resultados");
            }

            int puntosAcum= 0;
            while ((line = br.readLine()) != null) {

                if (lineCount == 0) {
                    // saltar la primera línea
                    lineCount++;
                    continue;
                }


                String[] values = line.split(";");
                String[] selecciones = {"Argentina","Arabia Saudita", "Polonia", "México"};

                String nombre1 = values[0].trim();
                String descripcion1 = selecciones[Integer.parseInt(nombre1)-1];

                String nombre2 = values[4].trim();
                String descripcion2 = selecciones[Integer.parseInt(nombre2)-1];

                System.out.println(nombre1 + descripcion1+"\n"+ nombre2 + descripcion2);

                Equipo equipo1 = new Equipo(nombre1,descripcion1);
                Equipo equipo2 = new Equipo(nombre2,descripcion2);
        
                partidos[i] = new Partido(equipo1, equipo2, golSele1[i], golSele2[i]);
                
                
                
                if (values[1]=="X"){
                    resultado = ResultadoEnum.GANADOR;
                    Pronostico pronostico1 = new Pronostico(partidos[i], equipo1, resultado);
                    //resultado = ResultadoEnum.PERDEDOR;
                    //Pronostico pronostico2 = new Pronostico(partidos[i], equipo2, resultado);
                    if (partidos[i].getResultado()  == resultado){
                        puntosAcum++;
                    }
                    System.out.println("Resultado del partido " + i + descripcion1 + partidos[i].getResultado());
                }else if(values[2]=="X"){
                    resultado = ResultadoEnum.EMPATE;
                    Pronostico pronostico1 = new Pronostico(partidos[i], equipo1, resultado);
                    //Pronostico pronostico2 = new Pronostico(partidos[i], equipo2, resultado);
                    if (partidos[i].getResultado() == resultado){
                        puntosAcum++;
                    }
                    System.out.println("Resultado del partido " + i + descripcion1 + partidos[i].getResultado());
                }else{
                    resultado = ResultadoEnum.PERDEDOR;
                    Pronostico pronostico1 = new Pronostico(partidos[i], equipo1, resultado);
                    //resultado = ResultadoEnum.GANADOR;
                    //Pronostico pronostico2 = new Pronostico(partidos[i], equipo2, resultado);
                    if (partidos[i].getResultado() == resultado){
                        puntosAcum++;
                    }
                    System.out.println("Resultado del partido " + i + descripcion1 + partidos[i].getResultado());
                }
                i++;

                /*      int golesEquipo1 = 0;
                int golesEquipo2 = 0;
            if (!values[1].isEmpty()) {
                golesEquipo1 = Integer.parseInt(values[1]);
                }
                if (!values[3].isEmpty()) {
                golesEquipo2 = Integer.parseInt(values[3]);
                }
                partidos[i] = new Partido(equipo1, equipo2, golesEquipo1, golesEquipo2);
                i++; */
            }

        System.out.println("Total de puntos acumulados: "+ puntosAcum);
        
        // Leer archivo de resultados
        /* try (BufferedReader br = new BufferedReader(new FileReader("Recursos/resultados.csv"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(";");
            Equipo equipo1 = new Equipo(values[1], values[2]);
            Equipo equipo2 = new Equipo(values[6], values[7]);
            int golesEquipo1 = Integer.parseInt(values[3]);
            int golesEquipo2 = Integer.parseInt(values[4]);
            Partido partido = new Partido(equipo1, equipo2, golesEquipo1, golesEquipo2);
            ResultadoEnum resultado;
            if (golesEquipo1 > golesEquipo2) {
            resultado = ResultadoEnum.GANADOR;
            } else if (golesEquipo1 < golesEquipo2) {
            resultado = ResultadoEnum.PERDEDOR;
            } else {
            resultado = ResultadoEnum.EMPATE;
            }
            Pronostico pronostico = new Pronostico(partido, equipo1, resultado);
            System.out.println("Puntos del pronóstico: " + pronostico.puntos());
        }
        } catch (IOException e) {
        System.out.println("Error al leer el archivo de resultados");
        } */
        }
        
    }
}

