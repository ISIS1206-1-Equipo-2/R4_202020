/**
 * 2020-10-17
 * Clase CitiBike.
 * @author Julián Andrés Méndez
 */
package model.logic;

/**
 * Representa el objeto bicicleta en CitiBike.
 */
public class Turista {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	
	/**
	 * Representa el tiempo de llegada a una estación.
	 */
	private String starTime;
	
	/**
	 * Representa el tiempo de salida de una estación.
	 */
	private String stopTime;
	
	/**
	 * Representa la edad del turista.
	 */
	private int edad;
	
	/**
	 * Representa la memebresia del turista.
	 */
	private String membresia;
	
	/**
	 * Representa times en una ruta
	 */
	private int times;

	/**
	 * Representa la identificación de la bicicleta del turista.
	 */
	private int id;
	
	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------
	
	/**
	 * Crea un turista en citiBike.
	 * @param String startTime. Tiempo de inicio del turista.
	 * @param String stopTme. Tiempo final del turista.
	 * @param int edad. Edad del turista.
	 * @param String membresia. Membresia del turista.
	 */
	public Turista(String startTime, String stopTme, int edad, String membresia, int id, int times){
		this.starTime = startTime;
		this.stopTime = stopTme;
		this.edad = edad; 
		this.membresia = membresia;
		this.times = times;
		this.id = id; 
	}
	
	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------
	
	/**
	 * Devuelve el número de identificación de la bicicleta del turista.
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * Devuelve el tiempo de inicio del turista.
	 */
	public String getStartTime( ){
		return starTime;
	}
	
	/**
	 * Devuelve el tiempo final del turista.
	 */
	public String getStopTime( ){
		return stopTime;
	}
	
	/**
	 * Devuelve la edad del turista.
	 */
	public int getEdad(){
		return edad;
	}
	
	/**
	 * Devuelve la membresia del turista
	 */
	public String getMembresia( ){
		return membresia;
	}
	
	/**
	 * Modifica el times de la ruta
	 * @param int times. Times nuevo.
	 */
	public void setTimes( int pTime ){
		times = pTime; 
	}
	
	/**
	 * Devuelve el times de la ruta.
	 */
	public int getTimes(){
		return times;
	}
}












