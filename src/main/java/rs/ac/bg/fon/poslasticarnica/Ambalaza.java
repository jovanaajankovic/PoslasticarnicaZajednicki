package rs.ac.bg.fon.poslasticarnica;

import java.io.Serializable;

public class Ambalaza implements Serializable {
	
	private static final long serialVersionUID = 2L;
	
	private Long ambalazaID;
    private String tip;       
    private Double zapremina; 


    public Ambalaza() {
    	
    }

    
    public Ambalaza(Long ambalazaID, String tip, Double zapremina) {
        setAmbalazaID(ambalazaID);
        setTip(tip);
        setZapremina(zapremina);
    }

 
    public Long getAmbalazaID() { 
        return ambalazaID; 
    }
    
    public void setAmbalazaID(Long ambalazaID) { 
    	if (ambalazaID == null)
			throw new NullPointerException("ID ne sme biti null.");
		
		if (ambalazaID <= 0)
	        throw new IllegalArgumentException("ID mora biti pozitivan broj.");
		
        this.ambalazaID = ambalazaID; 
    }

    
    public String getTip() { 
        return tip; 
    }
    
    public void setTip(String tip) {
    	if (tip == null)
			throw new NullPointerException("Tip ne sme biti null.");
		
		if (tip.trim().isEmpty())
			throw new IllegalArgumentException("Tip ne sme biti prazan.");
		
        this.tip = tip; 
    }
    

    public Double getZapremina() { 
        return zapremina; 
    }
    
    public void setZapremina(Double zapremina) {
    	if (zapremina == null)
			throw new NullPointerException("Zapremina ne sme biti null.");
		
		if (zapremina <= 0)
	        throw new IllegalArgumentException("Zapremina mora biti pozitivan broj.");
		
        this.zapremina = zapremina; 
    }

    
    @Override
    public String toString() {
        return tip + zapremina + " L)";
    }

}
