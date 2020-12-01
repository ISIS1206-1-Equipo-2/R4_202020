/**
 * 2020-11-17
 * Clase Digraph.
 * @author Julián Andrés Méndez
 * @author Juan Miguel Vega Caro
 */
package model.structures.algorithms;

import model.structures.directedGraph.DiGraph;
import model.structures.directedGraph.Vertex;
import model.structures.listaGenerica.ArregloDinamicoGenerico;

/**
 * Representa el algoritmo de la busqueda en profundidad en función de un ordenamiento. 
 */
public class DepthFirstOrder<K extends Comparable<K>,V, A> {

	// -----------------------------------------------------------------
	// Atributos.
	// -----------------------------------------------------------------

	/**
	 * Lista boolean que indica los alcanzamientos de los vertices.
	 */
	private ArregloDinamicoGenerico<Vertex<K,V,A>> marked;

	/**
	 * Lista Queue que representa la lista de vertices en preorder.
	 */
	private ArregloDinamicoGenerico<Vertex<K,V,A>> pre;

	/**
	 * Lista Queue que representa la lista de vertices en postorder.
	 */
	private ArregloDinamicoGenerico<Vertex<K,V,A>> post;

	/**
	 * Lista Stack que representa la lista de vertices en reversa del postorder.
	 */
	private ArregloDinamicoGenerico<Vertex<K,V,A>> reversePost;

	// -----------------------------------------------------------------
	// Constructor.
	// -----------------------------------------------------------------

	/**
	 * Crea un DepthFirstOrder con un grafo dirigido dado.
	 * @param DiGraph<K,V,A> G. Grafo dirigido conexo para realizar el depthFirstOrder.
	 */
	public DepthFirstOrder( DiGraph<K,V,A> G ) {
		pre = new ArregloDinamicoGenerico<Vertex<K,V,A>>(G.numVertices());
		post = new ArregloDinamicoGenerico<Vertex<K,V,A>>(G.numVertices());
		reversePost = new ArregloDinamicoGenerico<Vertex<K,V,A>>(G.numVertices());
		marked = new ArregloDinamicoGenerico<Vertex<K,V,A>>(G.numVertices());

		for(int i = 0; i<G.vertex().size(); i++)
		{
			Vertex<K,V,A> v = (Vertex<K, V, A>) G.vertex().getElemento(i+1);
			if( marked.isPresent(v)!=1)
			{
				boolean mark = v.getMark();
				if(!mark) dfs(G, v);
			}
		}
		
		for(int i = 0; i<G.vertex().size(); i++)
		{
			G.vertex().getElemento(i+1).unmark();
		}
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Se encarga de realizar el dfs a un grafo dirigido y vertice dado. 
	 * @param DiGraph<K,V,A>  G. Grafo dirigido conexo para realizar el depthFirstOrder.
	 * @param Vertex<K,V,A> v. Vertice dado para realizar el dfs. 
	 */
	private void dfs( DiGraph<K,V,A> G, Vertex<K,V,A> v) {
		pre.addLast(v);
		v.mark();
		for( int i = 0; i<v.vertexOut().size(); i++)
		{
			Vertex<K, V, A> temp = (Vertex<K,V,A>) v.vertexOut().getElemento(i+1);
			if(!temp.getMark()) {dfs(G,temp);}
		}
		post.addLast(v);
		reversePost.addFirst(v);
	}

	/**
	 * Devuelve la lista de vertices en pre orden
	 * @return Retorna la lista de vertices en pre orden. 
	 */
	public ArregloDinamicoGenerico<Vertex<K,V,A>> pre(){
		return pre;
	}

	/**
	 * Devuelve la lista de vertices en post orden
	 * @return Retorna la lista de vertices en post orden. 
	 */
	public ArregloDinamicoGenerico<Vertex<K,V,A>> post(){
		return post;
	}

	/**
	 * Devuelve la lista de vertices en reversa del post orden.
	 * @return Retorna la lista de vertices en reversa del post orden.
	 */
	public ArregloDinamicoGenerico<Vertex<K,V,A>> reversePost(){
		return reversePost;
	}

}



















