   import javax.swing.JApplet;
   import java.awt.Graphics;
   import java.awt.Color;
   import java.awt.Graphics2D;
   import java.awt.Rectangle;
   import java.lang.InterruptedException;
   import java.util.Scanner;
   import java.awt.event.KeyEvent;
   import java.awt.event.KeyListener;
   import java.util.Random;
   import java.awt.Font;
   import java.lang.Math;
   import java.lang.RuntimePermission;

   public class SnakeBoard extends JApplet implements KeyListener {
      
      private int s = 16;
      private int width = 32, height = 32; // Number of x and y boxes
      private final int APPLET_WIDTH = s*width;
      private final int APPLET_HEIGHT = s*height + 2*s;
      private int[][] board = new int[width][height];;
      private SnakeNode snake = new SnakeNode(5,5);
      private Scanner in;
      private int input;
      private int nextDirection = 2; // 1 up, 2 down, 3 left, 4 right
      private int currentDirection = 2;
      private SnakeTimer timer;
      private int milX, milY;    // Coordinates of pellet
      private boolean milEat = true;  // boolean to tell if pellet has been eaten
      private Random r;
      private int score = 0;
      private Font smallFont = new Font("ArcadeClassic",Font.PLAIN, 28);
      private Font bigFont = new Font("ArcadeClassic",Font.PLAIN, 100);
      private int gameState = 0; // 0-start, 1-Easy, 2-Med, 3-Miller, 4-GameOver
      private int colorCount = 0;
      private int colorCount2 = 0;
      private SnakeAudio sound;
      
      public void init() {
         setSize(APPLET_WIDTH, APPLET_HEIGHT);
         
         //RuntimePermission p = new RuntimePermission("accessClassInPackage.sun.audio");
         
         timer = new SnakeTimer();
         in = new Scanner(System.in);
         r = new Random();
         sound = new SnakeAudio();
         // Set up array for board
         board = new int[width][height];

         // Set up snake & add children
         snake.addNode(new SnakeNode());

		   addKeyListener(this);
		   setFocusable(true);
         
      }
      
      public void paint (Graphics g) {
         if(gameState == 0)
            drawTitle(g);
         else if(gameState == 1)
            drawEasy(g);
         else if(gameState == 2)
            drawMed(g);
         else if(gameState == 3)
            drawMiller(g);
         else if(gameState == 4)
            drawOver(g);
         

      }
      
      public void drawTitle(Graphics g) {
         g.setColor(Color.BLACK);
         g.fillRect(0,0,APPLET_WIDTH, APPLET_HEIGHT);
         g.setColor(Color.WHITE);
         g.setFont(bigFont);
         g.drawString("Welcome To",5, 200);
         g.drawString("Snake", 130, 350);
         g.setFont(smallFont);
         g.drawString("Press    a    for easy mode",10,400);
         g.drawString("Press    b    for medium mode",10,450);
         g.drawString("Press    c    for miller mode",10,500);
         g.fillRect(300,400,16,16);
         g.fillRect(317,400,16,16);
         g.fillRect(334,400,16,16);
         g.fillRect(351,400,16,16);
         g.fillRect(351,417,16,16);
         g.fillRect(351,434,16,16);
         g.fillRect(351,451,16,16);
         g.fillRect(368,451,16,16);
         g.fillRect(385,451,16,16);
         g.fillRect(402,451,16,16);
         g.fillRect(419,451,16,16);
         g.fillRect(445,490,16,16);
      }
      
      public void drawOver(Graphics g) {
         g.setColor(Color.BLACK);
         g.fillRect(0,0,APPLET_WIDTH, APPLET_HEIGHT);
         g.setColor(Color.WHITE);
         g.setFont(bigFont);
         g.drawString(String.format("GAME OVER!"), (int)(.8*s), (int)(15*s));
         g.setFont(smallFont);
         g.drawString(String.format("Final Score: %05d",score*100), (int)(8*s), (int)(20*s));
         g.drawString("Press     A     to   restart!", (int)6.2*s, 25*s);
         snake = new SnakeNode(5,5);
         snake.addNode(new SnakeNode());
         score = 0;
         currentDirection = 2;
         nextDirection = 2;
         milEat = true;
         board = snake.getLocationArray(new int[width][height]);
      }
      
      public void drawEasy (Graphics g) {
      
         checkCollision();
         
         switch (nextDirection) {
            case 1:  snake.moveUp();
                     currentDirection = 1;
                     break;
            case 2:  snake.moveDown();
                     currentDirection = 2;
                     break;
            case 3:  snake.moveLeft();
                     currentDirection = 3;
                     break;
            case 4:  snake.moveRight();
                     currentDirection = 4;
                     break;
         }
         
         // get Node Locations of snake;
         board = snake.getLocationArray(new int[width][height]);
         
         // Draw grid
         for(int i=0; i<width;i++)
            for(int j=0; j<height;j++) {
               if(board[i][j]==1) {
                  g.setColor(Color.RED);  // Color.RED
                  g.fillRect(s*i,s*j,s,s);
               }
               else {
                  g.setColor(Color.BLACK);
                  g.fillRect(s*i,s*j,s,s);
               }
            }
            
         // Check if pellet is eaten
         if(snake.getX() == milX && snake.getY() == milY){
            if(score != 0)
               sound.getRegEat();
            snake.addNode(new SnakeNode());
            milEat = true;
            score += 1;
         }
         // Draw the pellet
         if(milEat){
            genMil();
            g.setColor(Color.CYAN);
            g.fillRect(s*milX,s*milY,s,s);
            milEat = false;
         }
         else{
            g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            g.fillRect(s*milX,s*milY,s,s);
         }
         drawScore(g);
         timer.pauseSnake(90);
         repaint();
      }
      
      public void drawMed (Graphics g) {
      
         checkCollision();
         
         switch (nextDirection) {
            case 1:  snake.moveUp();
                     currentDirection = 1;
                     break;
            case 2:  snake.moveDown();
                     currentDirection = 2;
                     break;
            case 3:  snake.moveLeft();
                     currentDirection = 3;
                     break;
            case 4:  snake.moveRight();
                     currentDirection = 4;
                     break;
         }
         
         // get Node Locations of snake;
         board = snake.getLocationArray(new int[width][height]);
            
         // Draw grid
         for(int i=0; i<width;i++)
            for(int j=0; j<height;j++) {
               if(board[i][j]==1) {
                  g.setColor(Color.RED);  // Color.RED
                  g.fillRect(s*i,s*j,s,s);
               }
               else {
                  g.setColor(Color.BLACK);
                  g.fillRect(s*i,s*j,s,s);
               }
            }
         for(int i=0; i<width; i++)
            for(int j=0; j<height;j++) 
               if(i==0||i==width-1||j==0||j==height-1){
                  board[i][j] = 1;
                  g.setColor(new Color(0,0,153));
                  g.fillRect(s*i,s*j,s,s);
               }
            
         // Check if pellet is eaten
         if(snake.getX() == milX && snake.getY() == milY){
            snake.addNode(new SnakeNode());
            snake.addNode(new SnakeNode());
            milEat = true;
            score += 1;
         }
         // Draw the pellet
         if(milEat){
            if(score != 0)
               sound.getRegEat();
            genMil();
            g.setColor(Color.CYAN);
            g.fillRect(s*milX,s*milY,s,s);
            milEat = false;
         }
         else{
            g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            g.fillRect(s*milX,s*milY,s,s);
         }
         for(int j=0; j<height;j++) {
                  g.setColor(new Color(0,0,153));  // Color.RED
                  g.fillRect(s*j,s*32,s,s);
         }  
         
         drawScore(g);
          
         timer.pauseSnake(50);
         repaint();
      }
      
      public void drawMiller (Graphics g) {
      
         checkCollision();
         
         switch (nextDirection) {
            case 1:  snake.moveUp();
                     currentDirection = 1;
                     break;
            case 2:  snake.moveDown();
                     currentDirection = 2;
                     break;
            case 3:  snake.moveLeft();
                     currentDirection = 3;
                     break;
            case 4:  snake.moveRight();
                     currentDirection = 4;
                     break;
         }
         
         // get Node Locations of snake;
         board = snake.getLocationArray(new int[width][height]);
         
         // Draw grid
         for(int i=0; i<width;i++)
            for(int j=0; j<height;j++) {
               if(board[i][j]==1) {
                  g.setColor(getColor());  // Color.RED
                  g.fillRect(s*i,s*j,s,s);
               }
               else {
                  g.setColor(Color.BLACK);
                  g.fillRect(s*i,s*j,s,s);
               }
            }
         if(colorCount2 == 0)
            colorCount = (colorCount + 1)%6;  
         colorCount2 = (colorCount2 + 1)%3; 
         // Check if pellet is eaten
         if(snake.getX() == milX && snake.getY() == milY){
            for(int i=0; i<Math.pow(2,score);i++)
               snake.addNode(new SnakeNode());
            milEat = true;
            score += 1;
         }
         // Draw the pellet
         if(milEat){
            if(score != 0)
               sound.getRandomEatSound();
            genMil();
            g.setColor(Color.CYAN);
            g.fillRect(s*milX,s*milY,s,s);
            milEat = false;
         }
         else{
            g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            g.fillRect(s*milX,s*milY,s,s);
         }
         for(int j=0; j<height;j++) {
                  g.setColor(new Color(0,0,153));  // Color.RED
                  g.fillRect(s*j,s*32,s,s);
         }  
         
         drawScore(g);
          
         timer.pauseSnake(25);
         repaint();
      }

      public void checkCollision() {
         int headX = snake.getX();
         int headY = snake.getY();
      
         switch (nextDirection) {
            case 1:  
               if(headY - 1 < 0)
                  headY = 31;
               else
                  headY = (headY-1)%height;
               break;
               
            case 2:  
               headY = (headY+1)%height;
                break;
                
            case 3:  
               if(headX - 1 < 0)
                  headX = 31;
               else
                  headX = (headX-1)%width;
               break;
               
            case 4:  
               headX = (headX+1)%width;
               break;
         
         }
         
         if((board[headX][headY] == 1)){ //&& headX != snake.getLastChild().getX() && headY != snake.getLastChild().getY()
            if(gameState == 3)
               sound.getRandomDieSound();
            if(gameState == 1 || gameState == 2)
               sound.getRegDie();
            gameState = 4;
         }   
                
      }
      
      
      public void keyPressed(KeyEvent e){
         if(gameState == 0){
            if(e.getKeyCode() == KeyEvent.VK_A){
               gameState = 1;
               timer.resetTime();
               //sound.getRegStart();
               repaint();
            }
            else if (e.getKeyCode() == KeyEvent.VK_B){
               gameState = 2;
               timer.resetTime();
               //sound.getRegStart();
               repaint();
            }
            else if (e.getKeyCode() == KeyEvent.VK_C){
               gameState = 3;
               timer.resetTime();
               sound.getmovesound();
               repaint();
            }
         }
         else if(gameState == 4) {
            if(e.getKeyCode() == KeyEvent.VK_A){
               gameState = 0;
               repaint();
            }
         
         }
         if(gameState != 0 && gameState != 4){   
            if(e.getKeyCode() == KeyEvent.VK_UP) {
               if(currentDirection != 2)
                  nextDirection = 1;
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
               if(currentDirection != 1)
                  nextDirection = 2;
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
               if(currentDirection != 4)
                  nextDirection = 3;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
               if(currentDirection != 3)
                  nextDirection = 4;
            }
         }
      }
      public void keyReleased(KeyEvent e){
   
      }
      public void keyTyped(KeyEvent e){
   
      }
      
      public void genMil() {
         milX = r.nextInt(width - 4) + 2;
         milY = r.nextInt(height - 4) + 2;
         
         if(board[milX][milY] == 1)
            genMil(); 
      }
      
      public void drawScore(Graphics g) {
      
         g.setColor(new Color(0,0,153));  // Color.RED
         for(int j=0; j<height;j++) {
            g.fillRect(s*j,s*33,s,s);
            g.fillRect(s*j,s*32,s,s);
         }  
         g.setFont(smallFont);
         g.setColor(Color.WHITE);    
         g.drawString(String.format("Score: %05d",score*100), (int)(20*s), (int)(33.6*s));
         g.setColor(Color.WHITE);    
         g.drawString(timer.getTime(), (int)(1*s), (int)(33.6*s));
      }
         
   
      public Color getColor() {
         
         switch (colorCount) {
            case 0:  
               return Color.RED;
            case 2:
               return Color.ORANGE;
            case 3:
               return Color.YELLOW;
            case 4:
               return Color.GREEN;
            case 5:
               return Color.BLUE;
            case 6:
               return Color.PINK;
            default:
               return (Color.PINK);
         }
         
      }
   }