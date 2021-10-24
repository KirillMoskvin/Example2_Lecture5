package example5_batch_statement;

import commons.JDBCCredentials;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Arrays;

public final class Main {

  private static final @NotNull JDBCCredentials CREDS = JDBCCredentials.DEFAULT;

  public static void main(@NotNull String[] args) {
    try (Connection connection = DriverManager.getConnection(CREDS.url(), CREDS.login(), CREDS.password())) {
      PreparedStatement ps = connection.prepareStatement("INSERT INTO person VALUES (?, ?)");

      ps.setInt(1, 1);
      ps.setString(2, "John J.");
      ps.addBatch();

      ps.setInt(1, 2);
      ps.setString(2, "Mike M.");
      ps.addBatch();

      int[] results = ps.executeBatch();
      System.out.println("Completed: " + Arrays.toString(results));

    } catch (SQLException exception) {
      System.err.println(exception.getMessage());
    }
  }
}
