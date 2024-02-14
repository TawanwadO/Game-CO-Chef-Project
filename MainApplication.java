/*  
    6413104 Panfa Tonsomboon
    6413111 Wisara Wongrattanapipat
    6413213 Tawanwad Onnom 
    6413222 Phumed Thumtechanon
*/

package Project3_213;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.sound.sampled.*;

public class MainApplication {

    public static void main(String[] args) {

        Music m = new Music();
        new OpenScreen(m);
        
    }

};

class Music {
    
    private String path = "src/main/Java/Project3_213/music/"; 
    MySoundEffect themeSound;
    FloatControl fc;
    float previousVolume = 0;
	float currentVolume = 0;
	boolean mute = false;
	
	public MySoundEffect getSoundEffect() {
		return themeSound;
	}
    
    public Music(){ 
    	themeSound = new MySoundEffect(path + "Happy_cat.wav"); 
    	themeSound.playLoop(); }
    
    public void change_home(){
        themeSound.stop();
        themeSound = new MySoundEffect(path + "Happy_cat.wav"); 
        themeSound.playLoop(); 
    }
    
    public void change_normal(){ 
        themeSound.stop();
        themeSound = new MySoundEffect(path + "afternoon_nap.wav"); 
        themeSound.playLoop(); 
    }
    
    public void change_angel(){ 
        themeSound.stop();
        themeSound = new MySoundEffect(path + "marshmallow.wav"); 
        themeSound.playLoop(); 
    }
    
    public void change_devil(){ 
        themeSound.stop();
        themeSound = new MySoundEffect(path + "PussyCat.wav"); 
        themeSound.playLoop(); 
    }
    
    public void change_mexicohat(){ 
        themeSound.stop();
        themeSound = new MySoundEffect(path + "MexicanHatDance.wav"); 
        themeSound.playLoop(); 
    }
    
    public void change_teens(){ 
        themeSound.stop();
        themeSound = new MySoundEffect(path + "FoolishLove.wav"); 
        themeSound.playLoop(); 
    }

};

class MySoundEffect
{
	
    private Clip clip;
    //add
    FloatControl fc;
    float previousVolume = 0;
    float currentVolume = 0;
	//

    public MySoundEffect(String filename)
    {
	try
	{
            java.io.File file = new java.io.File(filename);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            //add
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            //
	}
	catch (Exception e) { e.printStackTrace(); }
    }
    public void playOnce()   { clip.setMicrosecondPosition(0); clip.start(); }
    public void playLoop()   { clip.loop(Clip.LOOP_CONTINUOUSLY); }
    public void stop()       { clip.stop(); }

};