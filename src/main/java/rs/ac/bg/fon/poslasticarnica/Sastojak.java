package rs.ac.bg.fon.poslasticarnica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Predstavlja sastojak poslastice.
 * <p>
 * Svaki sastojak ima redni broj, naziv i pripada nekoj poslastici.
 * </p>
 *
 * Nasledjuje klasu AbstractDomainObject i implementira metode za perzistenciju
 * u bazu podataka.
 * 
 * @author Jovana Jankovic
 */
public class Sastojak extends AbstractDomainObject {

	/** Jedinstveni identifikator verzije klase za potrebe serijalizacije. */
	private static final long serialVersionUID = 6L;

	/**
	 * Poslastica kojoj sastojak pripada.
	 * <p>
	 * Obelezena je kao <b>transient</b> da se ne bi serijalizovala.
	 * </p>
	 */
	private transient Poslastica poslastica;

	/** Redni broj sastojka kao int */
	private int rb;

	/** Naziv sastojka kao string */
	private String naziv;

	/**
	 * Inicijalizuje objekat klase Sastojak sa svim parametrima.
	 *
	 * @param poslastica Poslastica kojoj sastojak pripada. Ne sme biti null.
	 * @param rb         Redni broj sastojka. Mora biti veci od nule.
	 * @param naziv      Naziv sastojka. Ne sme biti null niti prazan string.
	 */
	public Sastojak(Poslastica poslastica, int rb, String naziv) {
		setPoslastica(poslastica);
		setRb(rb);
		setNaziv(naziv);
	}

	/**
	 * Inicijalizuje objekat klase Sastojak sa atributima koji imaju default
	 * vrednosti.
	 */
	public Sastojak() {
	}

	@Override
	public String nazivTabele() {
		return " Sastojak ";
	}

	@Override
	public String alijas() {
		return " s ";
	}

	@Override
	public String join() {
		return " JOIN POSLASTICA P ON (P.POSLASTICAID = S.POSLASTICAID) "
				+ "JOIN TIPPOSLASTICE TP ON (TP.TIPPOSLASTICEID = P.TIPPOSLASTICEID)";
	}

	@Override
	public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
		ArrayList<AbstractDomainObject> lista = new ArrayList<>();

		while (rs.next()) {
			TipPoslastice tp = new TipPoslastice(rs.getLong("TipPoslasticeID"), rs.getString("tp.Naziv"));

			Poslastica p = new Poslastica(rs.getLong("poslasticaID"), rs.getString("p.naziv"),
					rs.getDouble("cenaPoKomadu"), rs.getString("opis"), tp, null);

			Sastojak s = new Sastojak(p, rs.getInt("rb"), rs.getString("s.naziv"));

			lista.add(s);
		}

		rs.close();
		return lista;
	}

	@Override
	public String koloneZaInsert() {
		return " (poslasticaID, rb, naziv) ";
	}

	@Override
	public String uslov() {
		return " poslasticaID = " + poslastica.getPoslasticaID();
	}

	@Override
	public String vrednostiZaInsert() {
		return " " + poslastica.getPoslasticaID() + ", " + rb + ", " + "'" + naziv + "' ";
	}

	@Override
	public String vrednostiZaUpdate() {
		return "";
	}

	@Override
	public String uslovZaSelect() {
		return " WHERE P.POSLASTICAID = " + poslastica.getPoslasticaID();
	}

	/**
	 * Vraca poslasticu kojoj sastojak pripada.
	 *
	 * @return poslastica
	 */
	public Poslastica getPoslastica() {
		return poslastica;
	}

	/**
	 * Postavlja poslasticu kojoj sastojak pripada. Poslastica ne sme biti null.
	 *
	 * @param poslastica Poslastica kojoj sastojak pripada
	 * @throws java.lang.NullPointerException ako je poslastica null
	 */
	public void setPoslastica(Poslastica poslastica) {
		if (poslastica == null)
			throw new NullPointerException("Poslastica ne sme biti null.");

		this.poslastica = poslastica;
	}

	/**
	 * Vraca redni broj sastojka.
	 *
	 * @return redni broj
	 */
	public int getRb() {
		return rb;
	}

	/**
	 * Postavlja redni broj sastojka. Redni broj mora biti veci od nule.
	 *
	 * @param rb Redni broj
	 * @throws java.lang.IllegalArgumentException ako je redni broj negativan ili
	 *                                            jednak nuli
	 */
	public void setRb(int rb) {
		if (rb <= 0)
			throw new IllegalArgumentException("Redni broj mora biti pozitivan broj.");

		this.rb = rb;
	}

	/**
	 * Vraca naziv sastojka.
	 *
	 * @return naziv sastojka
	 */
	public String getNaziv() {
		return naziv;
	}

	/**
	 * Postavlja naziv sastojka. Naziv ne sme biti null niti prazan string.
	 *
	 * @param naziv Naziv
	 * @throws java.lang.NullPointerException     ako je naziv null
	 * @throws java.lang.IllegalArgumentException ako je naziv prazan ili sadrzi
	 *                                            samo razmake
	 */
	public void setNaziv(String naziv) {
		if (naziv == null)
			throw new NullPointerException("Naziv ne sme biti null.");

		if (naziv.trim().isEmpty())
			throw new IllegalArgumentException("Naziv ne sme biti prazan.");

		this.naziv = naziv;
	}

	/**
	 * Vraca tekstualni prikaz sastojka.
	 * 
	 * @return String sa podacima o rednom broju i nazivu sastojka u formatu rb + ".
	 *         " + naziv
	 */
	@Override
	public String toString() {
		return rb + ". " + naziv;
	}

}
