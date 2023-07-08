package application.uicomponents.volumeView;

import application.scenes.ViewController;
import business.MP3Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class VolumeViewController extends ViewController{
	private Slider volumeSlider;
	private Button muteButton;
	private Double oldVolume;
	
	private MP3Player player;
	
	public VolumeViewController(MP3Player player) {
		
		this.player = player;
		
		VolumeView view = new VolumeView();
		volumeSlider = view.getVolumeSlider();
		muteButton = view.getMuteButton();
		
		
		rootView = view;
		
		initialize();
		
	}

	@Override
	public void initialize() {
		
		volumeSlider.valueProperty().addListener(
				new ChangeListener<Number>() {
					public void changed(
							ObservableValue<? extends Number>oV,
							Number oldValue,
							Number newValue) {
						
						if(newValue.doubleValue() > 0.66) {
							muteButton.setId("mute-button-high");
						}else if(newValue.doubleValue() > 0.33) {
							muteButton.setId("mute-button-medi");
						}else if(newValue.doubleValue() > 0.001) {
							muteButton.setId("mute-button-low");
						}else {
							muteButton.setId("mute-button-mute");
						}
						
						
						
						player.setVolumeProp(newValue.doubleValue());
						player.setVolume(newValue.doubleValue()); 
					}
				});
		
		player.volumeProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				volumeSlider.setValue(newValue.doubleValue());
				player.setVolume(newValue.doubleValue());
				
			}
			
		});
		
		muteButton.addEventHandler(ActionEvent.ACTION, 
				event -> {
					if(player.getVolume() == 0) {
						player.setVolumeProp(oldVolume);
					}else {
						oldVolume = player.getVolume();
						player.setVolumeProp(0.0);
					}
				}
			);
		
	}
	
	public Pane getRootView() {
		return rootView;
	}
}
