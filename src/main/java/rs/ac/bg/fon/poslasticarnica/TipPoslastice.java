package rs.ac.bg.fon.poslasticarnica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipPoslastice extends AbstractDomainObject{
	
	  private static final long serialVersionUID = 8L;
	
	private Long tipPoslasticeID;
    private String naziv;

    @Override
    public String toString() {
        return naziv;
    }

    public TipPoslastice(Long tipPoslasticeID, String naziv) {
        this.tipPoslasticeID = tipPoslasticeID;
        this.naziv = naziv;
    }

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
            TipPoslastice tp = new TipPoslastice(rs.getLong("TipPoslasticeID"),
                    rs.getString("Naziv"));

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

    public Long getTipPoslasticeID() {
        return tipPoslasticeID;
    }

    public void setTipPoslasticeID(Long tipPoslasticeID) {
        this.tipPoslasticeID = tipPoslasticeID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

}
