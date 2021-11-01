package example2_metadata;

import commons.JDBCCredentials;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

public final class Main {

  private static final @NotNull JDBCCredentials CREDS = JDBCCredentials.DEFAULT;

  public static void main(@NotNull String[] args) {
    try (Connection connection = DriverManager.getConnection(CREDS.url(), CREDS.login(), CREDS.password())) {
      final DatabaseMetaData metaData = connection.getMetaData();
      final ResultSet resultSet = metaData.getTables(
        "",
        "public",
        null,
        new String[] {"TABLE"}
      );
      while (resultSet.next()) {
        System.out.println(resultSet.getString("TABLE_NAME"));
      }
    } catch (SQLException exception) {
      System.err.println(exception.getMessage());
    }
  }
}
