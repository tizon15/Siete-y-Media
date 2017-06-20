/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import juego.Carta;

import org.junit.Test;
import juego.Jugador;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


import org.junit.Before;

/**
 *
 * @author dam
 */
public class Prueba {

    /**
     * Método que se lanza antes de cada prueba. Se utiliza para crear uno o mas
     * objetos de la clase o clases a probar.
     */
    private Jugador jugador;
    

    @Before

    public void ejecutarPrevio() {
        jugador = new Jugador();
        ArrayList<Carta> cartas = new ArrayList();
        Carta carta1 = new Carta(Carta.ValorCarta.AS, Carta.PaloCarta.OROS);
        Carta carta2 = new Carta(Carta.ValorCarta.TRES, Carta.PaloCarta.BASTOS);
        Carta carta3 = new Carta(Carta.ValorCarta.CABALLO, Carta.PaloCarta.ESPADAS);

    }//ej

    @Test
    public void pruebaSumaSinCarta() {
        assertEquals(0.0, jugador.getSum());

    }//pruebaParCorrecto()

    @Test
    public void pruebaCartaFigura() {
        Carta carta;
        carta = new Carta(Carta.ValorCarta.CABALLO, Carta.PaloCarta.ESPADAS);
        assertEquals(0.5, carta.getValue());
    }//pruebaImparCorrecto()

    @Test
    
    public void pruebaCartaNumero() {
        Carta carta;
        carta=new Carta(Carta.ValorCarta.TRES , Carta.PaloCarta.BASTOS);
        
        assertEquals(3.0, carta.getValue());
    }
    public void pruebaCartaValorSuperiorSiete() {
        Carta carta;
        carta = new Carta(Carta.ValorCarta.AS, Carta.PaloCarta.OROS);

        assertNotEquals(8.0, carta.getValue());
    }

}
