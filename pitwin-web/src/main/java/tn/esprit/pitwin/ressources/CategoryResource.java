package tn.esprit.pitwin.ressources;



import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.pitwin.entities.Category;
import tn.esprit.pitwin.interfaces.ICategoryService;
import tn.esprit.pitwin.ressource.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@Path("/category")
public class CategoryResource {


    @EJB
    private ICategoryService categoryService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCategory(Category category) throws URISyntaxException {

        categoryService.create(category);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/rest/category/" + category.getId())),
                "category", category.getName())
                .entity(category).build();
    }


    @PUT
    public Response updateCategory(Category category) throws URISyntaxException {

        categoryService.edit(category);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "category", category.getId().toString())
                .entity(category).build();
    }


    @GET
    public List<Category> getAllCategories() {

        List<Category> categories = categoryService.findAll();
        return categories;
    }
 /*   
    @Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOffre() {
		if (categoryService.findAll() != null)
			return Response.status(Status.FOUND).entity(categoryService.findAll()).build();
		return Response.status(Status.NOT_FOUND).build();
	}
*/
    @Path("/{id}")
    @Produces("application/xml")
    @GET
    public Response getCategory(@PathParam("id") Long id) {

        Category category = categoryService.find(id);
        return Optional.ofNullable(category)
                .map(result -> Response.status(Response.Status.OK).entity(category).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeCategory(@PathParam("id") Long id) {

        categoryService.remove(categoryService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "category", id.toString()).build();
    }

}
