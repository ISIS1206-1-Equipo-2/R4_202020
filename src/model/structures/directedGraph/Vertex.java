/**
 * 2020-10-17
 * Clase Vertex.
 * @author Juli�n Andr�s M�ndez
 * @author Juan Miguel Vega Caro
 */

package model.structures.directedGraph;

import model.structures.listaGenerica.ArregloDinamicoGenerico;

/**
 * Representa el objeto vertice en la estructura de datos de grafo dirigido.
 */
public class Vertex<K extends Comparable<K>,V, A>{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	public static final int TAMA�O_ADJ = 30;

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Representa el identifiacador �nico del vertice.
	 */
	private K id;

	/**
	 * Representa el valor asociado al vertice.
	 */
	private V value;

	/**
	 * Representa la marca en un vertice.
	 */
	private boolean mark;

	/**
	 * Representa el identificador del cluster(componentes fuertementes conectados) al que pertenece.
	 */
	private int count;
	
	/**
	 * Representa el valor de una ruta a llegar con referencia a un nodo.
	 */
	private double path;
	
	/**
	 * Representa el arco ideal para una ruta min�ma.
	 */
	private Edge<K,V,A> edgeFrontPath; 
	
	/**
	 * Representa la lista de arcos adjacentes salientes a un vertice.
	 */
	private ArregloDinamicoGenerico< Edge<K, V, A> > adjEdgeOut;
	
	/**
	 * Representa la lista de arcos adjacentes entrantes al vertice.
	 */
	private ArregloDinamicoGenerico< Edge<K, V, A> > adjEdgeIn;
	
	/**
	 * Representa la lista de v�rtices adjacentes salientes a un vertice.
	 */
	private ArregloDinamicoGenerico< Vertex<K, V, A> > adjVertex;
	
	/**
	 * Representa el arco con con el total de peso hasta u v�rtice destino para llegar a 
	 */
	private Edge<K,V,A> EdgeTo;
	
	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Crea un v�rtice con identificador �nico y valor (informaci�n asociada), el vertice inicia desmarcado.
	 * @param K id. Identificador �nico del v�rtice.
	 * @param V value. Valor suministrado para el v�rtice.
	 */
	public Vertex( K id, V value){
		this.id = id;
		this.value = value;
		this.mark = false;
		this.count = -1;
		this.adjEdgeOut = new ArregloDinamicoGenerico< Edge<K, V, A> >( TAMA�O_ADJ );
		this.adjEdgeIn = new ArregloDinamicoGenerico< Edge<K, V, A> >( TAMA�O_ADJ );
		this.adjVertex = new ArregloDinamicoGenerico< Vertex<K, V, A> >( TAMA�O_ADJ );
		this.edgeFrontPath = null;
	}


	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Devuelve el edgeFrontPath asociado al vertice.
	 */
	public Edge<K,V,A> getEdgeFrontPath(){
		return edgeFrontPath;
	}
	
	/**
	 * Modifica el edgeFrontPath asociado al vertice.
	 * @param Edge<K,V,A> pFrontEdgePath
	 */
	public void setEdgeFrontPath( Edge<K,V,A> pEdgeFrontPath){
		edgeFrontPath = pEdgeFrontPath;
	}
	
	/**
	 * Calcula el count correspondiente del v�rtice.
	 * @return Retorna el count del v�rtice.
	 */
	public int getCount(){
		return count;
	}
	
	/**
	 * Modifica el count del v�rtice.
	 * @param int pCount. Actual count del v�rtice.
	 */
	public void setCount( int pCount){
		this.count = pCount;
	}
	
	/**
	 * Modifica el edgeTo de un vertife
	 * @param Edge<K,V,A> edge. Edge actual ideal para el vertice. 
	 */
	public void setEdgeTo(Edge<K,V,A> edge){
		this.EdgeTo = edge;
	}
	
	/**
	 * Devuelve el edgeTo del vertice.
	 * @return Retorna el edgeFront de un vertice. 
	 */
	public Edge<K,V,A> getEdgeTo(){
		return EdgeTo;
	}
	
	/**
	 * Calcula el valor de la ruta actual.
	 * @return Retorna el valor de la ruta actual.
	 */
	public double getPath(){
		return path;
	}
	
	/**
	 * Modifica el valor de la ruta actual.
	 * @param double path. Representa el nuevo valor de la ruta actual.
	 */
	public void setPath( double path)
	{
		this.path = path;
	}
	
	/**
	 * Calcula el identificador del v�rtice.
	 * @return Retorna el identificador del v�rtice.
	 */
	public K getId( ){
		return id;
	}

	/**
	 * Busca el valor asociado al v�rtice.
	 * @return Retorna el valor asociado al v�rtice.
	 */
	public V getValue( ){
		return value;
	}
	/**
	 * Verifica si el v�rtice se encuentra marcado o no.
	 * @return Retorna true si el v�rtice se encuentra marcado. De lo contrario, retorna false.
	 */
	public boolean getMark( ){
		return mark;
	}

	/**
	 * Agrega un arco adyacente al v�rtice.
	 * @param Edge<K, V, A> edge. Arco adyancente al v�rtice.
	 */
	public void addEddge( Edge<K, V, A> edge){
		Vertex<K, V, A> dest = edge.getDest();
		Vertex<K, V, A> source = edge.getSource();
		if( dest.equals(this.id) && source.equals(this.id)){
			adjEdgeIn.addLast(edge);
			adjEdgeOut.addLast(edge);
			adjVertex.addLast(dest);
		}
		
		else if(dest.equals(this.id) && !source.equals(id)){
			adjEdgeIn.addLast(edge);
		}
		
		else{
			adjEdgeOut.addLast(edge);
			adjVertex.addLast(dest);
		}
	}

	/**
	 * Marca el v�rtice.
	 */
	public void mark( ){
		this.mark = true;
	}

	/**
	 * Desmarca el v�rtice.
	 */
	public void unmark( ){
		this.mark = false; 
	}

	/**
	 * Calcula el n�mero de arcos (salientes) del v�rtice.
	 * @return Retorna n�mero de arcos (salientes) del v�rtice.
	 */
	public int outDegree( ){
		return adjEdgeOut.size();
	}

	/**
	 * Calcula el n�mero de arcos (entrantes) del v�rtice.
	 * @return Retorna n�mero de arcos (entrantes) del v�rtice.
	 */
	public int inDegree( ){
		return adjEdgeIn.size();
	}

	/**
	 * Calcula el arco con el v�rtice vertex (si existe). Retorna null si no existe.
	 * @param K vertex. Arco asociado al v�rtice.
	 * @return Retorna el arco con el v�rtice vertex (si existe. De lo contrario, retorna null.
	 */
	public Edge<K, V, A> getEdge(K vertex){
		boolean cent = false;
		Edge<K, V, A> res = null;
		for(int i = 0; i<adjEdgeOut.size() && !cent; i++){
			Edge<K, V, A> act =adjEdgeOut.getElemento(i+1);
			Vertex<K, V, A> dest = act.getDest();
			if(dest.equals(vertex)){
				res = act;
				cent = true;
			}
		}
		return res;
	}

	/**
	 * Devuelve una lista con sus v�rtices adyacentes (salientes).
	 * @return Retorna una lista con sus vertices adyancentes (salientes).
	 */
	public ArregloDinamicoGenerico< Vertex<K, V, A>> vertexOut(){
		return adjVertex;
	}

	/**
	 * Devuelve una lista con sus arcos adyacentes (salientes).
	 * @return Retorna una lista con sus vertices adyancentes (salientes).
	 */
	public ArregloDinamicoGenerico<Edge<K, V, A>> edgesOut(){
		return adjEdgeOut;
	}
	
	/**
	 * Devuelve una lista con sus arcos adyacentes (entrantes).
	 * @return Retorna una lista con sus vertices adyancentes (entrantes).
	 */
	public ArregloDinamicoGenerico<Edge<K, V, A>> edgesIn(){
		return adjEdgeIn;
	}
	
	/**
	 * Compara un v�rtice con otro dado por par�matro.
	 * @param Vertex<K, V, A> vertex. Vertice a comparar.
	 * @return Retorna true si los dos vertices son iguales. De lo contrario, retorna false.
	 */
	public boolean equals(K pId){
		return (pId.compareTo(id)==0)?true:false;
	}
}