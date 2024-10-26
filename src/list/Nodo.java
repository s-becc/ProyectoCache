package list;

public class Nodo<E> implements Position<E>{
	 private E element;
	    private Nodo<E> next;
	    private Nodo<E> previous;

	    public Nodo(E element) {
	        this.element = element;
	        this.next = null;
	        this.previous = null;
	    }
	    public void setElement(E elem) {
	    	element = elem;
	    }

	    public E element() {
	        return element;
	    }

	    public Nodo<E> getNext() {
	        return next;
	    }

	    public void setNext(Nodo<E> next) {
	        this.next = next;
	    }

	    public Nodo<E> getPrevious() {
	        return previous;
	    }

	    public void setPrevious(Nodo<E> previous) {
	        this.previous = previous;
	    }
}
