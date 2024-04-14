package pe.edu.tecsup.tienda.servlets.carrito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import pe.edu.tecsup.tienda.entities.Producto;
import pe.edu.tecsup.tienda.services.ProductoService;

/**
 * Servlet implementation class CarritoComprarServlet
 */
@WebServlet("/CarritoComprarServlet")
public class CarritoComprarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(CarritoComprarServlet.class);

	private ProductoService productoService;

	public CarritoComprarServlet() {
		this.productoService = new ProductoService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		log.info("Post CarritoComprarServlet");
		
		try {
			String id = request.getParameter("id");
			//log.info("id: " + id);
			
			Producto producto_seleccionado 
				= productoService.obtener(Integer.parseInt(id));
			
			log.info(producto_seleccionado);
			
			HttpSession session = request.getSession();
			
			List<Producto> productos 
				= (List<Producto>) session.getAttribute("mis_productos");
			
			// Es mi primer producto ?
			if (productos == null) {
				productos = new ArrayList<Producto>();
			} else {
				productos.add(producto_seleccionado);
			}
			
			session.setAttribute("mis_productos", productos);
			
			request.getSession().setAttribute("success", "Producto añadido al carrito");
			
			response.sendRedirect(request.getContextPath() + "/CarritoListarServlet");
		
		} catch (Exception e) {
			log.error(e, e);
			throw new ServletException(e.getMessage(), e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
