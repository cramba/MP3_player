package application.uicomponents.controlView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class ControlView extends HBox {
	private Button shuffleButton;
	private Button skipBackButton;
	private Button playButton;
	private Button pauseButton;
	private Button skipButton;
	private Button repeatButton;
	

	public ControlView() {
		
		shuffleButton = new Button();
		shuffleButton.getStyleClass().addAll("icon-button");
		shuffleButton.setId("shuffle-off");
		
		skipBackButton = new Button();
		skipBackButton.getStyleClass().addAll("icon-button");
		skipBackButton.setId("skipback-button");
		
		playButton = new Button();
		playButton.getStyleClass().addAll("icon-button");
		playButton.setId("play-button");
		
		pauseButton = new Button();
		pauseButton.getStyleClass().addAll("icon-button");
		pauseButton.setId("pause-button");
		
		skipButton = new Button();
		skipButton.getStyleClass().addAll("icon-button");
		skipButton.setId("skip-button");
		
		repeatButton = new Button();
		repeatButton.getStyleClass().addAll("icon-button");
		repeatButton.setId("repeat-off");
		
		this.setPadding(new Insets(10));
		this.setAlignment(Pos.CENTER);
		this.setId("hBox");
		
		this.getChildren().addAll(shuffleButton, skipBackButton, playButton, skipButton, repeatButton);
		this.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

	}
	
	public Button getShuffleButton() {
		return shuffleButton;
	}
	
	public Button getSkipBackButton() {
		return skipBackButton;
	}
	
	public Button getPlayButton( ) {
		return playButton;
	}
	
	public Button getPauseButton() {
		return pauseButton;
	}
	
	public Button getSkipButton() {
		return skipButton;
	}
	
	public Button getRepeatButton() {
		return repeatButton;
	}
}
