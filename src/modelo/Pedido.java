package modelo;

/*
 * Clase Pedido con una lista de productos y métodos como:
 * - agregarProducto(Producto producto, int cantidad)
 * - calcularTotal()
 * - confirmarPedido()
 */
import java.util.ArrayList;
import java.util.List;

public class Pedido {
	
	// Clase interna para relacionar producto y cantidad
    public static class ItemPedido {
        private Producto producto;
        private int cantidad;

        public ItemPedido(Producto producto, int cantidad) {
            this.producto = producto;
            this.cantidad = cantidad;
        }        

        /* -------------------------------------------------------------- */
        //Getter y Setter
		/**
		 * @return the producto
		 */
		public Producto getProducto() {
			return producto;
		}

		/**
		 * @param producto the producto to set
		 */
		public void setProducto(Producto producto) {
			this.producto = producto;
		}

		/**
		 * @return the cantidad
		 */
		public int getCantidad() {
			return cantidad;
		}

		/**
		 * @param cantidad the cantidad to set
		 */
		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
		
		public double getSubtotal() {
            return producto.getPrecio() * cantidad;
        }
    }
    
    /* -------------------------------------------------------------- */
    private List<ItemPedido> productos = new ArrayList<>();
    private boolean confirmado = false;
    private boolean anulado = false;
    
    
    public void agregarProducto(Producto producto, int cantidad) {
        productos.add(new ItemPedido(producto, cantidad));
    }


    //Getter y Setter
	/**
	 * @return the productos
	 */
	public List<ItemPedido> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(List<ItemPedido> productos) {
		this.productos = productos;
	}

	/* -------------------------------------------------------------- */
	public double calcularTotal() {
        double total = 0.0;
        for (ItemPedido item : productos) {
            total += item.getSubtotal();
        }
        return total;
    }
	
	/* -------------------------------------------------------------- */
	public void confirmarPedido() {
	    confirmado = true;
	    setAnulado(false);
	}

	public void anularPedido() {
	    confirmado = false;  
	    setAnulado(true);
	}

	public boolean estaConfirmado() {
	    return confirmado;
	}


	public boolean isAnulado() {
		return anulado;
	}


	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}
	
	//* ---------------------------------------------------------------------------------- */
	// TDD - agregarProducto
	/*
	 * RED: No existía el método, el test fallaba.
	 * GREEN: Creamos el método con lo mínimo para aprobar el test.
	 * REFACTOR: Mejoramos la implementación para permitir múltiples productos y cantidades.
	 */
	public void agregarProducto(Producto producto, int cantidad) {
	    productos.add(new ItemPedido(producto, cantidad));
	}
}
