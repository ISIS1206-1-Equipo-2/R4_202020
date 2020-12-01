/**
 * 2020-11-17
 * Clase TestDirectedGraph.
 * @author Julián Andrés Méndez
 * @author Juan Miguel Vega Caro
 */

package test.testAlgorithms;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.structures.algorithms.DepthFirstOrder;
import model.structures.directedGraph.DiGraph;
import model.structures.directedGraph.Vertex;
import model.structures.listaGenerica.ArregloDinamicoGenerico;

/**
 * Test del algoritmo de DepthFirstOrder.
 */
public class TestDepthFirstOrder {
	
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Representa la estructura de datos de grafo dirigido.
	 */
	private DiGraph<String, Integer, String> diGraph;
	
	/**
	 * Representa el algoritmo de DepthFirstOrder.
	 */
	private DepthFirstOrder<String, Integer, String> depthFirstOrder;
	
	// -----------------------------------------------------------------
	// Escenarios
	// -----------------------------------------------------------------
	
	/**
	 * Primer escenario del test.
	 */
	@Before
	public void setUp1() {
		diGraph = new DiGraph<String, Integer, String>();
		diGraph.insertVertex("A", 1);
		diGraph.insertVertex("B", 2);
		diGraph.insertVertex("C", 3);
		diGraph.insertVertex("D", 4);
		diGraph.insertVertex("E", 5);
		
		diGraph.addEdge("A", "B", 20, "EdgeAB");
		diGraph.addEdge("A", "C", 30, "EdgeAC");
		diGraph.addEdge("A", "D", 40, "EdgeAD");
		diGraph.addEdge("D", "C", 70, "EdgeDC");
		diGraph.addEdge("C", "E", 140, "EdgeCE");
		diGraph.addEdge("B", "E", 70, "EdgeBE");
		
		depthFirstOrder = new DepthFirstOrder<String, Integer, String>(diGraph);
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------


	/**
	 * Devuelve la lista de vertices en pre orden
	 * @return Retorna la lista de vertices en pre orden. 
	 */
	@Test
	public void pre(){
		ArregloDinamicoGenerico< Vertex<String,Integer,String> > temp = depthFirstOrder.pre();
		
		assertEquals("Error: El vertice A no se encuentra en la posición correcta", "A", temp.getElemento(1).getId());
		assertEquals("Error: El vertice B no se encuentra en la posición correcta", "B", temp.getElemento(2).getId());
		assertEquals("Error: El vertice E no se encuentra en la posición correcta", "E", temp.getElemento(3).getId());
		assertEquals("Error: El vertice C no se encuentra en la posición correcta", "C", temp.getElemento(4).getId());
		assertEquals("Error: El vertice E no se encuentra en la posición correcta", "D", temp.getElemento(5).getId());
	}

	/**
	 * Devuelve la lista de vertices en post orden
	 * @return Retorna la lista de vertices en post orden. 
	 */
	@Test
	public void post(){
		ArregloDinamicoGenerico< Vertex<String,Integer,String> > temp = depthFirstOrder.post();
		
		assertEquals("Error: El vertice E no se encuentra en la posición correcta", "E", temp.getElemento(1).getId());
		assertEquals("Error: El vertice B no se encuentra en la posición correcta", "B", temp.getElemento(2).getId());
		assertEquals("Error: El vertice C no se encuentra en la posición correcta", "C", temp.getElemento(3).getId());
		assertEquals("Error: El vertice D no se encuentra en la posición correcta", "D", temp.getElemento(4).getId());
		assertEquals("Error: El vertice A no se encuentra en la posición correcta", "A", temp.getElemento(5).getId());
	}

	/**
	 * Devuelve la lista de vertices en reversa del post orden.
	 * @return Retorna la lista de vertices en reversa del post orden.
	 */
	@Test
	public void reversePost(){
		ArregloDinamicoGenerico< Vertex<String,Integer,String> > temp = depthFirstOrder.reversePost();
		
		assertEquals("Error: El vertice A no se encuentra en la posición correcta", "A", temp.getElemento(1).getId());
		assertEquals("Error: El vertice D no se encuentra en la posición correcta", "D", temp.getElemento(2).getId());
		assertEquals("Error: El vertice C no se encuentra en la posición correcta", "C", temp.getElemento(3).getId());
		assertEquals("Error: El vertice B no se encuentra en la posición correcta", "B", temp.getElemento(4).getId());
		assertEquals("Error: El vertice E no se encuentra en la posición correcta", "E", temp.getElemento(5).getId());
	}

}