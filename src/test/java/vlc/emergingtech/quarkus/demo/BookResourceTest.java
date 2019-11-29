package vlc.emergingtech.quarkus.demo;

import org.junit.jupiter.api.Test;

import io.quarkus.deployment.builditem.ShutdownContextBuildItem;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * BookResourceTest
 */
@QuarkusTest
public class BookResourceTest {

  @Test
  public void testList() {
    given()
      .when().get("/book")
      .then()
        .statusCode(200)
        .contentType("application/json")
        .body(startsWith("["), endsWith("]"));
  }      

  @Test
  @Transactional
  public void testListWithResults() {

    given()
      .when().get("/book")
      .then()
        .statusCode(200)
        .contentType("application/json")
        .body("isbn[0]", equalTo("ISBN"))
        .body("author[0]", equalTo("AUTHOR"))
        .body("title[0]", is("TITLE"));

  }  

}