package pe.edu.tecsup.tienda.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import pe.edu.tecsup.tienda.entities.Categoria;
import pe.edu.tecsup.tienda.entities.Producto;
import pe.edu.tecsup.tienda.util.ConexionBD;

/**
 * 
 * @author jgomez
 *
 */
public class ProductoRepository {

	private static final Logger log = Logger.getLogger(ProductoRepository.class);

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Producto> listar() throws Exception {
		log.info("call listar()");

		Connection con = ConexionBD.obtenerConexion();

		String query = """
				SELECT p.id, p.categorias_id, c.nombre AS categorias_nombre,
					   p.nombre, p.descripcion, p.precio, p.stock, p.imagen_nombre,
					   p.imagen_tipo, p.imagen_tamanio, p.creado, p.estado
				FROM productos p INNER JOIN categorias c ON c.id=p.categorias_id
				WHERE estado=1
				ORDER BY id
				""";

		PreparedStatement stmt = con.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		List<Producto> lista = new ArrayList<Producto>();

		while (rs.next()) {
			Producto producto = new Producto();
			producto.setId(rs.getInt("id"));
			producto.setCategorias_id(rs.getInt("categorias_id"));
			Categoria categoria = new Categoria();
			categoria.setId(rs.getInt("categorias_id"));
			categoria.setNombre(rs.getString("categorias_nombre"));
			producto.setCategoria(categoria);
			producto.setNombre(rs.getString("nombre"));
			producto.setDescripcion(rs.getString("descripcion"));
			producto.setPrecio(rs.getDouble("precio"));
			if (rs.wasNull())
				producto.setPrecio(null);
			producto.setStock(rs.getInt("stock"));
			producto.setImagen_nombre(rs.getString("imagen_nombre"));
			producto.setImagen_tipo(rs.getString("imagen_tipo"));
			producto.setImagen_tamanio(rs.getLong("imagen_tamanio"));
			if (rs.wasNull())
				producto.setImagen_tamanio(null);
			producto.setEstado(rs.getInt("estado"));
			lista.add(producto);
		}
		rs.close();
		stmt.close();
		con.close();
		log.info("success! " + lista);
		return lista;
	}
}
