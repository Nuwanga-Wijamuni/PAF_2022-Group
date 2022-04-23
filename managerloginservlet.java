package com.transport;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


 
public class managerloginservlet extends HttpServlet {
private static final long serialVersionUID = 1L;
 
public managerloginservlet() {
}
 
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
    String managerID  = request.getParameter("managerid");
    String nic = request.getParameter("nic");
 
    manager loginBean = new manager();
 
    loginBean.setmanagerID(managerID);
    loginBean.setnic(nic);
 
    managerDButil loginDao = new managerDButil();
 
    try
    {
        String userValidate = managerDButil.authenticateUser( loginBean);
 
        if(userValidate.equals("Admin_Role"))
        {
            System.out.println("Admin's Home");
 
            HttpSession session = request.getSession(); //Creating a session
            session.setAttribute("Admin", managerID); //setting session attribute
            request.setAttribute("userName", managerID);
 
            request.getRequestDispatcher("success.jsp").forward(request, response);
        }
        else if(userValidate.equals("Editor_Role"))
        {
            System.out.println("Editor's Home");
 
            HttpSession session = request.getSession();
            session.setAttribute("Editor", managerID);
            request.setAttribute("userName", managerID);
 
            request.getRequestDispatcher("unsuccess.jsp").forward(request, response);
        }
        
        
        else if(userValidate.equals("Foreign_manager"))
        {
            System.out.println("Foreign manager Home");
 
            HttpSession session = request.getSession();
            session.setAttribute("Foreign", managerID);
            request.setAttribute("userName", managerID);
 
            request.getRequestDispatcher("Addfpackage.jsp").forward(request, response);
        }
        
        else if(userValidate.equals("Transport_Manager"))
        {
            System.out.println("Transport Manager Home");
 
            HttpSession session = request.getSession();
            session.setAttribute("Transport", managerID);
            request.setAttribute("userName", managerID);
 
            request.getRequestDispatcher("vehicleForm.jsp").forward(request, response);
        }
        
        
        else if(userValidate.equals("User_Role"))
        {
            System.out.println("User's Home");
 
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(10*60);
            session.setAttribute("User", managerID);
            request.setAttribute("userName", managerID);
 
            request.getRequestDispatcher("/JSP/User.jsp").forward(request, response);
        }
        else
        {
            System.out.println("Error message = "+userValidate);
            request.setAttribute("errMessage", userValidate);
 
            request.getRequestDispatcher("Finance.jsp").forward(request, response);
        }
    }
    catch (IOException e1)
    {
        e1.printStackTrace();
    }
    catch (Exception e2)
    {
        e2.printStackTrace();
    }
} //End of doPost()
}



