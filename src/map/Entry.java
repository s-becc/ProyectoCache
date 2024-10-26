package map;


public class Entry<K, V> {
    private K key;
    private V value;

    // Constructor
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

   
    public K getKey() {
        return key;
    }

 
    public V getValue() {
        return value;
    }
 
    public void setValue(V value) {
        this.value = value;
    }

 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry<?, ?> entry = (Entry<?, ?>) o;
        return key.equals(entry.key);
    }

    
    @Override
    public int hashCode() {
        return key.hashCode();
    }

    
    @Override
    public String toString() {
        return key + "=" + value;
    }
}
