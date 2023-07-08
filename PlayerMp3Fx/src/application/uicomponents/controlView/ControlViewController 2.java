package application.uicomponents.controlView;

import application.scenes.ViewController;
import business.MP3Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ControlViewController extends ViewController{
	private Button playButton;
	private Button pauseButton;
	private Button skipButton;
	private Button skipBackButton;
	private Button shuffleButton;
	private Button repeatButton;
	
	private MP3Player player;
	
	public ControlViewController(MP3Player player) {
		
		this.player = player;
		
		ControlView view = new ControlView();
		playButton = view.getPlayButton();
		pauseButton = view.getPauseButton();
		skipButton = view.getSkipButton();
		skipBackButton = view.getSkipBackButton();
		shuffleButton = view.getShuffleButton();
		repeatButton = view.getRepeatButton();
		
		rootView = view;
		
		initialize();
	}
	
	@Override
	public void initialize() {
		
		player.getPlayProp().addListener(
				new ChangeListener<Boolean>() {

					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
							Boolean newValue) {
						
						if(newValue) {
							playButton.setId("pause-button");
						}else {
							playButton.setId("play-button");
						}
					}
				});
		
		player.getShuffleProp().addListener(
				new ChangeListener<Boolean>() {

					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
							Boolean newValue) {
						
						if(newValue) {
							shuffleButton.setId("shuffle-on");
						}else {
							shuffleButton.setId("shuffle-off");
						}
					}
				});
		
		player.getRepeatProp().addListener(
				new ChangeListener<Boolean>() {

					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
							Boolean newValue) {
						
						if(newValue) {
							repeatButton.setId("repeat-on");
						}else {
							repeatButton.setId("repeat-off");
						}
					}
				});
		
		
		playButton.addEventHandler(ActionEvent.ACTION, 
			event -> {
				if(player.isPlaying()) {
					playButton.setId("play-button");
					player.pause();
				}else {
					playButton.setId("pause-button");
					player.play();
				}
			}
		);	
		
		pauseButton.addEventHandler(ActionEvent.ACTION, 
				event -> {
					player.pause();
				}
			);
		
		
		skipButton.addEventHandler(ActionEvent.ACTION, 
				event -> {

					if(player.isPlaying()) {
						player.skip();
					}else {
						playButton.setId("pause-button");
						player.setIsPlaying(true);
						player.skip();
					}
				}
			);
		
		skipBackButton.addEventHandler(ActionEvent.ACTION, 
				event -> {
					
					if(player.isPlaying()) {
						player.skipBack();
					}else {
						playButton.setId("pause-button");
						player.setIsPlaying(true);
						player.skip();
					}
				}
			);
		
		
		repeatButton.addEventHandler(ActionEvent.ACTION,
				event -> {
					if(player.getRepeat()) {
						player.setRepeat(false);
						repeatButton.setId("repeat-off");
					}else {
						player.setRepeat(true);
						repeatButton.setId("repeat-on");
					}
				}
			);
		
		
		shuffleButton.addEventHandler(ActionEvent.ACTION,
				event -> {
					if(player.getShuffle()) {
						player.setShuffle(false);
						shuffleButton.setId("shuffle-off");
					}else {
						player.setShuffle(true);
						shuffleButton.setId("shuffle-on");
						player.makeShuffleList();
					}
				}
			);
	}
	
	public Pane getRootView() {
		return rootView;
	}

}

