package vlc.emergingtech.quarkus.demo;

/**
 * BookShadowFactory
 */
public class BookShadowFactory {
  
    public static final String DEFAULT_ISBN = "ISBN";
    public static final String DEFAULT_TITLE = "Default Title";
    public static final String DEFAULT_AUTHOR = "Default Author";
  
    public static Book defaultBook() {
      Book book = new Book();
      book.setIsbn(DEFAULT_ISBN);
      book.setAuthor(DEFAULT_AUTHOR);
      book.setTitle(DEFAULT_TITLE);
      return book;
    }

    public static Builder builder() {
      return new Builder();
    }

    static class Builder {

      private final Book book;

      public Builder() {
        book = new Book();
      }

      Builder isbn(String isbn) {
        book.setIsbn(isbn);
        return this;
      }

      public Book build() {
        return book;
      }

    }
    
}