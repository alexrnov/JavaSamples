package jdbs;

import java.sql.*;

/**
 * Класс демонстрирует работу подготовленных(подставных) опреаторов и
 * запросов, которые подставляются вместо знака ?
 * Также демонстрируются различные запросы, метаданные
 */
public class DummyQuery {

  //подставной запрос
  private final String query1 = "SELECT * FROM Tab2 WHERE number = ?";

  public void method1() {
    System.setProperty("jdbc.drivers", "org.postgresql.Driver");
    String url = "jdbc:postgresql:base1";
    String name = "postgres";
    String pass = "pass";
    try {
      try (Connection conn = DriverManager.getConnection(url, name, pass)) {
        Statement stat = conn.createStatement();

        //executeUpdate можно выполнить команды UPDATE, INSERT, DELETE, CREATE TABLE, DROP TABLE
        stat.executeUpdate("CREATE TABLE Tab1 (id INT, name CHAR(20), number INT)");
        stat.executeUpdate("INSERT INTO Tab1 VALUES (1, 'text1', 5)");
        stat.executeUpdate("INSERT INTO Tab1 VALUES (2, 'text2', 3)");
        stat.executeUpdate("INSERT INTO Tab1 VALUES (3, 'text3', 4)");
        stat.executeUpdate("INSERT INTO Tab1 VALUES (4, 'text4', 3)");
        stat.executeUpdate("INSERT INTO Tab1 VALUES (5, 'text2', 8)");

        stat.executeUpdate("CREATE TABLE Tab2 (id INT, number INT, type CHAR(20))");
        stat.executeUpdate("INSERT INTO Tab2 VALUES (1, 6, 'type1')");
        stat.executeUpdate("INSERT INTO Tab2 VALUES (2, 9, 'type3')");
        stat.executeUpdate("INSERT INTO Tab2 VALUES (3, 8, 'type2')");
        stat.executeUpdate("INSERT INTO Tab2 VALUES (4, 5, 'type2')");
        stat.executeUpdate("INSERT INTO Tab2 VALUES (5, 2, 'type2')");
        stat.executeUpdate("INSERT INTO Tab2 VALUES (6, 8, 'type5')");
        stat.executeUpdate("INSERT INTO Tab2 VALUES (7, 9, 'type2')");
        //вывести содержимое таблицы
        try (ResultSet result = stat.executeQuery("SELECT * FROM Tab1")) {
          while(result.next()) {
            System.out.println(result.getString("id").trim()
              + " " + result.getString("name").trim() + " "
              + result.getString("number"));
          }
        }
        System.out.println("--------------------------------------");
        try (ResultSet result = stat.executeQuery("SELECT * FROM Tab2")) {
          while(result.next()) {
            System.out.println(result.getString("id") + " "
                    + result.getString("number") + " "
                    + result.getString("type"));
          }
        }
        System.out.println("--------------------------------------");
        System.out.println("Строки из Tab2, для которых значение поля number\n" +
                "совпадает со значением Number из Tab1");
        try (ResultSet result = stat.executeQuery("SELECT Tab2.id, Tab2.number, Tab2.type FROM Tab1, Tab2 WHERE Tab1.number = Tab2.number")) {
          while(result.next()) {
            System.out.println(result.getString(1) + " " + result.getString(2) + " "
            + result.getString(3));
          }
        }
        System.out.println("--------------------------------------");

        try (ResultSet result = stat.executeQuery("SELECT * FROM Tab2 WHERE number = 8")) {
          while(result.next()) {
            System.out.println(result.getString(1) + " "
                    + result.getString(2) + " "
                    + result.getString(3));
          }
        }

        System.out.println("--------------------------------------");

        System.out.println("Запрос на объединение таблиц по атрибуту number:");
        try (ResultSet result = stat.executeQuery("SELECT * FROM Tab1 INNER JOIN Tab2 ON Tab1.number = Tab2.number")) {
          while(result.next()) {
            System.out.println(result.getString(1).trim() + " "
                    + result.getString(2).trim() + " "
                    + result.getString(3).trim() + " "
                    + result.getString(4).trim() + " "
                    + result.getString(5).trim() + " "
                    + result.getString(6).trim());
          }
        }

        System.out.println("--------------------------------------");
        System.out.println("Подставной запрос: ");
        PreparedStatement stat2 = conn.prepareStatement(query1);
        //для первого(и единственного в этом запросе) вопросительного
        // знака подставить значение 2
        stat2.setInt(1, 9);
        try (ResultSet result = stat2.executeQuery()) {
          while (result.next()) {
            System.out.println(result.getString(1) + " "
                    + result.getString(2) + " "
                    + result.getString(3));
          }
        }
        System.out.println("--------------------");
        System.out.println("Вывести все таблицы базы данных:");
        DatabaseMetaData meta = conn.getMetaData();
        try(ResultSet result = meta.getTables(null,
                null, null,
                new String[] {"TABLE"})) {
          while(result.next()) {
            System.out.println(result.getString(3));
          }

        }
        System.out.println("-------------------------------");
        System.out.println("Названия и длина столбцов таблицы Tab1: ");
        try (ResultSet result = stat.executeQuery("SELECT * FROM Tab1")) {
          ResultSetMetaData meta2 = result.getMetaData();
          for (int i = 1; i <= meta2.getColumnCount(); i++) {
            String columnName = meta2.getColumnLabel(i);
            int columnWidth = meta2.getColumnDisplaySize(i);
            System.out.println(columnName + " " + columnWidth);
          }
        }
        stat.executeUpdate("DROP TABLE Tab1");//удалить первую таблицу
        stat.executeUpdate("DROP TABLE Tab2");//удалить вторую таблицу
        System.out.println("Таблицы удалены");
      }
    } catch(SQLException ex) {
      for (Throwable t : ex) { //перебор цепочки исключений
        t.printStackTrace();
      }
    }
  }
}
