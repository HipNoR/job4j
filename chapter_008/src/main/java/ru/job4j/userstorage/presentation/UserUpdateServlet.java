package ru.job4j.userstorage.presentation;

import ru.job4j.userstorage.logic.Validate;
import ru.job4j.userstorage.logic.ValidateService;
import ru.job4j.userstorage.persistent.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet to update existed user.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.3$
 * @since 0.1
 * 07.11.2018
 */
public class UserUpdateServlet extends HttpServlet {
    /**
     * Validate class instance.
     */
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        long id = Long.parseLong(req.getParameter("id"));
        User result = validate.findById(id);
        String role = (String) req.getSession().getAttribute("role");
        PrintWriter writer = resp.getWriter();
        writer.append("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>User update</title>\n"
                + "</head>\n"
                + "<body><table>\n"
                + "<form method=\"post\" action=\"edit\">\n"
                + "<input type=\"hidden\" name=\"action\" value=\"update\">\n"
                + "<input type=\"hidden\" name=\"id\" value=\"").append(String.valueOf(id)).append("\">\n")
                .append("<tr><td>ID</td><td>").append(String.valueOf(id)).append("</td></tr>\n")
                .append("<tr><td>Name</td><td><input type=\"text\" name=\"name\" value=\"").append(result.getName()).append("\"></td></tr>\n")
                .append("<tr><td>Login</td><td><input type=\"text\" name=\"login\" value=\"").append(result.getLogin()).append("\"></td></tr>\n")
                .append("<tr><td>Password</td><td><input type=\"text\" name=\"password\" value=\"").append(result.getPassword()).append("\"></td></tr>\n")
                .append("<tr><td>Email</td><td><input type=\"text\" name=\"email\" value=\"").append(result.getEmail()).append("\"></td></tr>\n");
        if (role.equals("admin")) {
        writer.append("<tr><td>Role</td><td><select name=\"role\">\n"
                + "<option value=\"admin\">Admin</option>\n"
                + "<option selected value=\"user\">User</option>\n");
        } else {
            writer.append("<tr><td>Role</td><td>").append(result.getRole()).append("</td></tr>");
            writer.append("<input type=\"hidden\" name=\"role\" value=\"").append(role).append("\">\n");
        }
        writer.append("<tr align=\"right\"><td colspan=\"2\"><input type=\"submit\" value=\"UPDATE\"></td></tr>\n")
                .append("</form></table></body></html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("ustore").forward(req, resp);
    }
}
