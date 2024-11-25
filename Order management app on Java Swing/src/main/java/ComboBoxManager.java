import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import java.time.format.TextStyle;
import java.util.Locale;


public class ComboBoxManager {

    public void initializeComboBox(JComboBox<String> jComboBox1, JComboBox <String> jComboBox2) {
        
        String[] items = {"", "Равиль", "Виталий", "Алексей"};
        jComboBox1.setModel(new DefaultComboBoxModel<>(items));
        String[] items_2 = {"", "после обеда", "до обеда", "с утра", "вечером"};
        jComboBox2.setModel(new DefaultComboBoxModel<>(items_2));
    }

    
    public void dateDisplay(JLabel dateLabel, JLabel dayLabel) {

        LocalDate currentDate = LocalDate.now();


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM", new Locale("ru"));
        String formattedDate = currentDate.format(dateFormatter);


        String dayOfWeekString = currentDate.getDayOfWeek()
                                             .getDisplayName(TextStyle.FULL, new Locale("ru"));

        dateLabel.setText(formattedDate);
        dayLabel.setText(dayOfWeekString + ",");

    }
}
