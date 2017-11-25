import java.util.Date;
import java.lang.Math;

public class SnakeTimer
{
   long d1, d2, iD;
   Date date1, date2, initialDate;
   
   public SnakeTimer()
   {
      initialDate= new java.util.Date();
      iD = initialDate.getTime();
   }

   public void resetTime() {
      initialDate = new java.util.Date();
      iD = initialDate.getTime();
   }
   public void pauseSnake(int delay)
   {
   
      date1= new java.util.Date();
      d1 = date1.getTime();
   
      date2 = new java.util.Date();
      d2 = date2.getTime();
   
      while(d2 - d1 < delay){
   
         date2 = new java.util.Date();
         d2 = date2.getTime();
   
      }
      
   }
   
   public double getSeconds()
   {
      date2 = new java.util.Date();
      d2 = date2.getTime();
      
      double seconds = (double) ((d2 - iD) / Math.pow(10,3));
         
      return (seconds);   
   }
   
   public double getMinutes() {     // Get total number of minutes elapsed.
      double time = getSeconds()/60.0;
         
      return (time);
   }
   
   public double getHours() {       // Get total number of hours elapsed.
      double time = getMinutes()/60.0;
      
      return (time);
   }
   public String getTime() {
      
      int hours = (int) Math.floor(getHours());
      int minutes = (int) Math.floor(getMinutes())%60;
      int seconds = (int) Math.floor(getSeconds())%60;
         
      String time = String.format("Time: %02d:%02d:%02d",hours,minutes,seconds);
       
      return(time);
      }   
}