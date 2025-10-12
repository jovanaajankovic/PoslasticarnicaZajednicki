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
class StavkaRacunaTest {

	private StavkaRacuna stavkaRacuna;
	private Racun racun;
	private Poslastica poslastica;

	@Mock
	private ResultSet rs; // simulirani objekat za ResultSet iz metode vratiListu

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
	void testSetRb() {
		stavkaRacuna.setRb(1);
		assertEquals(1, stavkaRacuna.getRb());
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

	@Test

	void testVratiListu_UspehSviObjekti() throws SQLException {
		// postavimo vrednosti za testnog administratora
		Long adminID = 10L;
		String adminIme = "Admin";
		String adminPrezime = "Test";
		String adminUser = "admin1";
		String adminPass = "pass";

		// postavimo vrednosti za testni racun
		Long racunID = 200L;
		Timestamp datumVreme = new Timestamp(new Date().getTime());
		double racunCena = 2000.00;

		// postavimo vrednosti za testni tip poslastice
		Long tipID = 30L;
		String tipNaziv = "Kolac";

		// postavimo vrednosti za testnu poslasticu
		Long poslasticaID = 40L;
		String poslasticaNaziv = "Snikers";
		double poslasticaCena = 350.00;
		String opis = "Kikiriki i cokolada";

		// postavimo vrednosti za testnu stavku racuna
		int rb = 1;
		int kolicina = 5;
		double stavkaCena = 1750.00; // 5 * 350

		when(rs.next()).thenReturn(true).thenReturn(false); // simulacija da je samo prvi vracen podatak dobar i da se
		// tu zaustavlja next

		when(rs.getLong("AdministratorID")).thenReturn(adminID);
		when(rs.getString("Ime")).thenReturn(adminIme);
		when(rs.getString("Prezime")).thenReturn(adminPrezime);
		when(rs.getString("Username")).thenReturn(adminUser);
		when(rs.getString("Password")).thenReturn(adminPass);

		when(rs.getLong("racunID")).thenReturn(racunID);
		when(rs.getTimestamp("datumVreme")).thenReturn(datumVreme);
		when(rs.getDouble("r.cena")).thenReturn(racunCena);

		when(rs.getLong("TipPoslasticeID")).thenReturn(tipID);
		when(rs.getString("tp.Naziv")).thenReturn(tipNaziv);

		when(rs.getLong("poslasticaID")).thenReturn(poslasticaID);
		when(rs.getString("p.naziv")).thenReturn(poslasticaNaziv);
		when(rs.getDouble("cenaPoKomadu")).thenReturn(poslasticaCena);
		when(rs.getString("opis")).thenReturn(opis);

		when(rs.getInt("rb")).thenReturn(rb);
		when(rs.getInt("kolicina")).thenReturn(kolicina);
		when(rs.getDouble("sr.cena")).thenReturn(stavkaCena);

		ArrayList<AbstractDomainObject> lista = stavkaRacuna.vratiListu(rs);

		assertNotNull(lista);
		assertEquals(1, lista.size());

		StavkaRacuna sr = (StavkaRacuna) lista.get(0);
		assertEquals(rb, sr.getRb());
		assertEquals(kolicina, sr.getKolicina());
		assertEquals(stavkaCena, sr.getCena());

		Poslastica p = sr.getPoslastica();
		assertNotNull(p);
		assertEquals(poslasticaID, p.getPoslasticaID());
		assertEquals(poslasticaNaziv, p.getNaziv());
		assertEquals(poslasticaCena, p.getCenaPoKomadu());
		assertEquals(opis, p.getOpis());

		TipPoslastice tp = p.getTipPoslastice();
		assertNotNull(tp);
		assertEquals(tipID, tp.getTipPoslasticeID());
		assertEquals(tipNaziv, tp.getNaziv());

		Racun r = sr.getRacun();
		assertNotNull(r);
		assertEquals(racunID, r.getRacunID());
		assertEquals(datumVreme, r.getDatumVreme());
		assertEquals(racunCena, r.getCena());

		Administrator a = r.getAdministrator();
		assertNotNull(a);
		assertEquals(adminID, a.getAdministratorID());
		assertEquals(adminIme, a.getIme());
		assertEquals(adminPrezime, a.getPrezime());
		assertEquals(adminUser, a.getUsername());
		assertEquals(adminPass, a.getPassword());

		verify(rs, times(1)).close(); // provera da je pozvan rs.close() iz metode vratiListu
	}

}
