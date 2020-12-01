/**
 * 2020-10-17
 * Clase CitiBike.
 * @author Julián Andrés Méndez
 */
package model.logic;

/**
 * Representa el objeto estación en CitiBike.
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
	 * Representa el nombre de la estación.
	 */
	private String nombre;
	
	/**
	 * Representa la latitud de la estación.
	 */
	private double latitud;
	
	/**
	 * Representa la longitud de la estación.
	 */
	private double longitud;
	
	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------
	
	/**
	 * Crea una estación en citiBike.
	 * @param int id. Identificación de la estación.
	 * @param String nombre. Nombre de la estación
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
	// Métodos
	// -----------------------------------------------------------------
	
	/**
	 * Devuelve el nombre de la estación.
	 */
	public String getNombre(){
		return nombre;
	}
	
	/**
	 * Devuelve la longitud de la estación.
	 */
	public double getLongitud(){
		return longitud;
	}
	
	/**
	 * Devuelve la latitud de la estación.
	 */
	public double getLatitud(){
		return latitud;
	}
	
	/**
	 * Devuelve la edad del turista en la estación.
	 */
	public int getEdad(){
		return edadTimes; 
	}
}












