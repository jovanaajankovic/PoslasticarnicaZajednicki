package rs.ac.bg.fon.poslasticarnica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OcenaPoslasticeTest {

	private OcenaPoslastice ocenaPoslastice;

	@BeforeEach
	void setUp() throws Exception {
		ocenaPoslastice = new OcenaPoslastice();
	}

	@AfterEach
	void tearDown() throws Exception {
		ocenaPoslastice = null;
	}

	@Test
	void testOcenaPoslastice() {
		assertNotNull(ocenaPoslastice);
	}

	@Test
	void testOcenaPoslasticeLongIntString() {
		ocenaPoslastice = new OcenaPoslastice(1L, 5, "Preukusno");

		assertNotNull(ocenaPoslastice);
		assertEquals(1L, ocenaPoslastice.getOcenaID());
		assertEquals(5, ocenaPoslastice.getOcena());
		assertEquals("Preukusno", ocenaPoslastice.getKomentar());
	}

	@Test
	void testSetOcenaID() {
		ocenaPoslastice.setOcenaID(1L);
		assertEquals(1L, ocenaPoslastice.getOcenaID());
	}

	@Test
	void testSetOcenaIDNull() {
		assertThrows(java.lang.NullPointerException.class, () -> ocenaPoslastice.setOcenaID(null));
	}

	@Test
	void testSetOcenaIDNegativan() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> ocenaPoslastice.setOcenaID(-1L));
	}

	@Test
	void testSetOcenaIDNula() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> ocenaPoslastice.setOcenaID(0L));
	}

	@Test
	void testSetOcena5() {
		ocenaPoslastice.setOcena(5);
		assertEquals(5, ocenaPoslastice.getOcena());
	}

	@Test
	void testSetOcena1() {
		ocenaPoslastice.setOcena(1);
		assertEquals(1, ocenaPoslastice.getOcena());
	}

	@Test
	void testSetOcenaVecaOd5() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> ocenaPoslastice.setOcena(6));
	}

	@Test
	void testSetOcenaNegativna() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> ocenaPoslastice.setOcena(-1));
	}

	@Test
	void testSetOcenaNula() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> ocenaPoslastice.setOcena(0));
	}

	@Test
	void testSetKomentar() {
		ocenaPoslastice.setKomentar("Preukusno");
		assertEquals("Preukusno", ocenaPoslastice.getKomentar());
	}

	@Test
	void testSetKomentarSaRazmakom() {
		ocenaPoslastice.setKomentar("Preukusna torta");
		assertEquals("Preukusna torta", ocenaPoslastice.getKomentar());
	}

	@Test
	void testSetKomentarNull() {
		assertThrows(java.lang.NullPointerException.class, () -> ocenaPoslastice.setKomentar(null));
	}

	@Test
	void testSetKomentarEmpty() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> ocenaPoslastice.setKomentar(""));
	}

	@Test
	void testSetKomentarBlankoZnak() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> ocenaPoslastice.setKomentar("   "));
	}

	@Test
	void testToString() {
		ocenaPoslastice.setOcena(5);
		ocenaPoslastice.setKomentar("Preukusno");

		assertTrue(ocenaPoslastice.toString().contains("5"));
		assertTrue(ocenaPoslastice.toString().contains("Preukusno"));
	}

}
