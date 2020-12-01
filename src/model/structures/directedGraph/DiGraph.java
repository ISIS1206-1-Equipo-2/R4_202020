/**
 * 2020-11-17
 * Clase Digraph.
 * @author Julián Andrés Méndez
 * @author Juan Miguel Vega Caro
 */

package model.structures.directedGraph;
import model.structures.listaGenerica.ArregloDinamicoGenerico;
import model.structures.listaComparable.ArregloDinamicoComparable;


/**
 * Representa la estructura de datos de grafo dirigido.
 */
public class DiGraph<K extends Comparable<K>, V, A> {

	// -----------------------------------------------------------------
	// Constantes.
	// -----------------------------------------------------------------

	public static final int TAMAÑO_INICIAL = 30;

	// -----------------------------------------------------------------
	// Atributos.
	// -----------------------------------------------------------------

	/**
	 * Lista de adyacencia de vertices.
	 */
	private ArregloDinamicoGenerico< Vertex<K, V, A> > vertexGraph;

	/**
	 * Lista de adyacencia de edges en el grafo.
	 */
	private ArregloDinamicoGenerico< Edge<K, V, A> >edgesGraph;

	/**
	 * Lista de adyacencia de identificador.
	 */
	private ArregloDinamicoComparable< K > vertexIdGraph;

	/**
	 * Representa el arco con peso mínimo en el grafo.
	 */
	private Edge<K, V, A> minWeight;

	/**
	 * Representa el arco con mayor peso en el grafo.
	 */
	private Edge<K, V, A> maxWeight;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Crea una estructura de datos de grafo dirigido.
	 */
	public DiGraph( ){
		vertexGraph = new ArregloDinamicoGenerico< Vertex<K, V, A> >(TAMAÑO_INICIAL);
		edgesGraph = new ArregloDinamicoGenerico< Edge<K, V, A> > (TAMAÑO_INICIAL);
		vertexIdGraph = new ArregloDinamicoComparable< K >(TAMAÑO_INICIAL);
		minWeight = null;
		maxWeight = null;
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Verifica si el vertice con id suministrado está en el grafo 
	 * @param K id. Vertice suministrado.
	 * @return Retorna true si el vertice se encuentra en el grafo. De lo contrario, retorna false. 
	 */
	public boolean containsVertex(K id){
		return (vertexIdGraph.isPresent(id)==-1)?false:true;
	}

	/**
	 * Calcula el número de vertices que se encuentran en el grafo. 
	 * @return El valor de vertices en el grafo.
	 */
	public int numVertices( ){
		return vertexIdGraph.size();
	}

	/**
	 * Calcula el número de arcos en el grafo.
	 * @return El número de arcos en el grafo. 
	 */
	public int numEdges( ){
		return edgesGraph.size();
	}

	/**
	 * Devuelve el arco con mayor peso en el grafo.
	 * @return Retorna el arco con mayor peso en el grafo.
	 */
	public Edge<K, V, A> maxWeight( ){
		return maxWeight;
	}

	/**
	 * Devuelve el arco con menor peso en el grafo.
	 * @return Retorna el arco con mayor peso en el grafo.
	 */
	public Edge<K, V, A> minWeight( ){
		return minWeight;
	}

	/**
	 * Añade un vértice al grafo con su identificador y valor.
	 * @param K id. Vertice suministrado.
	 * @param V value. El valor del vertice suministrado. 
	 */
	public void insertVertex( K id, V value){
		if(!containsVertex( id )){
			Vertex< K, V, A> newVertex = new Vertex<K, V, A>(id, value);
			vertexGraph.addLast( newVertex );
			vertexIdGraph.addLast( id );
		}
	}

	/**
	 * Añade un arco dirigido pesado entre el vértice origen y destino, con peso weight. 
	 * Si el arco ya existe se modifica su peso.
	 * @param K source. Vertice de origen.
	 * @param K dest. Vertice de origen.
	 * @param double weight. Peso del arco. 
	 * @param A info. Información asociada a un arco.
	 */
	public void addEdge( K source, K dest, double weight , A info){
		if(containsVertex( source ) && containsVertex( dest )){
			Edge< K, V, A> presentEdge = getEdge(source, dest);
			if(presentEdge == null){
				Vertex<K, V, A> vertexSource = getVertex( source );
				Vertex<K, V, A> vertexDest = getVertex( dest );
				Edge< K, V, A> edge = new Edge<K, V, A>(vertexSource, vertexDest, weight, info);
				vertexSource.addEddge(edge);
				vertexDest.addEddge(edge);
				
				edgesGraph.addLast(edge);
				setMaxMinWeight(edge);
			}
			else{
				presentEdge.setInfo(info);
				presentEdge.setWeight(weight);
				setMaxMinWeight(presentEdge);
			}
		}
	}

	/**
	 * Calcula el vértice a partir de su indentificador único.
	 * @param K id. Identificador único del vertice.
	 * @return Retorna el vértice perteneciente al identificador único.
	 */
	public Vertex<K, V, A> getVertex( K id){
		int index =  vertexIdGraph.isPresent(id);
		if (index == -1) return null;
		else return vertexGraph.getElemento(index);
	}

	/**
	 * Calcula el arco entre los vértices idS y idD (si existe). Retorna null si no existe.
	 * @param K idS. Identificación del értice de origen.
	 * @param k idD. Identificación del Vértice de destino.
	 * @return Retorna el arco perteneciente al vertice de origen y de destino único.
	 */
	public Edge<K, V, A> getEdge( K ids, K idD){
		Vertex<K, V, A> vertex = getVertex(ids);
		return vertex.getEdge(idD);
	}

	/**
	 * Devuelve una lista de arcos adyacentes (salientes) al vértice con id. 
	 * @param K id. Identificador único del vertice.
	 * @return Retorna la lista de arcos adyacentes (salientes) al vertice con id.
	 */
	public ArregloDinamicoGenerico<Edge<K, V, A>> adjacentEdges( K id){
		Vertex<K, V, A> vertex = getVertex( id );
		return vertex.edgesOut();
	}

	/**
	 * Devuelve una lista de vértices adyacentes (salientes) al vértice con id. 
	 * @param K id. Identificador único del vertice.
	 * @return Retorna la lista de vértices adyacentes (salientes) al vertice con id.
	 */
	public ArregloDinamicoGenerico<Vertex<K, V, A>> adjacentVertex( K id){
		Vertex<K, V, A> vertex = getVertex( id );
		return vertex.vertexOut();
	}

	/**
	 * Calcula el grado de entrada del vertice vertex (número de arcos entrantes).
	 * @param K vertex. Vertice a calular.
	 * @return Retorna el grado de entrada del vertice vertex.
	 */
	public int indegree (K vertex){
		Vertex<K, V, A> vertexToKnow = getVertex( vertex );
		return vertexToKnow.inDegree();
	}

	/**
	 * Calcula el grado de salida del vertice vertex (número de arcos salientes).
	 * @param K vertex. Vertice a calular.
	 * @return Retorna el grado de salida del vertice vertex.
	 */
	public int outdegree (K vertex){
		Vertex<K, V, A> vertexToKnow = getVertex( vertex );
		return vertexToKnow.outDegree();
	}

	/**
	 * Devuelve una lista de todos los arcos del grafo. 
	 * @return Retorna la lista de todos los arcos del grafo. 
	 */
	public ArregloDinamicoGenerico<Edge<K, V, A>> edges(){
		return edgesGraph;
	}

	/**
	 * Devuelve una lista de todos los vertices del grafo. 
	 * @return Retorna la lista de todos los vertices del grafo. 
	 */
	public ArregloDinamicoGenerico<Vertex<K, V, A>> vertex( ){
		return vertexGraph;
	}

	/**
	 * Calcula el nuevo max weight y min weight de los arcos en el grafo.
	 */
	public void setMaxMinWeight( Edge<K, V, A> edge){
		if(minWeight!=null) {if(edge.getWeight()<minWeight.getWeight()) minWeight = edge;}
		else {minWeight = edge;}

		if(maxWeight!=null) {if(edge.getWeight()>maxWeight.getWeight()) maxWeight = edge;}	
		else {maxWeight = edge;}
	}
	
	/**
	 * Se encarga de invertir el grafo presente. 
	 * @return Retorna el grafo invertido. 
	 */
	@SuppressWarnings("unchecked")
	public DiGraph reverse(){
		DiGraph reverseG = new DiGraph<>();
		for(int i = 0; i<vertexGraph.size(); i++)
		{
			Vertex<K,V,A> source = vertexGraph.getElemento(i+1);
			ArregloDinamicoGenerico< Edge<K, V, A> > adj = source.edgesOut();
			for(int j = 0; j< adj.size(); j++)
			{
				Edge<K, V, A> temp = adj.getElemento(j+1);
				Vertex<K,V,A> dest = temp.getDest();
				reverseG.insertVertex(source.getId(), source.getValue());
				reverseG.insertVertex(dest.getId(),dest.getValue());
				reverseG.addEdge(dest.getId(), source.getId(), temp.getWeight(), temp.getInfo());
			}
		}
		return reverseG;
	}
}
