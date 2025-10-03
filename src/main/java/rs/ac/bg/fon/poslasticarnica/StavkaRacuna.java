package rs.ac.bg.fon.poslasticarnica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StavkaRacuna extends AbstractDomainObject {
	
	    private static final long serialVersionUID = 7L;
	
	    private Racun racun;
	    private int rb;
	    private int kolicina;
	    private double cena;
	    private Poslastica poslastica;

	    public StavkaRacuna(Racun racun, int rb, int kolicina, double cena, Poslastica poslastica) {
	    	 setRacun(racun);
			 setRb(rb);
			 setKolicina(kolicina);
			 setCena(cena);
			 setPoslastica(poslastica);
	    }
	    

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
	            Administrator a = new Administrator(rs.getLong("AdministratorID"),
	                    rs.getString("Ime"), rs.getString("Prezime"),
	                    rs.getString("Username"), rs.getString("Password"));

	            Racun r = new Racun(rs.getLong("racunID"), rs.getTimestamp("datumVreme"),
	                    rs.getDouble("r.cena"), a, null);

	            TipPoslastice tp = new TipPoslastice(rs.getLong("TipPoslasticeID"),
	                    rs.getString("tp.Naziv"));

	            Poslastica p = new Poslastica(rs.getLong("poslasticaID"), rs.getString("p.naziv"),
	                    rs.getDouble("cenaPoKomadu"), rs.getString("opis"), tp, null);

	            StavkaRacuna sr = new StavkaRacuna(r, rs.getInt("rb"), rs.getInt("kolicina"),
	                    rs.getDouble("sr.cena"), p);

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
	        return " " + racun.getRacunID() + ", " + rb + ", "
	                + " " + kolicina + ", " + cena + ", " + poslastica.getPoslasticaID();
	    }
	    

	    @Override
	    public String vrednostiZaUpdate() {
	        return "";
	    }
	    

	    @Override
	    public String uslovZaSelect() {
	        return " WHERE R.RACUNID = " + racun.getRacunID();
	    }
	    

	    public Racun getRacun() {
	        return racun;
	    }

	    public void setRacun(Racun racun) {
	    	if (racun == null) 
		        throw new NullPointerException("Racun ne sme biti null.");
	    	
	        this.racun = racun;
	    }

	    
	    public int getRb() {
	        return rb;
	    }

	    public void setRb(int rb) {
	    	if (rb <= 0)
		        throw new IllegalArgumentException("Redni broj mora biti pozitivan broj.");
	    	
	        this.rb = rb;
	    }

	    
	    public int getKolicina() {
	        return kolicina;
	    }

	    public void setKolicina(int kolicina) {
	    	if (kolicina <= 0)
		        throw new IllegalArgumentException("Kolicina mora biti veca od nule.");
	    	
	        this.kolicina = kolicina;
	    }

	    
	    public double getCena() {
	        return cena;
	    }

	    public void setCena(double cena) {
	    	if (cena <= 0)
		        throw new IllegalArgumentException("Cena mora biti veca od nule.");
	    	
	        this.cena = cena;
	    }
	    

	    public Poslastica getPoslastica() {
	        return poslastica;
	    }

	    public void setPoslastica(Poslastica poslastica) {
	    	if (poslastica == null) 
		        throw new NullPointerException("Poslastica ne sme biti null.");
	    	
	        this.poslastica = poslastica;
	    }


}
