import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {
	
	private ArrayList <String> musicFiles;
	
	int CurrentIndex;
	
	String MusicFile;
	String File;
	
	public MusicPlayer() {
		
		
	}
	
//	public void playSound() {
//		try {
//			File soundFile = new File(File);	
//			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
//			AudioFormat format = ais.getFormat();
//			Info info = new Info(Clip.class, format);
//			Clip clip = (Clip) AudioSystem.getLine(info);
//			clip.open(ais);
//			clip.start();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void playSound( String file) {
		 
		try {
			FileInputStream fileInputStream = new FileInputStream(file+"mp3");
			
			
	}catch (Exception e) {
		System.out.println(e);
	}
	
}

}



 

