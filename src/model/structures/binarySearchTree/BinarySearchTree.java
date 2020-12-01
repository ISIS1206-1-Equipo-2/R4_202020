package model.structures.binarySearchTree;

import model.structures.listaGenerica.ArregloDinamicoGenerico;
import model.structures.listaGenerica.Lista;
import model.structures.shellSort.ShellSort;

public class BinarySearchTree<K extends Comparable<K> , V > implements TablasSimbolosOrdenada<K,V>
{

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------


	/**
	 * Raiz del arbol binario de busqueda
	 */
	public NodoBinario root;


	// -----------------------------------------------------------------
	// Cosntructor
	// -----------------------------------------------------------------

	/**
	 * Construye un nuevo arbol binario
	 */
	public BinarySearchTree()
	{
		//xd
	}
	/**
	 * Clase de los nodos del arbol binario de busqueda
	 */
	public class NodoBinario
	{
		/**
		 * LLave del nodo
		 */
		public K key;

		/**
		 * Valor del nodo
		 */
		public V val;

		/**
		 * Sub-arboles derecho e izquierdo del nodo
		 * 
		 */
		public NodoBinario left, right;

		/**
		 * N numero de nodos del arbol actual
		 */
		public int N;
		
		/**
		 * Color del link del Nodo
		 */
		boolean color;

		/**
		 * Construye la clase NodoBinario
		 * @param pKey Llave del nodo a constuir
		 * @param pVal Valor del nodo a construir
		 * @param pN N numero de nodos del nuevo arbol 
		 */
		public NodoBinario(K pKey, V pVal, int pN)
		{
			key = pKey; 
			val= pVal;
			N = pN;
		}
		
		public NodoBinario(K pKey, V pVal, int pN, boolean pColor)
		{
			key = pKey; 
			val= pVal;
			N = pN;
			color = pColor; 
		}
		
		public void setValue(V value){
			this.val = value;
		}
	}


	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------



	/**
	 * Retorna la raiz del arbol
	 */
	public NodoBinario getRoot()
	{
		return root;
	}

	/**
	 * Retornar el número de parejas [Llave,Valor] del árbol
	 * @return int numero de nodos del arbol
	 */
	public int size()
	{
		return size(root);
	}

	public int size(NodoBinario x)
	{
		return (x == null ? 0 : x.N);
	}
	
	public void deleteMin()
	{ root = deleteMin(root); }
	
	private NodoBinario deleteMin(NodoBinario x)
	{
	if (x.left == null) return x.right;
	x.left = deleteMin(x.left);
	x.N = 1 + size(x.left) + size(x.right);
	return x;
	}

	public void delete(K key)
	{
		root = delete(root, key);
	}
	
	public NodoBinario delete( NodoBinario x, K key){
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) x.left = delete(x.left, key);
		else if (cmp > 0) x.right = delete(x.right, key);
		else {
		if (x.right == null) return x.left;
		if (x.left == null) return x.right;
		NodoBinario t = x;
		x = min(t.right);
		x.right = deleteMin(t.right);
		x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	/**
	 * Informa si el árbol es vacío
	 * @return boolean true si el arbol esta vacio, false de lo contrario
	 */
	public boolean isEmpty ()
	{
		return root == null;
	}


	/**
	 * Retorna el valor V asociado a la llave key dada. Si la llave no se
	 * encuentra se retorna el valor null.
	 * @param key search key
	 * @return V value asociado a la search key
	 */

	public V get(K key)
	{
		return get(root, key);
	}

	public V get(NodoBinario x, K key)
	{
		if(x == null) return null;

		int comp = key.compareTo(x.key);

		if(comp <0 ) return get(x.left, key);

		else if(comp >0)return get(x.right, key);

		else return x.val;
	}
	
	/**
	 * Retorna el nodoBinario asociado a la llave key dada. Si la llave no se
	 * encuentra se retorna el valor null.
	 * @param key search key
	 * @return V value asociado a la search key
	 */

	public NodoBinario getNodo(K key)
	{
		return getNodo(root, key);
	}

	public NodoBinario getNodo(NodoBinario x, K key)
	{
		if(x == null) return null;

		int comp = key.compareTo(x.key);

		if(comp <0 ) return getNodo(x.left, key);

		else if(comp >0)return getNodo(x.right, key);

		else return x;
	}

	/**
	 * Retorna la altura del camino desde la raiz para llegar a la llave key (si
	 * la llave existe). Retorna valor –1 si la llave No existe
	 * @param key search key
	 * @return int height altura del path desde la raiz hasta el nodo con la llave key, retorna -1 si no existe 
	 * 		   un nodo con dicho key en el arbol
	 */
	public int getHeight(K key)
	{
		return getHeight(root,key);
	}

	public int getHeight(NodoBinario x, K key)
	{
		if(x == null) return -1;
		if(get(key) == null) return -1;

		int compareValue = key.compareTo(x.key);
		int height = 0; 

		if(compareValue > 0 )
		{
			height = 1 + getHeight(x.right, key);
		}
		else if(compareValue < 0 )
		{
			height = 1 + getHeight(x.left, key);
		}
		else if( compareValue == 0)
		{
			return 1;
		}
		return height - 1;
	}

	/**
	 * Indica si la llave key se encuentra en el árbol
	 * @param key search key
	 * @return true si hay un search hit, false de lo contrario
	 */
	public boolean contains(K key)
	{
		return (get(key) != null ? true : false);
	}

	/**
	 *	Inserta la pareja [key, val] en el árbol. Si la llave ya existe se reemplaza
	 *	el valor. 
	 * @param key llave del nuevo nodo a insertar
	 * @param val valor del nuevo nodo a insertar
	 */
	public void put(K key, V val)
	{

		root = put(root, key, val);

	}

	public NodoBinario put(NodoBinario x, K key, V val)
	{
		if(x == null) return new NodoBinario(key, val, 1);

		int compareValue = key.compareTo(x.key);

		if(compareValue > 0 ) 
		{
			x.right = put(x .right, key, val);
		}
		else if(compareValue < 0) 
		{
			x.left = put(x.left, key, val);
		}
		else
		{
			x.val = val;
		}
		x.N = size(x.left) + size(x.right) + 1;

		return x;
	}


	/**
	 * Retorna la altura del árbol definida como la altura de la rama más alta
	 *	(aquella que tenga mayor número de enlaces desde la raíz a una hoja).
	 * @return la altura de la rama mas alta en el arbol
	 */
	/**
     * Retorna la altura del árbol definida como la altura de la rama más alta
     *    (aquella que tenga mayor número de enlaces desde la raíz a una hoja).
     * @return la altura de la rama mas alta en el arbol
     */
    public int height()
    {
        return height(root)-1;
    }

    public int height(NodoBinario x)
    {
        if(x == null)
        {
            return 0;
        }
        else if(x.left == null && x.right == null)
        {
            return 1;
        }
        else
        {
            int left = (x.left == null) ? 0 : height(x.left);
            int right = (x.right == null) ? 0 : height(x.right);

            return 1 + Math.max(left,right);
        }

    }


	/**
	 * Retorna la llave más pequeña del árbol. Valor null si árbol vacío
	 * @return la menor llave del arbol
	 */
	public NodoBinario min()
	{
		return min(root);
	}

	public NodoBinario min(NodoBinario x)
	{
		if(x == null) return null;

		else if(x.left == null) return x;

		return min(x.left);

	}


	/**
	 * Retorna la llave más grande del árbol. Valor null si árbol vacío
	 * @return la mayor llave del arbol
	 */
	public K max()
	{
		return max(root);
	}

	public K max(NodoBinario x)
	{
		if(x == null) return null;

		else if(x.right == null) return x.key;

		return max(x.right);

	}


	/**
	 * Retorna las llaves del árbol. Para su implementación en BST o RBT
	 * deben retornarse usando un recorrido en Inorden.
	 * @return Una lista con las llaves del mapa
	 */
	public Lista<K> keySet()
	{
		return keySet(root);
	}

	public Lista<K> keySet(NodoBinario x)
	{
		if(x == null)
		{
			return null;
		}
		else
		{
			ArregloDinamicoGenerico<K> arr = new ArregloDinamicoGenerico<K>(100);

			keySet(arr, x);

			return arr;
		}
	}

	public void keySet(ArregloDinamicoGenerico a, NodoBinario x)
	{
		if(x == null)
		{
			return ;
		}

		keySet(a, x.left);

		a.addLast(x.key);

		keySet(a, x.right);

	}




	/**
	 * Retorna todas las llaves K en el árbol que se encuentran en el rango
	 * de llaves dado. Las llaves en el rango deben retornarse en orden
	 * ascendente. Por eficiencia, debe intentarse No recorrer todo el árbol.
	 */
	public Lista<K> keysInRange(K init, K end)
	{
		ArregloDinamicoGenerico<K> arr = new ArregloDinamicoGenerico<K>(100);
		keysInRange(root, arr, init, end);


		Comparable[]acc = new Comparable[arr.size()];
		for( int i= 0; i < arr.size() ; i++ )
		{
			acc[i] = arr.getElemento(i+1);
		}

		ShellSort.sortCalificacionAscendente(acc);

		ArregloDinamicoGenerico<K> arr2 = new ArregloDinamicoGenerico<K>(acc.length);
		for(int i = 0; i < acc.length; i++)
		{
			arr2.addLast((K)acc[i]);
		}
		
		return arr2;
	}

	public void keysInRange(NodoBinario x, ArregloDinamicoGenerico a, K lo, K hi)
	{
		if(x == null) return;	
		
		int cmplo = lo.compareTo(x.key);

		int cmphi = hi.compareTo(x.key); 

		if(cmplo < 0)  keysInRange(x.left, a, lo, hi);

		if(cmplo <= 0 && cmphi >=0) a.addLast(x.key);

		if(cmphi >0) keysInRange(x.right, a, lo , hi);
		
	}

	/**
	 * Retorna todos los valores V en el árbol que estén asociados al rango
	 * de llaves dado.
	 * Por eficiencia, debe intentarse No recorrer todo el árbol.
	 */
	public Lista<V> valuesInRange(K init, K end)
	{
		Lista<K> keys = keysInRange(init, end); 

		if(keys.size() == 0)
		{
			return null;
		}
		else
		{
			ArregloDinamicoGenerico<V> values = new ArregloDinamicoGenerico<V>(keys.size());
			for(int i = 0; i < keys.size(); i++)
			{
				values.addLast(get(keys.getElemento(i+1)));
			}
			return values;
		}

	}

}