package juego;

import java.awt.AWTException;
import juego.Jugador;
import java.awt.Color;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RootPaneContainer;

/**
 *
 */
/**
 * @author dam
 *
 */
public class UT1PO2Juego extends Thread {

    /**
     *
     */
    private final Baraja baraja;
    double mySum;
    double playerSum;
    UT1PO2Interfaz ventana;
    Jugador jugador = new Jugador();

    public UT1PO2Juego(UT1PO2Interfaz ventana) {
        baraja = new Baraja();
        this.ventana = ventana;

    }

    public void DarCartaInicio(Jugador jugador) {
    	//aqui obtenemos una carta de la baraja
        Carta carta = baraja.getCarta();
        //damos una carta al jugador
        jugador.giveCarta(carta);
        //cambiamos la carta de la mano del jugador
        jugador.setCartaMano(carta);
        //cambiamos la imagen 
        ventana.cambiarCarta(carta);
        //cogemos la puntuacion de la carta
        playerSum = carta.getValue();
        //cambiamos la puntuacion
        ventana.etPunt1.setText("Puntos:" + playerSum);
        ventana.etPunt2.setText("Puntos:" + mySum);
    }

    public void DarCartaDescubierta(Jugador jugador) {

        Carta carta = baraja.getCarta();
        jugador.giveCarta(carta);
        playerSum = jugador.getSum();
        //a�adimos una carta al tapete
        ventana.cambiarCartaCentro(carta);
        ventana.etPunt1.setText("Puntos:" + playerSum);
        if (!jugador.isInGame()) {
            JOptionPane.showMessageDialog(ventana, "Te has pasado, sumas: " + playerSum + "\n\t Gana Jugador 2");

            int confirmado = JOptionPane.showConfirmDialog(ventana, "¿Quieres jugar otra vez?", "Selecciona una opcion",
                    JOptionPane.YES_NO_OPTION);

            if (JOptionPane.YES_NO_OPTION != confirmado) {

                ventana.btPlantarse.setEnabled(false);
                ventana.btBocaAbajo.setEnabled(false);
                ventana.btDescubierta.setEnabled(false);
                ventana.dispose();
            } else {

                ventana.reiniciar(jugador);
                jugador.setEnJuego(true);
            }
        }

    }

    public void DarCartaBocaAbajo(Jugador jugador) {

        Carta carta = baraja.getCarta();
        ventana.cambiarCartaCentro(jugador.getCartaMano());
        jugador.setCartaMano(carta);

        jugador.giveCarta(carta);
        playerSum = jugador.getSum();
        ventana.cambiarCarta(carta);
        ventana.etPunt1.setText("Puntos:" + playerSum);

        if (!jugador.isInGame()) {

            JOptionPane.showMessageDialog(ventana, "Te has pasado, sumas: " + playerSum + "\n\t Gana Jugador 2");
            int confirmado = JOptionPane.showConfirmDialog(ventana, "¿Quieres jugar otra vez?", "Selecciona una opcion",
                    JOptionPane.YES_NO_OPTION);

            if (JOptionPane.YES_NO_OPTION != confirmado) {

                ventana.btPlantarse.setEnabled(false);
                ventana.btBocaAbajo.setEnabled(false);
                ventana.btDescubierta.setEnabled(false);
                ventana.dispose();
            } else {
                ventana.reiniciar(jugador);
                jugador.setEnJuego(true);
            }

        }
    }

    public void Plantarse(Jugador jugador) {
        jugador.isInGame();
        JOptionPane.showMessageDialog(ventana,
                "Te has plantado, tu puntuacion es: " + playerSum + "\n\t Le toca al Jugador 2");

    }

    public void Maquina(Jugador jugador) throws InterruptedException {
        if (jugador.isInGame()) {

            playerSum = jugador.getSum();
            mySum = 0.0d;
            while (mySum < 7.5) {

                Carta carta = baraja.getCarta();

                mySum += carta.getValue();

                ventana.etPunt2.setText("Puntos:" + mySum);
               
                ventana.cambiarCartaCentroMaquina(carta);
                ventana.revalidate();
                if (mySum > 7.5d) {
                    JOptionPane.showMessageDialog(ventana,
                            "Jugador2 se ha pasado: " + mySum + "\n\t Felicidades has ganado Jugador1");
                    int confirmado = JOptionPane.showConfirmDialog(ventana, "¿Quieres jugar otra vez?",
                            "Selecciona una opcion", JOptionPane.YES_NO_OPTION);

                    if (JOptionPane.YES_NO_OPTION != confirmado) {

                        ventana.btPlantarse.setEnabled(false);
                        ventana.btBocaAbajo.setEnabled(false);
                        ventana.btDescubierta.setEnabled(false);
                        ventana.dispose();
                    } else {
                        ventana.reiniciar(jugador);
                        mySum = 0.0d;
                        ventana.etPunt2.setText("Puntos: " + mySum);
                        jugador.setEnJuego(true);
                    }

                    break;
                }

                if (mySum > playerSum) {
                    JOptionPane.showMessageDialog(ventana,
                            "Has perdido con: " + playerSum + "\n\t" + "Gana el Jugador2 con: " + mySum);
                    int confirmado = JOptionPane.showConfirmDialog(ventana, "¿Quieres jugar otra vez?",
                            "Selecciona una opcion", JOptionPane.YES_NO_OPTION);

                    if (JOptionPane.YES_NO_OPTION != confirmado) {

                        ventana.btPlantarse.setEnabled(false);
                        ventana.btBocaAbajo.setEnabled(false);
                        ventana.btDescubierta.setEnabled(false);
                        ventana.dispose();
                    } else {
                        ventana.reiniciar(jugador);
                        mySum = 0.0d;
                        ventana.etPunt2.setText("Puntos:" + mySum);

                        jugador.setEnJuego(true);
                    }

                    break;
                }
                if (mySum == 7.5d && playerSum == 7.5d) {
                    JOptionPane.showMessageDialog(ventana,
                            "Ha habido un empate");
                    int confirmado = JOptionPane.showConfirmDialog(ventana, "¿Quieres jugar otra vez?",
                            "Selecciona una opcion", JOptionPane.YES_NO_OPTION);

                    if (JOptionPane.YES_NO_OPTION != confirmado) {

                        ventana.btPlantarse.setEnabled(false);
                        ventana.btBocaAbajo.setEnabled(false);
                        ventana.btDescubierta.setEnabled(false);
                        ventana.dispose();
                    } else {
                        ventana.reiniciar(jugador);
                        mySum = 0.0d;
                        ventana.etPunt2.setText("Puntos:" + mySum);

                        jugador.setEnJuego(true);
                    }
                    break;
                }

            }
        }
    }
    

}
