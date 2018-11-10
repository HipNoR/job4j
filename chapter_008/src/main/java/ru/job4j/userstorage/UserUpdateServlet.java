package ru.job4j.userstorage;

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
 * @version 0.1$
 * @since 0.1
 * 07.11.2018
 */
public class UserUpdateServlet extends HttpServlet {
    /**
     * Validate class instance.
     */
    private final ValidateService validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        long id = Long.parseLong(req.getParameter("id"));
        User result = validate.findById(id);
        PrintWriter writer = resp.getWriter();
        writer.append(
                "<!DOCTYPE html>\n"
                        + "<html lang=\"en\">\n"
                        + "<head>\n"
                        + "<meta charset=\"UTF-8\">\n"
                        + "<title>User update</title>\n"
                        + "</head>\n"
                        + "<body><table>\n"
                        + "<form method=\"post\" action=\"edit\">\n"
                        + "<input type=\"hidden\" name=\"action\" value=\"update\">\n"
                        + "<input type=\"hidden\" name=\"id\" value=\"" +  id + "\">\n"
                        + "<tr><td>ID</td><td>" + id + "</td></tr>\n"
                        + "<tr><td>Name</td><td><input type=\"text\" name=\"name\" value=\"" + result.getName() + "\"></td></tr>\n"
                        + "<tr><td>Login</td><td><input type=\"text\" name=\"login\" value=\"" + result.getLogin() + "\"></td></tr>\n"
                        + "<tr><td>Email</td><td><input type=\"text\" name=\"email\" value=\"" + result.getEmail() + "\"></td></tr>\n"
                        + "<tr align=\"right\"><td colspan=\"2\"><input type=\"submit\" value=\"UPDATE\"></td></tr>\n"
                        + "</form></table></body></html>"
        );
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       new UserServlet().doPost(req, resp);
    }
}
