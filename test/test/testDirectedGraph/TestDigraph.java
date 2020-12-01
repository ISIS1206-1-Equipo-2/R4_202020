/**
 * 2020-11-17
 * Clase TestDirectedGraph.
 * @author Julián Andrés Méndez
 * @author Juan Miguel Vega Caro
 */

package test.testDirectedGraph;
import static org.junit.Assert.*;
import model.structures.listaGenerica.ArregloDinamicoGenerico;
import org.junit.Before;
import org.junit.Test;
import model.structures.directedGraph.DiGraph;
import model.structures.directedGraph.Edge;
import model.structures.directedGraph.Vertex;

/**
 * Test de la estructura de datos de grafo dirigido.
 */
public class TestDigraph {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Representa la estructura de datos de grafo dirigido.
	 */
	private DiGraph<String, Integer, String> diGraph;
	
	// -----------------------------------------------------------------
	// Escenarios
	// -----------------------------------------------------------------
	
	/**
	 * Primer escenario del test.
	 */
	@Before
	public void setUp1() {
		diGraph = new DiGraph<String, Integer, String>();
	}
	
	/**
	 * Segundo escenario del test.
	 */
	public void setUp2(){
		diGraph.insertVertex("A", 1);
		diGraph.insertVertex("B", 2);
		diGraph.insertVertex("C", 3);
		diGraph.insertVertex("D", 4);
		diGraph.insertVertex("E", 5);
	}
	
	/**
	 * Tercer escenario del test.
	 */
	public void setUp3(){
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
	}
	
	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------
	
	/**
	 * Verifica si el vertice con id suministrado está en el grafo 
	 */
	@Test
	public void containsVertex( ){
		setUp2();
		assertTrue("Error: El grafo contiene el vertice dado", diGraph.containsVertex( "A" ));
		assertFalse("Error: El grafo no contiene el vertice dado", diGraph.containsVertex( "F" ));	

		assertTrue("Error: El grafo contiene el vertice dado", diGraph.containsVertex( "B" ));
		assertFalse("Error: El grafo no contiene el vertice dado", diGraph.containsVertex( "G" ));	

		assertTrue("Error: El grafo contiene el vertice dado", diGraph.containsVertex( "C" ));
		assertFalse("Error: El grafo no contiene el vertice dado", diGraph.containsVertex( "H" ));	
		
		assertTrue("Error: El grafo contiene el vertice dado", diGraph.containsVertex( "D" ));
		assertFalse("Error: El grafo no contiene el vertice dado", diGraph.containsVertex( "I" ));	

		assertTrue("Error: El grafo contiene el vertice dado", diGraph.containsVertex( "E" ));	
		assertFalse("Error: El grafo no contiene el vertice dado", diGraph.containsVertex( "J" ));	
	}
	
	/**
	 * Calcula el número de vertices que se encuentran en el grafo. 
	 */
	@Test
	public void numVertices( ){
		assertEquals("Error: Aún no existen vertices en el grafo", 0, diGraph.numVertices());
		setUp2();
		assertEquals("Error: El número de vértices en el grafo debería ser 5", 5, diGraph.numVertices());
	}
	
	/**
	 * Calcula el número de arcos en el grafo.
	 */
	@Test
	public void numEdges( ){
		assertEquals("Error: Aún no existen arcos en el grafo", 0, diGraph.numEdges());
		setUp3();
		assertEquals("Error: El número de arcos en el grafo debería ser 6", 6, diGraph.numEdges());		
	}
	
	/**
	 * Devuelve el arco con mayor peso en el grafo.
	 */
	@Test
	public void maxWeight( ){
		assertNull("Error: En el principio no existe ningun arco en el grafo", diGraph.maxWeight());
		setUp3();
		assertTrue("Error: El máx weight debería ser el arco edgeCE", diGraph.maxWeight().getInfo().equals("EdgeCE"));
	}
	
	/**
	 * Devuelve el arco con menor peso en el grafo.
	 */
	@Test
	public void minWeight( ){
		assertNull("Error: En el principio no existe ningun arco en el grafo", diGraph.minWeight());
		setUp3();
		assertTrue("Error: El min weight debería ser el arco edgeAB", diGraph.minWeight().getInfo().equals("EdgeAB"));
	}

	
	/**
	 * Añade un vértice al grafo con su identificador y valor.
	 */
	@Test
	public void insertVertex( ){
		diGraph.insertVertex("A", 1);
		assertTrue("Error: El vértice A no se insertó de forma correcta", diGraph.getVertex("A").equals("A"));
		diGraph.insertVertex("B", 2);
		assertTrue("Error: El vértice B no se insertó de forma correcta", diGraph.getVertex("B").equals("B"));
		diGraph.insertVertex("C", 3);
		assertTrue("Error: El vértice C no se insertó de forma correcta", diGraph.getVertex("C").equals("C"));
		diGraph.insertVertex("D", 4);
		assertTrue("Error: El vértice D no se insertó de forma correcta", diGraph.getVertex("D").equals("D"));
		diGraph.insertVertex("E", 5);
		assertTrue("Error:El vértice E no se insertó de forma correcta", diGraph.getVertex("E").equals("E"));
		assertEquals("Error: El tamaño total de vertices insertados debería ser 5", 5, diGraph.vertex().size());
	}
	
	/**
	 * Añade un arco dirigido pesado entre el vértice origen y destino con peso weight. 
	 */
	@Test
	public void addEdge( ){
		diGraph.addEdge("A", "B", 20, "EdgeAB");
		diGraph.addEdge("A", "C", 30, "EdgeAC");
		diGraph.addEdge("A", "D", 40, "EdgeAD");
		diGraph.addEdge("D", "C", 70, "EdgeDC");
		diGraph.addEdge("C", "E", 140, "EdgeCE");
		diGraph.addEdge("B", "E", 70, "EdgeBE");
	}
	
	/**
	 * Calcula el vértice a partir de su indentificador único.
	 */
	@Test
	public void getVertex( ){
		setUp2();
		assertTrue("Error: El vértice A no se obtuvó de forma correcta", diGraph.getVertex("A").equals("A"));
		assertTrue("Error: El vértice B no se obtuvó de forma correcta", diGraph.getVertex("B").equals("B"));
		assertTrue("Error: El vértice C no se obtuvó de forma correcta", diGraph.getVertex("C").equals("C"));
		assertTrue("Error: El vértice D no se obtuvó de forma correcta", diGraph.getVertex("D").equals("D"));
		assertTrue("Error: El vértice E no se obtuvó de forma correcta", diGraph.getVertex("E").equals("E"));
		assertNull("Error: El vértice desconocido no existe en el grafo", diGraph.getVertex("F"));

	}
	
	/**
	 * Calcula el arco entre los vértices idS y idD (si existe). Retorna null si no existe.
	 */
	@Test
	public void getEdges( ){
		setUp3();
		Edge<String, Integer, String> tempEdgeAB = diGraph.getEdge("A", "B");
		assertTrue("Errr: El arco resultante del vértice A hasta el vertice B es edgeAB", tempEdgeAB.getInfo().equals("EdgeAB"));
		
		Edge<String, Integer, String> tempEdgeAC = diGraph.getEdge("A", "C");
		assertTrue("Errr: El arco resultante del vértice A hasta el vertice C es edgeAC", tempEdgeAC.getInfo().equals("EdgeAC"));
		
		Edge<String, Integer, String> tempEdgeAD = diGraph.getEdge("A", "D");
		assertTrue("Errr: El arco resultante del vértice A hasta el vertice D es edgeAD", tempEdgeAD.getInfo().equals("EdgeAD"));
		
		Edge<String, Integer, String> tempEdgeDC = diGraph.getEdge("D", "C");
		assertTrue("Errr: El arco resultante del vértice D hasta el vertice C es edgeDC", tempEdgeDC.getInfo().equals("EdgeDC"));
		
		Edge<String, Integer, String> tempEdgeCE = diGraph.getEdge("C", "E");
		assertTrue("Errr: El arco resultante del vértice C hasta el vertice E es edgeCE", tempEdgeCE.getInfo().equals("EdgeCE"));
		
		Edge<String, Integer, String> tempEdgeBE = diGraph.getEdge("B", "E");
		assertTrue("Errr: El arco resultante del vértice B hasta el vertice E es edgeBE", tempEdgeBE.getInfo().equals("EdgeBE"));

	}
	
	/**
	 * Devuelve una lista de arcos adyacentes (salientes) al vértice con id. 
	 */
	@Test
	public void adjacentEdges( ){
		setUp3( );
		ArregloDinamicoGenerico< Edge<String, Integer, String> > adjOutToVertexA = diGraph.adjacentEdges("A");
		assertTrue("Error: El elemento en la posición 0 del adjOutToVertexA debe ser edgeAB", "EdgeAB".equals(adjOutToVertexA.getElemento(1).getInfo()));
		assertTrue("Error: El elemento en la posición 1 del adjOutToVertexA debe ser edgeAC", "EdgeAC".equals(adjOutToVertexA.getElemento(2).getInfo()));
		assertTrue("Error: El elemento en la posición 2 del adjOutToVertexA debe ser edgeAD", "EdgeAD".equals(adjOutToVertexA.getElemento(3).getInfo()));

		ArregloDinamicoGenerico< Edge<String, Integer, String> > adjOutToVertexB = diGraph.adjacentEdges("B");
		assertTrue("Error: El elemento en la posición 0 del adjOutToVertexB debe ser edgeBE", "EdgeBE".equals(adjOutToVertexB.getElemento(1).getInfo()));

		ArregloDinamicoGenerico< Edge<String, Integer, String> > adjOutToVertexC = diGraph.adjacentEdges("C");
		assertTrue("Error: El elemento en la posición 0 del adjOutToVertexC debe ser edgeCE", "EdgeCE".equals(adjOutToVertexC.getElemento(1).getInfo()));

		ArregloDinamicoGenerico< Edge<String, Integer, String> > adjOutToVertexD = diGraph.adjacentEdges("D");
		assertTrue("Error: El elemento en la posición 0 del adjOutToVertexD debe ser edgeDC", "EdgeDC".equals(adjOutToVertexD.getElemento(1).getInfo()));

		ArregloDinamicoGenerico< Edge<String, Integer, String> > adjOutToVertexE = diGraph.adjacentEdges("E");
		assertEquals("Error: La lista debe estar vacía", 0, adjOutToVertexE.size());
	}
	
	/**
	 * Devuelve una lista de vértices adyacentes (salientes) al vértice con id. 
	 */
	@Test
	public void adjacentVertex( ){
		setUp3( );
		ArregloDinamicoGenerico< Vertex<String, Integer, String> > adjOutToVertexA = diGraph.adjacentVertex("A");
		assertTrue("Error: El elemento en la posición 0 del adjOutToVertexA debe ser el vértice B",adjOutToVertexA.getElemento(1).equals("B"));
		assertTrue("Error: El elemento en la posición 1 del adjOutToVertexA debe ser el vértice C",adjOutToVertexA.getElemento(2).equals("C"));
		assertTrue("Error: El elemento en la posición 2 del adjOutToVertexA debe ser el vértice D",adjOutToVertexA.getElemento(3).equals("D"));

		ArregloDinamicoGenerico< Vertex<String, Integer, String> > adjOutToVertexB = diGraph.adjacentVertex("B");
		assertTrue("Error: El elemento en la posición 0 del adjOutToVertexB debe ser el vértice E",adjOutToVertexB.getElemento(1).equals("E"));
		
		ArregloDinamicoGenerico< Vertex<String, Integer, String> > adjOutToVertexC = diGraph.adjacentVertex("C");
		assertTrue("Error: El elemento en la posición 0 del adjOutToVertexB debe ser el vértice E",adjOutToVertexC.getElemento(1).equals("E"));
		
		ArregloDinamicoGenerico< Vertex<String, Integer, String> > adjOutToVertexD = diGraph.adjacentVertex("D");
		assertTrue("Error: El elemento en la posición 0 del adjOutToVertexB debe ser el vértice C",adjOutToVertexD.getElemento(1).equals("C"));
		
		ArregloDinamicoGenerico< Vertex<String, Integer, String> > adjOutToVertexE = diGraph.adjacentVertex("E");
		assertEquals("Error: La lista debe estar vacía", 0, adjOutToVertexE.size());
	}
	
	/**
	 * Calcula el grado de entrada del vertice vertex (número de arcos entrantes).
	 */
	@Test
	public void indegree ( ){
		setUp3();
		assertEquals("Error: El grado de entrada del vértice de ID A debe ser 0", 0, diGraph.indegree("A"));
		assertEquals("Error: El grado de entrada del vértice de ID B debe ser 1", 1, diGraph.indegree("B"));
		assertEquals("Error: El grado de entrada del vértice de ID C debe ser 2", 2, diGraph.indegree("C"));
		assertEquals("Error: El grado de entrada del vértice de ID D debe ser 1", 1, diGraph.indegree("D"));
		assertEquals("Error: El grado de entrada del vértice de ID E debe ser 2", 2, diGraph.indegree("E"));
	}
	
	/**
	 * Calcula el grado de salida del vertice vertex (número de vértices entrantes).
	 */
	@Test
	public void outdegree ( ){
		setUp3();
		assertEquals("Error: El grado de salida del vértice de ID A debe ser 3", 3, diGraph.outdegree("A"));
		assertEquals("Error: El grado de salida del vértice de ID B debe ser 1", 1, diGraph.outdegree("B"));
		assertEquals("Error: El grado de salida del vértice de ID C debe ser 1", 1, diGraph.outdegree("C"));
		assertEquals("Error: El grado de salida del vértice de ID D debe ser 1", 1, diGraph.outdegree("D"));
		assertEquals("Error: El grado de salida del vértice de ID E debe ser 0", 0, diGraph.outdegree("E"));
	}
	
	/**
	 * Devuelve una lista de todos los arcos del grafo. 
	 */
	@Test
	public void edges( ){
		setUp3();
		ArregloDinamicoGenerico<Edge<String, Integer, String>> edgeInGraph = diGraph.edges();
		assertTrue("Error: El elemento en la posición 0 del edgeInGraph debe ser edgeAB", "EdgeAB".equals(edgeInGraph.getElemento(1).getInfo()));
		assertTrue("Error: El elemento en la posición 1 del edgeInGraph debe ser edgeAC", "EdgeAC".equals(edgeInGraph.getElemento(2).getInfo()));
		assertTrue("Error: El elemento en la posición 2 del edgeInGraph debe ser edgeAD", "EdgeAD".equals(edgeInGraph.getElemento(3).getInfo()));
		assertTrue("Error: El elemento en la posición 3 del edgeInGraph debe ser edgeDC", "EdgeDC".equals(edgeInGraph.getElemento(4).getInfo()));
		assertTrue("Error: El elemento en la posición 4 del edgeInGraph debe ser edgeCE", "EdgeCE".equals(edgeInGraph.getElemento(5).getInfo()));
		assertTrue("Error: El elemento en la posición 5 del edgeInGraph debe ser edgeBE", "EdgeBE".equals(edgeInGraph.getElemento(6).getInfo()));
	}
	
	/**
	 * Devuelve una lista de todos los vertices del grafo. 
	 */
	@Test
	public void vertex( ){
		setUp3();
		ArregloDinamicoGenerico<Vertex<String, Integer, String>> vertexInGraph = diGraph.vertex();
		assertTrue("Error: El elemento en la posición 0 del vertexInGraph debe ser el vértice A", "A".equals(vertexInGraph.getElemento(1).getId()));
		assertTrue("Error: El elemento en la posición 1 del vertexInGraph debe ser el vértice B", "B".equals(vertexInGraph.getElemento(2).getId()));
		assertTrue("Error: El elemento en la posición 2 del vertexInGraph debe ser el vértice C", "C".equals(vertexInGraph.getElemento(3).getId()));
		assertTrue("Error: El elemento en la posición 3 del vertexInGraph debe ser el vértice D", "D".equals(vertexInGraph.getElemento(4).getId()));
		assertTrue("Error: El elemento en la posición 4 del vertexInGraph debe ser el vértice E", "E".equals(vertexInGraph.getElemento(5).getId()));

	}

	/**
	 * Calcula el nuevo max weight y min weight de los arcos en el grafo.
	 */
	@Test
	public void actualizarMaxMinWeight( ){
		assertNull("Error: En el principio no existe ningun arco en el grafo", diGraph.maxWeight());
		assertNull("Error: En el principio no existe ningun arco en el grafo", diGraph.minWeight());
		setUp3();
		assertTrue("Error: El máx weight debería ser el arco edgeCE", diGraph.maxWeight().getInfo().equals("EdgeCE"));
		assertTrue("Error: El min weight debería ser el arco edgeAB", diGraph.minWeight().getInfo().equals("EdgeAB"));
	}
	
	/**
	 * Se encarga de invertir el grafo presente. 
	 */
	@Test
	public void reverse(){
		setUp3();
		@SuppressWarnings("unchecked")
		DiGraph<String, Integer, String> n = diGraph.reverse();
		assertNotNull("El arco AB debería tener de vértice de origen B y de llegada A", n.getEdge("B", "A"));
		assertNotNull("El arco AB debería tener de vértice de origen E y de llegada B", n.getEdge("E", "B"));
		assertNotNull("El arco AB debería tener de vértice de origen C y de llegada A", n.getEdge("C", "A"));
		assertNotNull("El arco AB debería tener de vértice de origen E y de llegada C", n.getEdge("E", "C"));
		assertNotNull("El arco AB debería tener de vértice de origen C y de llegada D", n.getEdge("C", "D"));
		assertNotNull("El arco AB debería tener de vértice de origen D y de llegada A", n.getEdge("D", "A"));
	}
}