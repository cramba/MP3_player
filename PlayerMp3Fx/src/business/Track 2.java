package business;

import java.io.IOException;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class Track {
	
	String title;
	int length;
	String albumTitle;
	String interpret;
	byte [] coverFile;
	String soundFile;
	
	public Track(String file) {
        try {
            Mp3File mp3file = new Mp3File(file);
            if(mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                title = id3v2Tag.getTitle();
                System.out.println("Titel: " + title);
                length = (int) mp3file.getLengthInSeconds();
                //length = id3v2Tag.getLength();
                System.out.println("Länge: " + length);
                albumTitle = id3v2Tag.getAlbum();
                System.out.println("Album: " + albumTitle);
                interpret = id3v2Tag.getArtist();
                System.out.println("Artist: " + interpret);
                soundFile = file;
                coverFile = id3v2Tag.getAlbumImage();
//                soundFile = id3v2Tag.getAudiofileUrl();
                System.out.println(title +" "+length+" "+albumTitle+" "+interpret);
            }else {
                System.out.println("fail");
            }
        } catch (UnsupportedTagException | InvalidDataException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	public byte[] getCoverFile() {
		return coverFile;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getFile() {
		return soundFile;
	}
	
	public String getInterpret() {
		return interpret;
	}
	
	public int getLength() {
		return length;
	}
}
