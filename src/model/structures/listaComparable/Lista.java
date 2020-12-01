/**
 * 2020-11-17
 * Clase Lista.
 * @author Julián Andrés Méndez
 * @author Juan Miguel Vega Caro
 */

package model.structures.listaComparable;

/**
 * Representa la interfaz de Lista.
 */
public interface Lista <T extends Comparable<T>> {
	
	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------
	
	/**
	 * Agrega un elemento al principio del arreglo.
	 * @param T element. Elemento a agregar.
	 */
	public void addFirst( T element);
	
	/**
	 * Agrega un elemento al final del arreglo.
	 * @param T element. Elemento a agregar.
	 */
	public void addLast( T element );
	
	/**
	 * Agrega un elemento en una posición válida.
	 * @param T element. Elemento a agregar.
	 * @param int pos. Posición en donde se va a agregar el elemento.
	 * @throws Exception 
	 */
	public void insertElement(T element, int pos);
	
	/**
	 * Elimina el primer elemento en el arreglo.
	 * @return Retorna el elemento eliminado.
	 */
	T removefirst( );
	
	/**
	 * Elimina el último elemento en el arreglo.
	 * @return Retorna el elemento eliminado.
	 */
	 T removeLast( );
	 
	/**
	 * Elimina el elemento de una posición válida.
	 * @param int pos. La posición del elemento a eliminar.
	 * @return Retorna el elemento eliminado.
	 */
	 T deleteElement(int pos);
	 
	/**
	 * Busca el primer elemento del arreglo.
	 * @return Retorna el primer elemento del arreglo.
	 */
     T firstElement( );
     
    /**
 	 * Busca el último elemento del arreglo.
 	 * @return Retorna el último elemento del arreglo.
 	 */
 	 T lastElement( );
 	 
 	/**
 	 * Busca el elemento que se encuentra en la posición dada.
 	 * @param int i. La posición del elemento a buscar.
 	 * @return Retorna el elemento en la posición dada.
 	 */
 	 T getElemento(int i);
	
	/**
	 * Calcula el tamaño del arreglo.
	 * @return Retorna el tamaño del arreglo.
	 */
	 int size( );
	
	/**
	 * Verifica si el arreglo está vacío.
	 * @return Retorna true si el arreglo se encuentra vacío. De lo contrario retorna false.
	 */
	 boolean isEmpty( );
	 
	/**
	 * Busca un elemento y retorna su posición.
	 * @param T element. El elemento a buscar. 
	 * @return Retorna la Posición del elemento buscado. Si no se encuentra retorna -1.
	 */
	 int isPresent( T element);
	 
	 /**
	  * Intercambia dos elementos del arreglo.
	  * @param int pos1. La posición del primer elemento a intercambiar.
	  * @param int pos1. La posición del segundo elemento a intercambiar.
	  */
	 public void exchange( int pos1, int pos2);
	 
	 /**
	  * Cambia un elemento dada una posición valida en el arreglo.
	  * @param int pos1. La posición del elemento a cambiar.
	  * @param T elem. El elemento para cambiar la información.
	  */
	 public void changeInfo( int pos, T elem);	 
}
