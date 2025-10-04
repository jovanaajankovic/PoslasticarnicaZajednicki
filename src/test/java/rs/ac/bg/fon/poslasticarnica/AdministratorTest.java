package rs.ac.bg.fon.poslasticarnica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AdministratorTest {

	private Administrator administrator;

	@BeforeEach
	void setUp() throws Exception {
		administrator = new Administrator();
	}

	@AfterEach
	void tearDown() throws Exception {
		administrator = null;
	}

	@Test
	void testAdministrator() {
		assertNotNull(administrator);
	}

	@Test
	void testAdministratorLongStringStringStringString() {
		administrator = new Administrator(1L, "Jovana", "Jankovic", "jovana123", "jovana123");

		assertNotNull(administrator);
		assertEquals(1L, administrator.getAdministratorID());
		assertEquals("Jovana", administrator.getIme());
		assertEquals("Jankovic", administrator.getPrezime());
		assertEquals("jovana123", administrator.getUsername());
		assertEquals("jovana123", administrator.getPassword());
	}

	@Test
	void testSetAdministratorID() {
		administrator.setAdministratorID(1L);
		assertEquals(1L, administrator.getAdministratorID());
	}

	@Test
	void testSetAdministratorIDNull() {
		assertThrows(java.lang.NullPointerException.class, () -> administrator.setAdministratorID(null));
	}

	@Test
	void testSetAdministratorIDNegativan() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> administrator.setAdministratorID(-1L));
	}

	@Test
	void testSetAdministratorIDNula() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> administrator.setAdministratorID(0L));
	}

	@Test
	void testSetIme() {
		administrator.setIme("Jovana");
		assertEquals("Jovana", administrator.getIme());
	}

	@Test
	void testSetImeSaRazmakom() {
		administrator.setIme("Ana Marija");
		assertEquals("Ana Marija", administrator.getIme());
	}

	@Test
	void testSetImeNull() {
		assertThrows(java.lang.NullPointerException.class, () -> administrator.setIme(null));
	}

	@Test
	void testSetImeEmpty() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> administrator.setIme(""));
	}

	@Test
	void testSetImeBlankoZnak() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> administrator.setIme("   "));
	}

	@Test
	void testSetPrezime() {
		administrator.setPrezime("Jankovic");
		assertEquals("Jankovic", administrator.getPrezime());
	}

	@Test
	void testSetPrezimeSaRazmakom() {
		administrator.setPrezime("Jankovic Jovanovic");
		assertEquals("Jankovic Jovanovic", administrator.getPrezime());
	}

	@Test
	void testSetPrezimeNull() {
		assertThrows(java.lang.NullPointerException.class, () -> administrator.setPrezime(null));
	}

	@Test
	void testSetPrezimeEmpty() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> administrator.setPrezime(""));
	}

	@Test
	void testSetPrezimeBlankoZnak() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> administrator.setPrezime("   "));
	}

	@Test
	void testSetUsername() {
		administrator.setUsername("jovana123");
		assertEquals("jovana123", administrator.getUsername());
	}

	@Test
	void testSetUsernameNull() {
		assertThrows(java.lang.NullPointerException.class, () -> administrator.setUsername(null));
	}

	@Test
	void testSetUsernameEmpty() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> administrator.setUsername(""));
	}

	@Test
	void testSetPassword() {
		administrator.setPassword("jovana123"); // vise od 8 karaktera
		assertEquals("jovana123", administrator.getPassword());
	}

	@Test
	void testSetPassword8Karaktera() {
		administrator.setPassword("jovana12"); // tacno 8 karaktera
		assertEquals("jovana12", administrator.getPassword());
	}

	@Test
	void testSetPasswordNull() {
		assertThrows(java.lang.NullPointerException.class, () -> administrator.setPassword(null));
	}

	@Test
	void testSetPasswordKratka() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> administrator.setPassword("jovana1")); // manje od
																											// 8
																											// karaktera
	}

	@Test
	void testToString() {
		administrator.setAdministratorID(1L);
		administrator.setIme("Jovana");
		administrator.setPrezime("Jankovic");
		administrator.setUsername("jovana123");
		administrator.setPassword("jovana123");

		assertTrue(administrator.toString().contains("Jovana"));
		assertTrue(administrator.toString().contains("Jankovic"));
	}

	@ParameterizedTest
	@CsvSource({ "1, 1, true", "1, 2, false" })
	void testEquals(Long id1, Long id2, boolean expected) {
		Administrator administrator1 = new Administrator();
		administrator1.setAdministratorID(id1);

		Administrator administrator2 = new Administrator();
		administrator2.setAdministratorID(id2);

		assertEquals(expected, administrator1.equals(administrator2));
	}

	@Test
	void testNazivTabele() {
		assertEquals(" administrator ", administrator.nazivTabele());
	}

	@Test
	void testAlijas() {
		assertEquals(" a ", administrator.alijas());
	}

	@Test
	void testJoin() {
		assertEquals("", administrator.join());
	}

	@Test
	void testKoloneZaInsert() {
		assertEquals(" (Ime, Prezime, Username, Password) ", administrator.koloneZaInsert());
	}

	@Test
	void testUslov() {
		assertEquals(" AdministratorID = 1", administrator.uslov());
	}

	@Test
	void testVrednostiZaInsert() {
		assertEquals("'Jovana', 'Jankovic', 'jovana123', 'jovana123'", administrator.vrednostiZaInsert());
	}

	@Test
	void testVrednostiZaUpdate() {
		assertEquals(" Ime = 'Jovana', Prezime = 'Jankovic', Username = 'jovana123', Password = 'jovana123' ",
				administrator.vrednostiZaUpdate());
	}

	@Test
	void testUslovZaSelect() {
		assertEquals("", administrator.uslovZaSelect());
	}

}
