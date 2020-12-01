/**
 * 2020-11-17
 * Clase TestDirectedGraph.
 * @author Julián Andrés Méndez
 * @author Juan Miguel Vega Caro
 */

package test.testDirectedGraph;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.structures.directedGraph.Edge;
import model.structures.directedGraph.Vertex;
import model.structures.listaGenerica.ArregloDinamicoGenerico;

/**
 * Test de la estructura de datos de grafo dirigido.
 */
public class TestVertex {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	
	/**
	 * Representa el vértice A, vértice B, vértice C, vértice, D y el vértice E respectivamente.
	 */
	private Vertex<String, Integer, String> vertexA, vertexB, vertexC, vertexD, vertexE;

	/**
	 * Representa el Arco de AB, Arco de AC, Arco de AD, Arco de DC, Arco de CE y el Arco de BE respectivamente.
	 */
	private Edge<String, Integer, String> edgeAB, edgeAC, edgeAD, edgeDC, edgeCE, edgeBE;
	
	// -----------------------------------------------------------------
	// Escenarios
	// -----------------------------------------------------------------
	
	/**
	 * Primer escenario del test.
	 */
	@Before
	public void setUp1() {
		vertexA = new Vertex<String, Integer, String>( "A", 1);
		vertexB = new Vertex<String, Integer, String>( "B", 2);
		vertexC = new Vertex<String, Integer, String>( "C", 3);
		vertexD = new Vertex<String, Integer, String>( "D", 4);
		vertexE = new Vertex<String, Integer, String>( "E", 5);

		edgeAB = new Edge<String, Integer, String>(vertexA, vertexB, 20.0, "EdgeAB");
		edgeAC = new Edge<String, Integer, String>(vertexA, vertexC, 30.0, "EdgeAC");
		edgeAD = new Edge<String, Integer, String>(vertexA, vertexD, 40.0, "EdgeAD");
		edgeDC = new Edge<String, Integer, String>(vertexD, vertexC, 70.0, "EdgeDC");
		edgeCE = new Edge<String, Integer, String>(vertexC, vertexE, 140.0, "EdgeCE");
		edgeBE = new Edge<String, Integer, String>(vertexB, vertexE, 70.0, "EdgeBE");
	}
	
	/**
	 * Segundo escenario del test.
	 */
	public void setUp2(){
		vertexA.addEddge(edgeAB);
		vertexA.addEddge(edgeAC);
		vertexA.addEddge(edgeAD);
		vertexD.addEddge(edgeDC);
		vertexC.addEddge(edgeCE);
		vertexB.addEddge(edgeBE);

		vertexB.addEddge(edgeAB);
		vertexC.addEddge(edgeAC);
		vertexD.addEddge(edgeAD);
		vertexC.addEddge(edgeDC);
		vertexE.addEddge(edgeCE);
		vertexE.addEddge(edgeBE);
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------
	
	/**
	 * Calcula el identificador del vértice.
	 */
	@Test
	public void getId( ){
		assertEquals("Error: El identificador del vértice A: A", "A", vertexA.getId());
		assertEquals("Error: El identificador del vértice B: B", "B", vertexB.getId());
		assertEquals("Error: El identificador del vértice C: C", "C", vertexC.getId());
		assertEquals("Error: El identificador del vértice D: D", "D", vertexD.getId());
		assertEquals("Error: El identificador del vértice E: E", "E", vertexE.getId());
	}
	
	/**
	 * Busca el valor asociado al vértice.
	 */
	@Test
	public void getValue( ){
		assertTrue("Error: El valor del vértice A es 1", 1 == vertexA.getValue());
		assertTrue("Error: El valor del vértice B es 2", 2 == vertexB.getValue());
		assertTrue("Error: El valor del vértice C es 3", 3 == vertexC.getValue());
		assertTrue("Error: El valor del vértice D es 4", 4 == vertexD.getValue());
		assertTrue("Error: El valor del vértice E es 5", 5 == vertexE.getValue());
	}
	/**
	 * Verifica si el vértice se encuentra marcado o no.
	 */
	@Test
	public void getMark( ){
		assertFalse("Error: El valor inicial de marcado del vertice A debe ser falso", vertexA.getMark());
		assertFalse("Error: El valor inicial de marcado del vertice B debe ser falso", vertexA.getMark());
		assertFalse("Error: El valor inicial de marcado del vertice C debe ser falso", vertexA.getMark());
		assertFalse("Error: El valor inicial de marcado del vertice D debe ser falso", vertexA.getMark());
		assertFalse("Error: El valor inicial de marcado del vertice E debe ser falso", vertexA.getMark());
	}
	
	/**
	 * Agrega un arco adyacente al vértice.
	 */
	@Test
	public void addEdge( ){
		vertexA.addEddge(edgeAB);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexA.edgesOut().isPresent(edgeAB));
		vertexA.addEddge(edgeAC);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexA.edgesOut().isPresent(edgeAC));
		vertexA.addEddge(edgeAD);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexA.edgesOut().isPresent(edgeAD));
		vertexD.addEddge(edgeDC);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexD.edgesOut().isPresent(edgeDC));
		vertexC.addEddge(edgeCE);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexC.edgesOut().isPresent(edgeCE));
		vertexB.addEddge(edgeBE);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexB.edgesOut().isPresent(edgeBE));
	
		vertexB.addEddge(edgeAB);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexB.edgesIn().isPresent(edgeAB));
		vertexC.addEddge(edgeAC);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexC.edgesIn().isPresent(edgeAC));
		vertexD.addEddge(edgeAD);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexD.edgesIn().isPresent(edgeAD));
		vertexC.addEddge(edgeDC);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexC.edgesIn().isPresent(edgeDC));
		vertexE.addEddge(edgeCE);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexE.edgesIn().isPresent(edgeCE));
		vertexE.addEddge(edgeBE);
		assertNotEquals("Error: El arco no se encuentra en la lista de adyacencia correcta: ", -1, vertexE.edgesIn().isPresent(edgeBE));
	}
	
	/**
	 * Marca el vértice.
	 */
	@Test
	public void mark( ){
		vertexA.mark();
		assertFalse("Error: El valor del marcado del vértice A debería actualizarce a true", !vertexA.getMark());
		
		vertexB.mark();
		assertFalse("Error: El valor del marcado del vértice B debería actualizarce a true", !vertexB.getMark());
		
		vertexC.mark();
		assertFalse("Error: El valor del marcado del vértice C debería actualizarce a true", !vertexC.getMark());
		
		vertexD.mark();
		assertFalse("Error: El valor del marcado del vértice D debería actualizarce a true", !vertexD.getMark());
		
		vertexE.mark();
		assertFalse("Error: El valor del marcado del vértice E debería actualizarce a true", !vertexE.getMark());
	}
	
	/**
	 * Desmarca el vértice.
	 */
	@Test
	public void unmark( ){
		vertexA.unmark();
		assertFalse("Error: El valor del marcado del vértice A debería actualizarce a false", vertexA.getMark());
		
		vertexB.unmark();
		assertFalse("Error: El valor del marcado del vértice B debería actualizarce a false", vertexB.getMark());
		
		vertexC.unmark();
		assertFalse("Error: El valor del marcado del vértice C debería actualizarce a false", vertexC.getMark());
		
		vertexD.unmark();
		assertFalse("Error: El valor del marcado del vértice D debería actualizarce a false", vertexD.getMark());
		
		vertexE.unmark();
		assertFalse("Error: El valor del marcado del vértice E debería actualizarce a false", vertexE.getMark());
	}
	
	/**
	 * Calcula el número de arcos (salientes) del vértice.
	 * @return Retorna número de arcos (salientes) del vértice.
	 */
	@Test
	public void outDegree( ){
		setUp2();
		assertEquals("Error: El número de arcos salientes del vértice A debería ser 3", 3, vertexA.outDegree());
		assertEquals("Error: El número de arcos salientes del vértice B debería ser 1", 1, vertexB.outDegree());
		assertEquals("Error: El número de arcos salientes del vértice C debería ser 1", 1, vertexC.outDegree());
		assertEquals("Error: El número de arcos salientes del vértice D debería ser 1", 1, vertexD.outDegree());
		assertEquals("Error: El número de arcos salientes del vértice E debería ser 0", 0, vertexE.outDegree());
	}
	
	/**
	 * Calcula el número de arcos (entrantes) del vértice.
	 */
	@Test
	public void InDegree( ){
		setUp2();
		assertEquals("Error: El número de arcos entrantes del vértice A debería ser 0", 0, vertexA.inDegree());
		assertEquals("Error: El número de arcos entrantes del vértice B debería ser 1", 1, vertexB.inDegree());
		assertEquals("Error: El número de arcos entrantes del vértice C debería ser 2", 2, vertexC.inDegree());
		assertEquals("Error: El número de arcos entrantes del vértice D debería ser 1", 1, vertexD.inDegree());
		assertEquals("Error: El número de arcos entrantes del vértice E debería ser 2", 2, vertexE.inDegree());
	}
	
	/**
	 * Calcula el arco con el vértice vertex (si existe). Retorna null si no existe.
	 */
	@Test
	public void getEdge( ){
		setUp2();
		assertSame("Error: El arco que conduce al vértice B desde el vértice A debería ser edgeAB", edgeAB, vertexA.getEdge(vertexB.getId()));
		assertSame("Error: El arco que conduce al vértice C desde el vértice A debería ser edgeAC", edgeAC, vertexA.getEdge(vertexC.getId()));
		assertSame("Error: El arco que conduce al vértice D desde el vértice A debería ser edgeAD", edgeAD, vertexA.getEdge(vertexD.getId()));
		assertSame("Error: El arco que conduce al vértice C desde el vértice D debería ser edgeDC", edgeDC, vertexD.getEdge(vertexC.getId()));
		assertSame("Error: El arco que conduce al vértice E desde el vértice C debería ser edgeCE", edgeCE, vertexC.getEdge(vertexE.getId()));
		assertSame("Error: El arco que conduce al vértice E desde el vértice B debería ser edgeBE", edgeBE, vertexB.getEdge(vertexE.getId()));
	}
	
	/**
	 * Devuelve una lista con sus vértices adyacentes (salientes).
	 */
	@Test
	public void vertexOut( ){
		setUp2();
		ArregloDinamicoGenerico<Vertex <String, Integer, String> > vertexOutA = vertexA.vertexOut();
		assertNotEquals( "Error: El vértice saliente B debería estar en la lista de vértices adyacentes salientes del vértice A", -1, vertexOutA.isPresent(vertexB));
		assertNotEquals( "Error: El vértice saliente C debería estar en la lista de vértices adyacentes salientes del vértice A", -1, vertexOutA.isPresent(vertexC));         
		assertNotEquals( "Error: El vértice saliente D debería estar en la lista de vértices adyacentes salientes del vértice A", -1, vertexOutA.isPresent(vertexD));

		ArregloDinamicoGenerico<Vertex <String, Integer, String> > vertexOutB = vertexB.vertexOut();
		assertNotEquals( "Error: El vértice saliente E debería estar en la lista de vértices adyacentes salientes del vértice B", -1, vertexOutB.isPresent(vertexE));
		
		ArregloDinamicoGenerico<Vertex <String, Integer, String> > vertexOutC = vertexC.vertexOut();
		assertNotEquals( "Error: El vértice saliente E debería estar en la lista de vértices adyacentes salientes del vértice C", -1, vertexOutC.isPresent(vertexE));
		
		ArregloDinamicoGenerico<Vertex <String, Integer, String> > vertexOutD = vertexD.vertexOut();
		assertNotEquals( "Error: El vértice saliente C debería estar en la lista de vértices adyacentes salientes del vértice D", -1, vertexOutD.isPresent(vertexC));
		
		ArregloDinamicoGenerico<Vertex <String, Integer, String> > vertexOutE = vertexE.vertexOut();
		assertTrue( "Error: La lista de vértices adyacentes salientes del vértice D debería estar vacía", vertexOutE.isEmpty());
	}
	
	/**
	 * Devuelve una lista con sus arcos adyacentes (salientes).
	 */
	@Test
	public void edgesOut( ){
		setUp2();
		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesOutA = vertexA.edgesOut();
		assertNotEquals( "Error: El arco edgeAB debería estar en la lista de arcos adyacentes salientes del vértice A", -1, edgesOutA.isPresent(edgeAB));
		assertNotEquals( "Error: El arco edgeAC debería estar en la lista de arcos adyacentes salientes del vértice A", -1, edgesOutA.isPresent(edgeAC));         
		assertNotEquals( "Error: El arco edgeAD debería estar en la lista de arcos adyacentes salientes del vértice A", -1, edgesOutA.isPresent(edgeAB));
		
		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesOutB = vertexB.edgesOut();
		assertNotEquals( "Error: El arco edgeBC debería estar en la lista de arcos adyacentes salientes del vértice B", -1, edgesOutB.isPresent(edgeBE));
		
		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesOuC = vertexC.edgesOut();
		assertNotEquals( "Error: El arco edgeCE debería estar en la lista de arcos adyacentes salientes del vértice C", -1, edgesOuC.isPresent(edgeCE));
		
		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesOutD = vertexD.edgesOut();
		assertNotEquals( "Error: El arco edgeDC debería estar en la lista de arcos adyacentes salientes del vértice D", -1, edgesOutD.isPresent(edgeDC));
		
		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesOutE = vertexE.edgesOut();
		assertTrue( "Error: La lista de arcos adyacentes salientes del vértice E debería estar vacía", edgesOutE.isEmpty());
	}
	
	/**
	 * Devuelve una lista con sus arcos adyacentes (entrantes).
	 */
	public void edgesIn(){
		setUp2();
		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesInA = vertexA.edgesIn();
		assertTrue( "Error: La lista de arcos adyacentes entrantes del vértice A debería estar vacía", edgesInA.isEmpty());

		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesInB = vertexB.edgesIn();
		assertNotEquals( "Error: El arco edgeAB debería estar en la lista de arcos adyacentes entrantes del vértice B", -1, edgesInB.isPresent(edgeAB));

		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesInC = vertexC.edgesIn();
		assertNotEquals( "Error: El arco edgeAC debería estar en la lista de arcos adyacentes entrantes del vértice C", -1, edgesInC.isPresent(edgeAC));

		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesInD = vertexD.edgesIn();
		assertNotEquals( "Error: El arco edgeAD debería estar en la lista de arcos adyacentes entrantes del vértice D", -1, edgesInD.isPresent(edgeAC));

		ArregloDinamicoGenerico<Edge <String, Integer, String> > edgesInE = vertexE.edgesIn();
		assertNotEquals( "Error: El arco edgeCE debería estar en la lista de arcos adyacentes entrantes del vértice E", -1, edgesInE.isPresent(edgeAC));
	}
	
	/**
	 * Compara un vértice con otro dado por parámatro.
	 */
	public void equals(){
		assertTrue("Error: El vertice A es igual al vertice A", vertexA.equals(vertexA.getId()));
		assertFalse("Error: El vertice A es diferente al vertice B", vertexA.equals(vertexB.getId()));
		assertTrue("Error: El vertice B es igual al vertice B", vertexB.equals(vertexB.getId()));
		assertFalse("Error: El vertice B es diferente al vertice A", vertexB.equals(vertexA.getId()));
	}
}