package password_manager.Resource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Useful {
    public static String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String capitalize(String word){
        String capital = word.substring(0,1).toUpperCase() + word.substring(1);
        return capital;
    }
}
