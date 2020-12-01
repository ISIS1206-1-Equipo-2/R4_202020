/**
 * 2020-10-17
 * Clase CitiBike.
 * @author Juli�n Andr�s M�ndez
 */
package model.logic;

/**
 * Representa el objeto estaci�n en CitiBike.
 */
public class Estacion {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	
	/**
	 * Representa la edad de un turista.
	 */
	private int edadTimes; 
	
	/**
	 * Representa el nombre de la estaci�n.
	 */
	private String nombre;
	
	/**
	 * Representa la latitud de la estaci�n.
	 */
	private double latitud;
	
	/**
	 * Representa la longitud de la estaci�n.
	 */
	private double longitud;
	
	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------
	
	/**
	 * Crea una estaci�n en citiBike.
	 * @param int id. Identificaci�n de la estaci�n.
	 * @param String nombre. Nombre de la estaci�n
	 * @param double latitud.
	 * @param double longitud.
	 */
	public Estacion(String nombre, double latitud, double longitud, int edadTimes){
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
		this.edadTimes = edadTimes; 
	}
	
	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------
	
	/**
	 * Devuelve el nombre de la estaci�n.
	 */
	public String getNombre(){
		return nombre;
	}
	
	/**
	 * Devuelve la longitud de la estaci�n.
	 */
	public double getLongitud(){
		return longitud;
	}
	
	/**
	 * Devuelve la latitud de la estaci�n.
	 */
	public double getLatitud(){
		return latitud;
	}
	
	/**
	 * Devuelve la edad del turista en la estaci�n.
	 */
	public int getEdad(){
		return edadTimes; 
	}
}












