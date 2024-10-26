package list;
import java.util.Iterator;

public class Main{
  public static <E> boolean ejercicio3A(PositionList<E> pl, E elem){
	  ElemIterator<E> it = (ElemIterator<E>) pl.iterator();
	  boolean toRet = false;
	  while (it.hasNext() && !toRet)
		 if (it.next().equals(elem))
			 toRet = true;
	  return toRet;
  }
  public static <E> int ejercicio3B(PositionList<E> pl, E elem) {
	  int cont = 0;
	  ElemIterator<E> it = (ElemIterator<E>) pl.iterator();
	  while (it.hasNext())
		 if (it.next().equals(elem))
			 cont++;
	  return cont;
  }
  public static <E> boolean ejercicio3C(PositionList<E> pl, E elem, int n) {
	  int cont = 0;
	  ElemIterator<E> it = (ElemIterator<E>) pl.iterator();
	  while (it.hasNext() && cont < n)
		 if (it.next() == elem)
			 cont++;
	  return cont == n;
    }
  public static <E> PositionList<E> ejercicio4(PositionList<E> pl){
	  PositionList<E> toRet = new ListaDobleEnlazada<>();
	  Position<E> cursor = null;
	  try {
	   cursor = pl.first();
	  }catch (EmptyListException e) {e.printStackTrace();}
	  for (E p : pl) {
		  try {
		  toRet.addAfter(cursor, p);
		  toRet.addAfter(cursor, p);
		  cursor = toRet.next(cursor);
		  }catch (InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
	  }
	  return toRet;
  }
  public static Iterable<Character> ejercicio5(PositionList<Character> l1, PositionList<Character> l2){
	  PositionList<Character> toRet = new ListaDobleEnlazada<Character>();
      Iterator<Character> it1 = null;
	  Iterator<Position<Character>> it2 = l2.positions().iterator();
	  Position<Character> cursor = null;
	 try {
	  while (it2.hasNext()) {
		  cursor = it2.next();
		  it1 = l2.iterator();
		  while (it1.hasNext())
			  if (cursor.element().equals(it1.next())) {
				  l2.remove(cursor);
				  toRet.addLast(cursor.element());
			  }
	    }
	       } catch (InvalidPositionException e) {e.printStackTrace();};
	  return toRet;
  }
  public static PositionList<Character> soloVocales(PositionList<Character> pl, int n){
		  PositionList<Character> toRet = new ListaDobleEnlazada<Character>(); 
		  Position<Character> cursor = null;
		  boolean aux = true;
	  try {
	   cursor = pl.first();
  }catch (EmptyListException e) {e.printStackTrace();} 
	  while (n != 0 && aux) {
		  if (cursor.element().charValue() == 'a'  || cursor.element().charValue() == 'e' || cursor.element().charValue() == 'i'|| cursor.element().charValue() == 'o' || cursor.element().charValue() == 'u')
	          {toRet.addLast(cursor.element());   n--;}
		  try {
		  if (pl.next(pl.next(cursor)) == null)
			  aux = false;
		  else
		  cursor = pl.next(cursor);
	  }catch (InvalidPositionException | BoundaryViolationException e) {e.printStackTrace(); };	  
	  
         }
	  return toRet;
      }
  public static <E> void eliminar(PositionList<E> l1, PositionList<E> l2) {
	  for (Position<E> p : l2.positions())
		  if (contiene(l1,p.element()))
			  try {
			  l1.remove(p);
			  } catch (InvalidPositionException e) {e.printStackTrace();}
	  Position<E> pos = null;
	try {
		pos = l2.last();
	} catch (EmptyListException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
      while (pos.element() != null) {
          l1.addLast(pos.element());  // Agregar el elemento de L2 al final de L1
          try {
			pos = l2.prev(pos);
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Ir al elemento anterior en L2
  }
  }
  private static <E> boolean contiene(PositionList<E> lista, E elem) {
	  boolean toRet = false;
	  for (Position<E> p : lista.positions())
		  if (p.element().equals(elem))
			  toRet = true;
	  return toRet;
  }
  public static <E> PositionList<E> elemsEnComun(PositionList<E> l1, PositionList<E> l2){
	  PositionList<E> l3 = new ListaDobleEnlazada<>();
	  for (E elem : l1)
		  for (E e : l2)
			  if (elem.equals(e)) {
				  l3.addLast(elem);
				  break;
			  }
	  return l3;
					  }
  
}