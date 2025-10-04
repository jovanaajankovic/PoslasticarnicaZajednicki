package transfer.util;

/**
 * Interfejs koji definise sve operacije koje klijent moze zahtevati od servera.
 * <p>
 * Svaka operacija je predstavljena celobrojnim kodom (int konstanta). Ovi
 * kodovi se koriste u Request klasi kako bi server znao koju operaciju treba da
 * izvrsi.
 * 
 * @author Jovana Jankovic
 */
public interface Operation {

	/** Operacija prijavljivanja korisnika na sistem. */
	public static final int LOGIN = 0;

	/** Operacija odjavljivanja korisnika sa sistema. */
	public static final int LOGOUT = 1;

	/** Operacija dodavanja novog administratora. */
	public static final int ADD_ADMINISTRATOR = 2;

	/** Operacija brisanja administratora. */
	public static final int DELETE_ADMINISTRATOR = 3;

	/** Operacija azuriranja podataka o administratoru. */
	public static final int UPDATE_ADMINISTRATOR = 4;

	/** Operacija vracanja svih administratora. */
	public static final int GET_ALL_ADMINISTRATOR = 5;

	/** Operacija dodavanja nove poslastice. */
	public static final int ADD_POSLASTICA = 6;

	/** Operacija azuriranja poslastice. */
	public static final int UPDATE_POSLASTICA = 7;

	/** Operacija brisanja poslastice. */
	public static final int DELETE_POSLASTICA = 8;

	/** Operacija vracanja svih poslastica. */
	public static final int GET_ALL_POSLASTICA = 9;

	/** Operacija dodavanja racuna. */
	public static final int ADD_RACUN = 10;

	/** Operacija vracanja svih racuna. */
	public static final int GET_ALL_RACUN = 11;

	/** Operacija dodavanja tipa poslastice. */
	public static final int ADD_TIP_POSLASTICE = 12;

	/** Operacija brisanja tipa poslastice. */
	public static final int DELETE_TIP_POSLASTICE = 13;

	/** Operacija azuriranja tipa poslastice. */
	public static final int UPDATE_TIP_POSLASTICE = 14;

	/** Operacija vracanja svih tipova poslastice. */
	public static final int GET_ALL_TIP_POSLASTICE = 15;

}
