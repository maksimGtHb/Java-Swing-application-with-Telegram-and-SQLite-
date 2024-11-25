
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;

public class ButtonsActions {
    
    private final DatabaseManager dbManager;
    

   
  
      
    public ButtonsActions(){
        
        this.dbManager = new DatabaseManager();
        
        
    }
    
    
     public void erase(JTextField jTextField1, JTextField jTextField2,
    JTextField jTextField3, JTextField jTextField4, JTextField jTextField5, JComboBox<String> jComboBox1, JComboBox <String> jComboBox2) {                                         
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jComboBox1.setSelectedIndex(-1);
        jComboBox2.setSelectedIndex(-1);
        
    }                                        

    public void addRecord(JTextField jTextField1, JTextField jTextField2,
    JTextField jTextField3, JTextField jTextField4, JTextField jTextField5, JComboBox jComboBox1,JComboBox jComboBox2, JTable jTable2) {                                         
       
        
        
        String deviceModel = jTextField1.getText();
        String faultReason = jTextField2.getText();
        String address = jTextField3.getText();
        String contacts = jTextField4.getText();
        String date = jTextField5.getText();
        String master = (String)jComboBox1.getSelectedItem();
        String timeOfDay = (String)jComboBox2.getSelectedItem();

        try (PreparedStatement stmt = dbManager.conn.prepareStatement(
            "INSERT INTO orders (device, breakdown, address, contacts, date, master, timeOfDay) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
        stmt.setString(1, deviceModel);
        stmt.setString(2, faultReason);
        stmt.setString(3, address);
        stmt.setString(4, contacts);
        stmt.setString(5, date);
        stmt.setString(6, master);
        stmt.setString(7, timeOfDay);
        
        stmt.executeUpdate();

        
        
        dbManager.loadDataIntoTable(jTable2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    public void updateRecord(int id, JTextField jTextField1, JTextField jTextField2,
                         JTextField jTextField3, JTextField jTextField4, JTextField jTextField5,
                         JComboBox<String> jComboBox1, JComboBox <String> jComboBox2, JTable jTable2) {
    

     

// Get values from the input fields
    String deviceModel = jTextField1.getText();
    String faultReason = jTextField2.getText();
    String address = jTextField3.getText();
    String contacts = jTextField4.getText();
    String date = jTextField5.getText();
    String master = (String) jComboBox1.getSelectedItem();
    String timeOfDay = (String) jComboBox2.getSelectedItem();
    
    try (PreparedStatement stmt = dbManager.conn.prepareStatement(
            "UPDATE orders SET device = ?, breakdown = ?, address = ?, contacts = ?, date = ?, master = ?, timeOfDay = ? WHERE id = ?")) {
        
     
        stmt.setString(1, deviceModel);
        stmt.setString(2, faultReason);
        stmt.setString(3, address);
        stmt.setString(4, contacts);
        stmt.setString(5, date);
        stmt.setString(6, master);
        stmt.setString(7, timeOfDay);
        stmt.setInt(8, id); 
       
        
        int rowsUpdated = stmt.executeUpdate();
        
        if (rowsUpdated > 0) {
            System.out.println("Record updated successfully.");
            // Refresh the table data
            dbManager.loadDataIntoTable(jTable2);
        } else {
            System.out.println("No matching record found for update.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
      public void deleteRecord(int id, JTable jTable2) {
        try (PreparedStatement stmt = dbManager.conn.prepareStatement(
                "DELETE FROM orders WHERE id = ?")) {
            
    
            stmt.setInt(1, id);
            

            int rowsAffected = stmt.executeUpdate();
            

            if (rowsAffected > 0) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("No record found with the specified ID.");
            }

            // Refresh the table data to reflect the deletion
            dbManager.loadDataIntoTable(jTable2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
      }
        
     public void sendResult (JTextField jTextField1, JTextField jTextField2,
                         JTextField jTextField3, JTextField jTextField4,
                         JComboBox<String> jComboBox1, JComboBox <String> jComboBox2){
                
                
           String deviceModel = jTextField1.getText();
    String faultReason = jTextField2.getText();
    String address = jTextField3.getText();
    String contacts = jTextField4.getText();
   
    String master = (String) jComboBox1.getSelectedItem();
     String timeOfDay = (String) jComboBox2.getSelectedItem();
               
        String currentRecord = "🔵 " //mark
                + address + "\n\n" //address 
                + deviceModel + ". "  //device name
                + faultReason + "\n\n"  // fault reason
                + "Контакты: " + contacts + "\n\n"  // contacts
                + "Мастер: " + master + "\n\n" // master
                + "🕒 " + timeOfDay     //time of day 
                + ".";

        TelegramBot.sendMessage(currentRecord);
    }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
