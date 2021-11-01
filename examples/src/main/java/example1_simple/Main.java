package example1_simple;

import commons.FlywayInitializer;
import commons.JDBCCredentials;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Properties;

public final class Main {

  private static final @NotNull JDBCCredentials CREDS = JDBCCredentials.DEFAULT;

  public static void main(@NotNull String[] args) {
    FlywayInitializer.initDb();
    // Class.forName("org.postgresql.Driver");
    try (Connection connection = DriverManager.getConnection(CREDS.url(), CREDS.login(), CREDS.password())) {
      try (Statement statement = connection.createStatement()) {
        int id = 1;
        String name = "Ivanov I.I.";
        statement.executeUpdate("INSERT INTO person(id, name) VALUES (" + id + ",'" + name + "')");
        id++;
        name = "Petrov P.P.";
        statement.executeUpdate("INSERT INTO person(id, name) VALUES (" + id + ",'" + name + "')");
      } catch (SQLException exception) {
        System.err.println(exception.getMessage());
      }
      try (Statement statement = connection.createStatement()) {
        try (ResultSet resultSet = statement.executeQuery("SELECT id, name FROM person")) {
          final ResultSetMetaData metaData = resultSet.getMetaData();
          int columns = metaData.getColumnCount();
          for (int i = 1; i <= columns; i++) {
            System.out.print("\t\t" + metaData.getColumnLabel(i));
          }
          System.out.println();
          while (resultSet.next()) {
            final int id = resultSet.getInt("id");
            final String name = resultSet.getString("name");
            System.out.println("\t\t" + id + "\t\t" + name);
          }
        }
      }
    } catch (SQLException exception) {
      System.err.println(exception.getMessage());
    }


    var properties = new Properties();
    properties.setProperty("login", CREDS.login());
    properties.setProperty("password", CREDS.password());
    try (var connection = DriverManager.getConnection(CREDS.url(), properties)) {
      connection.beginRequest();
      connection.endRequest();
    } catch (SQLException ignored) {

    }
    System.out.println("Success");
  }
}
