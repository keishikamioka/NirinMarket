import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * File upload
 *
 * @author tomo
 */
@WebServlet(urlPatterns = {"/Uploasquery"})
@MultipartConfig
public class Uploadquery extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	request.setCharacterEncoding("UTF-8");

    	queryInsert(request);

    	ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/jump.jsp");
        dispatcher.forward(request, response);

    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
 	private void queryInsert(HttpServletRequest request) throws SecurityException, IOException {
 		//DOMConfigurator.configure("log4j.xml");
 		//log
 				Logger logger = Logger.getLogger(Uploadquery.class.getName());
 		        logger.setLevel(Level.FINEST);

 		     // ハンドラーを作成してロガーに登録
 		        Handler handler = new FileHandler("C:\\Users\\berry\\Develope Java\\pleiades\\workspace\\FileUploadWeb-master\\log\\sample.log");
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

 		    	String sql = "";

 		        // 画面から入力されたバイク情報を格納する。
 		        String baseno = request.getParameter("BaseNo");
 		        int BaseInfo = Integer.parseInt(baseno);
 		        String UserName = request.getParameter("UserName");
 		        String Explain= request.getParameter("Explain");
	    	    request.setAttribute("baseno",BaseInfo );

 		        //画面からの情報をデータベースに格納する。
 		        try {
 		            Class.forName("com.mysql.jdbc.Driver").newInstance();
 		    	       String connectionUrl = "jdbc:mysql://localhost:3306/sample" +
 		    	    		   "?user=root&password=Kami5330&characterEncoding=UTF-8&serverTimezone=JST";
 		    	    		Connection con = DriverManager.getConnection(connectionUrl);

 		    	    		 System.out.print("接続しました");

 		    	    	sql = "insert into sample.queryinfo(BaseInfo, UserName, QueryText) values ("
 		    	    			+ "'" +BaseInfo+ "'" + ","
 		    	    			+ "'" +UserName+ "'" + ","
 		    	    			+ "'" +Explain+ "'" + ");";

 		    	    	logger.info(sql);

 		    	        Statement stmt = con.createStatement();
 		    	        int test = stmt.executeUpdate(sql);




 		    	        stmt.close();
 		    	        con.close();

 		            }catch (Exception e) {
 		    	        e.printStackTrace();
 		        logger.log(Level.FINER, "エラー発生", e);
 		    	      }

 		        }
}
