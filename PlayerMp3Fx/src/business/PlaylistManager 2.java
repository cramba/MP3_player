package business;

import java.util.ArrayList;


public class PlaylistManager {
	ArrayList<Playlist> playlists = new ArrayList<Playlist>();
	
	public PlaylistManager() {
		Playlist playlist_1 = new Playlist("playlist_1", "---");
		playlist_1.addTrack("/Users/marcbachmann/Documents/Medieninformatik/3. Semester/EIBO/Praktikum/UE 01/mp3/01 Bring Mich Nach Hause.mp3");
		playlist_1.addTrack("/Users/marcbachmann/Documents/Medieninformatik/3. Semester/EIBO/Praktikum/UE 01/mp3/02 Drei Worte.mp3");
		playlists.add(playlist_1);
		
		Playlist hybridTheory = new Playlist("Hybrid Theory", "---");
		hybridTheory.addTrack("/Users/marcbachmann/Documents/Medieninformatik/3. Semester/EIBO/Praktikum/UE 01/mp3/Hybrid Theory/Papercut.mp3");
		hybridTheory.addTrack("/Users/marcbachmann/Documents/Medieninformatik/3. Semester/EIBO/Praktikum/UE 01/mp3/Hybrid Theory/One Step Closer.mp3");
		hybridTheory.addTrack("/Users/marcbachmann/Documents/Medieninformatik/3. Semester/EIBO/Praktikum/UE 01/mp3/Hybrid Theory/With You.mp3");
		//hybridTheory.addTrack("/Users/marcbachmann/Documents/Medieninformatik/3. Semester/EIBO/Praktikum/UE 01/mp3/Hybrid Theory/Pushing Me Away.mp3");
		playlists.add(hybridTheory);
		
		Playlist TopTracks = new Playlist("TopTracks", "---");
		TopTracks.addTrack("/Users/marcbachmann/Documents/Medieninformatik/3. Semester/EIBO/Praktikum/UE 01/mp3/10Tracks/Breezeblocks.mp3");
		TopTracks.addTrack("/Users/marcbachmann/Documents/Medieninformatik/3. Semester/EIBO/Praktikum/UE 01/mp3/10Tracks/Young-Folks.mp3");
		TopTracks.addTrack("/Users/marcbachmann/Documents/Medieninformatik/3. Semester/EIBO/Praktikum/UE 01/mp3/10Tracks/One-Dance.mp3");
		TopTracks.addTrack("/Users/marcbachmann/Documents/Medieninformatik/3. Semester/EIBO/Praktikum/UE 01/mp3/10Tracks/hammer.mp3");
		TopTracks.addTrack("/Users/marcbachmann/Documents/Medieninformatik/3. Semester/EIBO/Praktikum/UE 01/mp3/10Tracks/To The Ground.mp3");
		TopTracks.addTrack("/Users/marcbachmann/Documents/Medieninformatik/3. Semester/EIBO/Praktikum/UE 01/mp3/10Tracks/Alien.mp3");
		TopTracks.addTrack("/Users/marcbachmann/Documents/Medieninformatik/3. Semester/EIBO/Praktikum/UE 01/mp3/10Tracks/Californication.mp3");
		TopTracks.addTrack("/Users/marcbachmann/Documents/Medieninformatik/3. Semester/EIBO/Praktikum/UE 01/mp3/10Tracks/Falling Down.mp3");
		playlists.add(TopTracks);
	
	}
		
	
	public Playlist getPlaylist(String name){
		Playlist temp;
		for(int i = 0; i < playlists.size(); i++) {
			temp = playlists.get(i);
			if(temp.title == name) {
				return temp;
			}
		}
		System.out.println("Playlist nicht gefunden");
		return null;
	}
	
	
	public Playlist getAllTracks(){
		return null;
	}

}
