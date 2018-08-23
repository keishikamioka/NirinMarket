

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet(urlPatterns = {"/LoginCheck"})
@MultipartConfig
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void ProvideURL(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");

    	String URLpattern = "";
    	String URLType = request.getParameter("urltype");

    	HttpSession session = request.getSession();
    	if((String)session.getAttribute("username")!= null) {
    		switch (URLType) {
            case "login":
                URLpattern = "./alreadylogin.html";
                break;
            case "exhibit":
            	URLpattern = "./exhibt.html";
                break;
            case "chat":
            	URLpattern = "./chat.html";
                break;
            case "buy":
            	URLpattern = "./buy.html";
            }
    	}
    	else {
    		URLpattern = "./login.html";
    	}

    	ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(URLpattern);
        dispatcher.forward(request, response);

    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProvideURL(request, response);
	}

}
