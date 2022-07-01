package resource;

import dao.CartDao;
import dao.Dao;

import static utils.JSONUtils.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

public class ConfigurationResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    private final Long configID;

    public ConfigurationResource(UriInfo uriInfo, Request request, String configID) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.configID = Long.parseLong(configID);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getConfiguration() {
        return jsonStringToConfiguration(Dao.getConfiguration(configID)).toJSON().toString();
    }
}
