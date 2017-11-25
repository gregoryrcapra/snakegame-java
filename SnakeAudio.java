import java.io.*;
import sun.audio.*;
import java.util.Random;

public class SnakeAudio
{
   Random r = new Random();

public void SnakeAudio()
{
}

public void getdiesound()
{
    AudioPlayer MUSPlayer = AudioPlayer.player;
    AudioStream MUSFile;
    AudioData MData;
    AudioDataStream loop = null;
    try{
    MUSFile = new AudioStream(new FileInputStream("Agh.wav"));
    MData = MUSFile.getData();
    loop = new AudioDataStream(MData);
    }catch(IOException error){}
    MUSPlayer.start(loop); 
}

public void getdiesound1()
{
AudioPlayer MUSPlayer = AudioPlayer.player;
    AudioStream MUSFile;
    AudioData MData;
    AudioDataStream loop = null;
    try{
    MUSFile = new AudioStream(new FileInputStream("Die1.wav"));
    MData = MUSFile.getData();
    loop = new AudioDataStream(MData);
    }catch(IOException error){}
    MUSPlayer.start(loop); 
}


public void getdiesound2()
{
AudioPlayer MUSPlayer = AudioPlayer.player;
    AudioStream MUSFile;
    AudioData MData;
    AudioDataStream loop = null;
    try{
    MUSFile = new AudioStream(new FileInputStream("Ouch.wav"));
    MData = MUSFile.getData();
    loop = new AudioDataStream(MData);
    }catch(IOException error){}
    MUSPlayer.start(loop); 
}


public void getwoofsound1()
{
AudioPlayer MUSPlayer = AudioPlayer.player;
    AudioStream MUSFile;
    AudioData MData;
    AudioDataStream loop = null;
    try{
    MUSFile = new AudioStream(new FileInputStream("Woof1 1.wav"));
    MData = MUSFile.getData();
    loop = new AudioDataStream(MData);
    }catch(IOException error){}
    MUSPlayer.start(loop); 
}

public void getwoofsound2()
{
AudioPlayer MUSPlayer = AudioPlayer.player;
    AudioStream MUSFile;
    AudioData MData;
    AudioDataStream loop = null;
    try{
    MUSFile = new AudioStream(new FileInputStream("Woof2.wav"));
    MData = MUSFile.getData();
    loop = new AudioDataStream(MData);
    }catch(IOException error){}
    MUSPlayer.start(loop); 
}


public void getpelletsound1()
{
AudioPlayer MUSPlayer = AudioPlayer.player;
    AudioStream MUSFile;
    AudioData MData;
    AudioDataStream loop = null;
    try{
    MUSFile = new AudioStream(new FileInputStream("Caffinated.wav"));
    MData = MUSFile.getData();
    loop = new AudioDataStream(MData);
    }catch(IOException error){}
    MUSPlayer.start(loop); 
}


public void getpelletsound2()
{
AudioPlayer MUSPlayer = AudioPlayer.player;
    AudioStream MUSFile;
    AudioData MData;
    AudioDataStream loop = null;
    try{
    MUSFile = new AudioStream(new FileInputStream("Diet pellets.wav"));
    MData = MUSFile.getData();
    loop = new AudioDataStream(MData);
    }catch(IOException error){}
    MUSPlayer.start(loop); 
}


public void getpelletsound3()
{
AudioPlayer MUSPlayer = AudioPlayer.player;
    AudioStream MUSFile;
    AudioData MData;
    AudioDataStream loop = null;
    try{
    MUSFile = new AudioStream(new FileInputStream("Eat.wav"));
    MData = MUSFile.getData();
    loop = new AudioDataStream(MData);
    }catch(IOException error){}
    MUSPlayer.start(loop); 
}


public void getpelletsound4()
{
AudioPlayer MUSPlayer = AudioPlayer.player;
    AudioStream MUSFile;
    AudioData MData;
    AudioDataStream loop = null;
    try{
    MUSFile = new AudioStream(new FileInputStream("Milk pellets.wav"));
    MData = MUSFile.getData();
    loop = new AudioDataStream(MData);
    }catch(IOException error){}
    MUSPlayer.start(loop); 
}


public void getpelletsound5()
{
AudioPlayer MUSPlayer = AudioPlayer.player;
    AudioStream MUSFile;
    AudioData MData;
    AudioDataStream loop = null;
    try{
    MUSFile = new AudioStream(new FileInputStream("Sugar Free.wav"));
    MData = MUSFile.getData();
    loop = new AudioDataStream(MData);
    }catch(IOException error){}
    MUSPlayer.start(loop); 
}

public void getgameoversound()
{
AudioPlayer MUSPlayer = AudioPlayer.player;
    AudioStream MUSFile;
    AudioData MData;
    AudioDataStream loop = null;
    try{
    MUSFile = new AudioStream(new FileInputStream("Loser.wav"));
    MData = MUSFile.getData();
    loop = new AudioDataStream(MData);
    }catch(IOException error){}
    MUSPlayer.start(loop); 
}

public void getmovesound()
{
AudioPlayer MUSPlayer = AudioPlayer.player;
    AudioStream MUSFile;
    AudioData MData;
    AudioDataStream loop = null;
    try{
    MUSFile = new AudioStream(new FileInputStream("Move.wav"));
    MData = MUSFile.getData();
    loop = new AudioDataStream(MData);
    }catch(IOException error){}
    MUSPlayer.start(loop); 
}

public void getV8sound()
{
AudioPlayer MUSPlayer = AudioPlayer.player;
    AudioStream MUSFile;
    AudioData MData;
    AudioDataStream loop = null;
    try{
    MUSFile = new AudioStream(new FileInputStream("V8.wav"));
    MData = MUSFile.getData();
    loop = new AudioDataStream(MData);
    }catch(IOException error){}
    MUSPlayer.start(loop); 
}

   public void getRegEat()
   {
      AudioPlayer MUSPlayer = AudioPlayer.player;
      AudioStream MUSFile;
      AudioData MData;
      AudioDataStream loop = null;
      try {
         MUSFile = new AudioStream(new FileInputStream("regeat.wav"));
         MData = MUSFile.getData();
         loop = new AudioDataStream(MData);
      } catch(IOException error){
         System.out.println("File Not Found");
         }
      MUSPlayer.start(loop); 
   }
   public void getRegDie()
   {
      AudioPlayer MUSPlayer = AudioPlayer.player;
      AudioStream MUSFile;
      AudioData MData;
      AudioDataStream loop = null;
      try {
         MUSFile = new AudioStream(new FileInputStream("lifelost.wav"));
         MData = MUSFile.getData();
         loop = new AudioDataStream(MData);
      } catch(IOException error){}
      MUSPlayer.start(loop); 
   }
   public void getRegStart()
   {
      AudioPlayer MUSPlayer = AudioPlayer.player;
      AudioStream MUSFile;
      AudioData MData;
      AudioDataStream loop = null;
      try {
         MUSFile = new AudioStream(new FileInputStream("gameloop.wav"));
         MData = MUSFile.getData();
         loop = new AudioDataStream(MData);
      } catch(IOException error){System.out.println("File Not Found");}
      MUSPlayer.start(loop); 
   }

   public void getRandomDieSound() {
      int rand = r.nextInt(4);
      
      if(rand == 0)
         getdiesound();
      else if(rand == 1)
         getdiesound1();
      else if(rand == 2)
         getdiesound2();
      else if(rand == 3)
         getgameoversound();
   
   }
   
   public void getRandomEatSound() {
      int rand = r.nextInt(8);
      
      if(rand == 0)
         getwoofsound1();
      else if(rand == 1)
         getwoofsound2();
      else if(rand == 2)
         getpelletsound1();
      else if(rand == 3)
         getpelletsound2();
      else if(rand == 4)
         getpelletsound3();
      else if(rand == 5)
         getpelletsound4();
      else if(rand == 6)
         getpelletsound5();
      else if(rand == 7)
         getV8sound();
      else if(rand == 8)
         getpelletsound5();
   
   }

}

