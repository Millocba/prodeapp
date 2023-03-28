package com.prodeapp;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Leer archivo de pronósticos
        List<String> lineas = FileReaderUtil.readLines("Recursos/pronostico.csv");
        Partido[] partidos = new Partido[2];
        int i = 0;
        int lineCount = 0;
        String[] sele1 = new String[2];
        String[] sele2 = new String[2];
        int[] golSele1 = new int[2];
        int[] golSele2 = new int[2];

            ResultadoEnum resultado;
            
            int j =0;
            List<String> lineas1 = FileReaderUtil.readLines("Recursos/resultados.csv");
            for (String linea1 : lineas1) {
                   
                    if (j == 0) {
                        // saltar la primera línea
                        j++;
                        continue;
                    }
                    //separo los datos y cargo las variables locales de resultados
                    String[] values = linea1.split(";");
                    sele1[j-1] = values[0];
                    sele2[j-1] = values[5];
                    golSele1[j-1] = Integer.parseInt(values[3]);
                    golSele2[j-1] = Integer.parseInt(values[4]);
                    
                    j++;
                    }

            
            int puntosAcum= 0;
            //inicio el recorrido de los partidos
            for (String line : lineas) {

                if (lineCount == 0) {
                    // saltar la primera línea
                    lineCount++;
                    continue;
                }

                //separar campos y asignar valores a las variables pronostico
                String[] values = line.split(";");
                String[] selecciones = {"Argentina","Arabia Saudita", "Polonia", "México"};

                String nombre1 = values[0].trim();
                String descripcion1 = selecciones[Integer.parseInt(nombre1)-1];

                String nombre2 = values[4].trim();
                String descripcion2 = selecciones[Integer.parseInt(nombre2)-1];

                //System.out.println(nombre1 + descripcion1+"\n"+ nombre2 + descripcion2);
                
                //generar los obejos equipos
                Equipo equipo1 = new Equipo(nombre1,descripcion1);
                Equipo equipo2 = new Equipo(nombre2,descripcion2);
                
                //armar los partidos
                partidos[i] = new Partido(equipo1, equipo2, golSele1[i], golSele2[i]);
                               
                if (values[1].equals("X")){
                    resultado = ResultadoEnum.GANADOR;
                    //resultado2 = ResultadoEnum.PERDEDOR;
                }else if(values[2].equals("X")){
                    resultado = ResultadoEnum.EMPATE;
                    //resultado2 = ResultadoEnum.EMPATE;
                }else{
                    resultado = ResultadoEnum.PERDEDOR;
                    //resultado2 = ResultadoEnum.GANADOR;
                }
                Pronostico pronostico1 = new Pronostico(partidos[i], equipo1, resultado);
                //Pronostico pronostico2 = new Pronostico(partidos[i], equipo2, resultado2);

                //System.out.println(resultado);
                
                puntosAcum += pronostico1.puntos();

                System.out.println("Resultado del partido " + i + "-> " + descripcion1 + "-> " + partidos[i].getResultado());
                System.out.println("pronostico: " + pronostico1.getResultado());
                System.out.println("Sumas "+ puntosAcum + " puntos");
                //System.out.println("pronostico: " + pronostico2.getResultado());

                i++;

            }

        System.out.println("Total de puntos acumulados: "+ puntosAcum);
    
        
    }
}

