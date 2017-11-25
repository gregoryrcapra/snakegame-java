   import javax.swing.JFrame;
   public class SnakeFrame {
      public static void main(String[] args) {
         JFrame frame = new JFrame();
         SnakeBoard snake = new SnakeBoard();
         frame.add(snake);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(512, 564);
			frame.setVisible(true);
      
      }
   }
