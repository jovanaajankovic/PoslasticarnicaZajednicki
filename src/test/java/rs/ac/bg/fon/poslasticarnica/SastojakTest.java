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
class SastojakTest {

	private Sastojak sastojak;
	private Poslastica poslastica;

	@Mock
	private ResultSet rs; // simulirani objekat za ResultSet iz metode vratiListu

	@BeforeEach
	void setUp() throws Exception {
		poslastica = new Poslastica();
		sastojak = new Sastojak();
	}

	@AfterEach
	void tearDown() throws Exception {
		sastojak = null;
		poslastica = null;
	}

	@Test
	void testSastojakPoslasticaIntString() {
		sastojak = new Sastojak(poslastica, 1, "Cokolada");

		assertNotNull(sastojak);
		assertEquals(poslastica, sastojak.getPoslastica());
		assertEquals(1, sastojak.getRb());
		assertEquals("Cokolada", sastojak.getNaziv());
	}

	@Test
	void testSastojak() {
		assertNotNull(sastojak);
	}

	@Test
	void testSetPoslastica() {
		sastojak.setPoslastica(poslastica);
		assertEquals(poslastica, sastojak.getPoslastica());
	}

	@Test
	void testSetPoslasticaNull() {
		assertThrows(java.lang.NullPointerException.class, () -> sastojak.setPoslastica(null));
	}

	@Test
	void testSetRb() {
		sastojak.setRb(1);
		assertEquals(1, sastojak.getRb());
	}

	@Test
	void testSetNaziv() {
		sastojak.setNaziv("Cokolada");
		assertEquals("Cokolada", sastojak.getNaziv());
	}

	@Test
	void testSetNazivSaRazmakom() {
		sastojak.setNaziv("Smedji secer");
		assertEquals("Smedji secer", sastojak.getNaziv());
	}

	@Test
	void testSetNazivNull() {
		assertThrows(java.lang.NullPointerException.class, () -> sastojak.setNaziv(null));
	}

	@Test
	void testSetNazivEmpty() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> sastojak.setNaziv(""));
	}

	@Test
	void testSetNazivBlankoZnak() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> sastojak.setNaziv("   "));
	}

	@Test
	void testToString() {
		sastojak.setRb(1);
		sastojak.setNaziv("Cokolada");

		assertTrue(sastojak.toString().contains("1"));
		assertTrue(sastojak.toString().contains("Cokolada"));

	}

	@Test
	void testNazivTabele() {
		assertEquals(" Sastojak ", sastojak.nazivTabele());
	}

	@Test
	void testAlijas() {
		assertEquals(" s ", sastojak.alijas());
	}

	@Test
	void testJoin() {
		String expectedJoin = " JOIN POSLASTICA P ON (P.POSLASTICAID = S.POSLASTICAID) "
				+ "JOIN TIPPOSLASTICE TP ON (TP.TIPPOSLASTICEID = P.TIPPOSLASTICEID)";
		assertEquals(expectedJoin, sastojak.join());
	}

	@Test
	void testKoloneZaInsert() {
		poslastica.setPoslasticaID(1L);
		sastojak = new Sastojak(poslastica, 1, "Cokolada");
		assertEquals(" (poslasticaID, rb, naziv) ", sastojak.koloneZaInsert());
	}

	@Test
	void testUslov() {
		poslastica.setPoslasticaID(1L);
		sastojak = new Sastojak(poslastica, 1, "Cokolada");
		assertEquals(" poslasticaID = 1", sastojak.uslov());
	}

	@Test
	void testVrednostiZaInsert() {
		poslastica.setPoslasticaID(1L);
		sastojak = new Sastojak(poslastica, 1, "Cokolada");
		assertEquals(" 1, 1, 'Cokolada' ", sastojak.vrednostiZaInsert());
	}

	@Test
	void testVrednostiZaUpdate() {
		assertEquals("", sastojak.vrednostiZaUpdate());
	}

	@Test
	void testUslovZaSelect() {
		poslastica.setPoslasticaID(1L);
		sastojak = new Sastojak(poslastica, 1, "Cokolada");
		assertEquals(" WHERE P.POSLASTICAID = 1", sastojak.uslovZaSelect());
	}

	@Test
	void testVratiListu() throws SQLException {
		// postavimo vrednosti za testni tip poslastice
		Long tipID = 11L;
		String tipNaziv = "Kolac";

		// postavimo vrednosti za testnu poslasticu
		Long poslasticaID = 5L;
		String poslasticaNaziv = "Snikers";
		double cena = 350.00;
		String opis = "Kikiriki i cokolada";

		// postavimo vrednosti za testni sastojak
		int rb = 1;
		String sastojakNaziv = "Kikiriki puter";

		when(rs.next()).thenReturn(true).thenReturn(false); // simulacija da je samo prvi vracen podatak dobar i da se
		// tu zaustavlja next

		when(rs.getLong("TipPoslasticeID")).thenReturn(tipID);
		when(rs.getString("tp.Naziv")).thenReturn(tipNaziv);

		when(rs.getLong("poslasticaID")).thenReturn(poslasticaID);
		when(rs.getString("p.naziv")).thenReturn(poslasticaNaziv);
		when(rs.getDouble("cenaPoKomadu")).thenReturn(cena);
		when(rs.getString("opis")).thenReturn(opis);

		when(rs.getInt("rb")).thenReturn(rb);
		when(rs.getString("s.naziv")).thenReturn(sastojakNaziv);

		ArrayList<AbstractDomainObject> lista = sastojak.vratiListu(rs);

		assertNotNull(lista);
		assertEquals(1, lista.size());

		Sastojak s = (Sastojak) lista.get(0);
		assertEquals(rb, s.getRb());
		assertEquals(sastojakNaziv, s.getNaziv());

		Poslastica p = s.getPoslastica();
		assertNotNull(p);
		assertEquals(poslasticaID, p.getPoslasticaID());
		assertEquals(poslasticaNaziv, p.getNaziv());
		assertEquals(cena, p.getCenaPoKomadu());
		assertEquals(opis, p.getOpis());

		TipPoslastice tp = p.getTipPoslastice();
		assertNotNull(tp);
		assertEquals(tipID, tp.getTipPoslasticeID());
		assertEquals(tipNaziv, tp.getNaziv());

		verify(rs, times(1)).close(); // provera da je pozvan rs.close() iz metode vratiListu
	}
}
