package rs.ac.bg.fon.poslasticarnica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Sastojak extends AbstractDomainObject{
	
	private static final long serialVersionUID = 6L;
	
	private Poslastica poslastica;
    private int rb;
    private String naziv;

    public Sastojak(Poslastica poslastica, int rb, String naziv) {
    	 setPoslastica(poslastica);
		 setRb(rb);
		 setNaziv(naziv);
    }

    
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
            TipPoslastice tp = new TipPoslastice(rs.getLong("TipPoslasticeID"),
                    rs.getString("tp.Naziv"));

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
        return " " + poslastica.getPoslasticaID() + ", " + rb + ", "
                + "'" + naziv + "' ";
    }
    

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }
    

    @Override
    public String uslovZaSelect() {
        return " WHERE P.POSLASTICAID = " + poslastica.getPoslasticaID();
    }
    

    public Poslastica getPoslastica() {
        return poslastica;
    }

    public void setPoslastica(Poslastica poslastica) {
    	if (poslastica == null)
	        throw new NullPointerException("Poslastica ne sme biti null.");
    	
        this.poslastica = poslastica;
    }

    
    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
    	if (rb <= 0)
	        throw new IllegalArgumentException("Redni broj mora biti pozitivan broj.");
    	
        this.rb = rb;
    }

    
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
    	if (naziv == null)
			throw new NullPointerException("Naziv ne sme biti null.");
		
    	if (naziv.trim().isEmpty())
			throw new IllegalArgumentException("Naziv ne sme biti prazan.");
    	
        this.naziv = naziv;
    }
    
    
    @Override
	 public String toString() {
		 return rb + ". " + naziv;
	 }

}
