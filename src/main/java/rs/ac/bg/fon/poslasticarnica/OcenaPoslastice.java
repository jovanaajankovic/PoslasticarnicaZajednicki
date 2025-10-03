package rs.ac.bg.fon.poslasticarnica;

import java.io.Serializable;

public class OcenaPoslastice implements Serializable{
	
	  private static final long serialVersionUID = 3L;
	
	 private Long ocenaID;
	    private Integer ocena;    
	    private String komentar;


	    public OcenaPoslastice() { }

	    public OcenaPoslastice(Long ocenaID, Integer ocena, String komentar) {
	        this.ocenaID = ocenaID;
	        this.ocena = ocena;
	        this.komentar = komentar;

	    }


	    public Long getOcenaID() { 
	        return ocenaID;
	    }
	    public void setOcenaID(Long ocenaID) {
	        this.ocenaID = ocenaID; 
	    }

	    public Integer getOcena() {
	        return ocena; 
	    }
	    public void setOcena(Integer ocena) { 
	        this.ocena = ocena; 
	    }

	    public String getKomentar() {
	        return komentar; 
	    }
	    public void setKomentar(String komentar) {
	        this.komentar = komentar;
	    }

	    @Override
	    public String toString() {
	        return ocena + " / 5 - " + komentar;
	    }

}
