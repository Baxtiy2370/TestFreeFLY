package uz.b.testfreefly.servlets.user;

import uz.b.testfreefly.container.ApplicationContext;
import uz.b.testfreefly.domains.Users;
import uz.b.testfreefly.dto.LoginDTO;
import uz.b.testfreefly.exceptions.BadRequestException;
import uz.b.testfreefly.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/auth/login")
public class LogInServlet extends HttpServlet {
    public final UserService userService = ApplicationContext.getBean(UserService.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/auth/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginDTO loginDTO = LoginDTO.builder()
                .username(req.getParameter("username"))
                .password(req.getParameter("password"))
                .build();
        Users login = userService.login(loginDTO);
        HttpSession session=req.getSession();
        session.setAttribute("userId",login.getId());
        Users.Role userRole = login.getRole();
        switch (userRole){
            case USER -> resp.sendRedirect("/");
            case MANAGER -> resp.sendRedirect("/managerpage");
            case DIRECTOR -> req.getRequestDispatcher("/views/auth/director.jsp").forward(req,resp);
            case TICKETMAN -> resp.sendRedirect("/ticketmanpage");
        }
    }
}
