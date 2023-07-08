package business;

import java.util.ArrayList;
import java.util.Random;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class MP3Player {
	private SimpleMinim minim;
	private SimpleAudioPlayer audioPlayer;
	private int trackNo;
	private SimpleStringProperty actTrackName;
	private SimpleStringProperty actInterpretName;
	private SimpleDoubleProperty volume;
	private SimpleIntegerProperty timePassed; 
	private SimpleBooleanProperty repeat;

	private int randomNumber;
	private SimpleBooleanProperty shuffle;
	private ArrayList<Integer> shuffleList;
	private Playlist actPlaylist;
	boolean isPlaying = false;
	private SimpleBooleanProperty playProp;

	private Thread thread;
	
	private SimpleObjectProperty <Track> trackProp;
	
	public MP3Player() {
		PlaylistManager playlistManager = new PlaylistManager();
		volume = new SimpleDoubleProperty();
		timePassed = new SimpleIntegerProperty();
		trackProp = new SimpleObjectProperty<Track>();
		repeat = new SimpleBooleanProperty(false);
		shuffle = new SimpleBooleanProperty(false);
		playProp = new SimpleBooleanProperty();
		//actPlaylist = playlistManager.getPlaylist("playlist_1");
		//actPlaylist = playlistManager.getPlaylist("Hybrid Theory");
		actPlaylist = playlistManager.getPlaylist("TopTracks");
		minim = new SimpleMinim();
		trackNo = 0;
		
		actTrackName = new SimpleStringProperty();
		actInterpretName = new SimpleStringProperty();
		setVolumeProp(0.3);
		
		shuffleList = new ArrayList<Integer>(actPlaylist.getNumberOfTracks());
		
		thread = new Thread();
	}
	
	public ArrayList<Track> getList(){
		return actPlaylist.getPlaylist();
	}
	
	public final SimpleStringProperty actTrackNameProperty() {
		return this.actTrackName;
	}
	
	public final String getActTrackName() {
		return this.actTrackNameProperty().get();
	}
	
	public final void setActTrackName(String name) {
		this.actTrackNameProperty().set(name);
	}
	
	
	
	public final SimpleStringProperty actInterpretNameProperty() {
		return this.actInterpretName;
	}
	
	public final String getActInterpretName() {
		return this.actInterpretNameProperty().get();
	}
	
	public final void setActInterpretName(String name) {
		this.actInterpretNameProperty().set(name);
	}
	
	
	public double getVolume() {
		return volume.get();
	}
	
	public void setVolumeProp(Double value) {
		volume.setValue(value);
	}
	
	public SimpleDoubleProperty volumeProperty() {
		return volume;
	}
	
	
	
	public int getTimePassed() {
		return timePassed.get();
	}
	
	public void setTimePassed(int value) {
		timePassed.setValue(value);
	}
	
	public SimpleIntegerProperty timePassedProperty() {
		return timePassed;
	}
	
	public SimpleObjectProperty<Track> getTrackProp(){
		return trackProp;
	}
	
	
	public boolean getRepeat() {
		return repeat.getValue();
	}
	
	public void setRepeat(boolean value) {
		repeat.setValue(value);
	}
	
	public SimpleBooleanProperty getRepeatProp() {
		return repeat;
	}
	
	public Boolean getShuffle() {
		return shuffle.getValue();
	}
	
	public SimpleBooleanProperty getShuffleProp() {
		return shuffle;
	}
	
	public void setShuffle(boolean value) {
		shuffle.setValue(value);
	}
	
	public void playSelected(Track track) {
		if(audioPlayer == null) {
			trackNo = actPlaylist.getTrackNo(track);
			audioPlayer = minim.loadMP3File(track.getFile());
			trackProp.setValue(actPlaylist.getTrack(trackNo));
			setVolume(getVolume());
			setActTrackName(getTrackName()); 
			setActInterpretName(getInterpret());
		}else {
			if (audioPlayer.isPlaying()){
				audioPlayer.pause();
				trackNo = actPlaylist.getTrackNo(track);
				audioPlayer = minim.loadMP3File(track.getFile());
				trackProp.setValue(actPlaylist.getTrack(trackNo));
				setVolume(getVolume());
				setActTrackName(getTrackName()); 
				setActInterpretName(getInterpret());
			}
		}
		
		new Thread(){
			public void run(){
				audioPlayer.play();
				interrupt();
			}
		}.start();
		setIsPlaying(true);
		calcTimePassed();
	}
	
	
	public void play(){
		if(audioPlayer == null) {
			audioPlayer = minim.loadMP3File(actPlaylist.getTrack(trackNo).getFile());
			trackProp.setValue(actPlaylist.getTrack(trackNo));
			setVolume(getVolume());
			setActTrackName(getTrackName()); 
			setActInterpretName(getInterpret());
		}
		new Thread(){
			public void run(){
				audioPlayer.play();
				interrupt();
			}
		}.start();
	
		setIsPlaying(true);
		calcTimePassed();
	}
	
	public void pause() {
		if (audioPlayer != null) {
			audioPlayer.pause();
			setIsPlaying(false);
		}
	}
	
	public void skip() {
		if (audioPlayer.isPlaying()){
			audioPlayer.pause();
		}
		if(!getRepeat() && !getShuffle()) {
			if(trackNo == (actPlaylist.getNumberOfTracks()) -1) {
				trackNo = 0;
			}else {
				trackNo ++;
			}
		}else if(getShuffle() && !getRepeat()) {
			if(shuffleList.size() == 0) {
				makeShuffleList();
			}
			randomNumber = (int) (Math.random() * (shuffleList.size()));
			trackNo = shuffleList.remove(randomNumber);
		}
		audioPlayer = minim.loadMP3File(actPlaylist.getTrack(trackNo).getFile());
		trackProp.setValue(actPlaylist.getTrack(trackNo));
		setVolume(getVolume());
		setActTrackName(getTrackName()); 
		setActInterpretName(getInterpret());
		new Thread(){
			public void run(){
				audioPlayer.play();
				interrupt();
			}
		}.start();
	}
	
	public void skipBack() {
		if (audioPlayer.isPlaying()){
			audioPlayer.pause();
		}
		if(!getRepeat() && !getShuffle()) {
			if(trackNo == 0) {
				trackNo = actPlaylist.getNumberOfTracks() -1;
			}else {
				trackNo --;
			} 
		}else if(getShuffle() && !getRepeat()) {
			if(shuffleList.size() == 0) {
				makeShuffleList();
			}
			randomNumber = (int) (Math.random() * (shuffleList.size()));
			trackNo = shuffleList.remove(randomNumber);
		}
		audioPlayer = minim.loadMP3File(actPlaylist.getTrack(trackNo).getFile());
		trackProp.setValue(actPlaylist.getTrack(trackNo));
		setVolume(getVolume());
		setActTrackName(getTrackName()); 
		setActInterpretName(getInterpret());
		new Thread(){
			public void run(){
				audioPlayer.play();
				interrupt();
			}
		}.start();
	}
	
	public void makeShuffleList() {
		//liste erstellen
        for(int i = 1; i <= actPlaylist.getNumberOfTracks(); i++) {
            shuffleList.add(i - 1); 
        }
	}
	
	public void stop() {
		if (audioPlayer != null) {
			audioPlayer.rewind();
			audioPlayer.pause();
		}
	}
	
	public void setVolume(double amount){
		float dezibel = (float) (20 * Math.log(amount));
		if(audioPlayer != null) {
			audioPlayer.setGain(dezibel);
		}
	}
	
	public void calcTimePassed() {

        thread = new Thread(() -> {
            while (!thread.isInterrupted()) {

                while (audioPlayer.isPlaying() == true) {
                    timePassed.set(audioPlayer.position());
                    if (audioPlayer.position() == actPlaylist.getTrack(trackNo).getLength() - 1000) {

                        skip();
                    }
                    try {
                        thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block

                        thread.interrupt();
                        calcTimePassed();
                    }
                }
            }
            
            //skip();
        });

        thread.start();

    }
	
	public String getTrackName() {
		return actPlaylist.getTrackName(trackNo);
	}
	
	public String getInterpret() {
		return actPlaylist.getInterpret(trackNo);
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	
	public SimpleBooleanProperty getPlayProp() {
		return playProp;
	}
	
	public void setNotPlaying() {
		isPlaying = false;
		playProp.setValue(false);
	}
	
	public void setPlaying() {
		isPlaying = audioPlayer.isPlaying();
		playProp.setValue(audioPlayer.isPlaying());
	}
	
	public void setIsPlaying(Boolean value) {
		isPlaying = value;
		playProp.setValue(value);
	}
	
	
	public byte[] getCoverFile() {
		return actPlaylist.getTrack(trackNo).getCoverFile();
	}
	
	public Playlist getActPlaylist() {
		return actPlaylist;
	}
}