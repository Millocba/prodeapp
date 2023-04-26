package com.prodeapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetectarPronostico {

    public static ArrayList<Participante> cargarPredicciones (List<String> lineas, int puntosxacierto, int bonusxacierto, String nombreBd, String ipBd, String port) throws IOException{
        
        ArrayList<Participante> participantes = new ArrayList<Participante>();
        int i = 0;
        int contadorDeRonda = 0;
        String nombre = "";
        int ronda = 0;
        int rondaAcertada= 0;

        //consulta los pronosticos
        List<String> lineas1 = Conexion.conectar("SELECT * FROM view_resultado",nombreBd,ipBd,port);
        //validacion de los datos del archivo
        String mensaje= comprobarResultados(lineas1);
        if (!mensaje.equals("Ok")){
            System.out.println("Error: "+ mensaje);
            System.exit(1);
        }

        //Carga los partidos leidos en resultado
        ArrayList<Partido> partidos = LeerResultados.resultadoPartidos(lineas1);

        for(String line : lineas){
            //salta primera linea
            if (i==0){
                i++;
                continue;
            }
            
            
            //separa valores de la linea leída
            String[] values = line.split(";");
            nombre = values[0];
            String gana1 = values[2];
            
            //encuentra el resultado pronosticado
            ResultadoEnum resultado;
            resultado = gana1.equals("GANADOR") ? ResultadoEnum.GANADOR :
            gana1.equals("PERDEDOR") ? ResultadoEnum.PERDEDOR :
            ResultadoEnum.EMPATE;

            //encuentra el partido que se ha pronosticado, compara el id del equipo con el id del pronostico
            Partido partidoEncontrado = null;
            for (Partido partido : partidos) {
                //System.out.println(partido.getEquipo1Nombre()+equipo1.getNombre());
                //System.out.println(partido.getEquipo2Nombre()+equipo2.getNombre());
                
                
                if (partido.getIdpartido()== Integer.parseInt(values[4])) {
                    partidoEncontrado = partido;
                    //System.out.println("Partido de la ronda " + partido.getRondaNro());

                    //detectar el cambio de ronda o el final de la lectura
                    if(ronda!=partido.getRondaNro()){
                        
                        //si las rondas acertadas son iguales al total de rondas otorga bonusxacierto de 3 puntos
                        if(rondaAcertada==contadorDeRonda&&contadorDeRonda!=0){
                            
                            //busca el ultimo participante para agregar los puntos
                            Participante ultimoParticipante = participantes.get(participantes.size() - 1);
                            ultimoParticipante.setBonus(bonusxacierto);
                            System.out.println("\u001B[33m"+bonusxacierto + "+ de bonus para " + ultimoParticipante.getNombre()+
                            " por acertar todos los resultados de la ronda " + ronda + " \u001B[31m\u2764");
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
                        //si las rondas acertadas son iguales al total de rondas otorga bonusxacierto de 3 puntos
                        if(rondaAcertada==contadorDeRonda&&contadorDeRonda!=0){
                            
                            //busca el ultimo participante para agregar los puntos
                            Participante ultimoParticipante = participantes.get(participantes.size() - 1);
                            ultimoParticipante.setBonus(bonusxacierto);
                            System.out.println(bonusxacierto + "+ de bonus para " + ultimoParticipante.getNombre()+
                            " por acertar todos los resultados de la ronda " + ronda);
                        }
                    }
                    break;
                }
            }
            
            //se crea el objeto pronostico con el resumen de los objetos creados
            Pronostico pronostico = new Pronostico(partidoEncontrado,partidoEncontrado.getEquipo1(),resultado);

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
                participante.setPuntajeTotal(pronostico.calcularPuntos()*puntosxacierto);
                participantes.add(participante);
            } else {
                // si el participante ya existe, agregar la nueva prediccion a su lista de predicciones
                participanteExistente.addPronostico(pronostico);
                
                participanteExistente.setPuntajeTotal(pronostico.calcularPuntos()*puntosxacierto);
            }
        i++;   
        }
        //retorna los participantes con el array de las predicciones cargadas y los puntos obtenidos
        return participantes;
    
        
    }

    private static String comprobarResultados(List<String> lineas1) {
        String mensaje = "";
        int i =-1;
    
        for(String linea : lineas1){
            if(i==-1){
                i++;
                continue;
            }else{
                i++;
                String[] values = linea.split(";");
                if (values.length==10){

                    String regex = "(^[0-9]\\d*$)";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(values[0]);

                    if (!values[0].isEmpty()|| matcher.matches()){
                        
                        matcher = pattern.matcher(values[1]);
                        
                        if(values[1].isEmpty() || matcher.matches()){
                            String regex1 = "^[a-zA-Z]+([. áéíóúÁÉÍÓÚñÑ]?[a-zA-Z]+)*$";
                            Pattern pattern1 = Pattern.compile(regex1);
                            matcher = pattern1.matcher(values[2]);
    
                            if(values[2].isEmpty() || matcher.matches()){
                                
                                matcher = pattern.matcher(values[4]);
                                
                                if(values[4].isEmpty() || matcher.matches()){
                                    
                                    matcher = pattern.matcher(values[5]);
                                    
                                    if(values[5].isEmpty() || matcher.matches()){
                                        
                                        matcher = pattern.matcher(values[6]);
                                    
                                        if(values[6].isEmpty() || matcher.matches()){
                                            matcher = pattern1.matcher(values[7]);
                                            
                                            if(values[7].isEmpty() || matcher.matches()){
                                                mensaje = "Ok";
                                                continue;
                                            }else{
                                                mensaje = "Falta el nombre del equipo 2 en linea " + i + "-> " + linea ;
                                                break;
                                            }
                                        
                                        }else{
                                            mensaje = "Falta el equipo 2 en linea " + i + "-> " + linea ;
                                            break;
                                        }
                                        
                                    }else{
                                        mensaje = "Falta goles del equipo 2 en linea " + i + "-> " + linea ;
                                        break;
                                    }    
                                    
                                }else{
                                    mensaje = "Falta goles del equipo 1 en linea " + i + "-> " + linea ;
                                    break;
                                }
                                
                            }else{
                                mensaje = "Falta el nombre de equipo 1 en linea " + i + "-> " + linea;
                                break;
                            }
    
                        }else{
                            mensaje = "Falta el equipo 1 en linea " + i + "-> " + linea;
                            break;
                        }
    
    
                    }else{
                        mensaje = "Falta la ronda en linea " + i + "-> " + linea;
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
}


