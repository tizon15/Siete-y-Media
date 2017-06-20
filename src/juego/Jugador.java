package juego;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

	private static final double MAX = 7.5d;

    private List<Carta> cartas;

    private boolean enJuego;
    
    private Carta cartaMano;

    public Jugador() {
        cartas = new ArrayList<Carta>();
        this.enJuego = true;
    }

    public void giveCarta(Carta carta) {
        
//a�adimos la carta a la lista de cartas
        cartas.add(carta);

        double sum = getSum();
        
//comparamos la suma de las cartas con el numero maximo
        if (sum > MAX) {
        	//pasamos el buleano a false para salir de la partida
            enJuego = false;
        }
    }

    public boolean isInGame() {
    	
        return enJuego;
    }
    public void setEnJuego(boolean enJuego){
        this.enJuego=enJuego;
    }

    public double getSum() {
        double sum = 0.0d;
        for (Carta carta : cartas) {
            sum += carta.getValue();
        }
        return sum;
    }
    
    public Carta getCartaMano(){
    	return cartaMano;
    }
    public void setCartaMano(Carta cartaMano){
    	this.cartaMano=cartaMano;
    }
    public void vaciar(){
    	cartas.clear();
    }

}
