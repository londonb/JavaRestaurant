import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Restaurant.all().size(), 0);
  }
  @Test
  public void equals_returnsTrueIfRestaurantsAretheSame() {
    Restaurant firstRestaurant = new Restaurant ("Lardo");
    Restaurant secondRestaurant = new Restaurant ("Lardo");
    assertTrue(firstRestaurant.equals(secondRestaurant));
  }

  @Test
  public void save_savesRestaurantToDatabase() {
    Restaurant newRestaurant = new Restaurant("Lardo");
    newRestaurant.save();
    assertTrue(Restaurant.all().get(0).equals(newRestaurant));
  }


  @Test
  public void find_findsRestaurantInDatabase_true() {
    Restaurant newRestaurant = new Restaurant("Lardo");
    newRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(newRestaurant.getId());
    assertEquals(savedRestaurant.getName(), "Lardo");
  }

  @Test
  public void update_updatesRestaurantInDatabased() {
    Restaurant newRestaurant = new Restaurant("Lrdo");
    newRestaurant.save();
    newRestaurant.update("Lardo");
    assertEquals(newRestaurant.getName(), "Lardo");
  }
}
