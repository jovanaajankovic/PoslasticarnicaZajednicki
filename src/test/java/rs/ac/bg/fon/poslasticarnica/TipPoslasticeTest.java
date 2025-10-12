package rs.ac.bg.fon.poslasticarnica;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TipPoslasticeTest {

	private TipPoslastice tipPoslastice;

	@Mock
	private ResultSet rs; // simulirani objekat za ResultSet iz metode vratiListu

	@BeforeEach
	void setUp() throws Exception {
		tipPoslastice = new TipPoslastice();
	}

	@AfterEach
	void tearDown() throws Exception {
		tipPoslastice = null;
	}

	@Test
	void testTipPoslastice() {
		assertNotNull(tipPoslastice);
	}

	@Test
	void testTipPoslasticeLongString() {
		tipPoslastice = new TipPoslastice(1L, "Torta");

		assertNotNull(tipPoslastice);
		assertEquals(1L, tipPoslastice.getTipPoslasticeID());
		assertEquals("Torta", tipPoslastice.getNaziv());
	}

	@Test
	void testSetTipPoslasticeID() {
		tipPoslastice.setTipPoslasticeID(1L);
		assertEquals(1L, tipPoslastice.getTipPoslasticeID());
	}

	@Test
	void testSetTipPoslasticeIDNull() {
		assertThrows(java.lang.NullPointerException.class, () -> tipPoslastice.setTipPoslasticeID(null));
	}

	@Test
	void testSetTipPoslasticeIDNegativan() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> tipPoslastice.setTipPoslasticeID(-1L));
	}

	@Test
	void testSetTipPoslasticeIDNula() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> tipPoslastice.setTipPoslasticeID(0L));
	}

	@Test
	void testSetNaziv() {
		tipPoslastice.setNaziv("Torta");
		assertEquals("Torta", tipPoslastice.getNaziv());
	}

	@Test
	void testSetNazivNull() {
		assertThrows(java.lang.NullPointerException.class, () -> tipPoslastice.setNaziv(null));
	}

	@Test
	void testSetNazivEmpty() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> tipPoslastice.setNaziv(""));
	}

	@Test
	void testToString() {
		tipPoslastice.setNaziv("Torta");
		assertTrue(tipPoslastice.toString().contains("Torta"));
	}

	@Test
	void testNazivTabele() {
		assertEquals(" TipPoslastice ", tipPoslastice.nazivTabele());
	}

	@Test
	void testAlijas() {
		assertEquals(" tp ", tipPoslastice.alijas());
	}

	@Test
	void testJoin() {
		assertEquals("", tipPoslastice.join());
	}

	@Test
	void testKoloneZaInsert() {
		assertEquals(" (Naziv) ", tipPoslastice.koloneZaInsert());
	}

	@Test
	void testUslov() {
		tipPoslastice.setTipPoslasticeID(1L);
		assertEquals(" TipPoslasticeID = 1", tipPoslastice.uslov());
	}

	@Test
	void testVrednostiZaInsert() {
		tipPoslastice.setNaziv("Torta");
		assertEquals(" 'Torta' ", tipPoslastice.vrednostiZaInsert());
	}

	@Test
	void testVrednostiZaUpdate() {
		tipPoslastice.setNaziv("Torta");
		assertEquals(" Naziv = 'Torta' ", tipPoslastice.vrednostiZaUpdate());
	}

	@Test
	void testUslovZaSelect() {
		assertEquals("", tipPoslastice.uslovZaSelect());
	}

	@Test
	void testVratiListu() throws SQLException {
		// postavimo vrednosti za testni tip poslastice
		Long testID = 10L;
		String testNaziv = "Torta";

		when(rs.next()).thenReturn(true).thenReturn(false); // simulacija da je samo prvi vracen podatak dobar i da se
		// tu zaustavlja next

		when(rs.getLong("TipPoslasticeID")).thenReturn(testID);
		when(rs.getString("Naziv")).thenReturn(testNaziv);

		ArrayList<AbstractDomainObject> lista = tipPoslastice.vratiListu(rs);

		assertNotNull(lista);
		assertEquals(1, lista.size());

		TipPoslastice tp = (TipPoslastice) lista.get(0);
		assertEquals(testID, tp.getTipPoslasticeID());
		assertEquals(testNaziv, tp.getNaziv());

		verify(rs, times(1)).close(); // provera da je pozvan rs.close() iz metode vratiListu
	}

}
