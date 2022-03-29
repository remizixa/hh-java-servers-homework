
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class ServletApplication{

  private static Server createServer(int port) {
    Server server = new Server(port);
    ServletHandler servletHandler = new ServletHandler();
    servletHandler.addServletWithMapping(CounterServlet.class, "/counter");
    servletHandler.addServletWithMapping(CounterServlet.class, "/counter/clear");
    server.setHandler(servletHandler);
    return server;
  }
  public static void main(String[] args) throws Exception {
    // run, Jetty, run!
    int port = 8090;
    Server server = createServer(port);
    server.start();
    server.join();
  }
}
