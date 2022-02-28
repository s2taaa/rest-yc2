package rest;

import connector.impl.ProductConnector;
import model.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/products")
public class ProductService extends ProductConnector {
    @GET // This annotation indicates GET request
    @Path("/hello")
    public Response hello() {
        return Response.status(200).entity("hello").build();
    }

    @GET
    @Produces("application/json")
    public List<Product> getAll(){
        return super.getAll();
    }
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Product get(@PathParam("id") String id){
        try{
            return  super.get(id);
        }catch (Exception e){
            return null;
        }

    }


    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response create(Product product){
        try{
            super.save(product);
            return Response.status(200).entity("them thanh cong").build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    @PUT
    @Path("/{id}")
    @Produces("application/json")
    public Response update(@PathParam("id") String id, Product productUpdate){
        try
        {
            Product product = super.get(id);
            product.setCatalogId(productUpdate.getCatalogId());
            product.setName(productUpdate.getName());
            product.setImage(productUpdate.getImage());
            product.setPrice(productUpdate.getPrice());
            super.update(product);
            return Response.status(200).entity("sua thanh cong").build();
        }catch (Exception exception){
            return Response.status(Response.Status.BAD_REQUEST).build();

        }
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response deleteProduct(@PathParam("id") String id){
        try
        {
            super.delete(id);
            return Response.status(200).entity("Da xoa thanh cong").build();
        }catch (Exception exception){
            return Response.status(Response.Status.BAD_REQUEST).build();

        }

    }



}
