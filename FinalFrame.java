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
import java.lang.reflect.Array;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import java.io.PrintWriter;
  
public class FinalFrame  extends JFrame  
{
   private static char p;
   public static void main(String[] args)   
   {  
      JFrame frame = new JFrame("Don't Step On the White Tiles");
      frame.setSize(1200,700);
      Scanner in = new Scanner(System.in);
   
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      FinalPanel fa = new FinalPanel();
      //fa.setOpaque(true); 
      frame.add(fa);
      //String start = in.next();
      boolean go=true;
   
      frame.setVisible(true);
      while(go==true)
      {
         fa.setBackground(Color.WHITE);
         p='q';
         while(p=='q')
         {
            //fa.setStart(false);
            p=fa.getChar();
            System.out.println(p);
            if (p==' ')
               fa.setStart(true);
         }
      
         fa.setBackground(Color.WHITE);
         fa.resetD();
         boolean cor = true;
         Random r = new Random();
         int [] x = new int [100];
         for(int j=0; j<100;j++)
         {
            x[j]=r.nextInt(8);
         }
      
         Date date1= new java.util.Date();
      
         for (int i=0; i<50; i++)
         {
            fa.setNumber(i);
            p='q';
            fa.setSquare(x[i+5],x[i+4],x[i+3],x[i+2], x[i+1],x[i]);
            fa.resetP();
            fa.paintImmediately(0,0,1200,660);
            int c = 9;
         
            while(p=='q')
            {
               char p = fa.getChar();
               System.out.println(p);
            
               if(p=='a'){c=0;}
               else if(p=='s'){c=1;}
               else if(p=='d'){c=2;}
               else if(p=='f'){c=3;}
               else if(p=='j'){c=4;}
               else if(p=='k'){c=5;}
               else if(p=='l'){c=6;}
               else if(p==';'){c=7;}
            
               if(c==x[i+1])
               {
                  cor=true;
                  break;
               }
               else if(p!='q')
               {
                  i=50;
                  cor=false;
                  break;
               }
            }
         // String n = in.next();
         }
         fa.setBackground(Color.BLUE);
         Date date2= new java.util.Date();
         long xtim = date1.getTime();
         long ytim = date2.getTime();
         double time = (double)(ytim-xtim)/1000;
         fa.setEnd(cor, time);
         String gogo = JOptionPane.showInputDialog("Play again? Enter 'n' to stop or any other key to continue.");
         if(gogo.charAt(0)=='n')
            go=false;
      }
   }
}