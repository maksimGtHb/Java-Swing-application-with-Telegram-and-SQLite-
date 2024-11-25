
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;








public class CalendarAppClass extends JDialog{
    private int month, year;
    private JPanel calendarPanel;
    private JLabel monthLabel;
    private JTextField dateField;
     private JButton selectedDayButton; 
     private static final Map<Integer, String> monthsInRussian = new HashMap<>();
     
     
     
      static {
        monthsInRussian.put(1, "января");
        monthsInRussian.put(2, "февраля");
        monthsInRussian.put(3, "марта");
        monthsInRussian.put(4, "апреля");
        monthsInRussian.put(5, "мая");
        monthsInRussian.put(6, "июня");
        monthsInRussian.put(7, "июля");
        monthsInRussian.put(8, "августа");
        monthsInRussian.put(9, "сентября");
        monthsInRussian.put(10, "октября");
        monthsInRussian.put(11, "ноября");
        monthsInRussian.put(12, "декабря");
    }
    
     private static final String[] daysOfWeekInRussian = {
         "воскресенье", "понедельник", "вторник", "среда", "четверг", "пятница", "суббота"
    };
    
    public CalendarAppClass(JTextField dateField){
        
        
        this.dateField = dateField;
        
        setUndecorated(true);
        
        Calendar cal = Calendar.getInstance();
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        setTitle("Календарь");
        setSize(400, 400);
          setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton prevButton = new JButton("< Предыдущий");
        JButton nextButton = new JButton("Следующий >");
        
        Font buttonFont = new Font(prevButton.getFont().getName(), Font.PLAIN, 16);
        prevButton.setFont(buttonFont);
        nextButton.setFont(buttonFont);
       
        
        
        
        monthLabel = new JLabel("", SwingConstants.CENTER);
        updateMonthLabel();
        
        prevButton.addActionListener(e -> changeMonth(-1));
        nextButton.addActionListener(e -> changeMonth(1));

        topPanel.add(prevButton, BorderLayout.WEST);
        topPanel.add(monthLabel, BorderLayout.CENTER);
        topPanel.add(nextButton, BorderLayout.EAST);


        calendarPanel = new JPanel(new GridLayout(0, 7)); 
        addDaysOfWeek(); 
        updateCalendar(); 

        
        JPanel okPanel = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dispose()); 
        okPanel.add(okButton);
        
        monthLabel.setFont(buttonFont);
        okButton.setFont(buttonFont);
        
        
        
        
        
        
        // Add panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(calendarPanel, BorderLayout.CENTER);
        add(okPanel, BorderLayout.SOUTH); 
    }

    private void updateMonthLabel() {
        String[] months = {
            "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", 
            "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
        };
        monthLabel.setText(months[month] + " " + year);
    }

    private void addDaysOfWeek() {
        String[] daysOfWeek = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"};
        for (String day : daysOfWeek) {
            JLabel dayLabel = new JLabel(day, SwingConstants.CENTER);
            dayLabel.setFont(new Font("Arial", Font.BOLD, 14));
            calendarPanel.add(dayLabel);
        }
    }

    private void updateCalendar() {
        // Clear previous calendar days
        calendarPanel.removeAll();
        addDaysOfWeek();

        // Get the first day of the month and number of days in the month
        Calendar cal = new GregorianCalendar(year, month, 1);
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int startDay = cal.get(Calendar.DAY_OF_WEEK) - 2;
        if (startDay < 0) startDay += 7;
        // Add empty labels for days before the start of the month
        for (int i = 0; i < startDay; i++) {
            calendarPanel.add(new JLabel(""));
        }

        // Add buttons for each day in the month
        for (int day = 1; day <= daysInMonth; day++) {
            JButton dayButton = new JButton(String.valueOf(day));
            dayButton.setFocusPainted(false);

            // Highlight today's date
            if (day == Calendar.getInstance().get(Calendar.DAY_OF_MONTH) &&
                month == Calendar.getInstance().get(Calendar.MONTH) &&
                year == Calendar.getInstance().get(Calendar.YEAR)) {
                dayButton.setBackground(Color.CYAN);
            }
                  // Add ActionListener to each day button to update the date field
            final int selectedDay = day;
                dayButton.addActionListener(e -> selectDate(dayButton, selectedDay));

            
            
            calendarPanel.add(dayButton);
        }

    
        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    
    
        private void selectDate(JButton dayButton, int selectedDay) {
      
        if (selectedDayButton != null) {
            selectedDayButton.setBackground(null); // Default background
        }

    
        selectedDayButton = dayButton;
        selectedDayButton.setBackground(Color.YELLOW); // Set selected color


         String formattedDate = convertDate(selectedDay + "/" + (month + 1) + "/" + year);
         dateField.setText(formattedDate);
    }
    
    private void changeMonth(int amount) {
        month += amount;
        if (month > 11) {
            month = 0;
            year++;
        } else if (month < 0) {
            month = 11;
            year--;
        }
        updateMonthLabel();
        updateCalendar();
    }
  
    public static void main (String[] args){
        SwingUtilities.invokeLater(() -> {
            JTextField jTextField5 = new JTextField("Selected Date", 10); // Create JTextField in MainClass
              CalendarAppClass calendarApp = new CalendarAppClass(jTextField5);
            calendarApp.setVisible(true);
            
        });
    }
    

     public static String convertDate(String dateStr) {
      SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            // Parse the date from the input string
            Date date = inputFormat.parse(dateStr);

            // Use Calendar to extract day, month, and day of the week
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH) + 1; // Months are 0-based, so add 1
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; // Adjusting to 0-based index for Russian days

            // Get the Russian month name and day of the week name
            String monthNameInRussian = monthsInRussian.get(month);
            String dayOfWeekInRussian = daysOfWeekInRussian[dayOfWeek];

            // Format and return the result in the desired format
            return day + " " + monthNameInRussian + ", " + dayOfWeekInRussian;
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Return null if parsing fails
        }
    }   
}
