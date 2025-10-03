package rs.ac.bg.fon.poslasticarnica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Poslastica extends AbstractDomainObject {
	
	  private static final long serialVersionUID = 4L;
	
	private Long poslasticaID;
    private String naziv;
    private double cenaPoKomadu;
    private String opis;
    private TipPoslastice tipPoslastice;
    private ArrayList<Sastojak> sastojci;
    private Ambalaza ambalaza;
    private ArrayList<OcenaPoslastice> ocene = new ArrayList<>();

    @Override
    public String toString() {
        return naziv + " (Cena po komadu: " + cenaPoKomadu + "din)";
    }

    public Poslastica(Long poslasticaID, String naziv, double cenaPoKomadu, String opis, TipPoslastice tipPoslastice, ArrayList<Sastojak> sastojci) {
        this.poslasticaID = poslasticaID;
        this.naziv = naziv;
        this.cenaPoKomadu = cenaPoKomadu;
        this.opis = opis;
        this.tipPoslastice = tipPoslastice;
        this.sastojci = sastojci;
    }

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
            TipPoslastice tp = new TipPoslastice(rs.getLong("TipPoslasticeID"),
                    rs.getString("tp.Naziv"));

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
        return "'" + naziv + "', " + cenaPoKomadu + ", "
                + "'" + opis + "', " + tipPoslastice.getTipPoslasticeID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "', cenaPoKomadu = " + cenaPoKomadu + ", "
                + "opis = '" + opis + "' ";
    }

    @Override
    public String uslovZaSelect() {
        return " ORDER BY P.POSLASTICAID ASC";
    }

    public Long getPoslasticaID() {
        return poslasticaID;
    }

    public void setPoslasticaID(Long poslasticaID) {
        this.poslasticaID = poslasticaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCenaPoKomadu() {
        return cenaPoKomadu;
    }

    public void setCenaPoKomadu(double cenaPoKomadu) {
        this.cenaPoKomadu = cenaPoKomadu;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public TipPoslastice getTipPoslastice() {
        return tipPoslastice;
    }

    public void setTipPoslastice(TipPoslastice tipPoslastice) {
        this.tipPoslastice = tipPoslastice;
    }

    public ArrayList<Sastojak> getSastojci() {
        return sastojci;
    }

    public void setSastojci(ArrayList<Sastojak> sastojci) {
        this.sastojci = sastojci;
    }
    
    public Ambalaza getAmbalaza() { return ambalaza; }
    public void setAmbalaza(Ambalaza ambalaza) { this.ambalaza = ambalaza; }

    public ArrayList<OcenaPoslastice> getOcene() { return ocene; }
    public void setOcene(ArrayList<OcenaPoslastice> ocene) { this.ocene = ocene; }

}
