package rs.ac.bg.fon.poslasticarnica;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PoslasticaTest {

	private Poslastica poslastica;
	private TipPoslastice tipPoslastice;
	private ArrayList<Sastojak> sastojci;
	private Ambalaza ambalaza;
	private ArrayList<OcenaPoslastice> ocene;

	@BeforeEach
	void setUp() throws Exception {
		tipPoslastice = new TipPoslastice(1L, "Torta");

		sastojci = new ArrayList<>();
		sastojci.add(new Sastojak());

		ambalaza = new Ambalaza();

		ocene = new ArrayList<>();
		ocene.add(new OcenaPoslastice(1L, 5, "Sjajno"));

		poslastica = new Poslastica(1L, "Cokoladna torta", 500.0, "Ukusna torta", tipPoslastice, sastojci, ambalaza,
				ocene);
	}

	@AfterEach
	void tearDown() throws Exception {
		poslastica = null;
		tipPoslastice = null;
		sastojci = null;
		ambalaza = null;
		ocene = null;
	}

	@Test
	void testPoslasticaLongStringDoubleStringTipPoslasticeArrayListOfSastojak() {
		assertNotNull(poslastica);
		assertEquals(1L, poslastica.getPoslasticaID());
		assertEquals("Cokoladna torta", poslastica.getNaziv());
		assertEquals(500.0, poslastica.getCenaPoKomadu());
		assertEquals("Ukusna torta", poslastica.getOpis());
		assertEquals(tipPoslastice, poslastica.getTipPoslastice());
		assertEquals(sastojci, poslastica.getSastojci());
	}

	@Test
	void testPoslasticaLongStringDoubleStringTipPoslasticeArrayListOfSastojakAmbalazaArrayListOfOcenaPoslastice() {
		assertNotNull(poslastica);
		assertEquals(1L, poslastica.getPoslasticaID());
		assertEquals("Cokoladna torta", poslastica.getNaziv());
		assertEquals(500.0, poslastica.getCenaPoKomadu());
		assertEquals("Ukusna torta", poslastica.getOpis());
		assertEquals(tipPoslastice, poslastica.getTipPoslastice());
		assertEquals(sastojci, poslastica.getSastojci());
		assertEquals(ambalaza, poslastica.getAmbalaza());
		assertEquals(ocene, poslastica.getOcene());
	}

	@Test
	void testPoslastica() {
		assertNotNull(poslastica);
	}

	@Test
	void testSetPoslasticaID() {
		poslastica.setPoslasticaID(1L);
		assertEquals(1L, poslastica.getPoslasticaID());
	}

	@Test
	void testSetPoslasticaIDNull() {
		assertThrows(java.lang.NullPointerException.class, () -> poslastica.setPoslasticaID(null));
	}

	@Test
	void testSetPoslasticaIDNegativan() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> poslastica.setPoslasticaID(-1L));
	}

	@Test
	void testSetPoslasticaIDNula() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> poslastica.setPoslasticaID(0L));
	}

	@Test
	void testSetNaziv() {
		poslastica.setNaziv("Reforma");
		assertEquals("Reforma", poslastica.getNaziv());
	}

	@Test
	void testSetNazivSaRazmakom() {
		poslastica.setNaziv("Cokoladna torta");
		assertEquals("Cokoladna torta", poslastica.getNaziv());
	}

	@Test
	void testSetNazivNull() {
		assertThrows(java.lang.NullPointerException.class, () -> poslastica.setNaziv(null));
	}

	@Test
	void testSetNazivEmpty() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> poslastica.setNaziv(""));
	}

	@Test
	void testSetNazivBlankoZnak() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> poslastica.setNaziv("   "));
	}

	@Test
	void testSetCenaPoKomadu() {
		poslastica.setCenaPoKomadu(500.0);
		assertEquals(500.0, poslastica.getCenaPoKomadu());
	}

	@Test
	void testSetCenaPoKomaduNegativna() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> poslastica.setCenaPoKomadu(-100.0));
	}

	@Test
	void testSetCenaPoKomaduNula() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> poslastica.setCenaPoKomadu(0.0));
	}

	@Test
	void testSetOpis() {
		poslastica.setOpis("Kremasto");
		assertEquals("Kremasto", poslastica.getOpis());
	}

	@Test
	void testSetOpisSaRazmakom() {
		poslastica.setOpis("Ukusna torta");
		assertEquals("Ukusna torta", poslastica.getOpis());
	}

	@Test
	void testSetOpisNull() {
		assertThrows(java.lang.NullPointerException.class, () -> poslastica.setOpis(null));
	}

	@Test
	void testSetOpisEmpty() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> poslastica.setOpis(""));
	}

	@Test
	void testSetOpisBlankoZnak() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> poslastica.setOpis("   "));
	}

	@Test
	void testSetTipPoslastice() {
		poslastica.setTipPoslastice(tipPoslastice);
		assertEquals(tipPoslastice, poslastica.getTipPoslastice());
	}

	@Test
	void testSetTipPoslasticeNull() {
		assertThrows(java.lang.NullPointerException.class, () -> poslastica.setTipPoslastice(null));
	}

	@Test
	void testSetSastojci() {
		poslastica.setSastojci(sastojci);
		assertEquals(sastojci, poslastica.getSastojci());
	}

	@Test
	void testSetSastojciNull() {
		assertThrows(java.lang.NullPointerException.class, () -> poslastica.setSastojci(null));
	}

	@Test
	void testSetSastojciEmpty() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> poslastica.setSastojci(new ArrayList<>()));
	}

	@Test
	void testSetAmbalaza() {
		poslastica.setAmbalaza(ambalaza);
		assertEquals(ambalaza, poslastica.getAmbalaza());
	}

	@Test
	void testSetAmbalazaNull() {
		assertThrows(java.lang.NullPointerException.class, () -> poslastica.setAmbalaza(null));
	}
	
	@Test
	void testSetOcene() {
		poslastica.setOcene(ocene);
		assertEquals(ocene, poslastica.getOcene());
	}

	@Test
	void testSetOceneNull() {
		assertThrows(java.lang.NullPointerException.class, () -> poslastica.setOcene(null));
	}
	@Test
	void testToString() {
		poslastica.setNaziv("Cokoladna torta");
		poslastica.setCenaPoKomadu(500.0);
		assertTrue(poslastica.toString().contains("Cokoladna torta"));
		assertTrue(poslastica.toString().contains("500.0"));
	}
	
	@Test
    void testNazivTabele() {
        assertEquals(" Poslastica ", poslastica.nazivTabele());
    }

    @Test
    void testAlijas() {
        assertEquals(" p ", poslastica.alijas());
    }

    @Test
    void testJoin() {
        assertEquals(" JOIN TIPPOSLASTICE TP ON (TP.TIPPOSLASTICEID = P.TIPPOSLASTICEID) ", poslastica.join());
    }

    @Test
    void testKoloneZaInsert() {
        assertEquals(" (naziv, cenaPoKomadu, opis, TipPoslasticeID) ", poslastica.koloneZaInsert());
    }

    @Test
    void testVrednostiZaInsert() {
        String expected = "'" + poslastica.getNaziv() + "', " + poslastica.getCenaPoKomadu() +
                          ", '" + poslastica.getOpis() + "', " + poslastica.getTipPoslastice().getTipPoslasticeID() + " ";
        assertEquals(expected, poslastica.vrednostiZaInsert());
    }

    @Test
    void testVrednostiZaUpdate() {
        String expected = " naziv = '" + poslastica.getNaziv() + "', cenaPoKomadu = " +
                          poslastica.getCenaPoKomadu() + ", opis = '" + poslastica.getOpis() + "' ";
        assertEquals(expected, poslastica.vrednostiZaUpdate());
    }

    @Test
    void testUslov() {
        assertEquals(" poslasticaID = " + poslastica.getPoslasticaID(), poslastica.uslov());
    }

    @Test
    void testUslovZaSelect() {
        assertEquals(" ORDER BY P.POSLASTICAID ASC", poslastica.uslovZaSelect());
    }
}


