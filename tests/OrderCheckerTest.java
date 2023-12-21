package tests;

import files.OrderChecker;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class OrderCheckerTest {
	OrderChecker checker = new OrderChecker();

	@Test
	public void testAllToppingsAvailable() {
		String[] requestedToppings = {"Tomaten", "Käse", "Schinken"};
		String[] availableToppings = {"Tomaten", "Käse", "Schinken", "Pilze"};
		assertTrue(checker.arePizzaToppingsAvailable(requestedToppings, availableToppings));
	}

	@Test
	public void testSomeToppingsNotAvailable() {
		String[] requestedToppings = {"Tomaten", "Käse", "Ananas"};
		String[] availableToppings = {"Tomaten", "Käse", "Schinken", "Pilze"};
		assertFalse(checker.arePizzaToppingsAvailable(requestedToppings, availableToppings));
	}

	@Test
	public void testNoToppingsAvailable() {
		String[] requestedToppings = {"Ananas", "Oliven"};
		String[] availableToppings = {"Tomaten", "Käse", "Schinken", "Pilze"};
		assertFalse(checker.arePizzaToppingsAvailable(requestedToppings, availableToppings));
	}

	@Test
	public void testEmptyRequestedToppings() {
		String[] requestedToppings = {};
		String[] availableToppings = {"Tomaten", "Käse", "Schinken", "Pilze"};
		assertThrows(IllegalArgumentException.class, () -> checker.arePizzaToppingsAvailable(requestedToppings, availableToppings));
	}

	@Test
	public void testNullRequestedToppings() {
		String[] availableToppings = {"Tomaten", "Käse", "Schinken", "Pilze"};
		assertThrows(IllegalArgumentException.class, () -> checker.arePizzaToppingsAvailable(null, availableToppings));
	}

	@Test
	public void testEmptyAvailableToppings() {
		String[] requestedToppings = {"Tomaten", "Käse", "Schinken"};
		String[] availableToppings = {};
		assertThrows(IllegalArgumentException.class, () -> checker.arePizzaToppingsAvailable(requestedToppings, availableToppings));
	}

	@Test
	public void testNullAvailableToppings() {
		String[] requestedToppings = {"Tomaten", "Käse", "Schinken"};
		assertThrows(IllegalArgumentException.class, () -> checker.arePizzaToppingsAvailable(requestedToppings, null));
	}

}
