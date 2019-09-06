package jdbs;

import java.sql.*;

/**
 * Демонстрация работы драйвера JDBC. Сначала регистрируется
 * сам драйвер. Затем с помощью SQL-команды создается таблица,
 * и в нее записываются две строки, которые затем выводятся
 * на консоль с помощью SQL-запроса.
 */
public class JDBSSimple {
  public JDBSSimple() {

  }
  public void method1() {

    System.setProperty("jdbc.drivers", "org.postgresql.Driver");

    String url = "jdbc:postgresql:base1";
    String name = "postgres";
    String pass = "pass";

    try {
      try (Connection conn = DriverManager.getConnection(url, name, pass)) {
        Statement stat = conn.createStatement();
        stat.executeUpdate("CREATE TABLE Tab5 (name CHAR(20), r INT)");
        stat.executeUpdate("INSERT INTO Tab5 VALUES ('text1', 5)");
        stat.executeUpdate("INSERT INTO Tab5 VALUES ('text2', 6)");
        //вывести содержимое таблицы
        try(ResultSet result = stat.executeQuery("SELECT * FROM Tab5")) {
          //System.out.println(result.findColumn("name"));//номер колонки с указанным именем
          while(result.next()) {
            System.out.println(result.getString("name").trim() + " "
                    + result.getString("r").trim());

          }
        }
        stat.executeUpdate("DROP TABLE Tab5");//удалить таблицу
      }
    } catch(SQLException ex) {
      for (Throwable t : ex) {
        t.printStackTrace();
      }
    }
  }

}



