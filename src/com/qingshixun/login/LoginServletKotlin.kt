package com.qingshixun.login

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "LoginServletKotlin",urlPatterns = ["/LoginServletKotlin"])
class LoginServletKotlin: HttpServlet(){
    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        doGet(req, resp)
    }

    override fun doGet(request: HttpServletRequest?, response: HttpServletResponse?) {
        //获取 login.jsp 提交的输入数据
        val username=request?.getParameter("username")?:"unknow"
        val password=request?.getParameter("password")?:"unknow"
        val remember=request?.getParameter("rememberMe")?:"no"
        // 进行简易的登录验证，假定正确的账户密码是 admin/123456
        if(username.equals("admin")&&password.equals("123456")){
            // 登录成功后，将用户名保存到 Session
            request?.session?.setAttribute("username",username)
            request?.session?.setAttribute("password",password)
            request?.session?.setAttribute("rememberMe",remember)
            request!!.getRequestDispatcher("welcome.jsp").forward(request,response)

        }
        else{
            // 登录失败，准备直接将失败信息输出给客户端
            // 解决中文乱码的问题
            response?.contentType="text/html;charset=UTF-8"
            response?.writer?.append("登录失败，账户/密码错误")
        }

        /*val username:String=req?.getParameter("username")?:"unknow"
        val password:String=req?.getParameter("password")?:"unknow"
        resp?.contentType="text/html;charset=UTF-8"
        resp?.writer?.append("LoginServlet：$username  $password")*/
    }
}