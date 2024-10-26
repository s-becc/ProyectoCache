package map;


import list.InvalidPositionException;
import list.ListaDobleEnlazada;
import list.Position;
import list.PositionList;

public class MapeoHash<K,V> implements Map<K,V> {
 private PositionList<Entry<K,V>>[] arreglo;
 private int n;
 private int N;
 public MapeoHash() {
	 N = 11;
	 n = 0;
	 arreglo = (PositionList<Entry<K, V>>[]) new PositionList[N];  // Inicializa el arreglo
	 for (int i = 0; i < N; i++) {
	     arreglo[i] = new ListaDobleEnlazada<>();  // Llena el arreglo con listas vacías
	 }
 }
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return n;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return n == 0;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("La clave no es valida");
		V toRet = null;
		int index = key.hashCode() % N;
		for (Entry<K,V> entrada : arreglo[index])
			if (entrada.getKey().equals(key))
				toRet = entrada.getValue();
		return toRet;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		// TODO Auto-generated method stub
		if (key == null)
			throw new InvalidKeyException("La clave no es valida");
		int index = Math.abs(key.hashCode() % N);
		V toRet = null;
		for (Entry<K,V> entrada : arreglo[index])
			if (entrada.getKey().equals(key)) {
				toRet = entrada.getValue();
				entrada.setValue(value);
				 
				}
		if (toRet == null) {
			arreglo[index].addLast(new Entry<>(key,value));
		   n++;
		   }
		return toRet;
	}

	 
	@Override
	public V remove(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("La clave no es valida");
		V toRet = null;
		int index = Math.abs(key.hashCode() % N);
		for (Position<Entry<K,V>> entrada : arreglo[index].positions())
			if (entrada.element().getKey().equals(key)) {
				toRet = entrada.element().getValue();
				try {
					arreglo[index].remove(entrada);
				} catch (InvalidPositionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				n--;
			}
		return toRet;
	}

	@Override
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		PositionList<K> toRet = new ListaDobleEnlazada<>();
		for (int i = 0; i< arreglo.length; i++)
			for (Entry<K,V> entrada : arreglo[i])
				toRet.addLast(entrada.getKey());
		return toRet;
	}

	@Override
	public Iterable<V> values() {
		// TODO Auto-generated method stub
		PositionList<V> toRet = new ListaDobleEnlazada<>();
		for (int i = 0; i< arreglo.length; i++)
			for (Entry<K,V> entrada : arreglo[i])
				toRet.addLast(entrada.getValue());
		return toRet;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		// TODO Auto-generated method stub
		PositionList<Entry<K, V>> toRet = new ListaDobleEnlazada<>();
		for (int i = 0; i< arreglo.length; i++)
			for (Entry<K,V> entrada : arreglo[i])
				toRet.addLast(entrada);
		return toRet;
	}
	private void reHash() { // O(n)
        n=0;
        N *= N;
        Iterable<Entry<K,V>> allEntries = entries();
        arreglo = new ListaDobleEnlazada[N];
        for(int i=0; i<N;i++)
            arreglo[i] = new ListaDobleEnlazada<Entry<K,V>>();
        for(Entry<K,V> entry : allEntries) {
            K key =entry.getKey();
            V value = entry.getValue();
            try {
                put(key,value);
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }
    }

}
