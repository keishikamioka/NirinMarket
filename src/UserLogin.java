import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sample.bean.bikeinfoBean;


/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void CheckUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



 		//log
			Logger logger = Logger.getLogger(Uploadquery.class.getName());
	        logger.setLevel(Level.FINEST);

	     // ハンドラーを作成してロガーに登録
	        Handler handler = new FileHandler("C:\\Users\\berry\\Develope Java\\pleiades\\workspace\\NirinMarket\\log\\sample.log");
	        logger.addHandler(handler);

	        // フォーマッターを作成してハンドラーに登録
	        Formatter formatter =  new SimpleFormatter();
	        handler.setFormatter(formatter);

	        // INFOメッセージを出力
	        logger.log(Level.FINEST, "INFOメッセージ");

	        // それぞれのログレベルのメッセージを出力する簡易メソッドが用意されています。
	        logger.finest("FINESTメッセージ");
	        logger.finer("FINERメッセージ");
	        logger.fine("FINEメッセージ");
	        logger.config("CONFIGメッセージ");
	        logger.info("INFOメッセージ");
	        logger.warning("WARNINGメッセージ");
	        logger.severe("SEVEREメッセージ");


    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String Cpassword = "";
    	HttpSession session = request.getSession();

    	String sql = "";

    	logger.info("セッションのユーザ名は："+(String)session.getAttribute("username"));

    	if((String)session.getAttribute("username")!= null) {
    		logger.info("セッションあり");
    		ServletContext context = this.getServletContext();
	            RequestDispatcher dispatcher = context.getRequestDispatcher("/alreadylogin.html");
	            dispatcher.forward(request, response);
	            return;

    	}else {
    		logger.info("セッションなし");

    		try {
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	    	       String connectionUrl = "jdbc:mysql://localhost:3306/sample" +
	    	    		   "?user=root&password=Kami5330&characterEncoding=UTF-8&serverTimezone=JST";
	    	    		Connection con = DriverManager.getConnection(connectionUrl);

	    	    		 System.out.print("接続しました");

	    	    	sql = "select password from sample.userinfo where username = " + "'" + username +"';";

	    	    	logger.info(sql);

	    	    	Statement stmt = con.createStatement();
	 		        ResultSet rs = stmt.executeQuery(sql);
	 		        List<bikeinfoBean> list = new ArrayList<bikeinfoBean>();

	 		      // Formatter fm = new Formatter();


	 		      while (rs.next()) {
	 		        	//出品日
	 		    	 Cpassword  = rs.getString("password");

	 		        }

	 		     logger.info("ユーザネーム:"+username);
	 	    	 logger.info("パスワード:"+password);
	 		     logger.info("取得パスワード:"+Cpassword);

	 		        rs.close();
	 		        stmt.close();
	 		        con.close();


	 		    	if(Cpassword.equals(password)) {
	 		    		logger.info("aaaaaaaaaaaaaaaaaaaaaa");
	 		    		session = request.getSession();
	 		    		session.setAttribute("username",username);

	 		    		ServletContext context = this.getServletContext();
	 		            RequestDispatcher dispatcher = context.getRequestDispatcher("/jump.jsp");
	 		            dispatcher.forward(request, response);
	 		            return;
	 		    	}else {
	 		    		logger.info("bbbbbbbbbbbbbbbbbbbbbb");
	 		    		ServletContext context = this.getServletContext();
	 		            RequestDispatcher dispatcher = context.getRequestDispatcher("/index.html");
	 		            dispatcher.forward(request, response);
	 		            return;

	 		    	}

    		}catch (Exception e) {
	    	        e.printStackTrace();
	    	        logger.log(Level.FINER, "エラー発生", e);
	    	      }
    	}
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
		//doGet(request, response);
		CheckUser(request, response);
	}

}
