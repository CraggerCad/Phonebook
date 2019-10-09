package servlet;

import com.sun.org.apache.regexp.internal.RE;
import model.PhoneBook;
import model.User;
import service.PhoneBookService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 12/14/2017.
 */
public class UserServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        User userObj = new User();
        RequestDispatcher rd = null;
        UserService usrServiceObj = new UserService();
        //PhoneBookService phoneBookServiceObj = new PhoneBookService();

        String type = request.getParameter("logorreg");
        if (type.equals("register")) {
            userObj.setUsernName(request.getParameter("rusrname"));
            userObj.setPassword(request.getParameter("rusrpassword"));
            usrServiceObj.insert(userObj);
        }else{

            String userName = request.getParameter("usrname");
            String password = request.getParameter("usrpassword");
            int check = usrServiceObj.checkUser(userName,password);
            request.getSession().setAttribute("sessionId",check);
            if(check != 0){
                PhoneBookService phoneBookServiceObj = new PhoneBookService();
                List<PhoneBook> numberData = new ArrayList();
                String id1 = request.getSession().getAttribute("sessionId") + " ";
                int id = Integer.parseInt(id1.trim());
                numberData = phoneBookServiceObj.getDataUsingId(id);
                request.setAttribute("list",numberData);
//                request.setAttribute("check","aayo");
                 rd = request.getRequestDispatcher("/homePage.jsp");
                 rd.forward(request,response);
            }
            else{
                String message = "Invalid Username";
                rd = request.getRequestDispatcher("/index.jsp");
                request.setAttribute("message",message);
                rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request,response);
            }

        }
        rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
