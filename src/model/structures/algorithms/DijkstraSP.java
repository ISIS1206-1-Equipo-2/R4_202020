/**
 * 2020-11-17
 * Clase Digraph.
 * @author Julián Andrés Méndez
 * @author Juan Miguel Vega Caro
 */
package model.structures.algorithms;

import model.structures.binarySearchTree.BinarySearchTree;
import model.structures.binarySearchTree.BinarySearchTree.NodoBinario;
import model.structures.directedGraph.DiGraph;
import model.structures.directedGraph.Edge;
import model.structures.directedGraph.Vertex;
import model.structures.listaGenerica.ArregloDinamicoGenerico;

/**
 * Representa el algoritmo de DijikstraSP cuyo objetivo es la busquedas de caminos más cortos.
 */
public class DijkstraSP<K extends Comparable<K>,V, A> {

	// -----------------------------------------------------------------
	// Atributos.
	// -----------------------------------------------------------------

	/**
	 * Representa la lista de edges directos en referencia a un vertice.
	 */
	private ArregloDinamicoGenerico<Edge<K,V,A>> edgeTo;

	/**
	 * Representa la lista de pesos en el grafo
	 */
	private ArregloDinamicoGenerico<Vertex<K,V,A>> distTo;

	/**
	 * Representa la estructura de datos arbol rojo negro para el index Min PQ
	 */
	private BinarySearchTree<K, Double> pq;

	/**
	 * Representa el vertice v en el minPath
	 */
	private Vertex<K,V,A> source;

	// -----------------------------------------------------------------
	// Constructor.
	// -----------------------------------------------------------------

	/**
	 * Se encarga de calcular la ruta más corta dado un grafo y el vertice. 
	 * @param DiGraph<K, V, A>G. Grafo dirigido y conexo.
	 * @param Vertex<K,V,A> v. Vertice del grafo a sacar la ruta.
	 */
	public DijkstraSP (DiGraph<K, V, A> G, Vertex<K,V,A> v){
		source = v; 
		edgeTo = new ArregloDinamicoGenerico<Edge<K,V,A>>(G.numVertices());
		distTo = new ArregloDinamicoGenerico<Vertex<K,V,A>>(G.numVertices());
		pq = new BinarySearchTree<>();
		for(int i = 0; i< G.numVertices(); i++){ 
			Vertex<K, V,A > temp = G.vertex().getElemento(i+1);
			if(!v.equals(temp)){temp.setPath( Double.POSITIVE_INFINITY );}
			else temp.setPath(0.0);
			distTo.addLast(temp);
		}
		pq.put(v.getId(), 0.0);
		while (!pq.isEmpty()){
			NodoBinario n = pq.min();
			pq.delete(pq.min().key);
			relax(G, n);		
		}
	}

	// -----------------------------------------------------------------
	// Métodos.
	// -----------------------------------------------------------------

	/**
	 * Se encarga de actualizar la ruta más corta dado un grafo y un vertice,
	 * @param DiGraph<K, V, A>G. Grafo dirigido y conexo.
	 * @param Vertex<K,V,A> v. Vertice del grafo a sacar la ruta.
	 */
	@SuppressWarnings("unchecked")
	public void relax(DiGraph<K, V, A> G, NodoBinario v){
		K id = (K) v.key;
		for(int i = 0; i< G.adjacentEdges(id).size(); i++){
			Edge<K,V,A> edge = G.adjacentEdges(id).getElemento(i+1);
			Vertex<K,V,A> destLocation = edge.getDest();

			int index = distTo.isPresent(destLocation);
			Vertex<K,V,A> dest = distTo.getElemento(index);

			if( dest.getPath() > (Double) v.val +edge.getWeight()){
				dest.setPath((Double) v.val + edge.getWeight());
				edge.getSource().setEdgeTo(edge);
				dest.setEdgeFrontPath(edge);
				if( pq.contains(dest.getId())){
					BinarySearchTree<K, Double>.NodoBinario temp = pq.getNodo(dest.getId());
					temp.setValue(dest.getPath() );
				}
				else pq.put(dest.getId(), dest.getPath());
			}
		}
	}

	/**
	 * Calcula la ruta más rapida dado un vestice origen y un destino.
	 * @param Vertex<K,V,A> v. Vertice de partida
	 * @param Vertex<K,V,A> w. Vertice destino. 
	 * @return Retorna la lista de los arcos en la ruta más corta.
	 */
	public ArregloDinamicoGenerico<Edge<K,V,A>> minPath(Vertex<K,V,A> v, Vertex<K,V,A> w) {
		minPathAux(w);
		return edgeTo;
	}

	/**
	 * Actualiza la lista edgeTo con la ruta más corta de un vertice.
	 * @param Vertex<K,V,A> w. Vertice destino en función de un vertice origen.
	 */
	private void minPathAux(Vertex<K, V, A> w) {
		Edge<K,V,A> edge = w.getEdgeFrontPath();
		if( edge != null){
			Vertex<K,V,A> v = edge.getSource();
			if(v.equals(source)){
				edgeTo.addFirst(edge);
			}
			else{
				edgeTo.addFirst(edge);
				minPathAux(v);
			}
		}
	}

	/**
	 * Devuelve el arreglo de vertices con su ruta minímica.
	 * @return Retorna 
	 */
	public ArregloDinamicoGenerico<Vertex<K,V,A>> getDistTo( ){
		return distTo;
	}


}
