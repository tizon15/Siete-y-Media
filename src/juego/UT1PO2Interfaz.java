package juego;

import java.awt.AWTException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Robot;

import java.awt.event.ActionEvent;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;

public final class UT1PO2Interfaz extends JFrame {

    JLabel jugador1;
    JLabel jugador2;
    JLabel etPunt1;
    JLabel etPunt2;
    JLabel imagenBaraja;
    JLabel imagenJug1;
    JLabel imagenJug2;

    JButton btDescubierta;
    JButton btBocaAbajo;
    JButton btPlantarse;

    JPanel zonaSuperior;
    JPanel zonaCentral;
    JPanel zonaInferior;
    JPanel panelIzquierda;
    JPanel panelCentral;
    JPanel panelDerecha;
    JPanel panelArriba;
    JPanel panelAbajo;

    UT1PO2Juego juego = new UT1PO2Juego(this);
    Jugador jugador = new Jugador();

    public UT1PO2Interfaz() throws HelpSetException {

        super("Practica 2 - Siete y Media");

        // Elementos
        jugador1 = new JLabel("Jugador 1");
        jugador2 = new JLabel("Jugador 2 ");
        btBocaAbajo = new JButton("Boca Abajo");
        btDescubierta = new JButton("Descubierta");
        btPlantarse = new JButton("Me Planto");
        imagenBaraja = new JLabel(new ImageIcon("./Imagenes/BarajaCartas.png"));
        imagenJug1 = new JLabel(new ImageIcon("./Imagenes/BarajaCartas.png"));
        imagenJug2 = new JLabel(new ImageIcon("./Imagenes/BarajaCartas.png"));
        etPunt1 = new JLabel();
        etPunt2 = new JLabel();

        // Zonas y paneles
        JMenu menuEdicion = new JMenu("Ayuda");
        JMenuItem tuMenuItemInf;

        JMenuItem tuMenuItemDem;
        menuEdicion.add(tuMenuItemInf = new JMenuItem("Ayuda"));
        tuMenuItemInf.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));

        menuEdicion.add(tuMenuItemDem = new JMenuItem("Demostración"));

        tuMenuItemDem.addActionListener(e -> new Simulador().start());

        JMenuBar barraMenu = new JMenuBar();
        barraMenu.add(menuEdicion);
        // setJMenuBar(barraMenu);
        try {

            tuMenuItemDem.setMnemonic(KeyEvent.VK_T);
            //1 Definicion de la ubicación del archivo de ayuda
            URL archivoHelpSet = new File("hlp/Ayuda.hs").toURI().toURL();
            //2) Crea los objetos HelpSet y HelpBroker para gestionar el fichero
            HelpSet helpset = new HelpSet(getClass().getClassLoader(),
                    archivoHelpSet);
            HelpBroker gestorAyuda = helpset.createHelpBroker();
            // 3) Se asocia la ayuda al item de menu correspondiente
            gestorAyuda.enableHelpOnButton(tuMenuItemInf, "Bienvenida", helpset);

        } catch (MalformedURLException ex) {

        }

        pantallaPrincipal(jugador);

    }

    public void pantallaPrincipal(Jugador jugador) {
        //Creamos los paneles
        zonaSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));

        zonaCentral = new JPanel(new GridLayout(2, 1, 5, 5));
        panelArriba = new JPanel();
        panelAbajo = new JPanel();

        zonaInferior = new JPanel(new GridLayout(1, 3, 2, 2));
        panelIzquierda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //A�adimos los elementos
        // Zona Superior
        zonaSuperior.add(etPunt2);
        zonaSuperior.add(jugador2);
        zonaSuperior.add(imagenJug2);

        //Zona Central
        zonaCentral.add(panelArriba);
        zonaCentral.add(panelAbajo);

        // Zona Inferior
        zonaInferior.add(panelIzquierda);
        zonaInferior.add(panelCentral);
        zonaInferior.add(panelDerecha);
        panelIzquierda.add(imagenBaraja);

        panelCentral.add(imagenJug1);
        panelCentral.add(jugador1);
        panelCentral.add(etPunt1);

        panelDerecha.add(btDescubierta, BorderLayout.NORTH);
        panelDerecha.add(btBocaAbajo, BorderLayout.CENTER);
        panelDerecha.add(btPlantarse, BorderLayout.SOUTH);

        //Activamos los botones
        btDescubierta.setEnabled(true);
        btBocaAbajo.setEnabled(true);
        btPlantarse.setEnabled(true);
        //Damos color de fondo a los paneles centrales para que se parezca a un tapete
        panelArriba.setBackground(Color.green);
        panelAbajo.setBackground(Color.green);

        setLayout(new BorderLayout());
        add(zonaSuperior, BorderLayout.NORTH);
        add(zonaCentral, BorderLayout.CENTER);
        add(zonaInferior, BorderLayout.SOUTH);
        setSize(613, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //iniciamos el juego
        juego.DarCartaInicio(jugador);

        //hacemos que los botones puedan activarse mediante teclado
        btDescubierta.setMnemonic(KeyEvent.VK_D);
        btBocaAbajo.setMnemonic(KeyEvent.VK_B);
        btPlantarse.setMnemonic(KeyEvent.VK_P);

        //Hacemos que al clicar los botones tengan alguna funcionalidad 
        btDescubierta.addActionListener((ActionEvent e) -> {
            juego.DarCartaDescubierta(jugador);
        });
        btBocaAbajo.addActionListener((ActionEvent e) -> {
            juego.DarCartaBocaAbajo(jugador);
        });
        btPlantarse.addActionListener((ActionEvent e) -> {
            // TODO Auto-generated method stub
            juego.Plantarse(jugador);
            btDescubierta.setEnabled(false);
            btBocaAbajo.setEnabled(false);
            btPlantarse.setEnabled(false);

            try {
                juego.Maquina(jugador);
            } catch (InterruptedException e1) {

            }
        });

    }

    public void reiniciar(Jugador jugador) {
        ImageIcon baraja = new ImageIcon("./Imagenes/BarajaCartas.png");
        //borramos la zona Central
        panelArriba.removeAll();
        panelAbajo.removeAll();
        //hacemos que el jugador vacie su puntuacion 
        jugador.vaciar();
        //empezamos de nuevo la partida
        juego.DarCartaInicio(jugador);
        //activamos los botones
        btBocaAbajo.setEnabled(true);
        btDescubierta.setEnabled(true);
        btPlantarse.setEnabled(true);
        //refrescamos la pantalla
        panelArriba.revalidate();
        panelAbajo.revalidate();
        //repintamos la pantalla
        panelArriba.repaint();
        panelAbajo.repaint();

    }

    public void cambiarCarta(Carta carta) {
        //hacemos un setIcon para a�adir la imagen de la carta que obtengamos
        imagenJug1.setIcon(carta.imagen());

    }

    public void cambiarCartaCentro(Carta carta) {
        //creamos un nuevo JLabel que obtenga la imagen de la carta y la a�ada en el panel del jugador 1
        JLabel aux = new JLabel();
        aux.setIcon(carta.imagen());
        panelAbajo.add(aux);

    }

    public void cambiarCartaCentroMaquina(Carta carta) {
        //creamos un nuevo JLabel que obtenga la imagen de la carta y la a�ada en el panel del jugador 2
        JLabel aux = new JLabel();
        aux.setIcon(carta.imagen());
        panelArriba.add(aux);

    }

    class Simulador extends Thread {

        @Override
        public void run() {
            try {
                JOptionPane.showMessageDialog(juego.ventana, "Bienvenido al tutorial del juego del Siete y Media"
                        + "\n El objetivo es llegar a la puntuación de 7.5"
                        + "\n Para ganar al contrincante basta con conseguir que tu puntuación sea más ajustada que las del contrincante"
                        + "\n Si tu puntuación pasa de 7.5 te saldrá un mensaje avisandote de que te has pasado.");
                Robot simulador = new Robot();
                simulador.delay(2500);

                Point posicionBoton = btDescubierta.getLocationOnScreen();

                simulador.mouseMove(posicionBoton.x, posicionBoton.y);

                simulador.mousePress(InputEvent.BUTTON1_MASK);
                simulador.delay(1000);
                simulador.mouseRelease(InputEvent.BUTTON1_MASK);
                JOptionPane.showMessageDialog(null, "Al dar al boton para obtener una carta Descubierta"
                        + ", la carta de la mano se queda fija y la de la baraja pasa al tapete ");
                simulador.delay(2500);
                Point posicionBoton2 = btBocaAbajo.getLocationOnScreen();

                simulador.mouseMove(posicionBoton2.x, posicionBoton2.y);

                simulador.mousePress(InputEvent.BUTTON1_MASK);
                simulador.delay(1000);
                simulador.mouseRelease(InputEvent.BUTTON1_MASK);
                JOptionPane.showMessageDialog(null, "Al dar al boton para obtener una carta boca abajo"
                        + ", la carta de la mano se pone en el tapete y se"
                        + " te adjudica una nueva carta para seguir jugando ");
                simulador.delay(2500);
                Point posicionBoton3 = btPlantarse.getLocationOnScreen();

                simulador.mouseMove(posicionBoton3.x, posicionBoton3.y);

                simulador.mousePress(InputEvent.BUTTON1_MASK);
                simulador.delay(1000);
                simulador.mouseRelease(InputEvent.BUTTON1_MASK);
                JOptionPane.showMessageDialog(null, "Al dar al boton para plantarse,"
                        + "te muestra un mensaje de que te has platado, con tu puntuación "
                        + " y le toca jugar a la máquina ");

            } catch (AWTException awte) {
                JOptionPane.showMessageDialog(null, "Excepción en "
                        + "la simulación: " + awte.getMessage());
            }// catch
        }// run(
    }

}
