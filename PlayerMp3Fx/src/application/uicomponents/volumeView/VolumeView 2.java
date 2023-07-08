package application.uicomponents.volumeView;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class VolumeView extends VBox{
	private Slider volumeSlider;
	private Button muteButton;
	
	public VolumeView() {
		
		volumeSlider = new Slider();
		volumeSlider.setPrefHeight(130);
		volumeSlider.setMax(1);
		volumeSlider.setValue(0.3);
		volumeSlider.setOrientation(Orientation.VERTICAL);
		volumeSlider.setId("volume-slider");
		
		muteButton = new Button();
		muteButton.getStyleClass().addAll("icon-button");
		muteButton.setId("mute-button-medi");
		
		this.getChildren().addAll(volumeSlider, muteButton);
		this.setAlignment(Pos.CENTER);
		
		this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		
	}
	
	public Slider getVolumeSlider() {
		return volumeSlider;
	}
	
	public Button getMuteButton() {
		return muteButton;
	}

}
