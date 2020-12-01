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
import model.structures.algorithms.KosarajuSCC;
import model.structures.directedGraph.DiGraph;
import model.structures.directedGraph.Vertex;
import model.structures.listaGenerica.ArregloDinamicoGenerico;

/**
 * Test del algoritmo de KosarajuSC.
 */
public class TestKosarajuSCC {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	
	/**
	 * Representa la estructura de datos de grafo dirigido.
	 */
	private DiGraph<Integer, String, String> diGraph;
	
	/**
	 * Representa el algoritmo de KosarajuSC.
	 */
	private KosarajuSCC<Integer, String, String> kosarajuSCC;
	
	// -----------------------------------------------------------------
	// Escenarios
	// -----------------------------------------------------------------
	
	/**
	 * Primer escenario del test.
	 */
	@Before
	public void setUp1() {
		diGraph = new DiGraph<Integer, String, String>();
		diGraph.insertVertex(0, "A");
		diGraph.insertVertex(1, "B");
		diGraph.insertVertex(2, "C");
		diGraph.insertVertex(3, "D");
		diGraph.insertVertex(4, "E");
		diGraph.insertVertex(5, "F");
		diGraph.insertVertex(6, "G");
		diGraph.insertVertex(7, "H");
		diGraph.insertVertex(8, "I");
		diGraph.insertVertex(9, "J");
		diGraph.insertVertex(10, "K");
		diGraph.insertVertex(11, "L");
		diGraph.insertVertex(12, "M");
		
		diGraph.addEdge(0, 1, 10, "Edge01");
		diGraph.addEdge(0, 5, 10, "Edge05");
		diGraph.addEdge(2, 0, 10, "Edge20");
		diGraph.addEdge(2, 3, 10, "Edge23");
		diGraph.addEdge(3, 2, 10, "Edge32");
		diGraph.addEdge(3, 5, 10, "Edge35");
		diGraph.addEdge(4, 3, 10, "Edge43");
		diGraph.addEdge(4, 2, 10, "Edge42");
		diGraph.addEdge(5, 4, 10, "Edge54");
		diGraph.addEdge(6, 8, 10, "Edge68");
		diGraph.addEdge(6, 9, 10, "Edge69");
		diGraph.addEdge(6, 4, 10, "Edge64");
		diGraph.addEdge(6, 0, 10, "Edge60");
		diGraph.addEdge(7, 9, 10, "Edge79");
		diGraph.addEdge(7, 6, 10, "Edge76");
		diGraph.addEdge(8, 6, 10, "Edge86");
		diGraph.addEdge(9, 11, 10, "Edge911");
		diGraph.addEdge(9, 10, 10, "Edge910");
		diGraph.addEdge(10, 12, 10, "Edge910");
		diGraph.addEdge(11, 12, 10, "Edge1112");
		diGraph.addEdge(11, 4, 10, "Edge114");
		diGraph.addEdge(12, 9, 10, "Edge01");
		
		kosarajuSCC = new KosarajuSCC<Integer, String, String>(diGraph);
	}
	
	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------
	
	/**
	 * Se encarga de realizar el dfs a un grafo dirigido y vertice dado. 
	 */
	@Test
	public void dfs()
	{
		
	}

	/**
	 * Se encarga de verificar si dos vertices se encuentran fuertemente conectados. 
	 */
	@Test
	public void stronglyConnected( )
	{
		Vertex<Integer,String,String> zero = diGraph.getVertex(0);
		Vertex<Integer,String,String> one = diGraph.getVertex(1);
		Vertex<Integer,String,String> two = diGraph.getVertex(2);
		Vertex<Integer,String,String> three = diGraph.getVertex(3);
		Vertex<Integer,String,String> four = diGraph.getVertex(4);
		Vertex<Integer,String,String> five = diGraph.getVertex(5);
		Vertex<Integer,String,String> six = diGraph.getVertex(6);
		Vertex<Integer,String,String> seven = diGraph.getVertex(7);
		Vertex<Integer,String,String> eight = diGraph.getVertex(8);
		Vertex<Integer,String,String> nine = diGraph.getVertex(9);
		Vertex<Integer,String,String> ten = diGraph.getVertex(10);
		Vertex<Integer,String,String> eleven = diGraph.getVertex(11);
		Vertex<Integer,String,String> twelve = diGraph.getVertex(12);
		
		assertTrue("Error: Los componentes se encuentran fuertemente conectados", kosarajuSCC.stronglyConnected(zero, two));
		assertTrue("Error: Los componentes se encuentran fuertemente conectados", kosarajuSCC.stronglyConnected(zero, three));
		assertTrue("Error: Los componentes se encuentran fuertemente conectados", kosarajuSCC.stronglyConnected(zero, five));
		assertTrue("Error: Los componentes se encuentran fuertemente conectados", kosarajuSCC.stronglyConnected(zero, four));
		
		assertTrue("Error: Los componentes se encuentran fuertemente conectados", kosarajuSCC.stronglyConnected(six, eight));
		assertTrue("Error: Los componentes se encuentran fuertemente conectados", kosarajuSCC.stronglyConnected(eight, six));
		
		assertTrue("Error: Los componentes se encuentran fuertemente conectados", kosarajuSCC.stronglyConnected(nine, ten));
		assertTrue("Error: Los componentes se encuentran fuertemente conectados", kosarajuSCC.stronglyConnected(nine, twelve));
		assertTrue("Error: Los componentes se encuentran fuertemente conectados", kosarajuSCC.stronglyConnected(nine, eleven));
		
		assertFalse("Error: Los componentes se encuentran fuertemente conectados", kosarajuSCC.stronglyConnected(one, ten));
		assertFalse("Error: Los componentes se encuentran fuertemente conectados", kosarajuSCC.stronglyConnected(one, twelve));
		assertFalse("Error: Los componentes se encuentran fuertemente conectados", kosarajuSCC.stronglyConnected(one, eleven));
		
		assertFalse("Error: Los componentes se encuentran fuertemente conectados", kosarajuSCC.stronglyConnected(seven, ten));
		assertFalse("Error: Los componentes se encuentran fuertemente conectados", kosarajuSCC.stronglyConnected(seven, twelve));
		assertFalse("Error: Los componentes se encuentran fuertemente conectados", kosarajuSCC.stronglyConnected(seven, eleven));
	}
	
	/**
	 * Se encarga de calcular el número de vertices fuertemente conectados en el grafo. 
	 */
	@Test
	public void count()
	{
		assertEquals("Error: La cantidad de componentes fuertemente conectados debería ser 5.", 5, kosarajuSCC.count());
	}
}
