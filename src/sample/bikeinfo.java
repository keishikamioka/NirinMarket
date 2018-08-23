package sample;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sample.bean.bikeinfoBean;

/**import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import sample.bean.DataBean;
*/

/**
 * Servlet implementation class SampleClass
 */
@WebServlet("/bikeinfo")
public class bikeinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
 	//private Logger log = Logger.getLogger(SampleClass.class);


     /**
      * @see HttpServlet#HttpServlet()
      */
     public bikeinfo() {
         super();
         // TODO Auto-generated constructor stub
     }


 	/**
 	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 	 */
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		//DBからデータ取得
 		getDB(request);

 		response.setCharacterEncoding("UTF-8");

 		ServletContext context = this.getServletContext();
         RequestDispatcher dispatcher = context.getRequestDispatcher("/bike.jsp");
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
 	private void getDB(HttpServletRequest request) {
 		//DOMConfigurator.configure("log4j.xml");
 		try {
 				//Driver d = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getDeclaredConstructor().newInstance();
 		    	//IPアドレスはEC2再起動の度に変わります
 		        //String connUrl =
 		        //  "jdbc:driver://sample.c6eknefpgjpj.us-east-1.rds.amazonaws.com:3306/sample"
 		        //		/*database=motorcycle;"*/
 		        //    + "?user=keishi;password=kami5330";
 		        //Connection con = d.connect(connUrl, new Properties());

 		       Class.forName("com.mysql.jdbc.Driver").newInstance();
 		       String connectionUrl = "jdbc:mysql://localhost:3306/sample" +
 		    		   "?user=root&password=Kami5330&characterEncoding=UTF-8&serverTimezone=JST";
 		    		Connection con = DriverManager.getConnection(connectionUrl);

 		        String sql = "select * from bikeinfotest order by exno desc;";
 		        Statement stmt = con.createStatement();
 		        ResultSet rs = stmt.executeQuery(sql);
 		        List<bikeinfoBean> list = new ArrayList<bikeinfoBean>();

 		       Formatter fm = new Formatter();

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
 		        	String Explain = rs.getString("ExExplain");
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

 		        rs.close();
 		        stmt.close();
 		        con.close();

 		}
 		      catch (Exception e) {
 		        e.printStackTrace();
 		      } finally {

 		      }

 	}


 }


