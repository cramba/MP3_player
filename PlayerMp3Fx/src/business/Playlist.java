package business;

import java.sql.Date;
import java.util.ArrayList;


public class Playlist {
	String title;
	Date creationDate;
	String coverFile;
	ArrayList<Track> songs = new ArrayList<Track>();
	
	public Playlist(String title, String coverFile) {
		this.title = title;
		long millis=System.currentTimeMillis();  
		creationDate=new java.sql.Date(millis);  
	}
	
 	public void addTrack(Track track) {
 		songs.add(track);
 	}
 	
 	public void addTrack(String source) {
 		Track track = new Track(source);
 		songs.add(track);
 	}
	
	public int getNumberOfTracks(){
		return songs.size();
	}
	
	public Track getTrack(int no){
		return songs.get(no);
	}
	
	public String getTrackName(int no) {
		return songs.get(no).getTitle();
	}
	
	public String getInterpret(int no) {
		return songs.get(no).getInterpret();
	}
	
	public ArrayList<Track> getPlaylist() {
		return songs;
	}
	
	public int getTrackNo(Track track) {
		return songs.indexOf(track);
	}

}
