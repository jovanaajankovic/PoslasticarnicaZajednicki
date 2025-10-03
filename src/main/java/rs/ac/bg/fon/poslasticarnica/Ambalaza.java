package rs.ac.bg.fon.poslasticarnica;

import java.io.Serializable;

/**
 * Klasa koja predstavlja ambalazu poslastice.
 * <p>
 * Sadrzi informacije o jedinstvenom ID-u, tipu ambalaze i zapremini u litrima.
 * Implementira Serializable radi mogucnosti serijalizacije objekata.
 * 
 * @author Jovana Jankovic
 */
public class Ambalaza implements Serializable {

	/** Jedinstveni identifikator verzije klase za potrebe serijalizacije. */
	private static final long serialVersionUID = 2L;

	/** ID ambalaze kao Long */
	private Long ambalazaID;

	/** Tip ambalaze kao String npr. kutija, kesa... */
	private String tip;

	/** Zapremina ambalaze u litrima */
	private Double zapremina;

	/**
	 * Inicijalizuje objekat klase Ambalaza sa atributima koji imaju default
	 * vrednosti.
	 */
	public Ambalaza() {

	}

	/**
	 * Inicijalizuje objekat klase Amabalaza sa svim parametrima.
	 * 
	 * @param ambalazaID Jedinstveni ID ambalaze. Mora biti pozitivan broj.
	 * @param tip        Tip ambalaze. Ne sme biti null niti prazan string.
	 * @param zapremina  Zapremina ambalaze. Ne sme biti null niti manja ili jednaka
	 *                   nuli.
	 * 
	 */
	public Ambalaza(Long ambalazaID, String tip, Double zapremina) {
		setAmbalazaID(ambalazaID);
		setTip(tip);
		setZapremina(zapremina);
	}

	/**
	 * Vraca ID ambalaze.
	 * 
	 * @return ambalazaID
	 */
	public Long getAmbalazaID() {
		return ambalazaID;
	}

	/**
	 * Postavlja ID ambalaze. ID mora biti pozitivan broj i ne sme biti null.
	 * 
	 * @param ambalazaID ID ambalaze
	 * @throws java.lang.NullPointerException     ako je ID null
	 * @throws java.lang.IllegalArgumentException ako je ID manji ili jednak nuli
	 */
	public void setAmbalazaID(Long ambalazaID) {
		if (ambalazaID == null)
			throw new NullPointerException("ID ne sme biti null.");

		if (ambalazaID <= 0)
			throw new IllegalArgumentException("ID mora biti pozitivan broj.");

		this.ambalazaID = ambalazaID;
	}

	/**
	 * Vraca tip ambalaze.
	 * 
	 * @return tip
	 */
	public String getTip() {
		return tip;
	}

	/**
	 * Postavlja tip ambalaze. Tip ne sme biti null niti prazan string.
	 * 
	 * @param tip Tip ambalaze
	 * @throws java.lang.NullPointerException     ako je tip null
	 * @throws java.lang.IllegalArgumentException ako je tip prazan string
	 */
	public void setTip(String tip) {
		if (tip == null)
			throw new NullPointerException("Tip ne sme biti null.");

		if (tip.trim().isEmpty())
			throw new IllegalArgumentException("Tip ne sme biti prazan.");

		this.tip = tip;
	}

	/**
	 * Vraca zapreminu ambalaze.
	 * 
	 * @return zapremina
	 */
	public Double getZapremina() {
		return zapremina;
	}

	/**
	 * Postavlja zapreminu ambalaze. Zapremina ne sme biti null i mora biti
	 * pozitivan broj veci od nule.
	 * 
	 * @param zapremina Zapremina ambalaze
	 * @throws java.lang.NullPointerException     ako je zapremina null
	 * @throws java.lang.IllegalArgumentException ako je zapremina manja ili jednaka
	 *                                            nuli
	 */
	public void setZapremina(Double zapremina) {
		if (zapremina == null)
			throw new NullPointerException("Zapremina ne sme biti null.");

		if (zapremina <= 0)
			throw new IllegalArgumentException("Zapremina mora biti pozitivan broj.");

		this.zapremina = zapremina;
	}

	/**
	 * Vraca tekstualni prikaz tipa i zapremine ambalaze.
	 * 
	 * @return String sa podacima o tipu i zapremini ambalaze u formatu tip + " " +
	 *         zapremina + " L"
	 */
	@Override
	public String toString() {
		return tip + " " + zapremina + " L";
	}

}
