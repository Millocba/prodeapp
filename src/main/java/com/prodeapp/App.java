package com.prodeapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class App {
    public static void main(String[] args) throws IOException {

        //solicita leer el archivo pronostico
        List<String> lineas = FileReaderUtil.readLines("Recursos/Pronostico2.csv");
        
        //crear un metodo aparte
        int i = 0;
        for(String linea : lineas){
            if(i==0){
                i++;
                continue;
            }else{
                String[] values = linea.split(";");
                if (values.length==6){
                    if (!values[0].isEmpty()){
                        
                        String regex = "(^[1-9]\\d*$)";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(values[1]);
                        
                        if(values[1].isEmpty() || matcher.matches()){
                            
                            matcher = pattern.matcher(values[5]);

                            if(values[5].isEmpty() || matcher.matches()){
                                if(values[2]=="X"||values[3]=="X"||values[4]=="X"){
                                    continue;
                                }else{
                                    System.out.println("Falta el pronostico ");
                                    System.exit(1);
                                }
                                
                            }else{
                                System.out.println("Falta el equipo 2 ");
                                System.exit(1);
                            }

                        }else{
                            System.out.println("Falta el equipo 1 ");
                            System.exit(1);
                        }


                    }else{
                        System.out.println("Falta el nombre del participante");
                        System.exit(1);
                    }
                }
            }
        }


        //solicita obtener los participantes
        ArrayList<Participante> participantes = DetectarPronostico.cargarPredicciones(lineas);

        Collections.sort(participantes, new Comparator<Participante>() {
            @Override
            public int compare(Participante p1, Participante p2) {
                return Integer.compare(p2.getPuntajeTotal(), p1.getPuntajeTotal());
            }
        });
        
        // Imprimir los participantes ordenados
        for (Participante p : participantes) {
            System.out.println("El participante " + p.getNombre() + " obtuvo " + p.getPuntajeTotal() + " puntos y bonus de " + p.getBonus());
        }


    }
}

