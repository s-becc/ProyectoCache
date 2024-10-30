package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cache.FIFOCache;
import list.EmptyListException;
import list.Position;
import list.PositionList;

class FIFOCacheTest {


    @Test
    void testPageFaults() {
        FIFOCache cache = new FIFOCache(3);
        
        assertFalse(cache.accessPage(1));  // Page fault
        assertFalse(cache.accessPage(2));  // Page fault
        assertFalse(cache.accessPage(3));  // Page fault
        
        assertTrue(cache.accessPage(1));   // No page fault
        
        assertFalse(cache.accessPage(4));  // Page fault, carga page 4. Quita page 1.
        assertTrue(cache.accessPage(2));  // No page fault
        assertFalse(cache.accessPage(5));  // Page fault, carga page 5.
        
        assertEquals(5, cache.getPageFaultCount());
              
        PositionList<Integer> p1 = cache.getPageFrameStatus();
        List<Integer> list = new ArrayList<>();
        for (Position<Integer> pos : p1.positions()) {
            list.add(pos.element());
        }
        assertEquals(List.of(3,4,5),list );
        
	}
    
    // Tests propios
    @Test
    void testCacheUnaPagina() {
        FIFOCache cache = new FIFOCache(1); // Máxima memoria: 1. Fallos: 0
        
        assertFalse(cache.accessPage(1)); // Acceso a una página, debería generar un fallo. Fallos: 1
        
        assertTrue(cache.accessPage(1)); // Acceso repetido a la misma página, no debería generar fallos adicionales. Fallos: 1
        
        assertFalse(cache.accessPage(2)); // Acceso a una nueva página, elimina la anterior y genera un fallo. Fallos: 2

        assertEquals(2, cache.getPageFaultCount()); // Verificar el conteo de fallos de página
    }
    
    @Test
    void testMaxMemoria() {
    	int min = 1;
    	int max = 9999;
    	int randomMaxMemory = Math.round((float) Math.random()*(max-min) + min); // Un número entre 1 y 9999 que define la capacidad máxima del cach
    	
    	FIFOCache cache = new FIFOCache(randomMaxMemory);
    	for(int i=1;i<=randomMaxMemory;i++) {
    		cache.accessPage(i);
    	}
    	assertEquals(randomMaxMemory,cache.getPageFaultCount()); // Hasta acá cada página accedida fue nueva, generando un fallo por acceso
    	
    	assertTrue(cache.accessPage(1)); // Debería recordar a 1 porque todavía no estamos superando la memoria máxima
    	try {
			assertEquals(1,cache.getPageFrameStatus().first().element()); // Corroboramos que 1 es el primer elemento de la cola, como página más antigua
		} catch (EmptyListException e) {
			fail("Cache no tiene primer elemento");
		}
    	
    	assertFalse(cache.accessPage(0)); // 0 nunca fue ingresado
    	assertEquals(randomMaxMemory+1,cache.getPageFaultCount());

    	try {
			assertEquals(2,cache.getPageFrameStatus().first().element()); // 1 debería haber sido eliminado, dejando a 2 como el próximo elemento a eliminar
		} catch (EmptyListException e) {
			fail("Cache no tiene primer elemento");
		}
    	assertFalse(cache.accessPage(1)); // 1 debería haber sido olvidado.
    	assertEquals(randomMaxMemory+2,cache.getPageFaultCount()); // El acceso a 1 debió generar un fallo porque no debería estar cacheado.
    }

    @Test
    void testCacheVacia() {
        FIFOCache cache = new FIFOCache(0);

        // Acceso a cualquier página debería ser un fallo debido a la capacidad 0
        assertFalse(cache.accessPage(1));  
        assertFalse(cache.accessPage(2));   
        assertFalse(cache.accessPage(3));  

        // Verificar el conteo de fallos  
        assertEquals(3, cache.getPageFaultCount());
        
		assertEquals(0,cache.getPageFrameStatus().size()); // Corroboramos que la caché no tiene ningún elemento
    }


    
}
