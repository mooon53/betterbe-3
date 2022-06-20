package resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("session")
public class SessionResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public boolean getSession() {
        return false;
    }
}
