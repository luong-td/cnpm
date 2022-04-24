/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import control.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author DELL
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/index.jsp";
            String account = request.getParameter("taikhoan");
            String password = request.getParameter("matkhau");
            User user = new User(account, password);
//        try {
//            UserDB.insert(user);
//        } catch (Exception e) {
//        }
        try {
            if(UserDB.checklogin(user)){
                if(account.contains("admin"))
                    url="/mainadmin.jsp";
                else if(account.contains("nhanvien"))
                    url="/mainnv.jsp";
                else
                    url="/maindg.jsp";
//                request.getRequestDispatcher(url).forward(request, response);
                HttpSession session = request.getSession();
                session.setAttribute("tendangnhap", account);
            }else if(UserDB.checkSingup(user)){
                String messagedk ="Sai thông tin đăng nhập";
                request.setAttribute("messagedk", messagedk);
            }else{
                String messagedk ="Tài khoản không tồn tại";
                request.setAttribute("messagedk", messagedk);
//                url="/dangki.jsp";
//                request.getRequestDispatcher(url).forward(request, response);
            }
        } catch (Exception e) {
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
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

}
