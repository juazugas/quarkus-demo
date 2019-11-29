package vlc.emergingtech.quarkus.demo;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

/**
 * BookRepository
 */
@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {

  public List<Book> search(String criteria) {
    String value = Optional.ofNullable(criteria).map(c -> "%"+c+"%").orElse("");
    return searchHQL(value);
  }

  private List<Book> searchHQL(String value) {
    return find("isbn like ?1 or title like ?1 or author like ?1", value).list();
  }

  @Inject
  EntityManager em;

  @SuppressWarnings("unused")
	private List<Book> searchJPA(String value) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Book> q = cb.createQuery(Book.class);
    Root<Book> c = q.from(Book.class);
    q.where(cb.or(cb.like(c.get("isbn"), value),cb.like(c.get("title"), value),cb.like(c.get("author"), value)));
    
    return em.createQuery(q).getResultList();
	}
  
}