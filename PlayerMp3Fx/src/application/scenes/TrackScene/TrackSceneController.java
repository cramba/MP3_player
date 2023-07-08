package application.scenes.TrackScene;


import Enums.Scenes;
import application.Main;
import application.scenes.ViewController;
import business.MP3Player;
import business.Track;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class TrackSceneController extends ViewController{
	private Label titleLabel;
	private Label interpretLabel;
	private Button mainView;
	private ListView<Track> playlistView;
	
	private MP3Player player;
	private Main main;
	
	
	public TrackSceneController(MP3Player player, Main main) {
		
		this.player = player;
		this.main = main;
		
		TrackScene view = new TrackScene(player);
		titleLabel = view.getTitle();
		interpretLabel = view.getInterpret();
		mainView = view.getMainView();
		playlistView = view.getPlaylistView();
		
		rootView = view;
		
		view.getStylesheets().add(getClass().getResource("cell.css").toExternalForm());
		initialize();
	}

	@Override
	public void initialize() {
		
		
		playlistView.setCellFactory(new Callback<ListView<Track>, ListCell<Track>>() {

			@Override
			public ListCell<Track> call(ListView<Track> param) {
				// TODO Auto-generated method stub
				return new TrackCell(player);
			}
			
		});
		ObservableList<Track> uiModel = playlistView.getItems();
		uiModel.addAll(player.getList()); 
		
		playlistView.getSelectionModel()
					.selectedItemProperty().addListener(
							new ChangeListener<Track>(){
			@Override
			public void changed(
				ObservableValue<? extends Track> observable,
				Track oldValue,
				Track newValue) {
				player.playSelected(newValue);
			}
		});
			
		
		titleLabel.setText(player.getTrackName());
		interpretLabel.setText(player.getInterpret());
		
		player.actTrackNameProperty().addListener(
				new ChangeListener<String>() {
				
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							String newValue) {
						titleLabel.setText(newValue);
					}
				});
		
		
		player.actInterpretNameProperty().addListener(
				new ChangeListener<String>() {
				
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							String newValue) {
						interpretLabel.setText(newValue);
					}
				});
		
		mainView.addEventHandler(ActionEvent.ACTION, 
				event -> {
					main.switchScene(Scenes.FIRST_SCENE);
				}
			);
		
	}
	
	public Pane getRootView() {
		return rootView;
	}

}
