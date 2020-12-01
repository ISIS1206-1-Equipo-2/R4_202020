/**
 * 2020-11-17
 * Clase ArregloDinamicoGenerico.
 * @author Julián Andrés Méndez
 * @author Juan Miguel Vega Caro
 */

package model.structures.listaGenerica;

/**
 * Representa la estructura de datos arreglo dinámico generico.
 */
public class ArregloDinamicoGenerico <T> implements Lista<T> {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Capacidad maxima del arreglo.
	 */
	private int tamanoMax;

	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0).
	 */
	private int tamanoAct;

	/**
	 * Arreglo de elementos de tamaNo maximo.
	 */
	private T elementos[];

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial.
	 */
	public ArregloDinamicoGenerico( int max )
	{
		elementos = (T[]) new Object[max];
		tamanoMax = max;
		tamanoAct = 0;
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Agrega un elemento al principio del arreglo.
	 * @param T element Elemento a agregar.
	 */
	public void addFirst( T element )
	{			
		T[] copia = elementos.clone();
		if ( tamanoAct == tamanoMax )
		{  	maxTamanio( ); }
		for ( int i = 0; i < tamanoAct; i++)
		{
			elementos[i+1] = (T) copia[i];
		} 
		elementos[0] = element;
		tamanoAct++;
	}

	/**
	 * Agrega un elemento al final del arreglo.
	 * @param element Elemento a agregar.
	 */
	public void addLast( T element )
	{
		T[] copia = (T[]) elementos;
		if ( tamanoAct == tamanoMax )
		{  	maxTamanio( ); 
		for ( int i = 0; i < tamanoAct; i++)
		{
			elementos[i] = (T) copia[i];
		} 
		}	
		elementos[tamanoAct] = element;
		tamanoAct++;
	}

	/**
	 * Agrega un elemento en una posición válida.
	 * @param element Elemento a agregar.
	 * @param pos Posición en donde se va a agregar el elemento.
	 */
	public void insertElement(T element, int pos) {
		T[] copia = (T[]) elementos;
		elementos = (T[]) new Object[copia.length];
		if ( tamanoAct == tamanoMax ) { maxTamanio( );}		
		for ( int i = 1, j=1; i <= tamanoAct+1; i++){
			if( i == pos){
				elementos[i-1] = element;
				j++;
			}
			else{
				elementos[i-1] = (T) copia[i-j]; 
			}
		} 
		tamanoAct++;
	}

	/**
	 * Elimina el primer elemento en el arreglo.
	 * @return res Elemento eliminado.
	 */
	public T removefirst( )
	{
		T res;
		if(elementos.length == 0){ res = null; }
		else{
			res = elementos[0];
			for( int i = 0; i < elementos.length-1; i++)
			{
				elementos[i] = elementos[i+1];
			}
			elementos[elementos.length-1] = null;
			tamanoAct--;
		}
		return res;
	}

	/**
	 * Elimina el último elemento en el arreglo.
	 * @return res Elemento eliminado.
	 */
	public T removeLast( )
	{
		T res = elementos[elementos.length-1];
		elementos[elementos.length-1] = null;
		tamanoAct--;
		return res;
	}

	/**
	 * Elimina el elemento de una posición válida.
	 * @param int pos. La posición del elemento a eliminar.
	 * @return Retorna el elemento eliminado.
	 */
	public T deleteElement(int pos) {
		T res = null;
		boolean centinela = false;
		for (int i = 0; i < elementos.length && !centinela ; i++) {
			if (i+1 == pos) {
				res = (T) elementos[i];
				centinela = true; 
				for (int j = i; j < elementos.length - 1; j++) {
					elementos[j] = elementos[j+1];
				}
				elementos[elementos.length - 1] = null;
				tamanoAct--;
			}
		}
		return res; 
	}

	/**
	 * Retorna el primer elemento del arreglo.
	 * @return Primer elemento del arreglo.
	 */
	public T firstElement( )
	{
		return elementos[0];
	}

	/**
	 * Retorna el último elemento del arreglo.
	 * @return último elemento del arreglo.
	 */
	public T lastElement( )
	{
		if(tamanoAct!=0)
			return elementos[tamanoAct-1];
		else
			return elementos[tamanoAct];
	}

	/**
	 * Busca el elemento que se encuentra en la posición dada.
	 * @param int i. La posición del elemento a buscar.
	 * @return Retorna el elemento en la posición dada.
	 */
	public T getElemento(int i) {
		// TODO implementar
		return (i>0 && i<=elementos.length)?elementos[i-1]:null;
	}

	/**
	 * Retorna el tamaño del arreglo.
	 * @return Tamaño del arreglo.
	 */
	public int size( )
	{
		return tamanoAct;
	}

	/**
	 * Verifica si el arreglo está vacío.
	 * @return true si el arreglo se encuentra vacío, de lo contrario false.
	 */
	public boolean isEmpty( )
	{
		return (size()==0)?true:false;
	}

	/**
	 * Busca un elemento y retorna su posición.
	 * @param T element. El elemento a buscar. 
	 * @return Retorna la Posición del elemento buscado. Si no se encuentra retorna -1.
	 */
	public int isPresent( T element)
	{
		int res = -1;
		boolean cent = false; 
		for(int i = 0; i<elementos.length && !cent; i++)
		{
			if(elementos[i] !=null)	
			{
				if((elementos[i]).equals(element))
				{
					res = i+1;
					cent = true;
				}
			}
		}
		return res; 
	}

	/**
	 * Intercambia dos elementos del arreglo.
	 * @param int pos1. La posición del primer elemento a intercambiar.
	 * @param int pos1. La posición del segundo elemento a intercambiar.
	 */
	public void exchange( int pos1, int pos2)
	{
		T one = null;
		T two = null;
		boolean cent = false; 
		int cont = 0;

		for( int i = 1; i<elementos.length && !cent; i++)
		{
			if(i==pos1) {one = elementos[i-1]; cont++;}
			else if(i==pos2) {two = elementos[i-1]; cont++;}
			else if( cont == 2){ 
				cent = true;
				elementos[pos1-1] = two;
				elementos[pos2-1] = one;
			}
		}
	}

	/**
	 * Cambia un elemento dada una posición valida en el arreglo.
	 * @param int pos1. La posición del elemento a cambiar.
	 * @param T elem. El elemento para cambiar la información.
	 */
	public void changeInfo( int pos, T elem)
	{
		if(pos>0 && pos<=elementos.length)
		{
			elementos[pos-1] = elem;
		}
	}

	/**
	 * Aumenta el arreglo cuando se encuentra lleno.
	 */
	private void maxTamanio( )
	{
		tamanoMax = 2 * tamanoMax;
		elementos = (T[]) new Object[tamanoMax];
	}

}
