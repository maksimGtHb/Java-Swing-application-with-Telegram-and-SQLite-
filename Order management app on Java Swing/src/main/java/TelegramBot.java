
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.MalformedURLException; 


public class TelegramBot {

    private static final String TOKEN = "7687111136:AAELRkHTj1OH7tIo8FQPXZRXTaFsRvg22ZQ";
    private static final String CHAT_ID = "-4521156455";
    
    
    
    public static void main(String[] args) {
    
    String message = "The bot is working";
    sendMessage(message);
    
   
    
    
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void sendMessage (String message){
      
        
     
        
        try {
            String urlString = "https://api.telegram.org/bot" + TOKEN + "/sendMessage";
            URL url = new URL (urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String params = "chat_id=" + CHAT_ID + "&text=" + URLEncoder.encode(message, "UTF-8");
            try (OutputStream os = conn.getOutputStream()) {
                os.write(params.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);
        } catch (MalformedURLException e) {
            e.printStackTrace(); // Handle the MalformedURLException
        } catch (Exception e) {
            e.printStackTrace(); // Handle any other exceptions
        }
    }
}