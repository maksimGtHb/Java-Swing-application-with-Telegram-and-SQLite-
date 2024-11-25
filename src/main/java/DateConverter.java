import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateConverter {
    
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

    public static String convertDate(String dateStr) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            // Parse the input date string
            Date date = inputFormat.parse(dateStr);

            // Extract the day and month values
            SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
            int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));

            // Get the day as a string
            String day = dayFormat.format(date);

            // Get the Russian month name from the map
            String monthNameInRussian = monthsInRussian.get(month);

            // Return formatted date in Russian
            return day + " " + monthNameInRussian;
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Return null if parsing fails
        }
    }

    public static void main(String[] args) {
        String formattedDate = convertDate("12/04/2024");
        System.out.println(formattedDate);  // Output: 12 апреля
    }
}