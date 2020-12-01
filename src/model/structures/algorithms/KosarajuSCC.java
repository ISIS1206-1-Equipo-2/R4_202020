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
 * Representa el algoritmo de Kosaraju-Shair para la busqueda de clusteres (componentes fuertemente conectados).
 */
public class KosarajuSCC<K extends Comparable<K>,V, A> {

	// -----------------------------------------------------------------
	// Atributos.
	// -----------------------------------------------------------------

	/**
	 * Lista de componentes que indica los alcanzamientos de los vertices.
	 */
	private ArregloDinamicoGenerico<Vertex<K, V, A>> marked;

	/*
	 * Cantidad de elementos fuertemente conectados. 
	 */
	private int count; 

	// -----------------------------------------------------------------
	// Constructor.
	// -----------------------------------------------------------------

	/**
	 * Crea el algoritmo KosarajuSCC para un grafo dado. 
	 * @param Digraph G. 
	 */
	public KosarajuSCC( DiGraph<K,V,A> G )
	{
		marked = new ArregloDinamicoGenerico<Vertex<K, V, A>>(G.numVertices());

		@SuppressWarnings("unchecked")
		DepthFirstOrder<K, V, A> order = new DepthFirstOrder<K, V, A>(G.reverse());
		for(int i = 0; i<order.reversePost().size(); i++)
		{
			Vertex<K,V,A> v = G.getVertex(order.reversePost().getElemento(i+1).getId());
			if( marked.isPresent(v)!=1)
			{
				boolean mark = v.getMark();
				if(!mark) 
				{
					dfs(G, v);
					count++;
				}
			}
		}
	}

	// -----------------------------------------------------------------
	// Métodos.
	// -----------------------------------------------------------------

	/**
	 * Se encarga de realizar el dfs a un grafo dirigido y vertice dado. 
	 * @param Digraph G. Grafo dirigido conexo para realizar el depthFirstOrder.
	 * @param Vertex<K,V,A> v. Vertice dado para realizar el dfs. 
	 */
	public void dfs(DiGraph<K,V,A> G, Vertex<K,V,A> v)
	{
		v.mark();
		v.setCount( count);
		for( int i = 0; i<v.vertexOut().size(); i++)
		{
			Vertex<K, V, A> temp = (Vertex<K,V,A>) v.vertexOut().getElemento(i+1);
			if(!temp.getMark()) {dfs(G,temp);}
		}
	}

	/**
	 * Se encarga de verificar si dos vertices se encuentran fuertemente conectados. 
	 * @param Vertex<K,V,A> v. Vertice a verificar si está fuertemente conectado con otro dado.
	 * @param Vertex<K,V,A> w. Vertice a verificar si está fuertemente conectado con otro dado.
	 * @return Retorna true si los dos vertices se encuentran fuertemente conectados. De lo contrario, retorna false.
	 */
	public boolean stronglyConnected( Vertex<K,V,A> v, Vertex<K,V,A> w)
	{
		return v.getCount()==w.getCount();
	}
	
	/**
	 * Se encarga de calcular el número de vertices fuertemente conectados en el grafo. 
	 * @return Retorna el número de vertices fuertemente conectados del grafo.
	 */
	public int count()
	{
		return count;
	}
}
