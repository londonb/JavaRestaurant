import org.sql2o.*;
import java.util.List;
import java.util.*;

public class Cuisine {
  private int cuisine_id;
  private String type;
  private int restaurant_id;

  public Cuisine (String type, int restaurant_id) {
    this.type = type;
    this.restaurant_id = restaurant_id;
  }

  public int getId() {
    return cuisine_id;
  }

  public int getRestaurantId() {
    return restaurant_id;
  }

  public String getType() {
    return type;
  }

  @Override
  public boolean equals(Object otherCuisine){
    if (!(otherCuisine instanceof Cuisine)) {
      return false;
    } else {
      Cuisine newCuisine = (Cuisine) otherCuisine;
      return this.getType().equals(newCuisine.getType()) &&
        this.getId() == newCuisine.getId();
    }
  }

  // //CREATE
  public void save() {
    String sql = "INSERT INTO cuisine (type, restaurant_id) VALUES (:type, :restaurant_id)";
      try (Connection con = DB.sql2o.open()) {
        this.cuisine_id = (int) con.createQuery(sql, true).addParameter("type", type).addParameter("restaurant_id", restaurant_id).executeUpdate().getKey();
      /******************************************************
        Students: TODO: Create sql query and execute update
      *******************************************************/
    }
  }

  //READ
  public static List<Cuisine> all() {
    String sql = "SELECT * FROM cuisine";
      try (Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Cuisine.class);

      /******************************************************
        Students: TODO: Create sql query and execute update
      *******************************************************/
    }
  }

  // find
  public static Cuisine find(int cuisine_id) {
    String sql = "SELECT * FROM cuisine WHERE cuisine_id = :cuisine_id";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).addParameter("cuisine_id", cuisine_id).executeAndFetchFirst(Cuisine.class);
      }
  }
  // //UPDATE
  public void update(String newType) {
    this.type = newType;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE cuisine SET type=:type WHERE cuisine_id=:cuisine_id";
      con.createQuery(sql).addParameter("type", newType).addParameter("cuisine_id", cuisine_id).executeUpdate();
      /******************************************************
        Students: TODO: Create sql query and execute update
      *******************************************************/
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM cuisine WHERE cuisine_id=:cuisine_id";
      con.createQuery(sql).addParameter("cuisine_id", cuisine_id).executeUpdate();
      /******************************************************
        Students: TODO: Create sql query and execute update
      *******************************************************/
    }
  }
  //
  // /******************************************************
  //   Students:
  //   TODO: Create find method
  //   TODO: Create method to get restaurants
  // *******************************************************/

}
