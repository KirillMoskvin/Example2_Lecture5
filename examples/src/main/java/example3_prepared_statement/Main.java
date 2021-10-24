package example3_prepared_statement;

import commons.JDBCCredentials;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

public final class Main {

  private static final @NotNull JDBCCredentials CREDS = JDBCCredentials.DEFAULT;
  private static final @NotNull String INSERT_SQL = "INSERT INTO person(id, name) VALUES(?, ?)";
  private static final @NotNull String SELECT_SQL = "SELECT * FROM person WHERE name = ?";

  public static void main(@NotNull String[] args) {
    final var name = "Sidorov S.S.";
    try (Connection connection = DriverManager.getConnection(CREDS.url(), CREDS.login(), CREDS.password())) {
      try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
        statement.setInt(1, 3);
        statement.setString(2, name);
        statement.executeUpdate();
      }
      try (PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {
        statement.setString(1, name);
        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            System.out.println("Id: " + resultSet.getInt("id")
              + ", name: " + resultSet.getString("name"));
          }
        }
      }
    } catch (SQLException exception) {
      System.err.println(exception.getMessage());
    }
  }
}
