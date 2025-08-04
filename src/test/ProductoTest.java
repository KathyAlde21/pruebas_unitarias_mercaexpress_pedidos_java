package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Producto;

class ProductoTest {

	// Variable de producto que usaremos en varios tests
    private Producto producto;

    /**
     * Este método se ejecuta antes de cada test.
     * Crea un producto de prueba para asegurarnos que cada test
     * empiece con el mismo objeto, en el mismo estado.
     */
    @BeforeEach
    void setUp() {
        producto = new Producto(1, "Manzana", 500.0, 10);
    }

    /**
     * Verifica que el constructor y los getters funcionan correctamente.
     */
	@Test
	void testCrearProducto() {
        
        // Verificamos que los atributos estén bien asignados
        assertEquals(1, producto.getId());
        assertEquals("Manzana", producto.getNombre());
        assertEquals(500.0, producto.getPrecio());
        assertEquals(10, producto.getStockDisponible());
    }
	
	 /**
     * Verifica que descontarStock reduce el stock correctamente.
     */
    @Test
    void testDescontarStock() {
        producto.descontarStock(3);
        assertEquals(7, producto.getStockDisponible());
    }

    /**
     * Verifica que reponerStock aumenta el stock correctamente.
     */
    @Test
    void testReponerStock() {
        producto.reponerStock(5);
        assertEquals(15, producto.getStockDisponible());
    }

    /**
     * Verifica que descontarStock no baja el stock si la cantidad es mayor al disponible.
     */
    @Test
    void testDescontarStockMayorQueDisponible() {
        producto.descontarStock(20);
        assertEquals(10, producto.getStockDisponible(), 
            "El stock no debe cambiar si intento descontar más de lo disponible");
    }

    /**
     * Verifica que no se descuente stock con cantidad negativa.
     */
    @Test
    void testDescontarStockNegativo() {
        producto.descontarStock(-1);
        assertEquals(10, producto.getStockDisponible());
    }
    
    //* ---------------------------------------------------------------------------------- */
	 // Ciclo TDD para descontarStock:
	
	 // RED: Test escrito antes de que existiera el método descontarStock en Producto.
	 /*
	 @Test
	 void testDescontarStock() {
	     // Este test fallaba porque descontarStock no estaba implementado.
	 }
	 */
	
	 // GREEN: Se implementó el método con lo mínimo para aprobar el test.
	 /*
	 @Test
	 void testDescontarStock() {
	     // Al implementar descontarStock correctamente, el test pasó.
	 }
	 */
	
	 // REFACTOR: Mejoramos validaciones dentro del método y el test sigue pasando.
	 @Test
	 void testDescontarStock() {
	     producto.descontarStock(3);
	     assertEquals(7, producto.getStockDisponible());
	 }  
}
