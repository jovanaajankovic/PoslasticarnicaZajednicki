package rs.ac.bg.fon.poslasticarnica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Predstavlja poslasticu koja se prodaje u poslasticarnici.
 * <p>
 * Svaka poslastica ima jedinstveni ID, naziv, cenu po komadu, opis, tip
 * poslastice i listu sastojaka.
 * </p>
 * 
 * Nasledjuje klasu AbstractDomainObject i implementira metode za perzistenciju
 * u bazu podataka.
 * 
 * @author Jovana Jankovic
 */
public class Poslastica extends AbstractDomainObject {

	/** Jedinstveni identifikator verzije klase za potrebe serijalizacije. */
	private static final long serialVersionUID = 4L;

	/** ID poslastice kao Long */
	private Long poslasticaID;

	/** Naziv poslastice kao String */
	private String naziv;

	/** Cena po komadu poslastice kao double */
	private double cenaPoKomadu;

	/** Opis poslastice kao String */
	private String opis;

	/** Tip poslastice npr. kolac, torta... */
	private TipPoslastice tipPoslastice;

	/** Lista sastojaka od kojih se pravi poslastica */
	private ArrayList<Sastojak> sastojci;

	/** Ambalaza u kojoj se prodaje poslastica */
	private Ambalaza ambalaza;

	/** Lista ocena poslastice */
	private ArrayList<OcenaPoslastice> ocene;

	/**
	 * Inicijalizuje objekat klase Poslastica sa odredjenim parametrima.
	 * 
	 * @param poslasticaID  Jedinstveni ID poslastice. Mora biti pozitivan broj i ne
	 *                      sme biti null.
	 * @param naziv         Naziv poslastice. Ne sme biti null ili prazan string.
	 * @param cenaPoKomadu  Cena jedne poslastice. Mora biti veca od nule.
	 * @param opis          Opis poslastice. Ne sme biti null niti prazan string.
	 * @param tipPoslastice Tip poslastice. Ne sme biti null.
	 * @param sastojci      Lista sastojaka. Ne sme biti null niti prazna.
	 */
	public Poslastica(Long poslasticaID, String naziv, double cenaPoKomadu, String opis, TipPoslastice tipPoslastice,
			ArrayList<Sastojak> sastojci) {
		this.poslasticaID=poslasticaID;
		this.naziv=naziv;
		this.cenaPoKomadu=cenaPoKomadu;
		this.opis=opis;
		this.tipPoslastice=tipPoslastice;
		this.sastojci=sastojci;

	}

	/**
	 * Vraca tekstualni prikaz poslastice.
	 * 
	 * @return String sa podacima o nazivu i ceni poslastice u formatu naziv + "
	 *         (Cena po komadu: " + cenaPoKomadu + "din)"
	 */
	@Override
	public String toString() {
		return naziv + " (Cena po komadu: " + cenaPoKomadu + "din)";
	}

	/**
	 * Inicijalizuje objekat klase Poslastica sa svim parametrima.
	 * 
	 * @param poslasticaID  Jedinstveni ID poslastice. Mora biti pozitivan broj i ne
	 *                      sme biti null.
	 * @param naziv         Naziv poslastice. Ne sme biti null ili prazan string.
	 * @param cenaPoKomadu  Cena jedne poslastice. Mora biti veca od nule.
	 * @param opis          Opis poslastice. Ne sme biti null niti prazan string.
	 * @param tipPoslastice Tip poslastice. Ne sme biti null.
	 * @param sastojci      Lista sastojaka. Ne sme biti null niti prazna.
	 * @param ambalaza      Ambalaza za poslasticu. Ne sme biti null niti prazan
	 *                      string.
	 * @param ocene         Ocene koje je poslastica dobila. Ocene ne smeju biti
	 *                      manje od 1 i vece od 5.
	 */
	public Poslastica(Long poslasticaID, String naziv, double cenaPoKomadu, String opis, TipPoslastice tipPoslastice,
			ArrayList<Sastojak> sastojci, Ambalaza ambalaza, ArrayList<OcenaPoslastice> ocene) {
		setPoslasticaID(poslasticaID);
		setNaziv(naziv);
		setCenaPoKomadu(cenaPoKomadu);
		setOpis(opis);
		setTipPoslastice(tipPoslastice);
		setSastojci(sastojci);
		setAmbalaza(ambalaza);
		setOcene(ocene);
	}

	/**
	 * Inicijalizuje objekat klase Poslastica sa atributima koji imaju default
	 * vrednosti.
	 */
	public Poslastica() {

	}

	@Override
	public String nazivTabele() {
		return " Poslastica ";
	}

	@Override
	public String alijas() {
		return " p ";
	}

	@Override
	public String join() {
		return " JOIN TIPPOSLASTICE TP ON (TP.TIPPOSLASTICEID = P.TIPPOSLASTICEID) ";
	}

	@Override
	public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
		ArrayList<AbstractDomainObject> lista = new ArrayList<>();

		while (rs.next()) {
			TipPoslastice tp = new TipPoslastice(rs.getLong("TipPoslasticeID"), rs.getString("Naziv"));

			Poslastica p = new Poslastica(rs.getLong("poslasticaID"), rs.getString("p.naziv"),
					rs.getDouble("cenaPoKomadu"), rs.getString("opis"), tp, null);

			lista.add(p);
		}

		rs.close();
		return lista;
	}

	@Override
	public String koloneZaInsert() {
		return " (naziv, cenaPoKomadu, opis, TipPoslasticeID) ";
	}

	@Override
	public String uslov() {
		return " poslasticaID = " + poslasticaID;
	}

	@Override
	public String vrednostiZaInsert() {
		return "'" + naziv + "', " + cenaPoKomadu + ", " + "'" + opis + "', " + tipPoslastice.getTipPoslasticeID()
				+ " ";
	}

	@Override
	public String vrednostiZaUpdate() {
		return " naziv = '" + naziv + "', cenaPoKomadu = " + cenaPoKomadu + ", " + "opis = '" + opis + "' ";
	}

	@Override
	public String uslovZaSelect() {
		return " ORDER BY P.POSLASTICAID ASC";
	}

	/**
	 * Vraca ID poslastice.
	 * 
	 * @return ID poslastice
	 */
	public Long getPoslasticaID() {
		return poslasticaID;
	}

	/**
	 * Postavlja ID poslastice. ID poslastice mora biti pozitivan broj i ne sme biti
	 * null.
	 * 
	 * @param poslasticaID ID poslastice
	 * @throws java.lang.NullPointerException     ako je ID null
	 * @throws java.lang.IllegalArgumentException ako je ID manji ili jednak nuli
	 */
	public void setPoslasticaID(Long poslasticaID) {
		if (poslasticaID == null)
			throw new NullPointerException("ID ne sme biti null.");

		if (poslasticaID <= 0)
			throw new IllegalArgumentException("ID mora biti pozitivan broj.");

		this.poslasticaID = poslasticaID;
	}

	/**
	 * Vraca naziv poslastice.
	 * 
	 * @return naziv poslastice
	 */
	public String getNaziv() {
		return naziv;
	}

	/**
	 * Postavlja naziv poslastice. Naziv ne sme biti null niti prazan string.
	 * 
	 * @param naziv Naziv poslastice
	 * @throws java.lang.NullPointerException     ako je naziv null
	 * @throws java.lang.IllegalArgumentException ako je naziv prazan string ili
	 *                                            sadrzi samo razmake
	 */
	public void setNaziv(String naziv) {
		if (naziv == null)
			throw new NullPointerException("Naziv ne sme biti null.");

		if (naziv.trim().isEmpty())
			throw new IllegalArgumentException("Naziv ne sme biti prazan.");

		this.naziv = naziv;
	}

	/**
	 * Vraca cenu po komadu poslastice.
	 * 
	 * @return cena po komadu
	 */
	public double getCenaPoKomadu() {
		return cenaPoKomadu;
	}

	/**
	 * Postavlja cenu po komadu poslastice. Cena mora biti veca od nule.
	 * 
	 * @param cenaPoKomadu Cena poslastice
	 * @throws java.lang.IllegalArgumentException ako je cena manja ili jednaka nuli
	 */
	public void setCenaPoKomadu(double cenaPoKomadu) {
		if (cenaPoKomadu <= 0)
			throw new IllegalArgumentException("Cena po komadu mora biti veca od nule.");

		this.cenaPoKomadu = cenaPoKomadu;
	}

	/**
	 * Vraca opis poslastice.
	 * 
	 * @return opis poslastice
	 */
	public String getOpis() {
		return opis;
	}

	/**
	 * Postavlja opis poslastice. Opis ne sme biti null niti prazan string.
	 * 
	 * @param opis Opis poslastice
	 * @throws java.lang.NullPointerException     ako je opis null
	 * @throws java.lang.IllegalArgumentException ako je opis prazan string ili
	 *                                            sadrzi samo razmake
	 */
	public void setOpis(String opis) {
		if (opis == null)
			throw new NullPointerException("Opis ne sme biti null.");

		if (opis.trim().isEmpty())
			throw new IllegalArgumentException("Opis ne sme biti prazan.");

		this.opis = opis;
	}

	/**
	 * Vraca tip poslastice.
	 * 
	 * @return tip poslastice
	 */
	public TipPoslastice getTipPoslastice() {
		return tipPoslastice;
	}

	/**
	 * Postavlja tip poslastice. Tip poslastice ne sme biti null.
	 * 
	 * @param tipPoslastice Tip poslastice
	 * @throws java.lang.NullPointerException ako je tip poslastice null
	 */
	public void setTipPoslastice(TipPoslastice tipPoslastice) {
		if (tipPoslastice == null)
			throw new NullPointerException("Tip poslastice ne sme biti null.");

		this.tipPoslastice = tipPoslastice;
	}

	/**
	 * Vraca listu sastojaka poslastice.
	 * 
	 * @return lista sastojaka
	 */
	public ArrayList<Sastojak> getSastojci() {
		return sastojci;
	}

	/**
	 * Postavlja listu sastojaka. Lista sastojaka ne sme biti null niti prazna.
	 * 
	 * @param sastojci Lista sastojaka
	 * @throws java.lang.NullPointerException     ako je lista null
	 * @throws java.lang.IllegalArgumentException ako je lista prazna
	 */
	public void setSastojci(ArrayList<Sastojak> sastojci) {
		if (sastojci == null)
			throw new NullPointerException("Lista sastojaka ne sme biti null.");

		if (sastojci.isEmpty())
			throw new IllegalArgumentException("Poslastica mora imati bar jedan sastojak.");

		this.sastojci = sastojci;
	}

	/**
	 * Vraca ambalazu poslastice.
	 * 
	 * @return ambalaza
	 */
	public Ambalaza getAmbalaza() {
		return ambalaza;
	}

	/**
	 * Postavlja ambalazu poslastice Lista sastojaka ne sme biti null.
	 * 
	 * @param ambalaza Ambalaza poslastice
	 * @throws java.lang.NullPointerException ako je ambalaza null
	 */
	public void setAmbalaza(Ambalaza ambalaza) {
		if (ambalaza == null)
			throw new NullPointerException("Ambalaza ne sme biti null.");

		this.ambalaza = ambalaza;
	}

	/**
	 * Vraca listu ocena poslastice.
	 * 
	 * @return lista ocena
	 */
	public ArrayList<OcenaPoslastice> getOcene() {
		return ocene;
	}

	/**
	 * Postavlja listu ocena. Lista ocena ne sme biti null niti prazna.
	 * 
	 * @param ocene Lista ocena
	 * @throws java.lang.NullPointerException ako je lista null
	 */
	public void setOcene(ArrayList<OcenaPoslastice> ocene) {
		if (ocene == null)
			throw new NullPointerException("Lista ocena ne sme biti null.");

		this.ocene = ocene;
	}

}
