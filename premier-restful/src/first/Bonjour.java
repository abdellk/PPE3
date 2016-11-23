package first;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/civilites")
public class Bonjour {

	@GET
	@Path("{nom}")
	@Produces({MediaType.TEXT_PLAIN})
	public Response Bonjour(@PathParam("nom") String nom) {
		return Response.status(200).entity("Bonjour Madame, Monsieur "+ nom).build();
	}
}
