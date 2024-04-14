package pe.edu.tecsup.tienda.services;

import org.apache.log4j.Logger;

import pe.edu.tecsup.tienda.repositories.ProductoRepository;

/**
 * 
 * @author jgomez
 *
 */
public class ProductoService {

	private static final Logger log = Logger.getLogger(ProductoService.class);

	private ProductoRepository productoRepository;

	/**
	 * 
	 */
	public ProductoService() {

		this.productoRepository = new ProductoRepository();

	}

}
