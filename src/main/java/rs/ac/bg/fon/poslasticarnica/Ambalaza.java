package rs.ac.bg.fon.poslasticarnica;

import java.io.Serializable;

public class Ambalaza implements Serializable{
	  private static final long serialVersionUID = 2L;
	
	private Long ambalazaID;
    private String tip;       
    private Double zapremina; 


    public Ambalaza() { }

    public Ambalaza(Long ambalazaID, String tip, Double zapremina) {
        this.ambalazaID = ambalazaID;
        this.tip = tip;
        this.zapremina = zapremina;
    }

 
    public Long getAmbalazaID() { 
        return ambalazaID; 
    }
    
    public void setAmbalazaID(Long ambalazaID) { 
        this.ambalazaID = ambalazaID; 
    }

    public String getTip() { 
        return tip; 
    }
    public void setTip(String tip) {
        this.tip = tip; 
    }

    public Double getZapremina() { 
        return zapremina; 
    }
    public void setZapremina(Double zapremina) {
        this.zapremina = zapremina; 
    }

    @Override
    public String toString() {
        return tip + zapremina + " L)";
    }

}
