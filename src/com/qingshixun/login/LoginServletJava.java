package com.qingshixun.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServletJava",urlPatterns = {"/LoginServletJava"})
public class LoginServletJava extends HttpServlet {
    /**
     * Get 请求处理方法
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取 login.jsp 提交的输入数据
        String username = request.getParameter("username"); //登录用户名
        String password = request.getParameter("password"); //登录密码
        String remember = request.getParameter("rememberMe"); //是否记住密码

        // 进行简易的登录验证，假定正确的账户密码是 admin/123456
        if("admin".equals(username) && "123456".equals(password)){

            request.getSession().setAttribute("username", username);
            // 将登录密码保存到 Session 中，以供其它 Servlet 或 JSP 使用
            request.getSession().setAttribute("password", password);
            // 将“是否记住密码”存入 Session 中
            request.getSession().setAttribute("rememberMe",remember);
            //调转到登录成功后的页面 welcome.jsp
            request.getRequestDispatcher("welcome.jsp").forward(request, response);

        } else{

            // 登录失败，准备直接将失败信息输出给客户端
            // 解决中文乱码的问题，
            response.setContentType("text/html;charset=UTF-8");
            // 开启输出流，将括号中的字符信息输出给客户端
            response.getWriter().append("登录失败，账户/密码错误");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
