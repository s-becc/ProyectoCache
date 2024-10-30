package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import FIFOCache.FIFOCache;
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
    void testCacheUnaPagina() {
        FIFOCache cache = new FIFOCache(1);
        
        // Acceso a una página, debería generar un fallo
        assertFalse(cache.accessPage(1));
        
        // Acceso repetido a la misma página, no debería generar fallos adicionales
        assertTrue(cache.accessPage(1));   

        // Acceso a una nueva página, elimina la anterior
        assertFalse(cache.accessPage(2)); 

        // Verificar el conteo de fallos de página
        assertEquals(2, cache.getPageFaultCount());
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
    }


    
}
