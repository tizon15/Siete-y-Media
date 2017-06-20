package juego;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {

	private List<Carta> baraja;

	public Baraja() {
		baraja = new ArrayList<Carta>(40);

		for (Carta.PaloCarta paloCarta : Carta.PaloCarta.values()) {
			for (Carta.ValorCarta numeroCarta : Carta.ValorCarta.values()) {

				baraja.add(new Carta(numeroCarta, paloCarta));
			}
		}

		Collections.shuffle(baraja);
	}

	public Carta getCarta() {
		try {
			return baraja.remove(0);
		} catch (Exception e) {
			return null;
		}
	}

}