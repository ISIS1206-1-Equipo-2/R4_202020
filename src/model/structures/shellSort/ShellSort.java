package model.structures.shellSort;

/**
 * 2020-09-0
 * Representa el algoritmo sort
 * Clase implementada para usar el algoritmo sort.
 * @author Julián Andrés Méndez
 * @author Juan Miguel Vega Caro
 */

import java.util.*;

/**
 * Clase implementada para usar el algoritmo sort.
 */
public class ShellSort {
	
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	
	/**
	 * Ordena el arreglo de forma ascendente usando ShellSort según la calificación
	 * @param Lista lista Una lista que implemente Lista
	 * @param 
	 */
	public static void sortCalificacionAscendente(Comparable[] a)
	{
		
		int N = a.length; 
		int h = 1;
		
		while(h < N/3) h = 3*h +1;
		while(h >=1)
		{
			for(int i = h; i < N; i++)
			{
				for(int j = i; j >= h && less(a[j], a[j-h]); j-= h)
					exch(a, j, j- h);
			}
			h = h/3;
		}
	}
	
	
	/**
	 * Ordena el arreglo de forma descendente usando ShellSort según la calificación
	 * @param Arreglo de objetos comparables comparable[] a
	 */
	public static void sortCalificacionDescendente(Comparable[] a)
	{
		int N = a.length;
		int h = 1;
		
		while (h < N/3) h = 3*h +1;
		while(h >=1)
		{
			for(int i = h; i < N ; i++)
			{
				for(int j = i; j >= h && less(a[j-h], a[j]); j-= h)
					exch(a, j, j- h);
			}
			h = h/3;
		}
	}
	
	/**
	 * Método auxiliar, comprueba si un elemento es menor a otro, con respecto a la valoración
	 * @param a Elemento comparable a
	 * @param b Elemento comparable b
	 * @return Retorna true si el elemento a es menor que el elemento b. De lo contrario, retorna false. 
	 */
	private static boolean less(Comparable a, Comparable b)
	{
		return a.compareTo(b) < 0;
		
	}

	
	/**
	 * Exchanges elements int i and j index of the array
	 * pre: It assumes index i and j are into "a" bounds 
	 */
	private static void exch(Comparable[] a, int i, int j)
	{
		Comparable aux = a[i] ; a[i] = a[j] ; a[j] = aux;
	}
	
	/**
	 * Imprime el array en una sola linea .
	 * @param a Array en donde se imprimen sus elementos.
	 */
	private static void show(Comparable[] a)
	{
		for(int i = 0; i < a.length ; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}
	
	/**
	 * Verifica si un arreglo está ordenado.
	 * @param a elemento comparable a
	 * @return True si el arreglo está ordenado. De lo contrario, retorna false.
	 */
	public static boolean isSorted(Comparable[] a)
	{
		for(int i = 1; i < a.length; i++)
			if(less(a[i],a[i-1])) return false;
		return true;
	}


}
