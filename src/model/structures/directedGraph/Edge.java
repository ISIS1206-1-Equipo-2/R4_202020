/**
 * 2020-10-17
 * Clase Edge.
 * @author Julián Andrés Méndez
 * @author Juan Miguel Vega Caro
 */

package model.structures.directedGraph;

/**
 * Representa el objeto arco en la estructura de datos de grafo dirigido.
 */
public class Edge <K extends Comparable<K>, V, A>{
	
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	
	/**
	 * Representa el vertice de origen asociado al arco.
	 */
	private Vertex<K, V, A> source;
	
	/**
	 * Representa el vertice de destino asociado al arco.
	 */
	private Vertex<K, V, A> dest;
	
	/**
	 * Representa el peso asociado al arco.
	 */
	private double weight;
	
	/**
	 * Representa la información asociada a un arco.
	 */
	private A info;
	
	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------
	
	/**
	 * Crea el arco desde el vértice de origen al vertice destino con peso weight.
	 * @param VVertex<K, V, A> source. Vértice source de origen.
	 * @param Vertex<K, V, A> dest. Vértice dest de destino. 
	 * @param double weight. Peso del vértice.
	 * @param A info. Información asociada a un arco.
	 */
	public Edge( Vertex<K, V, A> source, Vertex<K, V, A> dest, double weight, A  info){
		this.source = source;
		this.dest = dest;
		this.weight = weight;
		this.info = info;
	}
	
	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Calcula el vértice de origen.
	 * @return Retorna el vértice de origen.
	 */
	public Vertex<K, V, A> getSource( ){
		return source;
	}
	
	/**
	 * Calcula el vértice de salida.
	 * @return Retorna el vértice de salida.
	 */
	public Vertex<K, V, A> getDest( ){
		return dest;
	}
	
	/**
	 * Calcula el peso del arco.
	 * @return Retorna el peso del arco.
	 */
	public double getWeight( ){
		return weight;
	}
	
	/**
	 * Calcula la información asociada a un arco.
	 * @return Retorna la información asociada al arco,
	 */
	public A getInfo( ){
		return info;
	}
	
	/**
	 * Modifica el peso del arco.
	 * @param Double weight. Nuevo valor del peso del arco.
	 */
	public void setWeight( double weight ){
		this.weight = weight;
	}
	
	/**
	 * Modifica la información del arco.
	 * @param A info. Nuevo valor de información asociado a un arco.
	 */
	public void setInfo( A info ){
		this.info = info;
	}
	
}