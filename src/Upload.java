import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * File upload
 *
 * @author tomo
 */
@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig
public class Upload extends HttpServlet {

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

		//log
		Logger logger = Logger.getLogger(Upload.class.getName());
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

    	int exno = 0;
    	int num = 1;
    	int ExNO = 1;
    	String sql2 = "";

    	String sh = ".jpg";
        // 画面から入力されたバイク情報を格納する。
        String UserName = request.getParameter("UserName");
        String eMail = request.getParameter("eMail");
        String UserPass= request.getParameter("UserPass");
        String Manu = request.getParameter("Manu");
        String Model = request.getParameter("Model");
        String Exyear = request.getParameter("Exyear");
        String Dis = request.getParameter("Dis");
        String ExArea = request.getParameter("ExArea");
        String Price = request.getParameter("Price");
        String Explain = request.getParameter("Explain");

     // バイク情報ExNOを取得する
        try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
	       String connectionUrl = "jdbc:mysql://localhost:3306/sample" +
	    		   "?user=root&password=Kami5330&characterEncoding=UTF-8&serverTimezone=JST";
	    		Connection con = DriverManager.getConnection(connectionUrl);

	    		 System.out.print("接続しました");

	    	String sql = "select MAX(ExNO) from bikeinfotest;";
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);

	        if(rs.next()){
	        exno = rs.getInt("MAX(ExNO)");
	        }


	        rs.close();
	        stmt.close();
	        con.close();

        }catch (Exception e) {
	        e.printStackTrace();
	        logger.log(Level.FINER, "エラー発生", e);
	      }

        ExNO += exno;
        //画面からの情報をデータベースに格納する。
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
    	       String connectionUrl = "jdbc:mysql://localhost:3306/sample" +
    	    		   "?user=root&password=Kami5330&characterEncoding=UTF-8&serverTimezone=JST";
    	    		Connection con = DriverManager.getConnection(connectionUrl);

    	    		 System.out.print("接続しました");

    	    	sql2 = "insert into sample.bikeinfotest (ExNO, UserName, eMail, UserPass, Manu, Model, Exyear, Dis, ExArea, price, ExExplain, ExDate, ExInfo) values("
    	    			+ "'" +ExNO+ "'" + ","
    	    			+ "'" +UserName+ "'" + ","
    	    			+ "'" +eMail+ "'" + ","
    	    			+ "'" +UserPass+ "'" + ","
    	    			+ "'" +Manu+ "'" + ","
    	    			+ "'" +Model+ "'" + ","
    	    			+ "'" +Exyear+ "'" + ","
    	    			+ "'" +Dis+ "'" + ","
    	    			+ "'" +ExArea+ "'" + ","
    	    			+ "'" +Price+ "'" + ","
    	    			+ "'" +Explain+ "'" + ","
    	    			+ "now()"+ ","
    	    			+ "'0'" + ");";

    	    	logger.info(sql2);

    	        Statement stmt = con.createStatement();
    	        int test = stmt.executeUpdate(sql2);

    	        //rs.close();
    	        stmt.close();
    	        con.close();

            }catch (Exception e) {
    	        e.printStackTrace();
        logger.log(Level.FINER, "エラー発生", e);
    	      }
//		try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//    	       String connectionUrl = "jdbc:mysql://localhost:3306/sample" +
//    	    		   "?user=root&password=Kami5330&characterEncoding=UTF-8&serverTimezone=JST";
//    	    		Connection con = DriverManager.getConnection(connectionUrl);
//
//    	    		 System.out.print("接続しました");
//
//    	    	sql2 = "insert into bikeinfo (ExNO, Manu, Model, Exyear, Dis, ExArea, price) values("
//    	    			+ "'18'" + ","
//    	    			+ "'kawasaki'" + ","
//    	    			+ "'ninja'" + ","
//    	    			+ "'2010'" + ","
//    	    			+ "'25000'" + ","
//    	    			+ "'東京都'" + ","
//    	    			+ "'250000'" + ");";
//
//    	    	System.out.print(sql2);
//
//    	        Statement stmt = con.createStatement();
//    	        int test = stmt.executeUpdate(sql2);
//
//    	        //rs.close();
//    	        stmt.close();
//    	        con.close();
//
//            }catch (Exception e) {
//    	        e.printStackTrace();
//    	        logger.log(Level.FINER, "エラー発生", e);
//    	      }

        // 画面遷移先で保存したファイルパスを表示
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>File upload result</h1>");
            out.println("<div>");
            //out.println("upload succeed[file path:" + uploadPath + "]");
            out.println("SQL文："+ sql2 +")");
            out.println("<div>");
            out.println("</body>");
            out.println("</html>");
        }

        // 画面から入力されたファイル情報を取得する。
        Part part = request.getPart("file1");
        File newfile = new File("C:\\Users\\berry\\Develope Java\\pleiades\\workspace\\FileUploadWeb-master\\web\\image\\"+ExNO);
        newfile.mkdir();
        Path uploadPath = Paths.get("C:\\Users\\berry\\Develope Java\\pleiades\\workspace\\FileUploadWeb-master\\web\\image\\" + ExNO +"\\"+ ExNO + "-1" + sh);
     // アップしたファイルを保存する。
        InputStream in = part.getInputStream();
        Files.copy(in, uploadPath, StandardCopyOption.REPLACE_EXISTING);


        Part part2 = request.getPart("file2");
        Part part3 = request.getPart("file3");
        Part part4 = request.getPart("file4");
        Part part5 = request.getPart("file5");
        Part part6 = request.getPart("file6");
        Part part7 = request.getPart("file7");
        Part part8 = request.getPart("file8");
        Part part9 = request.getPart("file9");

       if (part2.getSize() != 0){
        Path uploadPath2 = Paths.get("C:\\Users\\berry\\Develope Java\\pleiades\\workspace\\FileUploadWeb-master\\web\\image\\" + ExNO +"\\"+ ExNO + "-2" + sh);
        InputStream in2 = part2.getInputStream();
            Files.copy(in2, uploadPath2, StandardCopyOption.REPLACE_EXISTING);
       }

       if (part3.getSize() != 0){
       	Path uploadPath3 = Paths.get("C:\\Users\\berry\\Develope Java\\pleiades\\workspace\\FileUploadWeb-master\\web\\image\\" + ExNO +"\\"+ ExNO + "-3" + sh);
       	InputStream in3 = part3.getInputStream();
           Files.copy(in3, uploadPath3, StandardCopyOption.REPLACE_EXISTING);
      }

       if (part4.getSize() != 0){
       	Path uploadPath4 = Paths.get("C:\\Users\\berry\\Develope Java\\pleiades\\workspace\\FileUploadWeb-master\\web\\image\\" + ExNO +"\\"+ ExNO + "-4" + sh);
       	InputStream in4 = part4.getInputStream();
           Files.copy(in4, uploadPath4, StandardCopyOption.REPLACE_EXISTING);
      }

       if (part5.getSize() != 0){
       	Path uploadPath5 = Paths.get("C:\\Users\\berry\\Develope Java\\pleiades\\workspace\\FileUploadWeb-master\\web\\image\\" + ExNO +"\\"+ ExNO + "-5" + sh);
       	InputStream in5 = part5.getInputStream();
           Files.copy(in5, uploadPath5, StandardCopyOption.REPLACE_EXISTING);
      }

       if (part6.getSize() != 0){
       	Path uploadPath6 = Paths.get("C:\\Users\\berry\\Develope Java\\pleiades\\workspace\\FileUploadWeb-master\\web\\image\\" + ExNO +"\\"+ ExNO + "-6" + sh);
       	InputStream in6 = part6.getInputStream();
           Files.copy(in6, uploadPath6, StandardCopyOption.REPLACE_EXISTING);
      }

       if (part7.getSize() != 0){
       	Path uploadPath7 = Paths.get("C:\\Users\\berry\\Develope Java\\pleiades\\workspace\\FileUploadWeb-master\\web\\image\\" + ExNO +"\\"+ ExNO + "-7" + sh);
       	InputStream in7 = part7.getInputStream();
           Files.copy(in7, uploadPath7, StandardCopyOption.REPLACE_EXISTING);
       }
       if (part8.getSize() != 0){
       	Path uploadPath8 = Paths.get("C:\\Users\\berry\\Develope Java\\pleiades\\workspace\\FileUploadWeb-master\\web\\image\\" + ExNO +"\\"+ ExNO + "-8" + sh);
       	InputStream in8 = part8.getInputStream();
           Files.copy(in8, uploadPath8, StandardCopyOption.REPLACE_EXISTING);
      }

       if (part9.getSize() != 0){
       	Path uploadPath9 = Paths.get("C:\\Users\\berry\\Develope Java\\pleiades\\workspace\\FileUploadWeb-master\\web\\image\\" + ExNO +"\\"+ ExNO + "-9" + sh);
       	InputStream in9 = part9.getInputStream();
           Files.copy(in9, uploadPath9, StandardCopyOption.REPLACE_EXISTING);
      }

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

    private String getFielName(Part part) {
        String header = part.getHeader("content-disposition");
        System.out.println("***" + header);
        String[] split = header.split(";");
        // headerは、以下の内容になっているので、ここからfilenameである「fileupload.png」を取得
        // form-data; name="file"; filename="fileupload.png"
        String fileName =
                Arrays.asList(split).stream()
                        .filter(s -> s.trim().startsWith("filename"))
                        .collect(Collectors.joining());
        return fileName.substring(fileName.indexOf("=") + 1).replace("\"", "");
    }

}
