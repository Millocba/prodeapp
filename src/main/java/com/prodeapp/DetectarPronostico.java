package com.prodeapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetectarPronostico {

    public static ArrayList<Participante> cargarPredicciones (List<String> lineas) throws IOException{
        
        ArrayList<Participante> participantes = new ArrayList<Participante>();
        int i = 0;
        int contadorDeRonda = 0;
        String nombre = "";
        int ronda = 0;
        int rondaAcertada= 0;
        
        //salta primera linea
        for(String line : lineas){
            if (i==0){
                i++;
                continue;
            }
            
            int bonus = 0;
            
            String[] selecciones = {"","Argentina","Arabia Saudita", "Polonia", "México"};
            //separa valores de la linea leída
            String[] values = line.split(";");

            nombre = values[0];
            //crea los objetos equipo1 y equipo2
            Equipo equipo1 = new Equipo(values[1],selecciones[Integer.parseInt(values[1])]);
            //System.out.println(equipo1.getNombre()+equipo1.getDescripcion());
            String gana1 = values[2];
            String empata = values[3];
            String gana2 = values[4];
            Equipo equipo2 = new Equipo(values[5],selecciones[Integer.parseInt(values[5])]);
            //System.out.println(equipo2.getNombre()+equipo2.getDescripcion());
            
            
            //encuentra el resultado pronosticado
            ResultadoEnum resultado;

            if (gana1.equals("X")){
                resultado = ResultadoEnum.GANADOR;
                

            }else if (gana2.equals("X")){
                resultado = ResultadoEnum.PERDEDOR;
                
            }else{
                resultado = ResultadoEnum.EMPATE;
            }

            //llama la clase leer resultado con los datos del archivo
            List<String> lineas1 = FileReaderUtil.readLines("Recursos/resultados2.csv");
            ArrayList<Partido> partidos = LeerResultados.resultadoPartidos(lineas1);

            //encuentra el partido que se ha pronosticado, compara el id del equipo con el id del pronostico
            Partido partidoEncontrado = null;
            for (Partido partido : partidos) {
                //System.out.println(partido.getEquipo1Nombre()+equipo1.getNombre());
                //System.out.println(partido.getEquipo2Nombre()+equipo2.getNombre());
                
                if (partido.getEquipo1Nombre().equals(equipo1.getNombre()) && partido.getEquipo2Nombre().equals(equipo2.getNombre())) {
                    partidoEncontrado = partido;
                    //System.out.println("Partido de la ronda " + partido.getRondaNro());

                    //detectar el cambio de ronda o el final de la lectura
                    if(ronda!=partido.getRondaNro()){
                        
                        //si las rondas acertadas son iguales al total de rondas otorga bonus de 3 puntos
                        if(rondaAcertada==contadorDeRonda&&contadorDeRonda!=0){
                            bonus = 3;
                            //busca el ultimo participante para agregar los puntos
                            Participante ultimoParticipante = participantes.get(participantes.size() - 1);
                            ultimoParticipante.setBonus(bonus);
                            System.out.println(bonus + "+ de bonus para " + ultimoParticipante.getNombre()+
                            " por acertar todos los resultados de la ronda " + ronda);
                        }
                        ronda= partido.getRondaNro();
                        //regresa los contadores a 0
                        contadorDeRonda = 0;
                        rondaAcertada = 0;
                    }
                    contadorDeRonda++;
                    //System.out.println("condador de Ronda "+contadorDeRonda);

                    //carga el contador de aciertos
                    if (partidoEncontrado.getResultado() == resultado){
                        rondaAcertada++;
                        //System.out.println("condador de aciertos "+rondaAcertada);
                    }

                    //exclusivo para el ultimo elemento
                    if(lineas.size()-1==i){
                        //si las rondas acertadas son iguales al total de rondas otorga bonus de 3 puntos
                        if(rondaAcertada==contadorDeRonda&&contadorDeRonda!=0){
                            bonus = 3;
                            //busca el ultimo participante para agregar los puntos
                            Participante ultimoParticipante = participantes.get(participantes.size() - 1);
                            ultimoParticipante.setBonus(bonus);
                            System.out.println(bonus + "+ de bonus para " + ultimoParticipante.getNombre()+
                            " por acertar todos los resultados de la ronda " + ronda);
                        }
                    }
                    break;
                }
            }
            
            //se crea el objeto pronostico con el resumen de los objetos creados
            Pronostico pronostico = new Pronostico(partidoEncontrado,equipo1,resultado);

            // buscar si el participante ya existe en el ArrayList
            Participante participanteExistente = null;
            for (Participante p : participantes) {
                if (p.getNombre().equals(nombre)) {
                    participanteExistente = p;
                    
                    break;
                }
            }

            // si el participante no existe, crearlo y agregarlo al ArrayList
            if (participanteExistente == null) {
                Participante participante = new Participante(nombre);
                //System.out.println(participante.getNombre());
                participante.addPronostico(pronostico);
                participante.setPuntajeTotal(pronostico.calcularPuntos());
                participantes.add(participante);
            } else {
                // si el participante ya existe, agregar la nueva prediccion a su lista de predicciones
                participanteExistente.addPronostico(pronostico);
                
                participanteExistente.setPuntajeTotal(pronostico.calcularPuntos());
            }
        i++;   
        }
        //retorna los participantes con el array de las predicciones cargadas y los puntos obtenidos
        return participantes;
    
        
    }
}


