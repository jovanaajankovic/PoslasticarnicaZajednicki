package rs.ac.bg.fon.poslasticarnica;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RacunTest {

	private Racun racun;
	private Administrator administrator;

	@BeforeEach
	void setUp() throws Exception {
		racun = new Racun();
		administrator = new Administrator(1L, "Jovana", "Jankovic", "jovana123", "jovana123");
	}

	@AfterEach
	void tearDown() throws Exception {
		racun = null;
		administrator = null;
	}

	@Test
	void testRacunLongDateDoubleAdministratorArrayListOfStavkaRacuna() {
		Date datum = new Date();
		ArrayList<StavkaRacuna> stavkeRacuna = new ArrayList<>();
		stavkeRacuna.add(new StavkaRacuna());
		racun = new Racun(1L, datum, 1000.0, administrator, stavkeRacuna);

		assertNotNull(racun);
		assertEquals(1L, racun.getRacunID());
		assertEquals(datum, racun.getDatumVreme());
		assertEquals(1000.0, racun.getCena());
		assertEquals(administrator, racun.getAdministrator());
		assertEquals(stavkeRacuna, racun.getStavkeRacuna());
	}

	@Test
	void testRacun() {
		assertNotNull(racun);
	}

	@Test
	void testSetRacunID() {
		racun.setRacunID(1L);
		assertEquals(1L, racun.getRacunID());
	}

	@Test
	void testSetRacunIDNull() {
		assertThrows(java.lang.NullPointerException.class, () -> racun.setRacunID(null));
	}

	@Test
	void testSetRacunIDNegativan() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> racun.setRacunID(-1L));
	}

	@Test
	void testSetRacunIDNula() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> racun.setRacunID(0L));
	}

	@Test
	void testSetDatumVreme() {
		Date datum = new Date();
		racun.setDatumVreme(datum);
		assertEquals(datum, racun.getDatumVreme());
	}

	@Test
	void testSetDatumVremeNull() {
		assertThrows(java.lang.NullPointerException.class, () -> racun.setDatumVreme(null));
	}

	@Test
	void testSetDatumVremeFuture() {
		Date future = new Date(System.currentTimeMillis() + 100000); // trenutno vreme + 100s, dobije se datum u
																		// buducnosti
		assertThrows(java.lang.IllegalArgumentException.class, () -> racun.setDatumVreme(future));
	}

	@Test
	void testSetCena() {
		racun.setCena(1000.0);
		assertEquals(1000.0, racun.getCena());
	}

	@Test
	void testSetCenaNegativna() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> racun.setCena(-100.0));
	}

	@Test
	void testSetCenaNula() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> racun.setCena(0.0));
	}

	@Test
	void testSetAdministrator() {
		racun.setAdministrator(administrator);
		assertEquals(administrator, racun.getAdministrator());
	}

	@Test
	void testSetAdministratorNull() {
		assertThrows(java.lang.NullPointerException.class, () -> racun.setAdministrator(null));
	}

	@Test
	void testSetStavkeRacuna() {
		ArrayList<StavkaRacuna> stavkeRacuna = new ArrayList<>();
		stavkeRacuna.add(new StavkaRacuna());
		racun.setStavkeRacuna(stavkeRacuna);
		assertEquals(stavkeRacuna, racun.getStavkeRacuna());
	}

	@Test
	void testSetStavkeRacunaNull() {
		assertThrows(java.lang.NullPointerException.class, () -> racun.setStavkeRacuna(null));
	}

	@Test
	void testSetStavkeRacunaEmpty() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> racun.setStavkeRacuna(new ArrayList<>()));
	}

	@Test
	void testNazivTabele() {
		assertEquals(" Racun ", racun.nazivTabele());
	}

	@Test
	void testAlijas() {
		assertEquals(" r ", racun.alijas());
	}

	@Test
	void testJoin() {
		;
		assertEquals(" JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = R.ADMINISTRATORID) ", racun.join());
	}

	@Test
	void testKoloneZaInsert() {
		assertEquals(" (datumVreme, cena, AdministratorID) ", racun.koloneZaInsert());
	}

	@Test
	void testUslov() {
		assertEquals("", racun.uslov());
	}

	@Test
	void testVrednostiZaInsert() {
		administrator.getAdministratorID();
		racun.setDatumVreme(new Timestamp(System.currentTimeMillis()));
		racun.setCena(500.0);
		racun.setAdministrator(administrator);

		String expected = "'" + new Timestamp(racun.getDatumVreme().getTime()) + "', 500.0,  1 ";
		assertEquals(expected, racun.vrednostiZaInsert());
	}

	@Test
	void testVrednostiZaUpdate() {
		assertEquals("", racun.vrednostiZaUpdate());
	}

	@Test
	void testUslovZaSelectAdmin() {
		administrator.getAdministratorID();
		racun.setAdministrator(administrator);
		assertEquals(" WHERE A.ADMINISTRATORID = 1", racun.uslovZaSelect());
	}

	@Test
	void testUslovZaSelect() {
		assertEquals("", racun.uslovZaSelect());
	}

}
