package rs.ac.bg.fon.poslasticarnica;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StavkaRacunaTest {

	private StavkaRacuna stavkaRacuna;
	private Racun racun;
	private Poslastica poslastica;

	@BeforeEach
	void setUp() throws Exception {
		Administrator administrator = new Administrator(1L, "Jovana", "Jankovic", "jovana123", "jovana123");
		ArrayList<StavkaRacuna> stavke = new ArrayList<>();
		stavke.add(stavkaRacuna);
		racun = new Racun(1L, new Date(), 1000.0, administrator, stavke);

		TipPoslastice tip = new TipPoslastice(1L, "Torta");
		ArrayList<Sastojak> sastojci = new ArrayList<>();
		sastojci.add(new Sastojak());
		poslastica = new Poslastica(1L, "Cokoladna torta", 250.0, "Ukusna torta", tip, sastojci);

		stavkaRacuna = new StavkaRacuna(racun, 1, 2, 500.0, poslastica);

	}

	@AfterEach
	void tearDown() throws Exception {
		stavkaRacuna = null;
		racun = null;
		poslastica = null;
	}

	@Test
	void testStavkaRacunaRacunIntIntDoublePoslastica() {
		assertNotNull(stavkaRacuna);
		assertEquals(racun, stavkaRacuna.getRacun());
		assertEquals(1, stavkaRacuna.getRb());
		assertEquals(2, stavkaRacuna.getKolicina());
		assertEquals(500.0, stavkaRacuna.getCena());
		assertEquals(poslastica, stavkaRacuna.getPoslastica());
	}

	@Test
	void testStavkaRacuna() {
		assertNotNull(stavkaRacuna);
	}

	@Test
	void testSetRacun() {
		stavkaRacuna.setRacun(racun);
		assertEquals(racun, stavkaRacuna.getRacun());
	}

	@Test
	void testSetRacunNull() {
		assertThrows(java.lang.NullPointerException.class, () -> stavkaRacuna.setRacun(null));
	}

	@Test
	void testSetRb() {
		stavkaRacuna.setRb(1);
		assertEquals(1, stavkaRacuna.getRb());
	}

	@Test
	void testSetRbNegativan() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> stavkaRacuna.setRb(-1));
	}

	@Test
	void testSetRbNula() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> stavkaRacuna.setRb(0));
	}

	@Test
	void testSetKolicina() {
		stavkaRacuna.setKolicina(2);
		assertEquals(2, stavkaRacuna.getKolicina());
	}

	@Test
	void testSetKolicinaNula() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> stavkaRacuna.setKolicina(0));
	}

	@Test
	void testSetKolicinaNegativna() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> stavkaRacuna.setKolicina(-1));
	}

	@Test
	void testSetCena() {
		stavkaRacuna.setCena(250.0);
		assertEquals(250.0, stavkaRacuna.getCena());
	}

	@Test
	void testSetCenaNula() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> stavkaRacuna.setCena(0.0));
	}

	@Test
	void testSetCenaNegativna() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> stavkaRacuna.setCena(-250.0));
	}

	@Test
	void testSetPoslastica() {
		stavkaRacuna.setPoslastica(poslastica);
		assertEquals(poslastica, stavkaRacuna.getPoslastica());
	}

	@Test
	void testSetPoslasticaNull() {
		assertThrows(java.lang.NullPointerException.class, () -> stavkaRacuna.setPoslastica(null));
	}

	@Test
	void testNazivTabele() {
		assertEquals(" StavkaRacuna ", stavkaRacuna.nazivTabele());
	}

	@Test
	void testAlijas() {
		assertEquals(" sr ", stavkaRacuna.alijas());
	}

	@Test
	void testJoin() {
		String expectedJoin = " JOIN POSLASTICA P ON (P.POSLASTICAID = SR.POSLASTICAID) "
				+ "JOIN TIPPOSLASTICE TP ON (TP.TIPPOSLASTICEID = P.TIPPOSLASTICEID) "
				+ "JOIN RACUN R ON (R.RACUNID = SR.RACUNID) "
				+ "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = R.ADMINISTRATORID) ";
		assertEquals(expectedJoin, stavkaRacuna.join());
	}

	@Test
	void testKoloneZaInsert() {
		assertEquals(" (racunID, rb, kolicina, cena, poslasticaID) ", stavkaRacuna.koloneZaInsert());
	}

	@Test
	void testUslov() {
		assertEquals(" racunID = 1 AND RB = 1", stavkaRacuna.uslov());
	}

	@Test
	void testVrednostiZaInsert() {
		assertEquals(" 1, 1,  2, 500.0, 1", stavkaRacuna.vrednostiZaInsert());
	}

	@Test
	void testVrednostiZaUpdate() {
		assertEquals("", stavkaRacuna.vrednostiZaUpdate());
	}

	@Test
	void testUslovZaSelect() {
		assertEquals(" WHERE R.RACUNID = 1", stavkaRacuna.uslovZaSelect());
	}

}
