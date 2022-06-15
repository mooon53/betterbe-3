package resource;

import dao.Dao;
import model.HistoricalData;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static utils.JSONUtils.jsonStringsToHistoricalData;

@Path("/timeline")
public class TimelineResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @Path("{car}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HistoricalData> getHistory(@PathParam("car") String id) {
        List<String> historicalData = Dao.getHistoricalData(Long.parseLong(id));
        return jsonStringsToHistoricalData(historicalData);
    }
}
