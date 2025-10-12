package rs.ac.bg.fon.poslasticarnica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Predstavlja stavku racuna u poslasticarnici. Svaka stavka pripada odredjenom
 * racunu i sadrzi redni broj, kolicinu, cenu i poslasticu na koju se odnosi.
 * 
 * Nasledjuje klasu AbstractDomainObject i implementira metode za perzistenciju
 * u bazu podataka.
 *
 * @author Jovana Jankovic
 */
public class StavkaRacuna extends AbstractDomainObject {

	/** Jedinstveni identifikator verzije klase za potrebe serijalizacije. */
	private static final long serialVersionUID = 7L;

	/** Racun kome stavka pripada */
	private Racun racun;

	/** Redni broj stavke kao int */
	private int rb;

	/** Kolicina stavke na racunu kao int */
	private int kolicina;

	/** Cena stavke kao double */
	private double cena;

	/** Poslastica na koju se stavka odnosi */
	private Poslastica poslastica;

	/**
	 * Inicijalizuje objekat klase StavkaRacuna sa svim parametrima.
	 *
	 * @param racun      Racun kojem stavka pripada.
	 * @param rb         Redni broj stavke.
	 * @param kolicina   Kolicina poslastica. Mora biti veca od nule.
	 * @param cena       Cena stavke. Mora biti veca od nule.
	 * @param poslastica Poslastica koja se nalazi u stavci. Ne sme biti null.
	 */
	public StavkaRacuna(Racun racun, int rb, int kolicina, double cena, Poslastica poslastica) {
		setRacun(racun);
		setRb(rb);
		setKolicina(kolicina);
		setCena(cena);
		setPoslastica(poslastica);
	}

	/**
	 * Inicijalizuje objekat klase StavkaRacuna sa atributima koji imaju default
	 * vrednosti.
	 */
	public StavkaRacuna() {
	}

	@Override
	public String nazivTabele() {
		return " StavkaRacuna ";
	}

	@Override
	public String alijas() {
		return " sr ";
	}

	@Override
	public String join() {
		return " JOIN POSLASTICA P ON (P.POSLASTICAID = SR.POSLASTICAID) "
				+ "JOIN TIPPOSLASTICE TP ON (TP.TIPPOSLASTICEID = P.TIPPOSLASTICEID) "
				+ "JOIN RACUN R ON (R.RACUNID = SR.RACUNID) "
				+ "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = R.ADMINISTRATORID) ";
	}

	@Override
	public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
		ArrayList<AbstractDomainObject> lista = new ArrayList<>();

		while (rs.next()) {
			Administrator a = new Administrator(rs.getLong("AdministratorID"), rs.getString("Ime"),
					rs.getString("Prezime"), rs.getString("Username"), rs.getString("Password"));

			Racun r = new Racun(rs.getLong("racunID"), rs.getTimestamp("datumVreme"), rs.getDouble("r.cena"), a, null);

			TipPoslastice tp = new TipPoslastice(rs.getLong("TipPoslasticeID"), rs.getString("tp.Naziv"));

			Poslastica p = new Poslastica(rs.getLong("poslasticaID"), rs.getString("p.naziv"),
					rs.getDouble("cenaPoKomadu"), rs.getString("opis"), tp, null);

			StavkaRacuna sr = new StavkaRacuna(r, rs.getInt("rb"), rs.getInt("kolicina"), rs.getDouble("sr.cena"), p);

			lista.add(sr);
		}

		rs.close();
		return lista;
	}

	@Override
	public String koloneZaInsert() {
		return " (racunID, rb, kolicina, cena, poslasticaID) ";
	}

	@Override
	public String uslov() {
		return " racunID = " + racun.getRacunID() + " AND RB = " + rb;
	}

	@Override
	public String vrednostiZaInsert() {
		return " " + racun.getRacunID() + ", " + rb + ", " + " " + kolicina + ", " + cena + ", "
				+ poslastica.getPoslasticaID();
	}

	@Override
	public String vrednostiZaUpdate() {
		return "";
	}

	@Override
	public String uslovZaSelect() {
		return " WHERE R.RACUNID = " + racun.getRacunID();
	}

	/**
	 * Vraca racun kome stavka pripada.
	 *
	 * @return racun stavke
	 */
	public Racun getRacun() {
		return racun;
	}

	/**
	 * Postavlja racun kome stavka pripada.
	 *
	 * @param racun Racun kome stavka pripada
	 */
	public void setRacun(Racun racun) {
		this.racun = racun;
	}

	/**
	 * Vraca redni broj stavke.
	 * 
	 * @return redni broj
	 */
	public int getRb() {
		return rb;
	}

	/**
	 * Postavlja redni broj stavke.
	 *
	 * @param rb Redni broj stavke
	 */
	public void setRb(int rb) {
		this.rb = rb;
	}

	/**
	 * Vraca kolicinu poslastica u stavci.
	 *
	 * @return kolicina
	 */
	public int getKolicina() {
		return kolicina;
	}

	/**
	 * Postavlja kolicinu poslastica u stavci. Kolicina mora biti veca od nule.
	 *
	 * @param kolicina Kolicina poslastica
	 * @throws java.lang.IllegalArgumentException ako je kolicina manja ili jednaka
	 *                                            nuli
	 */
	public void setKolicina(int kolicina) {
		if (kolicina <= 0)
			throw new IllegalArgumentException("Kolicina mora biti veca od nule.");

		this.kolicina = kolicina;
	}

	/**
	 * Vraca cenu stavke racuna.
	 *
	 * @return cena stavke
	 */
	public double getCena() {
		return cena;
	}

	/**
	 * Postavlja cenu stavke racuna. Cena mora biti veca od nule.
	 *
	 * @param cena Cena stavke racuna
	 * @throws java.lang.IllegalArgumentException ako je cena manja ili jednaka nuli
	 */
	public void setCena(double cena) {
		if (cena <= 0)
			throw new IllegalArgumentException("Cena mora biti veca od nule.");

		this.cena = cena;
	}

	/**
	 * Vraca poslasticu koja je deo stavke racuna.
	 *
	 * @return poslastica
	 */
	public Poslastica getPoslastica() {
		return poslastica;
	}

	/**
	 * Postavlja poslasticu za stavku racuna. Poslastica ne sme biti null.
	 *
	 * @param poslastica Poslastica
	 * @throws java.lang.NullPointerException ako je poslastica null
	 */
	public void setPoslastica(Poslastica poslastica) {
		if (poslastica == null)
			throw new NullPointerException("Poslastica ne sme biti null.");

		this.poslastica = poslastica;
	}

}
