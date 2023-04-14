package ultis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class datetimeCurrent {
    public static void main(String[] args) {
        dateCurrent();
    }
    public static String dateCurrent(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNow = dateFormat.format(new Date());
//        String dateNow1 = dateNow.replaceAll("[:/]", "_");
        System.out.println("Thời gian hiện tại: " + dateNow);
        return dateNow;
    }
}

