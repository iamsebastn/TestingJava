package files;

/**
 * Pizzeria mit Öffnungszeiten, in denen diese Bestellungen bearbeiten kann.
 */
public class PizzaShop {
	/**
	 * Stunde zu der die Pizzeria öffnet.
	 */
	private int opens;
	/**
	 * Stunde zu der die Pizzeria schließt.
	 */
	private int closes;
	/**
	 * Anzahl der Pizzen die von dieser Pizzeria pro Stunde gebacken werden können.
	 */
	private int pizzasPerHour;
	/**
	 * Anzahl der noch zu backenden Pizzen.
	 */
	private int pendingPizzas;

	/**
	 * Erzeugt eine neue Pizzeria mit den gegebenen Öffnungszeiten.
	 *
	 * @param opens  Stunde zu der die Pizzeria öffnet
	 * @param closes Stunde zu der die Pizzeria schließt
	 */
	public PizzaShop(int opens, int closes, int pizzasPerHour) {
		if (opens < 0 || opens >= 24) {
			throw new IllegalArgumentException("No valid hour: " + opens);
		}
		if (closes < 0 || closes >= 24) {
			throw new IllegalArgumentException("No valid hour: " + closes);
		}
		if (opens >= closes) {
			throw new IllegalStateException("Cannot open before closing! " + opens + " until " + closes);
		}
		if (0 >= pizzasPerHour) {
			throw new IllegalArgumentException("Must be able to produce pizzas");
		}
		this.opens = opens;
		this.closes = closes;
		this.pizzasPerHour = pizzasPerHour;
	}

	/**
	 * Prüft, ob eine Bestellung in den Öffnungszeiten bearbeitet werden kann.
	 * Berechnet dazu die Kapazität an Hand von {@link #pizzasPerHour} und bezieht
	 * noch offene Bestellungen {@link #pendingPizzas} in die Berechnung mit ein.
	 *
	 * @param currentHour     aktuelle Stunde
	 * @param requestedPizzas Anzahl der bestellten Pizzen
	 * @return Ob die Bestellung bearbeitet werden kann
	 */
	public boolean canProcessOrder(int currentHour, int requestedPizzas) {
		// Überprüfen, ob mindestens eine Pizza bestellt wurde
		if (requestedPizzas <= 0) {
			throw new IllegalArgumentException("Es muss mindestens eine Pizza bestellt werden.");
		}

		// Überprüfen, ob die Bestellung innerhalb der Öffnungszeiten liegt
		if (currentHour < opens || currentHour >= closes) {
			throw new IllegalArgumentException("Bestellungen können nur während der Öffnungszeiten bearbeitet werden.");
		}

		// Überprüfen, ob die Bestellung innerhalb der Kapazität liegt
		int availableCapacity = (closes - currentHour) * pizzasPerHour - pendingPizzas;
		if (requestedPizzas > availableCapacity) return false;


		// Wenn alle Bedingungen erfüllt sind, kann die Bestellung bearbeitet werden
		return true;
	}

	public int getPendingPizzas() {
		return pendingPizzas;
	}

	public void setPendingPizzas(int pendingPizzas) {
		this.pendingPizzas = pendingPizzas;
	}
}
