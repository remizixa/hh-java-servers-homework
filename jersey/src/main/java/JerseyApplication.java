import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class JerseyApplication {

  private static Server createServer(int port) {
    Server server = new Server(port);

    ServletContextHandler ctx = new ServletContextHandler();
    server.setHandler(ctx);
    ServletHolder servletHolder = ctx.addServlet(ServletContainer.class, "/*");
    servletHolder.setInitOrder(1);
    servletHolder.setInitParameter("jersey.config.server.provider.packages", "resource");
    //servletHolder.setInitParameter("jakarta.ws.rs.Application", "resource");
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
