package list;
 
import java.util.Iterator;

public class ListaDobleEnlazada<E> implements PositionList<E>{
	    protected Nodo<E> centinelaInicio;
	    protected Nodo<E> centinelaFin;
	    protected int size;

	    public ListaDobleEnlazada() {
	        centinelaInicio = new Nodo<>(null);  
	        centinelaFin = new Nodo<>(null);    
	        centinelaInicio.setNext(centinelaFin);
	        centinelaFin.setPrevious(centinelaInicio);
	        size = 0;
	    }
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		// TODO Auto-generated method stub
		if (size == 0) 
			throw new EmptyListException("La lista esta vacia");
		return centinelaInicio.getNext();
	}

	@Override
	public Position<E> last() throws EmptyListException {
		// TODO Auto-generated method stub
		if (size == 0) 
			throw new EmptyListException("La lista esta vacia");
		return centinelaFin.getPrevious();
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		// TODO Auto-generated method stub
		if (!(p instanceof Nodo)) {
            throw new InvalidPositionException("Posición no válida.");
        }

        Nodo<E> node = (Nodo<E>) p;  
        Nodo<E> nextNode = node.getNext();

        if (nextNode == centinelaFin) {
            throw new BoundaryViolationException("No hay siguiente nodo, se ha llegado al final.");
        }

        return nextNode; 
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		// TODO Auto-generated method stub
		if (!(p instanceof Nodo)) {
            throw new InvalidPositionException("Posición no válida.");
        }

        Nodo<E> node = (Nodo<E>) p;  
        Nodo<E> prevNode = node.getPrevious();

        if (prevNode == centinelaInicio) {
            throw new BoundaryViolationException("No hay siguiente nodo, se ha llegado al final.");
        }

        return prevNode; 
	}

	@Override
	public void addFirst(E element) {
		// TODO Auto-generated method stub
		Nodo<E> nodo = new Nodo<>(element);
		nodo.setNext(centinelaInicio.getNext());
		nodo.setPrevious(centinelaInicio);
		centinelaInicio.getNext().setPrevious(nodo);
		centinelaInicio.setNext(nodo);
		size++;
	}

	@Override
	public void addLast(E element) {
		// TODO Auto-generated method stub
		Nodo<E> nodo = new Nodo<>(element);
		nodo.setNext(centinelaFin);
		nodo.setPrevious(centinelaFin.getPrevious());
		centinelaFin.getPrevious().setNext(nodo);
		centinelaFin.setPrevious(nodo);
		size++;
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (!(p instanceof Nodo<E>))
			throw new InvalidPositionException("posicion no valida");
		Nodo<E> nodoElem = new Nodo<>(element);
		Nodo<E> nodoP = (Nodo<E>) p;
		nodoElem.setPrevious(nodoP);
		nodoElem.setNext(nodoP.getNext());
		nodoP.getNext().setPrevious(nodoElem);
		nodoP.setNext(nodoElem);
		size++;
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (!(p instanceof Nodo<E>))
			throw new InvalidPositionException("posicion no valida");
		Nodo<E> nodoElem = new Nodo<>(element);
		Nodo<E> nodoP = (Nodo<E>) p;
		nodoElem.setNext(nodoP);
		nodoElem.setPrevious(nodoP.getPrevious());
		nodoP.getPrevious().setNext(nodoElem);
		nodoP.setPrevious(nodoElem);
		size++;
		
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
	    Nodo<E> nodoP = checkPosition(p);  // Valida la posición y hace el cast

	    // Luego sigues con la lógica de eliminación del nodo...
	    nodoP.getNext().setPrevious(nodoP.getPrevious());
	    nodoP.getPrevious().setNext(nodoP.getNext());

	    E toRet = nodoP.element();

	    nodoP.setNext(null);
	    nodoP.setPrevious(null);
	    nodoP.setElement(null);
	    size--;

	    return toRet;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (!(p instanceof Nodo<E>))
			throw new InvalidPositionException("posicion no valida");
		E toRet = p.element();
		Nodo<E> nodoP = (Nodo<E>) p;
		nodoP.setElement(toRet);
		return toRet;
	}

	@Override
	public Iterator<E> iterator(){
		// TODO Auto-generated method stub
		 Iterator<E> e = new ElemIterator<>(this);
		 
		return e;
	}  

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		PositionList<Position<E>> pl = new ListaDobleEnlazada<Position<E>>();
		Nodo<E> n = centinelaInicio.getNext();
		while (n != centinelaFin) {
			pl.addLast(n);
			n = n.getNext();
		    }
		return pl;
	}
	 public void ejercicio2(E e1, E e2) throws EmptyListException{
		  
		 if (size == 0)
	        throw new EmptyListException("La lista no tiene un primer elemento");	
		 try {
		  this.addAfter(centinelaInicio.getNext(), e1);
		  this.addBefore(centinelaFin.getPrevious(),e2);
		 }catch (InvalidPositionException e) {e.printStackTrace();}
		 
	 } 
	
	private Nodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
	    if (p == null || !(p instanceof Nodo<?>)) {
	        throw new InvalidPositionException("Posición no válida");
	    }
	    Nodo<E> nodo = (Nodo<E>) p;
	    
	    // Asegurarse de que el nodo no esté desvinculado (podrías agregar más validaciones aquí)
	    if (nodo.getNext() == null || nodo.getPrevious() == null) {
	        throw new InvalidPositionException("El nodo ya ha sido eliminado o no es válido");
	    }
	    
	    return nodo;
	}
	public int duplicarElem(E elem) {
		int cont = 0;
		Nodo<E> cursor = null;
		try {
			cursor = (Nodo<E>) first();
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (cursor != centinelaFin) {
			if (cursor.element().equals(elem)) {
				try {
					addBefore(cursor, elem);
				} catch (InvalidPositionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cont++;
	}
	 cursor = cursor.getNext();
		}
		return cont;
}
}
		
	 

 
