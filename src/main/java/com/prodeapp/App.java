package com.prodeapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        
        //limpiar pantalla
        try {
            clearConsole();
        } catch (IOException e) {
            // Manejar excepción
            System.out.println("Error al limpiar la consola");
        }

        System.out.println("****************** PRODEAPP ******************\n");

        //solicita leer el archivo pronostico
        List<String> lineas = FileReaderUtil.readLines("Recursos/Pronostico2.csv");
        
        //crear un metodo aparte
        String mensaje = comprobarPronostico(lineas);
        if (!mensaje.equals("Ok")){
            System.out.println("Error: "+ mensaje);
            System.exit(1);
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

        System.out.println("\n****************** Saliendo ******************");

    }

private static String comprobarPronostico(List<String> lineas) {
    String mensaje ="";

    int i = -1;
    for(String linea : lineas){
        if(i==-1){
            i++;
            continue;
        }else{
            i++;
            String[] values = linea.split(";");
            if (values.length==6){
                if (!values[0].isEmpty()){
                    
                    String regex = "(^[1-9]\\d*$)";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(values[1]);
                    
                    if(values[1].isEmpty() || matcher.matches()){
                        
                        matcher = pattern.matcher(values[5]);

                        if(values[5].isEmpty() || matcher.matches()){
                            if(values[2].equals("X")||values[3].equals("X")||values[4].equals("X")){
                                mensaje = "Ok";
                                continue;
                            }else{
                                mensaje = "Falta el pronostico en linea " + i + "-> " + linea ;
                                break;
                            }
                            
                        }else{
                            mensaje = "Falta el equipo 2 en linea " + i + "-> " + linea;
                            break;
                        }

                    }else{
                        mensaje = "Falta el equipo 1 en linea " + i + "-> " + linea;
                        break;
                    }


                }else{
                    mensaje = "Falta el participante en linea " + i + "-> " + linea;
                    break;
                }
            }else{
                mensaje = "Faltan datos en linea " + i + "-> " + linea;
                break;
            }
        }
    
    }
    return mensaje;
}


// Limpiar la consola
public static void clearConsole() throws IOException, InterruptedException {
    // Verificar el sistema operativo
    if (System.getProperty("os.name").contains("Windows")) {
        // En Windows
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } else {
        // En Linux/Mac
        
        Runtime.getRuntime().exec("clear");
        new ProcessBuilder("clear").inheritIO().start().waitFor();
    }
}

}