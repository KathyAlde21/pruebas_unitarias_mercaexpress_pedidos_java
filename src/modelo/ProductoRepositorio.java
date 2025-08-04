package modelo;

public interface ProductoRepositorio {
	Producto buscarPorId(int id);
    void actualizarProducto(Producto producto);
}
