package com.prodeapp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    Equipo equipo1 = new Equipo("Equipo 1", "Descripción equipo 1");
    Equipo equipo2 = new Equipo("Equipo 2", "Descripción equipo 2");
    Partido partidoGanado = new Partido(1,equipo1, equipo2, 2, 1, new Ronda("1"));
    Partido partidoPerdido = new Partido(2,equipo1, equipo2, 1, 2, new Ronda("1"));
    Partido partidoEmpatado = new Partido(3,equipo1, equipo2, 1, 1, new Ronda("1"));
    
    @Test
    public void testCalcularResultado() {

        assertEquals(ResultadoEnum.GANADOR, partidoGanado.getResultado());
        assertEquals(ResultadoEnum.PERDEDOR, partidoPerdido.getResultado());
        assertEquals(ResultadoEnum.EMPATE, partidoEmpatado.getResultado());
    }

    @Test
    public void testCalcularPuntos() {
      Partido partido = partidoGanado;
      Pronostico pronosticoGanadorA = new Pronostico(partido, equipo1, ResultadoEnum.GANADOR);
      Pronostico pronosticoEmpate = new Pronostico(partido, equipo1, ResultadoEnum.EMPATE);
      Pronostico pronosticoPerdedor = new Pronostico(partido, equipo1, ResultadoEnum.PERDEDOR);
      
      assertEquals(1, pronosticoGanadorA.calcularPuntos());
      assertEquals(0, pronosticoEmpate.calcularPuntos());
      assertEquals(0, pronosticoPerdedor.calcularPuntos());
    }


    @Test
    public void testAddPronostico() {
        // Creamos un participante y un pronóstico
        Participante participante = new Participante("Juan");
        Equipo equipo1 = new Equipo("Equipo 1", "Descripción del equipo 1");
        Equipo equipo2 = new Equipo("Equipo 2", "Descripción del equipo 2");
        Partido partido = new Partido(1,equipo1, equipo2, 2, 1, new Ronda("1"));
        Pronostico pronostico = new Pronostico(partido, equipo1, ResultadoEnum.GANADOR);

        // Agregamos el pronóstico al participante
        participante.addPronostico(pronostico);

        // Verificamos que el pronóstico se haya agregado correctamente
        assertEquals(1, participante.getPronosticos().size());
        assertEquals(pronostico, participante.getPronosticos().get(0));
    }

    @Test
    public void testSetPuntajeTotal() {
        // Creamos un participante
        Participante participante = new Participante("Juan");

        // Establecemos su puntaje total
        participante.setPuntajeTotal(10);

        // Verificamos que el puntaje se haya establecido correctamente
        assertEquals(10, participante.getPuntajeTotal());

        // Establecemos su puntaje total nuevamente
        participante.setPuntajeTotal(5);

        // Verificamos que el puntaje se haya actualizado correctamente
        assertEquals(15, participante.getPuntajeTotal());
    }

    @Test
    public void testSetBonus() {
        // Creamos un participante
        Participante participante = new Participante("Juan");

        // Establecemos su bonus
        participante.setBonus(2);

        // Verificamos que el bonus se haya establecido correctamente
        assertEquals(2, participante.getBonus());

        // Establecemos su bonus nuevamente
        participante.setBonus(3);

        // Verificamos que el bonus se haya actualizado correctamente
        assertEquals(5, participante.getBonus());
    }
}



