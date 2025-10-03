package rs.ac.bg.fon.poslasticarnica;

import java.io.Serializable;

public class OcenaPoslastice implements Serializable {
	
	    private static final long serialVersionUID = 3L;
	
	    private Long ocenaID;
	    private Integer ocena;    
	    private String komentar;


	    public OcenaPoslastice() { 
	    	
	    }

	    
	    public OcenaPoslastice(Long ocenaID, Integer ocena, String komentar) {
	        setOcenaID(ocenaID);
	        setOcena(ocena);
	        setKomentar(komentar);
	    }


	    public Long getOcenaID() { 
	        return ocenaID;
	    }
	    
	    public void setOcenaID(Long ocenaID) {
	    	if (ocenaID == null)
				throw new NullPointerException("ID ne sme biti null.");
			
			if (ocenaID <= 0)
		        throw new IllegalArgumentException("ID mora biti pozitivan broj.");
			
	        this.ocenaID = ocenaID; 
	    }

	    
	    public Integer getOcena() {
	        return ocena; 
	    }
	    
	    public void setOcena(Integer ocena) { 
	    	if (ocenaID <= 0)
		        throw new IllegalArgumentException("Ocena mora biti pozitivan broj.");
	    	
	        this.ocena = ocena; 
	    }

	    
	    public String getKomentar() {
	        return komentar; 
	    }
	    
	    public void setKomentar(String komentar) {
	    	if (komentar == null)
				throw new NullPointerException("Komentar ne sme biti null.");
			
			if (komentar.trim().isEmpty())
				throw new IllegalArgumentException("Komentar ne sme biti prazan.");
			
	        this.komentar = komentar;
	    }

	    
	    @Override
	    public String toString() {
	        return ocena + " / 5 - " + komentar;
	    }

}
