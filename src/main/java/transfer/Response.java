package transfer;

import java.io.Serializable;

import transfer.util.ResponseStatus;

/**
 * Klasa koja predstavlja odgovor servera na zahtev klijenta.
 * <p>
 * Odgovor moze sadrzati podatke, poruku o gresci (ako je doslo do greske) i
 * status odgovora (uspeh ili greska). Implementira Serializable radi slanja
 * preko mreze.
 * 
 * @author Jovana Jankovic
 */
public class Response implements Serializable {

	/** Jedinstveni identifikator verzije klase za potrebe serijalizacije. */
	private static final long serialVersionUID = 2L;

	/** Podaci koje server vraca klijentu. */
	private Object data;

	/** Poruka o gresci ako je do nje doslo/ */
	private String errorMessage;

	/** Status odgovora (uspesan ili neuspesan). */
	private ResponseStatus responseStatus;

	/**
	 * Inicijalizuje objekat klase Response sa atributima koji imaju default
	 * vrednosti.
	 */
	public Response() {
	}

	/**
	 * Inicijalizuje objekat klase Response sa svim parametrima.
	 * 
	 * @param data           Podaci koje server vraca klijentu
	 * @param errorMessage   Poruka o gresci ako je do nje doslo
	 * @param responseStatus Status odgovora (SUCCESS / ERROR)
	 */
	public Response(Object data, String errorMessage, ResponseStatus responseStatus) {
		this.data = data;
		this.errorMessage = errorMessage;
		this.responseStatus = responseStatus;
	}

	/**
	 * Vraca podatke koje je server poslao.
	 * 
	 * @return objekat sa podacima
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Postavlja podatke koje server salje.
	 * 
	 * @param data Objekat sa podacima
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * Vraca poruku o gresci ako do nje doslo.
	 * 
	 * @return poruka o gresci
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Postavlja poruku o gresci.
	 * 
	 * @param errorMessage Poruka o gresci
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Vraca status odgovora.
	 * 
	 * @return SUCCESS ili ERROR
	 */
	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	/**
	 * Postavlja status odgovora.
	 * 
	 * @param responseStatus SUCCESS ili ERROR
	 */
	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

}
