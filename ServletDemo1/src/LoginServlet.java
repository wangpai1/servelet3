import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setContentType("text/html; charset=UTF-8");
       //request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter(); //获得输出流
        //从请求对象中获得指定参数的值
        String strName = request.getParameter("txtName");
        String strPwd = request.getParameter("txtPwd");


        Connection conn = null;   Statement stmt;   ResultSet rs;
        PreparedStatement pstmt;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn=DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:49741;Databasename=dbName","sa","123456");

          stmt = conn.createStatement();
          //  String sql = "select * from Students where StuName='"+strName+"' and stuPwd='"+strPwd+"'";
          //  System.out.println(sql);
          //  rs = stmt.executeQuery(sql);

            String  sql = "select * from Table_1 where username=? and pwd=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, strName);
            pstmt.setString(2, strPwd);
            rs=pstmt.executeQuery();

            if (rs.next())
            {
                out.println("hi" + strName + " "+strPwd + "<br>");

                HttpSession session = request.getSession(true);
                ServletContext servletContext = getServletContext();
                Object num = servletContext.getAttribute("num");
                out.println("<br>");
                out.println("当前在线人数："+num);

                Cookie cooUserName= new Cookie("username", strName);//创建一个键值对的cookie对象
                cooUserName.setMaxAge(60*60*24*30);//设置cookie的生命周期
                response.addCookie(cooUserName);//添加到response中

                Cookie cooPwd= new Cookie("pwd", strPwd);//创建一个键值对的cookie对象
                cooPwd.setMaxAge(60*60*24*30);//设置cookie的生命周期
                response.addCookie(cooPwd);//添加到response中







              //  RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/index.html");//得到转发器

               // requestDispatcher.forward(request, response);//转发（调度）请求给/sessionLoginDe

            }
            else {

                out.println("错误的用户名和密码");
            }
            rs.close(); 	conn.close();
            //stmt.close();
            pstmt.close();
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }



    // response.sendRedirect("/page/index.html");
       // request.getRequestDispatcher("/page/index.html").forward(request,response);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
