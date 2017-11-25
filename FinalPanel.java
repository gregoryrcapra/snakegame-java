import java.awt.Color;
import java.awt.geom.Line2D;
import java.lang.Math;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JComponent;
import java.util.ArrayList;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import java.awt.BasicStroke;
import java.io.PrintWriter;
import java.lang.Math;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.lang.Double;

public class FinalPanel extends JPanel implements KeyListener
{
   private int n;
   private int n2;
   private int n3;
   private int n4;
   private int n5;
   private int nn;
   private JPanel gPanel;
   private char p='q';
   private String end = "";
   private String time = "";
   private boolean draw = true;
   private ArrayList <Double> highScore = new ArrayList <Double>();
   private boolean b=false;
   private int number;
   private boolean start;

   public FinalPanel()
   {
      //super(new BorderLayout());
      JPanel pp = new JPanel();
      pp.addKeyListener(this);
      pp.setFocusable(true);
      this.add(pp);
      
      try
      {
         File inputFile = new File("highscores.txt");
         Scanner inFile = new Scanner(inputFile);
         while (inFile.hasNext())
         {
            String next = inFile.next();
            Double nextDouble = Double.valueOf(next);
            highScore.add(nextDouble);
         }inFile.close();  
      }
      catch (Exception e)
      {
         System.err.println(e.getMessage());
      }   
   }
         
   public void keyPressed(KeyEvent e)
   {}

   public void keyTyped(KeyEvent e)
   {
      keyS(e);
   }
    
   public void keyReleased(KeyEvent e)
   {}
    
   public void keyS(KeyEvent event)
   {
      String keystr;
      int eventID = event.getID();
      char key = event.getKeyChar();
      p=key;
      b=true;
   }
  
   public void resetP()
   {
      p='q';
      b=false;
   }
  
   public void resetD()
   {
      draw=true;
      end = "";
      time = "";
   }
   public void setStart(boolean st)
   {
      start=st; 
      repaint();  
   }
  
   public char getChar()
   {
      return p;
   }
  
   public void setNumber(int y)
   {
      number=y;
   }

   public void setSquare(int x, int x2, int x3, int x4, int x5, int x6)
   {
      n = x;
      n2 = x2;
      n3 = x3;
      n4 = x4;
      n5 = x5;
      nn = x6;
   }
   public void setEnd (boolean e, double d)
   {
      draw=false;
      if(e==true)
      {
         end = "You Won!" ;
         time = "Time: ";
         time = time.concat(String.valueOf(d));
         highScore.add(d);
      }
   
      if(e==false)
      {
         end = "You Lost!";
         n=0;
      }
      repaint();
   }

   public void paintComponent(Graphics g)   
   {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      //g2.setBackground(new Color(50, 180, 190));
      Font f2 = new Font("Verdana", Font.BOLD, 40);
      g2.setFont(f2);
      if(start==false)
      {
         try
         {
            BufferedImage img = ImageIO.read(new File("tiles.png"));
            g2.drawImage(img, 871, 62, null);
            
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-img.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            g2.drawImage(op.filter(img, null), 0, 62, null);
         }
         catch (Exception e)
         {
            System.err.println(e.getMessage());
         }
         g2.drawString("Press Space to Start", 370, 350);
      }
      
      if(start==true)
      {
         if(draw==false)
         {
            g2.setColor(Color.WHITE);
            g2.drawString(end, 100, 100);
            g2.drawString(time, 100, 400);
            g2.drawString("High Scores", 550, 80);
            if(highScore.isEmpty()==false)
            {   
               try
               {
                  PrintWriter pw = new PrintWriter("highscores.txt");      
                  highScore = getMergeSorted(highScore);
                  double sum=0;
                  double avg=0;

                  
                  for(int ii=0; ii<10;  ii++)
                  {
                     
                     g2.drawString(String.valueOf(ii+1), 500, 40*ii+150);
                     g2.drawString("Avg", 500, 580);
                  
                     if(highScore.size()>ii)
                     {
                        sum = sum + highScore.get(ii);

                        pw.println(highScore.get(ii)+" "); 

                        String hh = String.valueOf(highScore.get(ii));
                        g2.drawString(hh, 620, 40*ii+150); 
                     }
                  } pw.close();
                 avg = (double) sum/highScore.size();
                 String avgString = String.valueOf(avg);
                 g2.drawString(avgString, 620, 580);

               }   
               catch (Exception e)
               {
                  System.err.println(e.getMessage());
               }
            }
         }
         if(draw==true)
         {       
            try
            {
               File soundFile = new File("gunshot.wav");
               AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
               DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
               Clip clip = (Clip) AudioSystem.getLine(info);
               clip.open(sound);
               clip.start();
            }
            catch (Exception e)
            {
               System.err.println(e.getMessage());
            }
            g2.setColor(Color.BLACK);
            
            g2.fillRect(150*n, 0 ,150, 120);
            g2.fillRect(150*n2, 120 ,150, 120);
            g2.fillRect(150*n3, 240 ,150, 120);
            g2.fillRect(150*n4, 360 ,150, 120);
            g2.fillRect(150*n5, 480 ,150, 120);
         
            g2.setColor(Color.lightGray);
            if(b==true)
            {
               g2.fillRect(150*n5, 480 ,150, 120);
            }
            g2.fillRect(150*nn, 600 ,150, 60);
         
            if(number==0)
               g2.fillRect(0, 600 ,1200, 60);
         
            g2.setFont(f2);
            g2.setColor(new Color(0,200,200));
            
            if(n==0)
               g2.drawString("a", 150*n+65, 60);
            if(n==1)
               g2.drawString("s", 150*n+65, 60);   
            if(n==2)
               g2.drawString("d", 150*n+65, 60);
            if(n==3)
               g2.drawString("f", 150*n+65, 60);
            if(n==4)
               g2.drawString("j", 150*n+65, 60);
            if(n==5)
               g2.drawString("k", 150*n+65, 60);
            if(n==6)
               g2.drawString("l", 150*n+65, 60);
            if(n==7)
               g2.drawString(";", 150*n+65, 60);
         
            if(n2==0)
               g2.drawString("a", 150*n2+65, 180);
            if(n2==1)
               g2.drawString("s", 150*n2+65, 180);   
            if(n2==2)
               g2.drawString("d", 150*n2+65, 180);
            if(n2==3)
               g2.drawString("f", 150*n2+65, 180);
            if(n2==4)
               g2.drawString("j", 150*n2+65, 180);
            if(n2==5)
               g2.drawString("k", 150*n2+65, 180);
            if(n2==6)
               g2.drawString("l", 150*n2+65, 180);
            if(n2==7)
               g2.drawString(";", 150*n2+65, 180);
         
            if(n3==0)
               g2.drawString("a", 150*n3+65, 300);
            if(n3==1)
               g2.drawString("s", 150*n3+65, 300);   
            if(n3==2)
               g2.drawString("d", 150*n3+65, 300);
            if(n3==3)
               g2.drawString("f", 150*n3+65, 300);
            if(n3==4)
               g2.drawString("j", 150*n3+65, 300);
            if(n3==5)
               g2.drawString("k", 150*n3+65, 300);
            if(n3==6)
               g2.drawString("l", 150*n3+65, 300);
            if(n3==7)
               g2.drawString(";", 150*n3+65, 300);
         
            if(n4==0)
               g2.drawString("a", 150*n4+65, 420);
            if(n4==1)
               g2.drawString("s", 150*n4+65, 420);   
            if(n4==2)
               g2.drawString("d", 150*n4+65, 420);
            if(n4==3)
               g2.drawString("f", 150*n4+65, 420);
            if(n4==4)
               g2.drawString("j", 150*n4+65, 420);
            if(n4==5)
               g2.drawString("k", 150*n4+65, 420);
            if(n4==6)
               g2.drawString("l", 150*n4+65, 420);
            if(n4==7)
               g2.drawString(";", 150*n4+65, 420);
         
            if(n5==0)
               g2.drawString("a", 150*n5+65, 540);
            if(n5==1)
               g2.drawString("s", 150*n5+65, 540);   
            if(n5==2)
               g2.drawString("d", 150*n5+65, 540);
            if(n5==3)
               g2.drawString("f", 150*n5+65, 540);
            if(n5==4)
               g2.drawString("j", 150*n5+65, 540);
            if(n5==5)
               g2.drawString("k", 150*n5+65, 540);
            if(n5==6)
               g2.drawString("l", 150*n5+65, 540);
            if(n5==7)
               g2.drawString(";", 150*n5+65, 540);
            
            g2.setColor(Color.lightGray);
            
            for (int i=1; i<=8; i++)
            {
               if(i==4)
                  g2.setStroke(new BasicStroke(8));
               if(i==5)
                  g2.setStroke(new BasicStroke(3));
               Line2D.Double Line = new Line2D.Double((1200/8)*i,0,(1200/8)*i,700);
               g2.draw(Line);
            }
            for(int j=1; j<6; j++)
            {
               Line2D.Double Line2 = new Line2D.Double(0,(600/5)*j,1200, (600/5)*j);
               g2.draw(Line2);
            }
               
            if (number==49)
               g2.fillRect(0, 360 ,1200, 120);
            if (number>=48)
               g2.fillRect(0, 240 ,1200, 120);
            if (number>=47)
               g2.fillRect(0, 120 ,1200, 120);                       
            if (number>=46)
               g2.fillRect(0, 0 ,1200, 120);  
         }
      }
   }
   
   public static ArrayList<Double> getMergeSorted(ArrayList<Double> inData)
   {
      int inSize = inData.size();
      ArrayList<Double> bigList = new ArrayList<Double>();
      
      if (inSize == 1)
      {
         bigList.add(inData.get(0));
         return(bigList);
      }
      
      else
      {
         ArrayList<Double> smallListOne = new ArrayList<Double>();
         ArrayList<Double> smallListTwo = new ArrayList<Double>();
         for (int i = 0; i < inSize/2; i++)
            smallListOne.add(inData.get(i)); 
         for (int i = inSize/2; i < inSize; i++)
            smallListTwo.add(inData.get(i));
            
         smallListOne = getMergeSorted(smallListOne);
         smallListTwo = getMergeSorted(smallListTwo);
         int size1 = smallListOne.size();
         int size2 = smallListTwo.size();
         
         int index1 = 0;
         int index2 = 0;
         
         bigList.clear();
         
         while(index1 < size1 && index2 < size2)
         {
            if (smallListOne.get(index1) < smallListTwo.get(index2))
               bigList.add(smallListOne.get(index1++));
            else
               bigList.add(smallListTwo.get(index2++));
         }
         
         while (index1 < size1)
            bigList.add(smallListOne.get(index1++));
         while (index2 < size2)
            bigList.add(smallListTwo.get(index2++));
            
         return(bigList);
      }
   }
}