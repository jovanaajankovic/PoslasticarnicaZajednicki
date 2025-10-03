package rs.ac.bg.fon.poslasticarnica;

import java.io.Serializable;

/**
 * Klasa koja predstavlja ocenu poslastice.
 * <p>
 * Sadrzi informacije o jedinstvenom ID-u ocene, samoj oceni (1-5) i komentaru.
 * Implementira Serializable radi serijalizacije objekata.
 * 
 * @author Jovana Jankovic
 */
public class OcenaPoslastice implements Serializable {

	/** * Jedinstveni identifikator verzije klase za potrebe serijalizacije. */
	private static final long serialVersionUID = 3L;

	/** ID ocene kao Long */
	private Long ocenaID;

	/** Vrednost ocene kao int */
	private int ocena;

	/** Komentar uz ocenu kao string */
	private String komentar;

	/**
	 * Inicijalizuje objekat klase OcenaPoslastice sa atributima koji imaju default
	 * vrednosti.
	 */
	public OcenaPoslastice() {

	}

	/**
	 * Inicijalizuje objekat klase OcenaPoslastice sa svim parametrima.
	 * 
	 * @param ocenaID  Jedinstveni ID ocene. Mora biti pozitivan broj.
	 * @param ocena    Vrednost ocene. Mora biti u rasponu od 1 do 5.
	 * @param komentar Komentar uz ocenu. Ne sme biti null niti prazan string.
	 * 
	 */
	public OcenaPoslastice(Long ocenaID, int ocena, String komentar) {
		setOcenaID(ocenaID);
		setOcena(ocena);
		setKomentar(komentar);
	}

	/**
	 * Vraca ID ocene.
	 * 
	 * @return ocenaID
	 */
	public Long getOcenaID() {
		return ocenaID;
	}

	/**
	 * Postavlja ID ocene. ID mora biti pozitivan broj i ne sme biti null.
	 * 
	 * @param ocenaID ID ocene
	 * @throws java.lang.NullPointerException     ako je ID null
	 * @throws java.lang.IllegalArgumentException ako je ID manji ili jednak nuli
	 */
	public void setOcenaID(Long ocenaID) {
		if (ocenaID == null)
			throw new NullPointerException("ID ne sme biti null.");

		if (ocenaID <= 0)
			throw new IllegalArgumentException("ID mora biti pozitivan broj.");

		this.ocenaID = ocenaID;
	}

	/**
	 * Vraca vrednost ocene.
	 * 
	 * @return ocena
	 */
	public Integer getOcena() {
		return ocena;
	}

	/**
	 * Postavlja vrednost ocene. Ocena mora biti u rasponu od 1 do 5.
	 * 
	 * @param ocena Vrednost ocene
	 * @throws java.lang.IllegalArgumentException ako je ocena manja od 1 i veca od
	 *                                            5
	 */
	public void setOcena(int ocena) {
		if (ocena < 1 || ocena > 5)
			throw new IllegalArgumentException("Ocena mora biti izmedju 1 i 5.");

		this.ocena = ocena;
	}

	/**
	 * Vraca komentar uz ocenu.
	 * 
	 * @return komentar
	 */
	public String getKomentar() {
		return komentar;
	}

	/**
	 * Postavlja komentar uz ocenu. Komentar ne sme biti null niti prazan string.
	 * 
	 * @param komentar Komentar uz ocenu
	 * @throws java.lang.NullPointerException     ako je komentar null
	 * @throws java.lang.IllegalArgumentException ako je komentar prazan string
	 */
	public void setKomentar(String komentar) {
		if (komentar == null)
			throw new NullPointerException("Komentar ne sme biti null.");

		if (komentar.trim().isEmpty())
			throw new IllegalArgumentException("Komentar ne sme biti prazan.");

		this.komentar = komentar;
	}

	/**
	 * Vraca tekstualni prikaz ocene i komentara.
	 * 
	 * @return String sa podacima o oceni i komentaru u formatu ocena + " / 5 - " +
	 *         komentar
	 * 
	 */
	@Override
	public String toString() {
		return ocena + " / 5 - " + komentar;
	}

}
