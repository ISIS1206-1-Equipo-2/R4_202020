package model.structures.binarySearchTree;

import model.structures.binarySearchTree.BinarySearchTree.NodoBinario;

/**
 * 2020-10-14
 * Clase TablaSimbolosOrdenada.
 * @author Juli�n Andr�s M�ndez
 * @author Juan Miguel Vega
 */

import model.structures.listaGenerica.Lista;

public interface TablasSimbolosOrdenada<K extends Comparable, V> 
{
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
	
	/**
	 * Retornar el n�mero de parejas [Llave,Valor] del �rbol
	 * @return int numero de nodos del arbol
	 */
	int size();

	/**
	 * Informa si el �rbol es vac�o
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
	 * la llave existe). Retorna valor �1 si la llave No existe
	 * @param key search key
	 * @return int height altura del path desde la raiz hasta el nodo con la llave key, retorna -1 si no existe 
	 * 		   un nodo con dicho key en el arbol
	 */
	int getHeight(K key);


	/**
	 * Indica si la llave key se encuentra en el �rbol
	 * @param key search key
	 * @return true si hay un search hit, false de lo contrario
	 */
	boolean contains(K key);



	/**
	 *	Inserta la pareja [key, val] en el �rbol. Si la llave ya existe se reemplaza
	 *	el valor. 
	 * @param key llave del nuevo nodo a insertar
	 * @param val valor del nuevo nodo a insertar
	 */
	void put(K key, V val);


	/**
	 * Retorna la altura del �rbol definida como la altura de la rama m�s alta
	 *	(aquella que tenga mayor n�mero de enlaces desde la ra�z a una hoja).
	 * @return la altura de la rama mas alta en el arbol
	 */
	int height();


	/**
	 * Retorna la llave m�s peque�a del �rbol. Valor null si �rbol vac�o
	 * @return la menor llave del arbol
	 */
	NodoBinario min();


	/**
	 * Retorna la llave m�s grande del �rbol. Valor null si �rbol vac�o
	 * @return la mayor llave del arbol
	 */
	K max();


	/**
	 * Retorna las llaves del �rbol. Para su implementaci�n en BST o RBT
	 * deben retornarse usando un recorrido en Inorden.
	 * @return Una lista con las llaves del mapa
	 */
	Lista<K> keySet();

	
	/**
	 * Retorna todas las llaves K en el �rbol que se encuentran en el rango
 	 * de llaves dado. Las llaves en el rango deben retornarse en orden
 	 * ascendente. Por eficiencia, debe intentarse No recorrer todo el �rbol.
	 */
	Lista<K> keysInRange(K init, K end);
	
	/**
	 * Retorna todos los valores V en el �rbol que est�n asociados al rango
	 * de llaves dado.
	 * Por eficiencia, debe intentarse No recorrer todo el �rbol.
	 */
	Lista<V> valuesInRange(K init, K end);


}
