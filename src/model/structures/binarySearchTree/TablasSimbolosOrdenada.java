package model.structures.binarySearchTree;

import model.structures.binarySearchTree.BinarySearchTree.NodoBinario;

/**
 * 2020-10-14
 * Clase TablaSimbolosOrdenada.
 * @author Julián Andrés Méndez
 * @author Juan Miguel Vega
 */

import model.structures.listaGenerica.Lista;

public interface TablasSimbolosOrdenada<K extends Comparable, V> 
{
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	
	/**
	 * Retornar el número de parejas [Llave,Valor] del árbol
	 * @return int numero de nodos del arbol
	 */
	int size();

	/**
	 * Informa si el árbol es vacío
	 * @return boolean true si el arbol esta vacio, false de lo contrario
	 */
	boolean isEmpty ();


	/**
	 * Retorna el valor V asociado a la llave key dada. Si la llave no se
	 * encuentra se retorna el valor null.
	 * @param key search key
	 * @return V value asociado a la search key
	 */
	V get(K key);


	/**
	 * Retorna la altura del camino desde la raiz para llegar a la llave key (si
	 * la llave existe). Retorna valor –1 si la llave No existe
	 * @param key search key
	 * @return int height altura del path desde la raiz hasta el nodo con la llave key, retorna -1 si no existe 
	 * 		   un nodo con dicho key en el arbol
	 */
	int getHeight(K key);


	/**
	 * Indica si la llave key se encuentra en el árbol
	 * @param key search key
	 * @return true si hay un search hit, false de lo contrario
	 */
	boolean contains(K key);



	/**
	 *	Inserta la pareja [key, val] en el árbol. Si la llave ya existe se reemplaza
	 *	el valor. 
	 * @param key llave del nuevo nodo a insertar
	 * @param val valor del nuevo nodo a insertar
	 */
	void put(K key, V val);


	/**
	 * Retorna la altura del árbol definida como la altura de la rama más alta
	 *	(aquella que tenga mayor número de enlaces desde la raíz a una hoja).
	 * @return la altura de la rama mas alta en el arbol
	 */
	int height();


	/**
	 * Retorna la llave más pequeña del árbol. Valor null si árbol vacío
	 * @return la menor llave del arbol
	 */
	NodoBinario min();


	/**
	 * Retorna la llave más grande del árbol. Valor null si árbol vacío
	 * @return la mayor llave del arbol
	 */
	K max();


	/**
	 * Retorna las llaves del árbol. Para su implementación en BST o RBT
	 * deben retornarse usando un recorrido en Inorden.
	 * @return Una lista con las llaves del mapa
	 */
	Lista<K> keySet();

	
	/**
	 * Retorna todas las llaves K en el árbol que se encuentran en el rango
 	 * de llaves dado. Las llaves en el rango deben retornarse en orden
 	 * ascendente. Por eficiencia, debe intentarse No recorrer todo el árbol.
	 */
	Lista<K> keysInRange(K init, K end);
	
	/**
	 * Retorna todos los valores V en el árbol que estén asociados al rango
	 * de llaves dado.
	 * Por eficiencia, debe intentarse No recorrer todo el árbol.
	 */
	Lista<V> valuesInRange(K init, K end);


}
