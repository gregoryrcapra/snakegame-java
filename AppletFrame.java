   import java.awt.*;
   import javax.swing.JFrame;
   
   public class AppletFrame {
   
      public static void main(String[] args) {
         SnakeBoard myApplet = new SnakeBoard(); // define applet of interest
         JFrame myFrame = new JFrame("SNAKE!"); // create frame with title

         myApplet.init();	

         myFrame.add(myApplet, BorderLayout.CENTER);
         myFrame.pack(); // set window to appropriate size (for its elements)
         myFrame.setVisible(true); // usual step to make frame visible
			myFrame.setSize(512, 564);
         myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
      }
      
   }
   
   