package FIFOCache;

import list.*;
import map.*;

public class FIFOCache implements FIFOCacheI<Integer> {
	private PositionList<Integer> ListaPaginas;
	private Map<Integer,Integer> MapPaginas;
	private int capacity;
	private int fallos;
	
	public FIFOCache(int capacity) {
		ListaPaginas = new ListaDobleEnlazada<>();
		MapPaginas = new MapeoHash<>();
		this.capacity = capacity;
		fallos = 0;
	}

	@Override
	public boolean accessPage(Integer pageNumber) {
		// TODO Auto-generated method stub
		boolean esta = false;
		try {
		if (MapPaginas.get(pageNumber) != null)
			esta = true;
		else {
		loadPage(pageNumber);
		fallos++;
		  }
		}catch (InvalidKeyException e) { e.printStackTrace();}
		return esta;
	}

	@Override
	public PositionList<Integer> getPageFrameStatus() {
		// TODO Auto-generated method stub
		return ListaPaginas;
	}

	@Override
	public int getPageFaultCount() {
		// TODO Auto-generated method stub
		return fallos;
	}
	private void loadPage(int pageNumber) {
		ListaPaginas.addLast(pageNumber);
		try {
			MapPaginas.put(pageNumber,pageNumber);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ListaPaginas.size() > capacity) 
			try {
				ListaPaginas.remove(ListaPaginas.first());
				MapPaginas.remove(pageNumber);
			} catch (InvalidPositionException | EmptyListException | InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
