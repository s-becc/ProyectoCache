package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ElemIterator<E> implements Iterator<E> {
    private Position<E> cursor;
    private PositionList<E> lista;
   
    public ElemIterator(ListaDobleEnlazada<E> lista) {
    	this.lista = lista;
        if (lista.isEmpty()) {
            cursor = null;  
        } else 
        try {
            cursor = lista.first(); 
        }catch (EmptyListException e) {e.printStackTrace();};
    }

    @Override
    public boolean hasNext() {
        return cursor != null;
    }

    @Override
    public E next() throws NoSuchElementException{
    	if (cursor == null) {
    		throw new NoSuchElementException("no hay siguiente");
    	}
    		E toRet = cursor.element();
    		try {
    			cursor = (cursor == lista.last()) ? null : lista.next(cursor);
    		}catch (InvalidPositionException | BoundaryViolationException | EmptyListException e) {e.printStackTrace();}
    		return toRet;
    }
}