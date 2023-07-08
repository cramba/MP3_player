package application.scenes.MainScene;

import application.scenes.ViewController;
import application.uicomponents.controlView.ControlViewController;
import application.uicomponents.progress.ProgressViewController;
import application.uicomponents.volumeView.VolumeViewController;
import business.MP3Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainScene extends BorderPane {

	private Label title;
	private Label interpret;
	private Button showTracks;
	private ImageView coverView;
	
	private MP3Player player;
	
	public MainScene(MP3Player player) {
		// header
		this.player = player;
		VBox header = new VBox();
		title = new Label("Hier Titel einfügen");
		title.setId("title-label");
		title.getStyleClass().addAll("info-label");
		interpret = new Label("Hier Interpreten einfügen");
		interpret.getStyleClass().addAll("info-label");
		showTracks = new Button();
		showTracks.getStyleClass().addAll("icon-button");
		showTracks.setId("show-tracks");
		header.getChildren().addAll(title, interpret, showTracks);
		header.setPadding(new Insets(10));
		header.setAlignment(Pos.CENTER);
		header.setSpacing(5);
		header.setId("header");
		this.setTop(header);
		
		//center
		coverView = new ImageView();
		coverView.setFitHeight(400);
		coverView.setFitWidth(400);
		this.setCenter(coverView);	
		
		//Volume Slider
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
	
	public ImageView getCoverView() {
		return coverView;
	}
	
	public Button getShowTracks() {
		return showTracks;
	}
}