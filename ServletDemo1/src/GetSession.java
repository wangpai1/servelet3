

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetSession extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();// 创建Session
        // session.setMaxInactiveInterval(10);//设置失效时间10秒 不是销毁
        PrintWriter writer = resp.getWriter();
        ServletContext servletContext = getServletContext();
        Object num = servletContext.getAttribute("num");
        writer.write("在线人数:" + num);
    }
}
