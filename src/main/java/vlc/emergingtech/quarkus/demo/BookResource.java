package vlc.emergingtech.quarkus.demo;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.quarkus.hibernate.orm.panache.PanacheQuery;


/**
 * BookResource
 */
@Path("/book")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

  @Inject
  BookRepository repository;

  @GET
  public List<Book> list() {
    return repository.listAll();
  }

  @POST
  @Transactional
  public Book create(Book book) {
    repository.persist(book);
    return book;
  }

  @GET
  @Path("search")
  public List<Book> search(@QueryParam("q") String criteria) {
    return repository.search(criteria);
  }

  @GET
  @Path("{isbn}")
  public Book findByIsbn(@PathParam("isbn") String isbn) {
    return Optional.ofNullable(isbn)
              .map(val -> repository.find("isbn", val))
              .<Book>map(PanacheQuery::firstResult)
              .orElseThrow(NotFoundException::new);
  }
  
}