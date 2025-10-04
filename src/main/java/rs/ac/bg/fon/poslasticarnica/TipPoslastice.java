package rs.ac.bg.fon.poslasticarnica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Predstavlja tip poslastice u poslasticarnici. Tip poslastice se identifikuje
 * pomocu ID-a i naziva (npr. torta, kolac, sladoled).
 * 
 * Nasledjuje klasu AbstractDomainObject i implementira metode za perzistenciju
 * u bazu podataka.
 *
 * @author Jovana Jankovic
 */
public class TipPoslastice extends AbstractDomainObject {

	/** Jedinstveni identifikator verzije klase za potrebe serijalizacije. */
	private static final long serialVersionUID = 8L;

	/** ID tipa poslastice kao long */
	private Long tipPoslasticeID;

	/** Naziv poslastice kao String */
	private String naziv;

	/**
	 * Vraca tekstualni prikaz tipa poslastice.
	 * 
	 * @return naziv tipa poslastice
	 */
	@Override
	public String toString() {
		return naziv;
	}

	/**
	 * Inicijalizuje objekat klase TipPoslastice sa svim parametrima.
	 *
	 * @param tipPoslasticeID Jedinstveni ID tipa poslastice. Ne sme biti null i
	 *                        mora biti veci od nule.
	 * @param naziv           Naziv tipa poslastice. Ne sme biti null niti prazan
	 *                        string.
	 */
	public TipPoslastice(Long tipPoslasticeID, String naziv) {
		setTipPoslasticeID(tipPoslasticeID);
		setNaziv(naziv);
	}

	/**
	 * Inicijalizuje objekat klase TipPoslastice sa atributima koji imaju default
	 * vrednosti.
	 */
	public TipPoslastice() {
	}

	@Override
	public String nazivTabele() {
		return " TipPoslastice ";
	}

	@Override
	public String alijas() {
		return " tp ";
	}

	@Override
	public String join() {
		return "";
	}

	@Override
	public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
		ArrayList<AbstractDomainObject> lista = new ArrayList<>();

		while (rs.next()) {
			TipPoslastice tp = new TipPoslastice(rs.getLong("TipPoslasticeID"), rs.getString("Naziv"));

			lista.add(tp);
		}

		rs.close();
		return lista;
	}

	@Override
	public String koloneZaInsert() {
		return " (Naziv) ";
	}

	@Override
	public String uslov() {
		return " TipPoslasticeID = " + tipPoslasticeID;
	}

	@Override
	public String vrednostiZaInsert() {
		return " '" + naziv + "' ";
	}

	@Override
	public String vrednostiZaUpdate() {
		return " Naziv = '" + naziv + "' ";
	}

	@Override
	public String uslovZaSelect() {
		return "";
	}

	/**
	 * Vraca jedinstveni ID tipa poslastice.
	 *
	 * @return ID tipa poslastice
	 */
	public Long getTipPoslasticeID() {
		return tipPoslasticeID;
	}

	/**
	 * Postavlja jedinstveni ID tipa poslastice. ID tipa poslastice ne sme biti null
	 * i mora biti veci od nule.
	 *
	 * @param tipPoslasticeID ID tipa poslastice
	 * @throws java.lang.NullPointerException     ako je tipPoslasticeID null
	 * @throws java.lang.IllegalArgumentException ako je tipPoslasticeID manji ili
	 *                                            jednak nuli
	 */
	public void setTipPoslasticeID(Long tipPoslasticeID) {
		if (tipPoslasticeID == null)
			throw new NullPointerException("ID ne sme biti null.");

		if (tipPoslasticeID <= 0)
			throw new IllegalArgumentException("ID mora biti pozitivan broj.");

		this.tipPoslasticeID = tipPoslasticeID;
	}

	/**
	 * Vraca naziv tipa poslastice.
	 *
	 * @return naziv tipa poslastice
	 */
	public String getNaziv() {
		return naziv;
	}

	/**
	 * Postavlja naziv tipa poslastice. Naziv tipa poslastice ne sme biti null niti
	 * prazan string.
	 * 
	 * @param naziv Naziv tipa poslastice
	 * @throws java.lang.NullPointerException     ako je naziv null
	 * @throws java.lang.IllegalArgumentException ako je naziv prazan string
	 */
	public void setNaziv(String naziv) {
		if (naziv == null)
			throw new NullPointerException("Naziv ne sme biti null.");

		if (naziv.isEmpty())
			throw new IllegalArgumentException("Naziv ne sme biti prazan.");

		this.naziv = naziv;
	}

}
