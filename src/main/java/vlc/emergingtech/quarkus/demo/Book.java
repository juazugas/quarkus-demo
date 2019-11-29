package vlc.emergingtech.quarkus.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Book
 */
@Entity
public class Book {

  @Id
  @GeneratedValue
  private Long id;

  @Pattern(regexp = "\\d+")
  private String isbn;

  @NotBlank
  private String title;

  private String author;

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the isbn
   */
  public String getIsbn() {
    return isbn;
  }

  /**
   * @param isbn the isbn to set
   */
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the author
   */
  public String getAuthor() {
    return author;
  }

  /**
   * @param author the author to set
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getClass().getName()).append("[");
    sb.append("isbn").append("=").append(isbn);
    sb.append(",");
    sb.append("title").append("=").append(title);
    sb.append("]");
    return sb.toString();
  }

}
