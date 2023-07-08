package application.scenes.TrackScene;

import application.scenes.ViewController;
import application.uicomponents.controlView.ControlViewController;
import application.uicomponents.progress.ProgressViewController;
import application.uicomponents.volumeView.VolumeViewController;
import business.MP3Player;
import business.Track;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class TrackScene extends BorderPane{
	
	private Label title;
	private Label interpret;
	private Button mainView;
	private ListView<Track> playlistView;

	private MP3Player player;
	
	public TrackScene(MP3Player player) {
		this.player = player;
		
		//header
		VBox header = new VBox();
		title = new Label("Hier Titel einfügen");
		title.setId("title-label");
		title.getStyleClass().addAll("info-label");
		interpret = new Label("Hier Interpreten einfügen");
		interpret.setId("interpret-label");
		interpret.getStyleClass().addAll("info-label");
		mainView = new Button();
		mainView.getStyleClass().addAll("icon-button");
		mainView.setId("show-playing");
		header.getChildren().addAll(title, interpret, mainView);
		
		header.setPadding(new Insets(10));
		header.setAlignment(Pos.CENTER);
		header.setSpacing(5);
		header.setId("header");
		this.setTop(header);
		
		//center
		playlistView = new ListView<Track>();
		this.setCenter(playlistView);
		
		VBox left = new VBox();
		left.setAlignment(Pos.CENTER);
		ViewController volumeViewController;
		volumeViewController = new VolumeViewController(player);
		left.getChildren().add(volumeViewController.getRootView());
		this.setLeft(left);
		
		//bottom
		VBox bottom = new VBox();
		
		ViewController controlViewController;
		controlViewController = new ControlViewController(player);
		
		ViewController progressViewController;
		progressViewController = new ProgressViewController(player);
		
		bottom.getChildren().addAll(controlViewController.getRootView(), progressViewController.getRootView());
		
		this.setBottom(bottom);
		this.setId("BPane");		
		this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	}
	
	public Label getTitle() {
		return title;
	}
	
	public Label getInterpret() {
		return interpret;
	}
	
	public Button getMainView() {
		return mainView;
	}
	
	public ListView getPlaylistView() {
		return playlistView;
	}
}
