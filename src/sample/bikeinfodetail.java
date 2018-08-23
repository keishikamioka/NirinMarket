package sample;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.logging.FileHandler;
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

import sample.bean.bikeinfoBean;
import sample.bean.queryinfoBean;

/**import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import sample.bean.DataBean;
*/

/**
 * Servlet implementation class SampleClass
 */
@WebServlet("/bikeinfodetail")
public class bikeinfodetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
 	//private Logger log = Logger.getLogger(SampleClass.class);


     /**
      * @see HttpServlet#HttpServlet()
      */
     public bikeinfodetail() {
         super();
         // TODO Auto-generated constructor stub
     }


 	/**
 	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 	 */
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 		//log
 		//log
		Logger logger = Logger.getLogger(bikeinfodetail.class.getName());
        logger.setLevel(Level.FINEST);

     // ハンドラーを作成してロガーに登録
        Handler handler = new FileHandler("C:\\Users\\berry\\Develope Java\\pleiades\\workspace\\FileUploadWeb-master\\log\\sample.log");
        logger.addHandler(handler);

        // フォーマッターを作成してハンドラーに登録
        java.util.logging.Formatter formatter =  new SimpleFormatter();
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




 		//DBからデータ取得

 		response.setCharacterEncoding("UTF-8");

        String Number = request.getParameter("Number");
        logger.info("パラメータからの取得値は" + Number);
        //int  = Integer.parseInt(Numberstring);
        getDB(request, Number);
        getquerry(request, Number);



 		ServletContext context = this.getServletContext();
         RequestDispatcher dispatcher = context.getRequestDispatcher("/motodetail.jsp");
         dispatcher.forward(request, response);
 	}


 	/**
 	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 	 */
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		// TODO Auto-generated method stub
 		doGet(request, response);
 	}


 	//AWSのEC2のSQLserver接続
 	private void getDB(HttpServletRequest request, String number) {
 		//DOMConfigurator.configure("log4j.xml");
 		try {
 			//log
 	 		//log
 			Logger logger = Logger.getLogger(bikeinfodetail.class.getName());
 	        logger.setLevel(Level.FINEST);

 	     // ハンドラーを作成してロガーに登録
 	        Handler handler = new FileHandler("C:\\Users\\berry\\Develope Java\\pleiades\\workspace\\FileUploadWeb-master\\log\\sample.log");
 	        logger.addHandler(handler);

 	        // フォーマッターを作成してハンドラーに登録
 	        java.util.logging.Formatter formatter =  new SimpleFormatter();
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





 		       Class.forName("com.mysql.jdbc.Driver").newInstance();
 		       String connectionUrl = "jdbc:mysql://localhost:3306/sample" +
 		    		   "?user=root&password=Kami5330&characterEncoding=UTF-8&serverTimezone=JST";
 		    		Connection con = DriverManager.getConnection(connectionUrl);

 		    		;


 		        String sql = "select * from bikeinfotest where ExNO="+number+";";

 		        logger.info(sql);

 		        Statement stmt = con.createStatement();
 		        ResultSet rs = stmt.executeQuery(sql);
 		        List<bikeinfoBean> list = new ArrayList<bikeinfoBean>();
 		       List<queryinfoBean> list2 = new ArrayList<queryinfoBean>();

 		       Formatter fm = new Formatter();

 		       String Explain="";

 		        while (rs.next()) {
 		        	//出品日
 		        	String ExDate = rs.getString("ExDate");
 		        	//メーカ名
 		        	String Manu = rs.getString("Manu");
 		        	//モデル名
 		        	String Model = rs.getString("Model");
 		        	//年式
 		        	String exyear = rs.getString("Exyear");
 		        	String Exyear = exyear.substring(0,4) + "年";
 		        	//走行距離
 		        	String dis = rs.getString("Dis");
 		        	String Dis = dis + "km";
 		        	//出品地域
 		        	String ExArea = rs.getString("ExArea");
		        	//管理番号
 		        	int exno = rs.getInt("ExNO");
 		        	String  ExNO = String.format("%05d",exno);
 		        	//購入ステータス
 		        	int exinfo = rs.getInt("ExInfo");
 		        	String ExInfo = "";
 		        	if(exinfo == 0) {
 		        		ExInfo = "購入可";
 		        	}else {
 		        		ExInfo = "SOLD";
 		        	}
 		        	//価格
 		        	int price =rs.getInt("Price");
 		        	String priceS = String.format("%,d",price);
 		        	String Price = "￥" + priceS;
 		        	//説明文
 		        	Explain = rs.getString("ExExplain");
 		        	//画像
 		        	String ExImage = "<img src=\".\\image\\" + exno +"\\"+exno+"-1.jpg\" width=\"193\" height=\"130\">";

 		        	list.add(new bikeinfoBean(ExDate,Manu,Model,Exyear,Dis,ExArea,ExNO,ExInfo,Price,ExImage,Explain));


 		        	//list.add(new bikeinfoBean(rs.getString("ExNO"),
 	 		        //						rs.getString("Manu"),
 	 		        //						rs.getString("Model"),
 	 		        //						rs.getString("Exyear"),
 	 		        //						rs.getString("Dis"),
 	 		        //						rs.getString("ExArea"),
 	 		        //						String.format("%,d",rs.getString("Price"));

 		        }
 		       request.setAttribute("dbdata", list);
   		      request.setAttribute("explain",Explain );

 		        rs.close();
 		        stmt.close();
 		        con.close();

 		}
 		      catch (Exception e) {
 		        e.printStackTrace();
 		      } finally {

 		      }

 	}

 	private void getquerry(HttpServletRequest request, String number) {
 		//DOMConfigurator.configure("log4j.xml");
 		try {
 			//log
 	 		//log
 			Logger logger = Logger.getLogger(bikeinfodetail.class.getName());
 	        logger.setLevel(Level.FINEST);

 	     // ハンドラーを作成してロガーに登録
 	        Handler handler = new FileHandler("C:\\Users\\berry\\Develope Java\\pleiades\\workspace\\FileUploadWeb-master\\log\\sample.log");
 	        logger.addHandler(handler);

 	        // フォーマッターを作成してハンドラーに登録
 	        java.util.logging.Formatter formatter =  new SimpleFormatter();
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





 		       Class.forName("com.mysql.jdbc.Driver").newInstance();
 		       String connectionUrl = "jdbc:mysql://localhost:3306/sample" +
 		    		   "?user=root&password=Kami5330&characterEncoding=UTF-8&serverTimezone=JST";
 		    		Connection con = DriverManager.getConnection(connectionUrl);

 		        String sql2 = "select * from queryinfo where BaseInfo="+number+";";

 		       logger.info(sql2);

 		        Statement stmt = con.createStatement();
 		        ResultSet rs2 = stmt.executeQuery(sql2);
 		       List<queryinfoBean> list2 = new ArrayList<queryinfoBean>();

 		       while (rs2.next()) {

		        	//名前
		        	String username = rs2.getString("UserName");
		        	String UserName = "名前:"+username;
		        	//問い合わせ内容
		        	String QueryText = rs2.getString("QueryText");

		        	list2.add(new queryinfoBean(UserName,QueryText));
		        }

   		       request.setAttribute("querydata",list2);


 		        rs2.close();
 		        stmt.close();
 		        con.close();

 		}
 		      catch (Exception e) {
 		        e.printStackTrace();
 		      } finally {

 		      }

 	}


 }


