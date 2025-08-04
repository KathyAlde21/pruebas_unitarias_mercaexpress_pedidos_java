package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.GestorPedidos;
import modelo.Pedido;
import modelo.Producto;

class GestorPedidosTest {
	
	// Definimos variables que usaremos en muchos tests
	private Producto manzana;
    private Producto pera;
    private Producto naranja;
    private Pedido pedido;
    private GestorPedidos gestor;
	
    /**
     * Este método se ejecuta **antes de cada test**.
     * Sirve para crear los objetos de prueba (fixture).
     * Así, cada test empieza con los mismos objetos y datos iniciales.
     */
    @BeforeEach
    void setUp() {
    	manzana = new Producto(1, "Manzana", 500.0, 10); // 10 manzanas disponibles
        pera = new Producto(2, "Pera", 700.0, 5);        // 5 peras disponibles
        setNaranja(new Producto(3, "Naranja", 400.0, 5));  // 5 naranjas disponibles
        pedido = new Pedido();                           // Pedido vacío para cada test
        gestor = new GestorPedidos();                    // Gestor nuevo para cada test
    }
	

	/* ---------------------------------------------------------------------------------------------------- */
    
	// Test para validarStock
	@Test
    void testValidarStockSuficiente() {
        Producto manzana = new Producto(1, "Manzana", 500.0, 10);
        Producto pera = new Producto(2, "Pera", 700.0, 5);

        Pedido pedido = new Pedido();
        pedido.agregarProducto(manzana, 2); // hay stock suficiente
        pedido.agregarProducto(pera, 1);    // hay stock suficiente

        GestorPedidos gestor = new GestorPedidos();
        boolean stockValido = gestor.validarStock(pedido);

        assertTrue(stockValido);
    }

    @Test
    void testValidarStockInsuficiente() {
        Producto manzana = new Producto(1, "Manzana", 500.0, 1);

        Pedido pedido = new Pedido();
        pedido.agregarProducto(manzana, 2); // pide más de lo que hay

        GestorPedidos gestor = new GestorPedidos();
        boolean stockValido = gestor.validarStock(pedido);

        assertFalse(stockValido);
    }
    
    /* ---------------------------------------------------------------------------------------------------- */
    /**
     * Este test verifica que se pueda realizar un pedido si hay suficiente stock.
     * Utiliza la manzana del fixture, que ya tiene 10 unidades.
     */
    @Test
    void testRealizarPedidoConStockSuficiente() {
        pedido.agregarProducto(manzana, 3); // Pide 3 manzanas, hay 10
        boolean resultado = gestor.realizarPedido(pedido);

        assertTrue(resultado, "El pedido debe realizarse correctamente");
        assertEquals(7, manzana.getStockDisponible(), "El stock debe descontarse correctamente");
        assertTrue(pedido.estaConfirmado(), "El pedido debe estar confirmado");
    }
    
    /**
     * Este test verifica que NO se pueda realizar un pedido si falta stock.
     * Utiliza la pera del fixture, que solo tiene 5 unidades.
     */
    @Test
    void testRealizarPedidoConStockInsuficiente() {
        pedido.agregarProducto(pera, 6); // Pide 6 peras, solo hay 5
        boolean resultado = gestor.realizarPedido(pedido);

        assertFalse(resultado, "El pedido NO debe realizarse");
        assertEquals(5, pera.getStockDisponible(), "El stock NO debe cambiar");
        assertFalse(pedido.estaConfirmado(), "El pedido NO debe estar confirmado");
    }

    
    /* ---------------------------------------------------------------------------------------------------- */
    // Test para cancelarPedido
    @Test
    void testCancelarPedido() {
        Producto naranja = new Producto(3, "Naranja", 400.0, 5);
        Pedido pedido = new Pedido();
        pedido.agregarProducto(naranja, 2);

        GestorPedidos gestor = new GestorPedidos();
        gestor.realizarPedido(pedido); // primero confirma y descuenta stock

        boolean resultado = gestor.cancelarPedido(pedido);

        assertTrue(resultado, "El pedido debe poder cancelarse");
        assertEquals(5, naranja.getStockDisponible(), "El stock debe reponerse al cancelar");
        assertFalse(pedido.estaConfirmado(), "El pedido ya NO debe estar confirmado");
    }
    
    @Test
    void testCancelarPedidoNoConfirmado() {
        Producto platano = new Producto(4, "Plátano", 350.0, 3);
        Pedido pedido = new Pedido();
        pedido.agregarProducto(platano, 1);

        GestorPedidos gestor = new GestorPedidos();
        // No se realiza el pedido (no se confirma)

        boolean resultado = gestor.cancelarPedido(pedido);

        assertFalse(resultado, "No se debe poder cancelar un pedido no confirmado");
        assertEquals(3, platano.getStockDisponible(), "El stock no debe cambiar");
    }


	public Producto getNaranja() {
		return naranja;
	}

	public void setNaranja(Producto naranja) {
		this.naranja = naranja;
	}
    
	//* ---------------------------------------------------------------------------------- */
	// Ciclo TDD para validarStock:

	// RED: Escribimos el test antes de que existiera el método validarStock.
	/*
	@Test
	void testValidarStockSuficiente() {
	    // Este test fallaba porque validarStock no existía en GestorPedidos.
	}
	*/

	// GREEN: Se implementó validarStock con lo básico para pasar el test.
	/*
	@Test
	void testValidarStockSuficiente() {
	    // Al implementar validarStock, el test pasó correctamente.
	}
	*/

	// REFACTOR: Se mejoró la implementación para manejar casos límite.
	@Test
	void testValidarStockSuficiente() {
	    pedido.agregarProducto(manzana, 2); // hay stock suficiente
	    pedido.agregarProducto(pera, 1);    // hay stock suficiente

	    boolean stockValido = gestor.validarStock(pedido);

	    assertTrue(stockValido);
	}
}
