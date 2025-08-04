package test;

 // Importa Mockito (mock, when, verify, etc.)

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.GestorPedidos;
import modelo.Producto;
import modelo.ProductoRepositorio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GestorPedidosMockTest {

	private ProductoRepositorio repositorioMock;
    private GestorPedidos gestor;
    private Producto manzana;

    @BeforeEach
    void setUp() {
        // Creamos el mock del repositorio con Mockito
        repositorioMock = mock(ProductoRepositorio.class);
        gestor = new GestorPedidos(repositorioMock);
        manzana = new Producto(1, "Manzana", 500.0, 10);

        // Definimos el comportamiento del mock: cuando reciba buscarPorId(1), devuelve manzana
        when(repositorioMock.buscarPorId(1)).thenReturn(manzana);
    }

    @Test
    void testObtenerProductoPorIdConMock() {
        Producto resultado = gestor.obtenerProductoPorId(1);

        assertNotNull(resultado, "El mock debe devolver el producto manzana");
        assertEquals("Manzana", resultado.getNombre());

        // Verificamos que buscarPorId(1) fue llamado exactamente una vez
        verify(repositorioMock, times(1)).buscarPorId(1);
    }
    
  //* ---------------------------------------------------------------------------------- */
 // Ciclo TDD para obtenerProductoPorId usando mockito:

 // RED: El test fue escrito antes de implementar el método en GestorPedidos.
 /*
 @Test
 void testObtenerProductoPorIdConMock() {
     // Este test fallaba porque obtenerProductoPorId no existía o no usaba el repositorio.
 }
 */

 // GREEN: Se implementó el método obtenerProductoPorId usando el mock.
 /*
 @Test
 void testObtenerProductoPorIdConMock() {
     // Con la implementación básica, el test comenzó a pasar.
 }
 */

 // REFACTOR: Se mejoró la implementación, se revisaron validaciones, el test sigue pasando.
 @Test
 void testObtenerProductoPorIdConMock() {
     Producto resultado = gestor.obtenerProductoPorId(1);

     assertNotNull(resultado, "El mock debe devolver el producto manzana");
     assertEquals("Manzana", resultado.getNombre());

     // Verificamos la interacción con el mock
     verify(repositorioMock, times(1)).buscarPorId(1);
 }
}
