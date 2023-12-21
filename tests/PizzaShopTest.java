package tests;

import files.PizzaShop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PizzaShopTest {
	private PizzaShop shop = new PizzaShop(10, 20, 10);

	/**
	 * Es muss bei jeder Bestellung mindestens eine Pizza bestellt werden, sonst
	 * soll ein Fehler ausgelöst werden.
	 */
	@Test
	void testMustOrderPizza() {
		// act & assert
		assertThrows(IllegalArgumentException.class, () -> {
			shop.canProcessOrder(12, 0);
		});
	}

	/**
	 * Bei Bestellungen vor der Öffnungszeit der Pizzeria soll ein Fehler ausgelöst
	 * werden.
	 */
	@Test
	void testNoPizzaBeforeOpening() {
		// arrange
		shop.setPendingPizzas(0);
		// act & assert
		assertThrows(IllegalArgumentException.class, () -> {
			shop.canProcessOrder(9, 34);
		});
	}

	/**
	 * Bei Bestellungen nach der Öffnungszeit der Pizzeria soll ein Fehler ausgelöst
	 * werden.
	 */
	@Test
	void testNoPizzaAfterClosing() {
		// arrange
		shop.setPendingPizzas(0);
		// act & assert
		assertThrows(IllegalArgumentException.class, () -> {
			shop.canProcessOrder(23, 34);
		});
	}

	/**
	 * Bestellungen innerhalb der Kapazität sollen bearbeitet werden können.
	 */
	@Test
	void testProcessableOrder() {
		// arrange
		shop.setPendingPizzas(0);
		// act
		boolean canProcess = shop.canProcessOrder(12, 34);
		// assert
		assertTrue(canProcess);
	}

	/**
	 * Bestellungen außerhalb der Kapazität sollen nicht bearbeitet werden können.
	 */
	@Test
	void testUnprocessableOrder() {
		// arrange
		shop.setPendingPizzas(0);
		// act
		boolean canProcess = shop.canProcessOrder(16, 50);
		// assert
		assertFalse(canProcess);
	}

	/**
	 * Die volle Kapazität der Pizzeria soll ausgeschöpft werden können
	 */
	@Test
	void testProcessableUntilFull() {
		// arrange
		shop.setPendingPizzas(0);
		// act
		boolean canProcess = shop.canProcessOrder(16, 40);
		// assert
		assertTrue(canProcess);
	}

	/**
	 * Sobald die Kapazität überschritten wird, sollen die Bestellung nicht mehr
	 * bearbeitet werden.
	 */
	@Test
	void testUnprocessableWhenFull() {
		// arrange
		shop.setPendingPizzas(0);
		// act
		boolean canProcess = shop.canProcessOrder(16, 41);
		// assert
		assertFalse(canProcess);
	}

	/**
	 * Noch offene Bestellungen sollen in die Kapazität eingerechnet werden.
	 */
	@Test
	void testProcessableOrderWithPending() {
		// arrange
		shop.setPendingPizzas(10);
		// act
		boolean canProcess = shop.canProcessOrder(12, 34);
		// assert
		assertTrue(canProcess);
	}

	/**
	 * Noch offene Bestellungen sollen in die Kapazität eingerechnet werden.
	 */
	@Test
	void testUnprocessableOrderWithPending() {
		// arrange
		shop.setPendingPizzas(10);
		// act
		boolean canProcess = shop.canProcessOrder(16, 34);
		// assert
		assertFalse(canProcess);
	}

	// Konstruktur Tests
	@Test
	void testInvalidOpenHours() {
		// act & assert
		assertThrows(IllegalArgumentException.class, () -> {
			new PizzaShop(-1, 20, 10);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new PizzaShop(24, 20, 10);
		});
	}

	@Test
	void testInvalidClosingHours() {
		// act & assert
		assertThrows(IllegalArgumentException.class, () -> {
			new PizzaShop(10, -1, 10);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new PizzaShop(10, 24, 10);
		});
	}

	@Test
	void testNoClosingBeforeOpeningHours() {
		// act & assert
		assertThrows(IllegalStateException.class, () -> {
			new PizzaShop(10, 9, 10);
		});
	}

	@Test
	void testMustProducePizzas() {
		// act & assert
		assertThrows(IllegalArgumentException.class, () -> {
			new PizzaShop(10, 20, 0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new PizzaShop(10, 20, -1);
		});
	}

	// Getter & Setter Tests
	@Test
	void testGetterReturnsSetPendingPizzas() {
		// arrange
		int testPendingValue = 42;
		shop.setPendingPizzas(testPendingValue);
		// act
		int actualPending = shop.getPendingPizzas();
		// assert
		assertEquals(testPendingValue, actualPending);
	}
}
