package resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import counter.CounterJersey;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/counter")
public class CounterResource {

    @GET
    @Produces({"application/json"})
    public Response get() throws IOException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        CounterJersey counter = new CounterJersey(df.format(new Date()), CounterJersey.count);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(counter);

        return Response.ok(json).build();
    }

    @POST
    public Response post() {
        CounterJersey.count += 1;

        return Response.ok().build();
    }

    @POST
    @Path(value = "/clear")
    public Response postClear(@CookieParam(value = "hh-auth") String cookie) {
        if (cookie == null || cookie.length() < 10) {
            return Response.status(400).build();
        }
        CounterJersey.count = 0;

        return Response.ok().build();
    }

    @DELETE
    public Response delete(@HeaderParam(value = "Subtraction-Value") String header) {
        try {
            int value = Integer.parseInt(header);
            CounterJersey.count -= value;

            return Response.ok().build();
        } catch (NumberFormatException e) {

            return Response.status(400).build();
        }
    }
}
