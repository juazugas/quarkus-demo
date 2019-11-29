package vlc.emergingtech.quarkus.demo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

/**
 * BookRepositoryTest
 */
@QuarkusTest
@Transactional
public class BookRepositoryTest {

  @Inject
  private BookRepository repository;

  @Test
  public void testSearch() {
    // given
    createBookWithIsbn("Search");

    // when
    List<Book> result = repository.search("IS");

    // then
    assertThat(result, hasSize(1));
  }

  @Test
  public void testSearchByIsbn() {
    // given 
    createBookWithIsbn("searchByIsbn");

    // when 
    List<Book> result = repository.search(BookShadowFactory.DEFAULT_ISBN);

    // then
    assertThat(result, hasSize(1));
    assertThat(result, hasItem(hasProperty("isbn", equalTo(BookShadowFactory.DEFAULT_ISBN))));
  }

  @Test
  public void testSave() {
    // given
    Book book = BookShadowFactory.defaultBook();

    // when
    repository.persist(book);

    // then
    assertThat(book.getId(), notNullValue());
  }

  public Book createBookWithIsbn(String isbn) {
    Book book = BookShadowFactory.builder().isbn(isbn).build();
    repository.persist(book);
    return book;
  }

}