package files;

import java.util.Arrays;
import java.util.List;

public class OrderChecker {

	/**
	 * Prüft, ob alle Zutaten für eine Pizza vorhanden sind. Zutaten müssen dabei
	 * exakt gleich (Groß-/Kleinschreibung) auf der Liste der verfügbaren Zutaten
	 * stehen um als vorhanden zu zählen.
	 *
	 * @param requestedToppings Liste der zu prüfenden Zutaten
	 * @param availableToppings Liste der verfügbaren Zutaten
	 * @return Verfügbarkeit der Zutaten
	 * @throws IllegalArgumentException wenn eine der Listen nicht vorhanden oder
	 *                                  leer ist
	 */
	public boolean arePizzaToppingsAvailable(String[] requestedToppings, String[] availableToppings) {
		// Überprüfen, ob die Listen vorhanden und nicht leer sind
		if (requestedToppings == null || requestedToppings.length == 0 || availableToppings == null || availableToppings.length == 0) {
			throw new IllegalArgumentException("Die Listen dürfen nicht null oder leer sein.");
		}

		// Erstellen einer Liste der verfügbaren Zutaten für eine einfachere Suche
		List<String> availableToppingsList = Arrays.asList(availableToppings);

		// Überprüfen, ob jede angeforderte Zutat verfügbar ist
		for (String topping : requestedToppings) {
			if (!availableToppingsList.contains(topping)) {
				return false;
			}
		}

		// Wenn alle Zutaten verfügbar sind, true zurückgeben
		return true;
	}

}
