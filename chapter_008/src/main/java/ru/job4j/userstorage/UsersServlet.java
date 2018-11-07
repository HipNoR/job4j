package ru.job4j.userstorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet to display all users.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 05.11.2018
 */
public class UsersServlet extends HttpServlet {
    /**
     * Validate class instance.
     */
    private final ValidateService validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        List<User> users = validate.findAll();
        StringBuilder sb = new StringBuilder("<table border=\"1\" cellpadding=\"7\">");
        if (users.size() == 0) {
            sb.append("<tr><td>Storage is empty</td></tr>");
        } else {
            sb.append(
                    "<tr>"
                            + "<td>Id</td>"
                            + "<td>Name</td>"
                            + "<td>Login</td>"
                            + "<td>Email</td>"
                            + "<td>CreateDate</td>"
                            + "<td>Actions</td>"
                            + "</tr>"
            );
            users.forEach(user -> sb.append(String.format(
                    "<tr>"
                            + "<td>%s</td>\n"
                            + "<td>%s</td>\n"
                            + "<td>%s</td>\n"
                            + "<td>%s</td>\n"
                            + "<td>%s</td>\n"
                            + "<td><form action=\"list\" method=\"POST\">\n"
                            + "<input type=\"hidden\" name=\"action\" value=\"delete\"\n>"
                            + "<input type=\"hidden\" name=\"id\" value=\"%1$s\"\n>"
                            + "<input type=\"submit\" value=\"Delete user\"></form>\n"
                            + "<form method=\"GET\" action=\"edit\">\n"
                            + "<input type=\"hidden\"name=\"id\" value=\"%1$s\"\n>"
                            + "<input type=\"submit\" value=\"Update user\"></form></td>\n"
                            + "</tr>",
                    user.getId(), user.getName(), user.getLogin(), user.getEmail(), user.getCreateDate()))
            );
        }
        sb.append(
                "<tr align=\"center\"><td colspan=\"6\">"
                        + "<form action=\"create\" method=\"GET\">"
                        + "<input type=\"submit\" value=\"Create new user\">"
                        + "</form></td></tr></table>"
        );

        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(
                "<!DOCTYPE html>\n"
                        + "<html lang=\"en\">\n"
                        + "<head>\n"
                        + "<meta charset=\"UTF-8\">\n"
                        + "<title>List of all users</title>\n"
                        + "</head>\n"
                        + "<body>\n"
        );
        writer.append(sb.toString());
        writer.append("</body></html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new UserServlet().doPost(req, resp);
        this.doGet(req, resp);
    }
}
