package ftmk.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import ftmk.model.Professor;
import ftmk.ws.consumer.ProfessorFacade;

/**
* Servlet implementation class SearchServlet
*/
@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
 

	/**
	 * @see HttpServlet#HttpServlet()
	 */
 
	public SearchServlet() {

		super();
		// TODO Auto-generated constructor stub
 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request,
	 * HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, 
	 * HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Get parameter
		String param = request.getParameter("param");

		try {

			ProfessorFacade professorFacade = new ProfessorFacade();
			List<Professor> result = professorFacade.searchProfessors(param);

			// Put result in session
			HttpSession session = request.getSession();
			session.setAttribute("size", result.size());
			session.setAttribute("result", result);

			// Redirect result to searchResult.jsp
			response.sendRedirect("searchResult.jsp");

		} catch (Exception ex) {

			ex.printStackTrace();

		}
	}
}