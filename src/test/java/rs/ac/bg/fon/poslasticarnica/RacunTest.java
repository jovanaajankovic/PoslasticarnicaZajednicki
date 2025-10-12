package rs.ac.bg.fon.poslasticarnica;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RacunTest {

	private Racun racun;
	private Administrator administrator;

	@Mock
	private ResultSet rs; // simulirani objekat za ResultSet iz metode vratiListu

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

	@Test

	void testVratiListu() throws SQLException {
		// postavimo vrednosti za testnog administratora
		Long adminID = 1L;
		String adminIme = "Petar";
		String adminPrezime = "Petrovic";
		String adminUser = "admin";
		String adminPass = "pass123";

		// postavimo vrednosti za testni racun
		Long racunID = 100L;
		Timestamp datumVreme = new Timestamp(new Date().getTime());
		double cena = 1500.50;

		when(rs.next()).thenReturn(true).thenReturn(false); // simulacija da je samo prvi vracen podatak dobar i da se
		// tu zaustavlja next

		// vracanje vrednosti atributa za administratora
		when(rs.getLong("AdministratorID")).thenReturn(adminID);
		when(rs.getString("Ime")).thenReturn(adminIme);
		when(rs.getString("Prezime")).thenReturn(adminPrezime);
		when(rs.getString("Username")).thenReturn(adminUser);
		when(rs.getString("Password")).thenReturn(adminPass);

		// vracanje vrednosti atributa za racun
		when(rs.getLong("racunID")).thenReturn(racunID);
		when(rs.getTimestamp("datumVreme")).thenReturn(datumVreme);
		when(rs.getDouble("cena")).thenReturn(cena);

		ArrayList<AbstractDomainObject> lista = racun.vratiListu(rs);

		assertNotNull(lista);
		assertEquals(1, lista.size());

		Racun r = (Racun) lista.get(0);
		assertEquals(racunID, r.getRacunID());
		assertEquals(datumVreme, r.getDatumVreme());
		assertEquals(cena, r.getCena());

		Administrator a = r.getAdministrator();
		assertNotNull(a);
		assertEquals(adminID, a.getAdministratorID());
		assertEquals(adminIme, a.getIme());
		assertEquals(adminPrezime, a.getPrezime());
		assertEquals(adminUser, a.getUsername());
		assertEquals(adminPass, a.getPassword());

		assertNotNull(r.getStavkeRacuna());
		assertTrue(r.getStavkeRacuna().isEmpty());

		verify(rs, times(1)).close(); // provera da je pozvan rs.close() iz metode vratiListu
	}

}
