import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DatabaseManager {
  Connection conn;
  
  public DatabaseManager() {
    connectToDatabase();
  }
  
  public void connectToDatabase() {
    try {
      Class.forName("org.sqlite.JDBC");
      String url = "jdbc:sqlite:C:\\Users\\maksc\\Documents\\NetBeansProjects\\Service-of-orders\\src\\main\\resources\\main.db";
      this.conn = DriverManager.getConnection(url);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      System.err.println("SQLite JDBC driver not found.");
      e.printStackTrace();
    } 
  }
  
  public void loadDataIntoTable(JTable jTable) {
     DefaultTableModel model = new DefaultTableModel((Object[])new String[] { "ID", "Аппарат", "Причина", "Адрес", "Контакты", "Дата", "Мастер", "Время"}, 0);
    try {
      PreparedStatement statement = this.conn.prepareStatement("SELECT * FROM orders ORDER BY id DESC");
      try {
        ResultSet rs = statement.executeQuery();
        int rowCount = 0;
        while (rs.next()) {
          model.addRow(new Object[] { Integer.valueOf(rs.getInt("id")), rs
                .getString("device"), rs
                .getString("breakdown"), rs
                .getString("address"), rs
                .getString("contacts"), rs
                .getString("date"), rs
                .getString("master"), rs
                .getString("timeOfDay") });
          rowCount++;
        } 
        jTable.setModel(model);
        jTable.getTableHeader().setFont(new Font("Arial", 1, 10));
        jTable.getColumnModel().getColumn(0).setMaxWidth(30);
     
        if (statement != null)
          statement.close(); 
      } catch (Throwable throwable) {
        if (statement != null)
          try {
            statement.close();
          } catch (Throwable throwable1) {
            throwable.addSuppressed(throwable1);
          }  
        throw throwable;
      } 
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
}
