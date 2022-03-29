import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class CounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().endsWith("/clear")) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            return;
        }
        PrintWriter writer = response.getWriter();
        writer.print(Counter.counter);
        writer.flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().endsWith("/clear")) {
            Cookie[] cookies = request.getCookies();
            if (cookies == null || Arrays.stream(cookies)
                  .filter(c->(c.getName().equals("hh-auth") && c.getValue().length() >= 10))
                  .toArray().length == 0){
              response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
              Counter.counter = 0;
              response.setStatus(HttpServletResponse.SC_OK);
            }
        } else {
            Counter.counter++;
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int value = Integer.parseInt(request.getHeader("Subtraction-Value"));
            Counter.counter -= value;
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}