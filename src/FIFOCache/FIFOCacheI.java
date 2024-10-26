package FIFOCache;
import list.*;

public interface FIFOCacheI<E> {

	    /* Accede a una página en memoria, generando un fallo de página si no está presente.
	     * Retorna True en caso que la página esté presente, False en caso contrario.  
	     * */
	    public boolean accessPage(E pageNumber);

	    /* Devuelve una lista con el estado actual de los marcos de página.
	     * Por ejemplo, si se cargaron las páginas en el siguiente orden: 1,2,3,4
	     * en una cache con capacidad = 3
	     * debería retornar una lista: <2,3,4>
	     * */
	    public PositionList<E> getPageFrameStatus();

	    /* Devuelve el número total de fallos de página ocurridos*/
	    public int getPageFaultCount();
}