package rs.ac.bg.fon.poslasticarnica;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class AbstractDomainObject implements Serializable{
	
	    private static final long serialVersionUID = 1L;
	
	    public abstract String nazivTabele();
	    public abstract String alijas();
	    public abstract String join();
	    public abstract ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException;
	    public abstract String koloneZaInsert();
	    public abstract String uslov();
	    public abstract String vrednostiZaInsert();
	    public abstract String vrednostiZaUpdate();
	    public abstract String uslovZaSelect();

}
