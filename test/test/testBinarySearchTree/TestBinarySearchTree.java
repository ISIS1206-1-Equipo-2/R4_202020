package test.testBinarySearchTree;

import model.structures.listaGenerica.ArregloDinamicoGenerico;
import model.structures.binarySearchTree.BinarySearchTree;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

public class TestBinarySearchTree {

	/**
	 * Tabla de separate Chaining
	 */
	private BinarySearchTree <String, Integer>BinarySearchTree;

	@Before
	public void setUp1() {
		BinarySearchTree = new BinarySearchTree();
	}

	public void setUp2() {
		BinarySearchTree.put("a", 1);
		BinarySearchTree.put("b", 2);
		BinarySearchTree.put("c", 3);
		BinarySearchTree.put("d", 4);
		BinarySearchTree.put("e", 5);
		BinarySearchTree.put("f", 6);
		BinarySearchTree.put("g", 7);
		BinarySearchTree.put("h", 8);
		BinarySearchTree.put("i", 9);
		BinarySearchTree.put("j", 10);
		BinarySearchTree.put("k", 11);
		BinarySearchTree.put("l", 12);
		BinarySearchTree.put("m", 13);
		BinarySearchTree.put("n", 14);
		BinarySearchTree.put("o", 15);
		BinarySearchTree.put("p", 16);
		BinarySearchTree.put("q", 17);
		BinarySearchTree.put("r", 18);
		BinarySearchTree.put("s", 19);
		BinarySearchTree.put("t", 20);
		BinarySearchTree.put("u", 21);
		BinarySearchTree.put("v", 22);
		BinarySearchTree.put("w", 23);
		BinarySearchTree.put("x", 24);
		BinarySearchTree.put("y", 25);
		BinarySearchTree.put("z", 26);
	}

	public void setUp3() {
		BinarySearchTree.put("a", 1);
		BinarySearchTree.put("a", 2);
		BinarySearchTree.put("a", 3);
		BinarySearchTree.put("d", 4);
		BinarySearchTree.put("e", 5);
		BinarySearchTree.put("f", 6);
		BinarySearchTree.put("g", 7);
		BinarySearchTree.put("g", 8);
		BinarySearchTree.put("i", 9);
		BinarySearchTree.put("j", 10);
		BinarySearchTree.put("k", 11);
		BinarySearchTree.put("l", 12);
		BinarySearchTree.put("l", 13);
		BinarySearchTree.put("l", 14);
		BinarySearchTree.put("o", 15);
		BinarySearchTree.put("p", 16);
		BinarySearchTree.put("p", 17);
		BinarySearchTree.put("r", 18);
		BinarySearchTree.put("s", 19);
		BinarySearchTree.put("t", 20);
		BinarySearchTree.put("u", 21);
		BinarySearchTree.put("v", 22);
		BinarySearchTree.put("v", 23);
		BinarySearchTree.put("v", 24);
		BinarySearchTree.put("y", 25);
		BinarySearchTree.put("z", 26);
	}
	
	public void setUp4() {
		BinarySearchTree.put("b", 1);
		BinarySearchTree.put("a", 2);
		BinarySearchTree.put("c", 3);
	}
	

	/**
	 * Se encarga de verificar que el tamaño del set up sea el correcto (prueba 1 - Elementos sin repetir).
	 * Retorna el número de parejas [llave, valor] del árbol.
	 */
	@Test
	public void size1(){
		assertSame("El valor no corresponde al tamaño del árbol",0,  BinarySearchTree.size());
		setUp2();
		assertSame("El valor no corresponde al tamaño del árbol", 26, BinarySearchTree.size());
	}

	/**
	 * Se encarga de verificar que el tamaño del set up sea el correcto (prueba 2 - Elementos repetidos). 
	 * Retorna el número de parejas [llave, valor] del árbol.
	 */
	@Test
	public void size2(){
		setUp3();
		assertSame("El valor no corresponde al tamaño del árbol", 18, BinarySearchTree.size());
	}

	/**
	 * Se encarga de verificar si el árbol se encuentra vacío o no.
	 * Retorna un booleano con base a si el arbol se encuentra vacío.
	 */
	@Test
	public void isEmpty(){
		assertTrue("El árbol se encuentra vacío", BinarySearchTree.isEmpty());
		setUp2();
		assertFalse("El árbol se encuentra vacío", BinarySearchTree.isEmpty());
	}

	/**
	 * Se encarga de verificar si la el valor asociado a una llave dada es el mismo en el setUp (prueba 1 - Elementos sin repetir).
	 * Retorna el valor V asociado a la llave key dada. Si la llave no se encuentra se retorna null.
	 */
	@Test
	public void get1(){
		setUp2();
		assertSame("El valor registrado es difente al valor esperado", 1, BinarySearchTree.get("a"));
		assertSame("El valor registrado es difente al valor esperado", 2, BinarySearchTree.get("b"));
		assertSame("El valor registrado es difente al valor esperado", 3, BinarySearchTree.get("c"));
		assertSame("El valor registrado es difente al valor esperado", 4, BinarySearchTree.get("d"));
		assertSame("El valor registrado es difente al valor esperado", 5, BinarySearchTree.get("e"));
		assertSame("El valor registrado es difente al valor esperado", 6, BinarySearchTree.get("f"));
		assertSame("El valor registrado es difente al valor esperado", 7, BinarySearchTree.get("g"));
		assertSame("El valor registrado es difente al valor esperado", 8, BinarySearchTree.get("h"));
		assertSame("El valor registrado es difente al valor esperado", 9, BinarySearchTree.get("i"));
		assertSame("El valor registrado es difente al valor esperado", 10, BinarySearchTree.get("j"));
		assertSame("El valor registrado es difente al valor esperado", 11, BinarySearchTree.get("k"));
		assertSame("El valor registrado es difente al valor esperado", 12, BinarySearchTree.get("l"));
		assertSame("El valor registrado es difente al valor esperado", 13, BinarySearchTree.get("m"));
		assertSame("El valor registrado es difente al valor esperado", 14, BinarySearchTree.get("n"));
		assertSame("El valor registrado es difente al valor esperado", 15, BinarySearchTree.get("o"));
		assertSame("El valor registrado es difente al valor esperado", 16, BinarySearchTree.get("p"));
		assertSame("El valor registrado es difente al valor esperado", 17, BinarySearchTree.get("q"));
		assertSame("El valor registrado es difente al valor esperado", 18, BinarySearchTree.get("r"));
		assertSame("El valor registrado es difente al valor esperado", 19, BinarySearchTree.get("s"));
		assertSame("El valor registrado es difente al valor esperado", 20, BinarySearchTree.get("t"));
		assertSame("El valor registrado es difente al valor esperado", 21, BinarySearchTree.get("u"));
		assertSame("El valor registrado es difente al valor esperado", 22, BinarySearchTree.get("v"));
		assertSame("El valor registrado es difente al valor esperado", 23, BinarySearchTree.get("w"));
		assertSame("El valor registrado es difente al valor esperado", 24, BinarySearchTree.get("x"));
		assertSame("El valor registrado es difente al valor esperado", 25, BinarySearchTree.get("y"));
		assertSame("El valor registrado es difente al valor esperado", 26, BinarySearchTree.get("z"));
	}

	/**
	 * Se encarga de verificar si la el valor asociado a una llave dada es el mismo en el setUp (prueba 2 - elementos repetidos).
	 * Retorna el valor V asociado a la llave key dada. Si la llave no se encuentra se retorna null.
	 */
	@Test
	public void get2(){
		setUp3();
		assertSame("El valor registrado es difente al valor esperado", 3, BinarySearchTree.get("a"));
		assertSame("El valor registrado es difente al valor esperado", 4, BinarySearchTree.get("d"));
		assertSame("El valor registrado es difente al valor esperado", 5, BinarySearchTree.get("e"));
		assertSame("El valor registrado es difente al valor esperado", 6, BinarySearchTree.get("f"));
		assertSame("El valor registrado es difente al valor esperado", 8, BinarySearchTree.get("g"));
		assertSame("El valor registrado es difente al valor esperado", 9, BinarySearchTree.get("i"));
		assertSame("El valor registrado es difente al valor esperado", 10, BinarySearchTree.get("j"));
		assertSame("El valor registrado es difente al valor esperado", 11, BinarySearchTree.get("k"));
		assertSame("El valor registrado es difente al valor esperado", 14, BinarySearchTree.get("l"));
		assertSame("El valor registrado es difente al valor esperado", 15, BinarySearchTree.get("o"));
		assertSame("El valor registrado es difente al valor esperado", 17, BinarySearchTree.get("p"));
		assertSame("El valor registrado es difente al valor esperado", 18, BinarySearchTree.get("r"));
		assertSame("El valor registrado es difente al valor esperado", 19, BinarySearchTree.get("s"));
		assertSame("El valor registrado es difente al valor esperado", 20, BinarySearchTree.get("t"));
		assertSame("El valor registrado es difente al valor esperado", 21, BinarySearchTree.get("u"));
		assertSame("El valor registrado es difente al valor esperado", 24, BinarySearchTree.get("v"));
		assertSame("El valor registrado es difente al valor esperado", 25, BinarySearchTree.get("y"));
		assertSame("El valor registrado es difente al valor esperado", 26, BinarySearchTree.get("z"));
	}

	/**
	 * Se encarga de verificar si la altura del arbol corresponde a la altura del setUp (prueba 1 - elementos sin repetir).
	 * Retorna la altura del árbol definida como la áltura de la rama más alta; es decir, aquella que tenga mayor número de enlaces desde la raíz a una hoja.
	 */
	@Test
	public void height1(){
		setUp2();
		assertSame("La altura del árbol no es la correcta", 25, BinarySearchTree.height());
	}

	/**
	 * Se encarga de verificar si la altura del arbol corresponde a la altura del setUp (prueba 2 - elementos repetidos).
	 * Retorna la altura del árbol definida como la áltura de la rama más alta; es decir, aquella que tenga mayor número de enlaces desde la raíz a una hoja.
	 */
	@Test
	public void height2(){
		setUp4();
		assertSame("La altura del árbol no es la correcta", 1, BinarySearchTree.height());
	}

	/**
	 * Se encarga de verificar si la llave más pequeña del arbol corresponde a la llave más pequeña del setUp.
	 * Retorna la llave más pequeña del árbol. Valor null si árbol vacío.
	 */
	@Test
	public void min(){
		assertNull("El valor registrado debería ser null", BinarySearchTree.min());
		setUp2();
		assertSame("El valor registrado no corresponde al esperado", "a", BinarySearchTree.min().key);
	}

	/**
	 * Se encarga de verificar si la llave más grande del arbol corresponde a la llave más grande del setUp.
	 * Retorna la llave más grande del árbol. Valor null si árbol vacío
	 */
	@Test
	public void max(){
		assertNull("El valor registrado debería ser null", BinarySearchTree.max());
		setUp2();
		assertSame("El valor registrado no corresponde al esperado", "z", BinarySearchTree.max());
	}

	/**
	 * Se encarga de verificar que las llaves del arbol correspondan a las llaves del setUp.
	 * Retorna las llaves del árbol. Para su implementación en BST o RBT deben retornarse usando un recorrido en Inorden.
	 */
	@Test
	public void keySet(){
		setUp2();
		ArregloDinamicoGenerico<String> p = (ArregloDinamicoGenerico) BinarySearchTree.keySet();
		assertSame("El tamaño del keySet no es el correcto", 26, p.size());
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("a"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("b"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("c"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("d"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("e"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("f"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("g"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("h"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("i"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("j"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("k"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("l"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("m"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("n"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("o"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("p"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("q"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("r"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("s"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("t"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("u"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("v"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("w"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("x"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("y"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("z"));		
	}

	/**
	 * Se encarga de verificar si las llaves se encuentran entre dos parámetros de  fechas con base al setUp.
	 * Retorna todas las llaves K en el árbol que se encuentran en el rango de llaves dado. Las llaves en el rango deben retornarse en orden ascendente. 
	 */
	@Test
	public void keyInRange1(){
		setUp2();
		ArregloDinamicoGenerico<String> p = (ArregloDinamicoGenerico) BinarySearchTree.keysInRange("a", "c");
		assertSame("El tamaño de la lista no coincide", 3, p.size());
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("a"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("b"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("c"));
	}
	
	/**
	 * Se encarga de verificar si las llaves se encuentran entre dos parámetros de  fechas con base al setUp.
	 * Retorna todas las llaves K en el árbol que se encuentran en el rango de llaves dado. Las llaves en el rango deben retornarse en orden ascendente. 
	 */
	@Test
	public void keyInRange2(){
		setUp2();
		ArregloDinamicoGenerico<String> p = (ArregloDinamicoGenerico) BinarySearchTree.keysInRange("a", "z");
		assertSame("El tamaño de la lista no coincide", 26, p.size());
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("a"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("b"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("c"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("d"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("e"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("f"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("g"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("h"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("i"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("j"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("k"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("l"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("m"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("n"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("o"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("p"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("q"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("r"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("s"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("t"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("u"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("v"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("x"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("y"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("z"));
		
	}

	/**
	 * Se encarga de verificar si los valores se encuentran entre dos parámetros de  fechas con base al setUp.
	 * Retorna todos los valores V en el árbol que estén asociados al rangode llaves dado.
	 */
	@Test
	public void valuesInRange(){
		setUp2();
		ArregloDinamicoGenerico<Integer> p = (ArregloDinamicoGenerico) BinarySearchTree.valuesInRange("a", "c");
		assertSame("El tamaño del keySet no es el correcto", 3, p.size());
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent(1));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent(2));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent(3));
	}


}
