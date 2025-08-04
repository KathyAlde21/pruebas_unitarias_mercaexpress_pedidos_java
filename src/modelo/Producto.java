package modelo;

/*
 * Clase Producto con atributos: id, nombre, precio, stockDisponible.
 */

public class Producto {

	private int id;
    private String nombre;
    private double precio;
    private int stockDisponible;

    public Producto(int id, String nombre, double precio, int stockDisponible) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stockDisponible = stockDisponible;
    }

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * @return the stockDisponible
	 */
	public int getStockDisponible() {
		return stockDisponible;
	}

	/**
	 * @param stockDisponible the stockDisponible to set
	 */
	public void setStockDisponible(int stockDisponible) {
		this.stockDisponible = stockDisponible;
	}
	
	/* -------------------------------------------------------------- */
	public void descontarStock(int cantidad) {
	    if (cantidad > 0 && stockDisponible >= cantidad) {
	        stockDisponible -= cantidad;
	    }
	}

	public void reponerStock(int cantidad) {
	    if (cantidad > 0) {
	        stockDisponible += cantidad;
	    }
	}
	
	//* ---------------------------------------------------------------------------------- */
	// TDD - descontarStock
	/*
	 * RED: El método no existía, el test fallaba.
	 * GREEN: Se creó el método con lo mínimo necesario.
	 * REFACTOR: Se añadieron validaciones para evitar valores negativos y controlar el stock.
	 */
	public void descontarStock(int cantidad) {
	    if (cantidad > 0 && stockDisponible >= cantidad) {
	        stockDisponible -= cantidad;
	    }
	}
}
