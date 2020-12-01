package test.testAlgorithms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.structures.algorithms.DepthFirstOrder;
import model.structures.algorithms.DijkstraSP;
import model.structures.binarySearchTree.BinarySearchTree;
import model.structures.binarySearchTree.BinarySearchTree.NodoBinario;
import model.structures.directedGraph.DiGraph;
import model.structures.directedGraph.Edge;
import model.structures.directedGraph.Vertex;
import model.structures.listaGenerica.ArregloDinamicoGenerico;

public class TestDijkstraSP {

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
	private DijkstraSP<String, Integer, String> dijkstraSP;

	// -----------------------------------------------------------------
	// Escenarios
	// -----------------------------------------------------------------

	/**
	 * Primer escenario del test.
	 */
	public void setUp1() {
		diGraph = new DiGraph<String, Integer, String>();
		diGraph.insertVertex("A", 1);
		diGraph.insertVertex("B", 2);
		diGraph.insertVertex("C", 3);
		diGraph.insertVertex("D", 4);
		diGraph.insertVertex("E", 5);

		diGraph.addEdge("A", "B", 20, "EdgeAB");
		diGraph.addEdge("A", "C", 30, "EdgeAC");
		diGraph.addEdge("A", "D", 110, "EdgeAD");
		diGraph.addEdge("C", "D", 70, "EdgeCD");
		diGraph.addEdge("C", "E", 140, "EdgeCE");
		diGraph.addEdge("B", "E", 70, "EdgeBE");

		dijkstraSP = new DijkstraSP<String, Integer, String>(diGraph, diGraph.getVertex("A"));
	}
	
	/**
	 * Segundo escenario del test.
	 */
	public void setUp2() {
		diGraph = new DiGraph<String, Integer, String>();
		diGraph.insertVertex("A", 1);
		diGraph.insertVertex("B", 2);
		diGraph.insertVertex("C", 3);
		diGraph.insertVertex("D", 4);
		diGraph.insertVertex("E", 5);

		diGraph.addEdge("A", "B", 1, "EdgeAB");
		diGraph.addEdge("A", "C", 30, "EdgeAC");
		diGraph.addEdge("A", "D", 40, "EdgeAD");
		diGraph.addEdge("C", "D", 1, "EdgeCD");
		diGraph.addEdge("E", "C", 1, "EdgeEC");
		diGraph.addEdge("B", "E", 1, "EdgeBE");

		dijkstraSP = new DijkstraSP<String, Integer, String>(diGraph, diGraph.getVertex("A"));
	}
	
	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------
	
	/**
	 * Se encarga de actualizar la ruta más corta dado un grafo y un vertice,
	 */
	@Test
	public void relax1( ){
		setUp1();
		assertTrue("Error: La ruta más corta desde A a B debió ser el nodo con info AB", dijkstraSP.getDistTo().getElemento(2).getEdgeFrontPath().getInfo().equals("EdgeAB"));
		assertTrue("Error: La ruta más corta desde A a C debió ser el nodo con info AC", dijkstraSP.getDistTo().getElemento(3).getEdgeFrontPath().getInfo().equals("EdgeAC"));
		assertTrue("Error: La ruta más corta desde A a D debió ser el nodo con info CD", dijkstraSP.getDistTo().getElemento(4).getEdgeFrontPath().getInfo().equals("EdgeCD"));
		assertTrue("Error: La ruta más corta desde A a E debió ser el nodo con info BE", dijkstraSP.getDistTo().getElemento(5).getEdgeFrontPath().getInfo().equals("EdgeBE"));
	}
	
	/**
	 * Se encarga de actualizar la ruta más corta dado un grafo y un vertice,
	 */
	@Test
	public void relax2(){
		setUp2();
		assertTrue("Error: La ruta más corta desde A a B debió ser el nodo con info AB", dijkstraSP.getDistTo().getElemento(2).getEdgeFrontPath().getInfo().equals("EdgeAB"));
		assertTrue("Error: La ruta más corta desde A a C debió ser el nodo con info AC", dijkstraSP.getDistTo().getElemento(3).getEdgeFrontPath().getInfo().equals("EdgeEC"));
		assertTrue("Error: La ruta más corta desde A a D debió ser el nodo con info CD", dijkstraSP.getDistTo().getElemento(4).getEdgeFrontPath().getInfo().equals("EdgeCD"));
		assertTrue("Error: La ruta más corta desde A a E debió ser el nodo con info BE", dijkstraSP.getDistTo().getElemento(5).getEdgeFrontPath().getInfo().equals("EdgeBE"));

	}
	
	/**
	 * Calcula la ruta más rapida dado un vestice origen y un destino.
	 */
	@Test
	public void minPath1( ) {
		Vertex<String, Integer, String> vSubA, vSubD;
		ArregloDinamicoGenerico<Edge<String, Integer, String>> arr;
		setUp1();
		vSubA = dijkstraSP.getDistTo().getElemento(1);
		vSubD = dijkstraSP.getDistTo().getElemento(4);
		
		arr = dijkstraSP.minPath(vSubA, vSubD);
		assertTrue("La posición del arco con info AC es incorrecta", arr.getElemento(1).getInfo().equals("EdgeAC"));
		assertTrue("La posición del arco con info CD es incorrecta", arr.getElemento(2).getInfo().equals("EdgeCD"));
	}
	
	@Test
	public void minPath2(){
		setUp2();
		Vertex<String, Integer, String> vSubA, vSubD;
		ArregloDinamicoGenerico<Edge<String, Integer, String>> arr;
		setUp2();
		vSubA = dijkstraSP.getDistTo().getElemento(1);
		vSubD = dijkstraSP.getDistTo().getElemento(4);
		
		arr = dijkstraSP.minPath(vSubA, vSubD);
		assertTrue("La posición del arco con info AB es incorrecta", arr.getElemento(1).getInfo().equals("EdgeAB"));
		assertTrue("La posición del arco con info BE es incorrecta", arr.getElemento(2).getInfo().equals("EdgeBE"));
		assertTrue("La posición del arco con info EC es incorrecta", arr.getElemento(3).getInfo().equals("EdgeEC"));
		assertTrue("La posición del arco con info CD es incorrecta", arr.getElemento(4).getInfo().equals("EdgeCD"));
	}
}
