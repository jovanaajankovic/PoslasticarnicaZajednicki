package rs.ac.bg.fon.poslasticarnica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AmbalazaTest {

	private Ambalaza ambalaza;

	@BeforeEach
	void setUp() throws Exception {
		ambalaza = new Ambalaza();
	}

	@AfterEach
	void tearDown() throws Exception {
		ambalaza = null;
	}

	@Test
	void testAmbalaza() {
		assertNotNull(ambalaza);
	}

	@Test
	void testAmbalazaLongStringDouble() {
		ambalaza = new Ambalaza(1L, "Kutija", 1.0);

		assertNotNull(ambalaza);
		assertEquals(1L, ambalaza.getAmbalazaID());
		assertEquals("Kutija", ambalaza.getTip());
		assertEquals(1.0, ambalaza.getZapremina());
	}

	@Test
	void testSetAmbalazaID() {
		ambalaza.setAmbalazaID(1L);
		assertEquals(1L, ambalaza.getAmbalazaID());
	}

	@Test
	void testSetAmbalazaIDNull() {
		assertThrows(java.lang.NullPointerException.class, () -> ambalaza.setAmbalazaID(null));
	}

	@Test
	void testSetAmbalazaIDNegativan() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> ambalaza.setAmbalazaID(-1L));
	}

	@Test
	void testSetAmbalazaIDNula() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> ambalaza.setAmbalazaID(0L));
	}

	@Test
	void testSetTip() {
		ambalaza.setTip("Kutija");
		assertEquals("Kutija", ambalaza.getTip());
	}

	@Test
	void testSetTipSaRazmakom() {
		ambalaza.setTip("Plasticna kesa");
		assertEquals("Plasticna kesa", ambalaza.getTip());
	}

	@Test
	void testSetTipNull() {
		assertThrows(java.lang.NullPointerException.class, () -> ambalaza.setTip(null));
	}

	@Test
	void testSetTipEmpty() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> ambalaza.setTip(""));
	}

	@Test
	void testSetTipBlankoZnak() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> ambalaza.setTip("   "));
	}

	@Test
	void testSetZapremina() {
		ambalaza.setZapremina(1.0);
		assertEquals(1.0, ambalaza.getZapremina());
	}

	@Test
	void testSetZapreminaNull() {
		assertThrows(java.lang.NullPointerException.class, () -> ambalaza.setZapremina(null));
	}

	@Test
	void testSetZapreminaNegativan() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> ambalaza.setZapremina(-1.0));
	}

	@Test
	void testSetZapreminaNula() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> ambalaza.setZapremina(0.0));
	}

	@Test
	void testToString() {
		ambalaza.setTip("Kutija");
		ambalaza.setZapremina(1.0);

		assertTrue(ambalaza.toString().contains("Kutija"));
		assertTrue(ambalaza.toString().contains("1.0"));
	}

}
