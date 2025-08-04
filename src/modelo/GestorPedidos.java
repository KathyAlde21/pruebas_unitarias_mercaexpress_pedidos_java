package modelo;

/*
 * Clase GestorPedidos con lógica de negocio:
 * - realizarPedido(Pedido pedido)
 * - cancelarPedido(Pedido pedido)
 * - validarStock(Pedido pedido)
 */

public class GestorPedidos {

	/******************************************************************************/
    //CONSTRUCTORES PARA TEST MOCK
    private ProductoRepositorio repositorio;

    // Constructor para usar con mocks en pruebas
    public GestorPedidos(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    // Constructor normal para uso habitual
    public GestorPedidos() {
        this.repositorio = null;
    }

    // Método de ejemplo que usa el repositorio
    public Producto obtenerProductoPorId(int id) {
        if (repositorio == null) {
            return null;
        }
        return repositorio.buscarPorId(id);
    }
	
	/******************************************************************************/
	//CONSTRUCTORES PARA TEST JUNIT
	// Valida si hay suficiente stock para todos los productos del pedido
    public boolean validarStock(Pedido pedido) {
        for (Pedido.ItemPedido item : pedido.getProductos()) {
            Producto producto = item.getProducto();
            int cantidadSolicitada = item.getCantidad();
            if (producto.getStockDisponible() < cantidadSolicitada) {
                return false;
            }
        }
        return true;
    }

    // Realiza el pedido: descuenta stock si hay suficiente y devuelve true si se realizó, false si no.
    public boolean realizarPedido(Pedido pedido) {
        if (!validarStock(pedido)) {
            return false;
        }
        for (Pedido.ItemPedido item : pedido.getProductos()) {
            Producto producto = item.getProducto();
            int cantidad = item.getCantidad();
            // Descontar el stock disponible
            producto.descontarStock(cantidad);
        }
        pedido.confirmarPedido();
        return true;
    }

     
    // Cancela el pedido y repone el stock
    public boolean cancelarPedido(Pedido pedido) {
        if (!pedido.estaConfirmado()) {
            return false; // Solo se puede cancelar si ya estaba confirmado
        }
        for (Pedido.ItemPedido item : pedido.getProductos()) {
            Producto producto = item.getProducto();
            int cantidad = item.getCantidad();
            // Devolver el stock al producto
            producto.reponerStock(cantidad);
        }
        pedido.anularPedido();
        return true;
    }
    
    //* ---------------------------------------------------------------------------------- */
    // TDD - validarStock
    /*
     * RED: El método no existía y los tests fallaban.
     * GREEN: Se agregó el método con lógica simple para aprobar el test.
     * REFACTOR: Se refinó el método para comprobar todos los productos del pedido.
     */
    public boolean validarStock(Pedido pedido) {
        for (Pedido.ItemPedido item : pedido.getProductos()) {
            Producto producto = item.getProducto();
            int cantidadSolicitada = item.getCantidad();
            if (producto.getStockDisponible() < cantidadSolicitada) {
                return false;
            }
        }
        return true;
    }  
 // TDD - obtenerProductoPorId usando mock
    /*
     * RED: El método no existía o no usaba el repositorio, así que el test fallaba.
     * GREEN: Se creó el método usando el repositorio y el mock.
     * REFACTOR: Se añadió validación para evitar null pointer.
     */
    public Producto obtenerProductoPorId(int id) {
        if (repositorio == null) {
            return null; // o lanzar una excepción según diseño
        }
        return repositorio.buscarPorId(id);
    }
}
