
   public class SnakeNode {
   
      boolean hasChild;
      boolean head = false;
      //SnakeNode parent;
      SnakeNode child;
      int x, y;
      
      public SnakeNode(int startX, int startY) {
         x = startX;
         y = startY;
         head = true;
      }
      
      public SnakeNode() {
      }
      /*
      public SnakeNode(int startX, int startY, SnakeNode p) {
         x = startX;
         y = startY;
         p.setChild(this);
         parent = p;
      }*/
      
      public int getX() {
      
      return x;
      }
      
      public int getY() {
      
      return y;
      }
      
      public void setX(int xx) {
         x = xx;
      }
      
      public void setY(int yy) {
         y = yy;
      }
      
      public void addNode(SnakeNode s) {
         if(hasChild == true)
            child.addNode(s);
         else {
            child = s;
            hasChild = true;
            child.setLocation(x,y);
         }
      }
      
      public SnakeNode getChild() {
      
      return child;
      }
      
      public SnakeNode getLastChild() {
         if(hasChild == true)
            return child.getLastChild();
         else
            return this;
      }
      
      public void setLocation(int xx, int yy) {
         if(hasChild)
            child.setLocation(x,y);
         x = xx;
         y = yy;
      }
      
      public int[][] getLocationArray(int[][] board) {
         board[x][y] = 1;
         
         if(hasChild)
            return child.getLocationArray(board);
         else
            return board;
            
      }

      public void moveUp() {
         if(y-1 < 0)
            setLocation(x%32,31);
         else
            setLocation(x%32,y-1);
      }
      
      public void moveDown() {
         setLocation(x%32,(y+1)%32);
      }
      
      public void moveLeft() {
         if(x-1 < 0)
            setLocation(31,(y%32));
         else
            setLocation(x-1,y%32);
      }
      
      public void moveRight() {
         setLocation((x+1)%32,y%32);
      }
      
      
   }