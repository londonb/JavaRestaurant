import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class CuisineTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Cuisine.all().size(), 0);
  }

  @Test
  public void equals_checksIfCuisineTheSame() {
    Cuisine firstCuisine = new Cuisine ("American", 1);
    Cuisine secondCuisine = new Cuisine ("American", 1);
    assertTrue(firstCuisine.equals(secondCuisine));

  }

  @Test
  public void save_savesCuisineToDatabase() {
    Cuisine newCuisine = new Cuisine ("American", 1);
    newCuisine.save();
    assertTrue(Cuisine.all().get(0).equals(newCuisine));
  }

  @Test
  public void find_findsCuisineToDatabase() {
    Cuisine myCuisine = new Cuisine ("American", 1);
    myCuisine.save();
    Cuisine savedCuisine = Cuisine.find(myCuisine.getId());
    assertEquals(savedCuisine.getType(), "American");
  }
  @Test
  public void update_updateCuisineInDatabase() {
    Cuisine myCuisine = new Cuisine ("America", 1);
    myCuisine.save();
    myCuisine.update("American");
    assertEquals(myCuisine.getType(), "American");
  }

  @Test
  public void delete_deletesCuisineFromDatabase() {
    Cuisine myCuisine = new Cuisine ("American", 1);
    myCuisine.save();
    myCuisine.delete();
    assertEquals(Cuisine.all().size(), 0);


  }
}
