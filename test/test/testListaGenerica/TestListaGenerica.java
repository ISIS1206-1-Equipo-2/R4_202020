/**
 * 2020-11-17
 * Clase TestListaGenerica.
 * @author Julián Andrés Méndez
 * @author Juan Miguel Vega Caro
 */

package test.testListaGenerica;
import model.structures.listaGenerica.ArregloDinamicoGenerico;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test de la estructura de datos arreglo dinámico generico.
 */
public class TestListaGenerica {
	
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------
	
	/**
	 * Representa el tamaño incial del arreglo dinámico generico.
	 */
	private static int TAMANO=100;
	
	
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	
	/**
	 * Representa la estructura de datos arreglo dinámico generico.
	 */
	private ArregloDinamicoGenerico<String> arreglo;
	
	// -----------------------------------------------------------------
	// Escenarios
	// -----------------------------------------------------------------	
	
	/**
	 * Primer escenario del test.
	 */
	@Before
	public void setUp1() {
		arreglo= new ArregloDinamicoGenerico<String>(TAMANO);
	}

	/**
	 * Segundo escenario del test.
	 */
	public void setUp2() {
		for(int i =0; i< TAMANO*2; i++){
			arreglo.addLast(""+i);
		}
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------	
	
	/**
	 * Verifica que el arreglo sea diferente de null y que su tamaño sea igual a cero.
	 */
	@Test
	public void testArregloDinamico() {
		assertTrue(arreglo!=null);
		assertEquals(0, arreglo.size());
		
	}

	/**
	 * Agrega un elemento al principio del arreglo.
	 */
	@Test
	public void addFirst() {
		setUp2();
		for(int i = 200; i<299; i++)
		{
			String first = ""+i;
			arreglo.addFirst(first);
			assertEquals("El elemento es incorrecto",1, arreglo.isPresent(first));
		}
	}
	
	/**
	 * Agrega un elemento al final del arreglo.
	 */
	@Test
	public void addLast() {
		setUp2();
		for(int i = 200; i<299; i++)
		{
			String last = ""+i;
			arreglo.addLast(last);
			assertEquals("El elemento es incorrecto", arreglo.size(), arreglo.isPresent(last));
		}
	}
	
	/**
	 * Agrega un elemento en una posición válida.
	 */
	@Test
	public void insertElement() {
		setUp2();
		arreglo.insertElement("200", 1);
		assertEquals("El elemento encontrado no coincide", 1, arreglo.isPresent("200"));
		assertEquals("La lista fallo al correr la posición", 2, arreglo.isPresent("0"));

		arreglo.insertElement("201", 4);
		assertEquals("La lista fallo al correr la posición", 3, arreglo.isPresent("1"));
		assertEquals("El elemento encontrado no coincide", 4, arreglo.isPresent("201"));
		assertEquals("La lista fallo al correr la posición", 5, arreglo.isPresent("2"));
	}
	
	/**
	 * Elimina el primer elemento en el arreglo.
	 */
	@Test
	public void removefirst() {
		setUp2();
		String e = (String) arreglo.removefirst();
		assertEquals("El elemento se elimino incorrectamente",-1, arreglo.isPresent(e));
		assertEquals("Las posiciones están corridas incorrectamente", arreglo.isPresent("1"), 1);
	}
	
	/**
	 * Elimina el último elemento en el arreglo.
	 */
	@Test
	public void removeLast() {
		setUp2();
		String e = (String) arreglo.removefirst();
		assertEquals("El último elemento se eliminó incorrectamente",-1, arreglo.isPresent(e));
	}
	
	/**
	 * Retorna el primer elemento del arreglo.
	 */
	@Test
	public void firstElement() {
		setUp2();
		assertEquals("No retornó el primer elemento", "0", arreglo.firstElement());
	}
	
	/**
	 * Retorna el último elemento del arreglo.
	 */
	@Test
	public void lastElement() {
		setUp2();
		assertEquals("No retornó el último elemento", "199", arreglo.lastElement());
	}
	
 	/**
 	 * Busca el elemento que se encuentra en la posición dada.
 	 */
	@Test
	public void getElemento() {
		setUp2();
		for( int i = 1; i<50; i++)
		{
			int indice = i-1;
			assertEquals("El elemento no coincide",arreglo.getElemento(i),""+indice);
		}
	}
	
	/**
	 * Retorna el tamaño del arreglo.
	 */
	@Test
	public void size() {
		setUp2();
		assertEquals("El tamaño no coincide",200, arreglo.size() );
	}
	
	/**
	 * Verifica si el arreglo está vacío.
	 */
	@Test
	public void isEmpty() {
		assertTrue("El arreglo se encuentra vacío", arreglo.isEmpty());
		setUp2();
		assertFalse("El arreglo no se encuentra vacío", arreglo.isEmpty());
	}

	/**
	 * Busca un elemento y retorna su posición.
	 */
	@Test
	public void isPresent() {
		setUp2();
		for(int i = 0; i < arreglo.size(); i++){
			assertEquals("La posición no coincide", Integer.toString(i+1), Integer.toString(arreglo.isPresent(Integer.toString(i))));
		}
	}
	
	 /**
	  * Intercambia dos elementos del arreglo.
	  */
	@Test
	public void exchange() {
		setUp2();
		String elem1 = (String)arreglo.getElemento(1);
		String elem2 =(String) arreglo.getElemento(3);
		arreglo.exchange(1, 3);
		assertEquals("El intercambio no se ejecutó correctamente",elem1, arreglo.getElemento(3));
		assertEquals("El intercambio no se ejecutó correctamente", elem2, arreglo.getElemento(1));
	}
	
	 /**
	  * Cambia un elemento dada una posición valida en el arreglo.
	  */
	@Test
	public void changeInfo() {
		setUp2();
		arreglo.changeInfo(4,""+3);
		assertEquals("El cambio del elemento falló", arreglo.getElemento(4),""+3);
	}



}