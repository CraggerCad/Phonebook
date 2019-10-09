package servlet;

import com.sun.org.apache.regexp.internal.RE;
import model.PhoneBook;
import service.PhoneBookService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 12/14/2017.
 */
@WebServlet(name = "PhoneBookServlet")
public class PhoneBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String toDo = request.getParameter("editOrAdd");
        System.out.println(toDo);

        PhoneBook phoneBookObj = new PhoneBook();
        List<PhoneBook> phoneBookList = new ArrayList<>();
        PhoneBookService phoneBookServiceObj = new PhoneBookService();
        String id = request.getSession().getAttribute("sessionId") + " ";
        if(toDo.contains("add")) {
            phoneBookObj.setName(request.getParameter("name"));
            phoneBookObj.setPhoneNumber(request.getParameter("phnnumber"));
            phoneBookObj.setUserId(Integer.parseInt(id.trim()));
            phoneBookServiceObj.insertInfo(phoneBookObj);
        }
        if(toDo.contains("edit")) {
            PhoneBook phoneBookObjEdit = new PhoneBook();
            PhoneBookService phoneBookServiceEditObj = new PhoneBookService();
            phoneBookObjEdit.setName(request.getParameter("name"));
            phoneBookObjEdit.setPhoneNumber(request.getParameter("phnnumber"));
            phoneBookServiceEditObj.updatePhoneInfo(Integer.parseInt(request.getParameter("id")), phoneBookObjEdit);
        }

        phoneBookList = phoneBookServiceObj.getDataUsingId(Integer.parseInt(id.trim()));
        request.setAttribute("list",phoneBookList);
        RequestDispatcher rd = request.getRequestDispatcher("/homePage.jsp");
        rd.forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String toDo = request.getParameter("op");

       if (toDo.contains("sendtoaddpage")){
           request.setAttribute("editOrAdd","add");
           RequestDispatcher rd =request.getRequestDispatcher("/add.jsp");
           rd.forward(request,response);
       }
       if(toDo.contains("edit")){
           int id = Integer.parseInt(request.getParameter("Id"));
           System.out.println("Id in phone book servlet "+id);
           PhoneBook phoneBookObj = new PhoneBook();
           PhoneBookService phoneBookServiceObj = new PhoneBookService();
           phoneBookObj = phoneBookServiceObj.getPhoneDataToEdit(id);
           request.setAttribute("phone",phoneBookObj);
           //request.setAttribute("id",id);
           request.setAttribute("editOrAdd","edit");
           RequestDispatcher rd = request.getRequestDispatcher("/add.jsp");
           rd.forward(request,response);

       }
       if(toDo.contains("delete")){
           int phoneId =Integer.parseInt(request.getParameter("Id"));
           List<PhoneBook> phoneBookList = new ArrayList<>();
           PhoneBookService phoneBookServiceObj = new PhoneBookService();
           phoneBookServiceObj.delete(phoneId);
           request.setAttribute("message",phoneId);
           String userId1 = request.getSession().getAttribute("sessionId") + " ";
           int userId = Integer.parseInt(userId1.trim());
           phoneBookList = phoneBookServiceObj.getDataUsingId((userId));
           request.setAttribute("list",phoneBookList);
           RequestDispatcher rd = request.getRequestDispatcher("/homePage.jsp");
           rd.forward(request,response);
       }



    }
}
