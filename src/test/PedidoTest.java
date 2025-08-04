package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Pedido;
import modelo.Producto;

class PedidoTest {

	// Objetos de prueba para usar en los tests
    private Producto manzana;
    private Producto pera;
    private Pedido pedido;

    /**
     * Se ejecuta antes de cada test.
     * Prepara los objetos comunes para los tests.
     */
    @BeforeEach
    void setUp() {
        manzana = new Producto(1, "Manzana", 500.0, 10);
        pera = new Producto(2, "Pera", 700.0, 5);
        pedido = new Pedido();
    }

    /**
     * Verifica que se pueden agregar productos y calcular el total.
     */
	@Test
	void testAgregarProductoYCalcularTotal() {

        pedido.agregarProducto(manzana, 2); // 2 x 500 = 1000
        pedido.agregarProducto(pera, 1);    // 1 x 700 = 700

        assertEquals(2, pedido.getProductos().size());
        assertEquals(1700.0, pedido.calcularTotal());
    }	
	
	/**
     * Verifica que un pedido nuevo no está confirmado ni anulado.
     */
    @Test
    void testPedidoNoConfirmadoAlCrear() {
        assertFalse(pedido.estaConfirmado());
    }

    /**
     * Verifica el comportamiento de confirmar un pedido.
     */
    @Test
    void testConfirmarPedido() {
        pedido.confirmarPedido();
        assertTrue(pedido.estaConfirmado());
    }

    /**
     * Verifica el comportamiento de anular un pedido.
     */
    @Test
    void testAnularPedido() {
        pedido.confirmarPedido(); // Confirmar antes de anular
        pedido.anularPedido();
        assertFalse(pedido.estaConfirmado());
    }
    
    //* ---------------------------------------------------------------------------------- */
	 // Ciclo TDD para agregarProducto:
	
	 // RED: Escribimos el test antes de que exista el método o la lógica
	 /*
	 @Test
	 void testAgregarProductoYCalcularTotal() {
	     // Este test fallaba al inicio porque agregarProducto no estaba implementado.
	 }
	 */
	
	 // GREEN: Se implementa la lógica mínima para que el test pase.
	 /*
	 @Test
	 void testAgregarProductoYCalcularTotal() {
	     // Al implementar agregarProducto en Pedido, el test comenzó a pasar.
	 }
	 */
	
	 // REFACTOR: Mejoramos el código si es necesario y nos aseguramos que los tests siguen pasando.
	 @Test
	 void testAgregarProductoYCalcularTotal() {
	     // Test final tras refactorización. Código más limpio y sigue aprobando el test.
	     pedido.agregarProducto(manzana, 2); // 2 x 500 = 1000
	     pedido.agregarProducto(pera, 1);    // 1 x 700 = 700
	     assertEquals(2, pedido.getProductos().size());
	     assertEquals(1700.0, pedido.calcularTotal());
 }
}
