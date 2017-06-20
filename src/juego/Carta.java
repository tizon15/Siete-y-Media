package juego;
import javax.swing.ImageIcon;

public class Carta {

	private ValorCarta numero;

	private PaloCarta palo;

	private ImageIcon imagen;

	public Carta(ValorCarta numero, PaloCarta palo) {
		this.numero = numero;
		this.palo = palo;

		String nombre = "" + numero + "_" + palo;

		// Creo ImagenIcon para coger las imagenes.
		imagen = new ImageIcon("./Imagenes/" + nombre + ".png");
		// Descripcionn de la imagen.
		imagen.setDescription(nombre);
	}

	public double getValue() {
		return numero.getValue();
	}

    

	//Hacemos que el nombre de la carta obtenga el valor que nosotros queremos
	public enum ValorCarta {
		AS(1), DOS(2), TRES(3), CUATRO(4), CINCO(5), SEIS(6), SIETE(7), SOTA(0.5), CABALLO(0.5), REY(0.5);

		private double value;

		ValorCarta(double value) {
			this.value = value;
		}

		public double getValue() {
			return value;
		}
	}

	//pasamos la imagen.
	public ImageIcon imagen() {
		return imagen;
	}

	// a�adimos el palo de las cartas
	public enum PaloCarta {
		BASTOS, OROS, COPAS, ESPADAS
	}

    public void setValorCarta(ValorCarta numero) {
        this.numero = numero;
    }

    public void setPaloCarta(PaloCarta palo) {
        this.palo = palo;
    }
        
}
