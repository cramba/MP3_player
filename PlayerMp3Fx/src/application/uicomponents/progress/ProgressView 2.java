package application.uicomponents.progress;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class ProgressView extends HBox {
	
	private Label startTime;
	private Label endTime;
	private Slider progress;
	
	public ProgressView() {
		
		startTime = new Label("00.00");
		startTime.getStyleClass().addAll("time-label");
		progress = new Slider();
		progress.setPrefWidth(200);
		endTime = new Label("00.00");
		endTime.getStyleClass().addAll("time-label");
		
		this.setPadding(new Insets(10));
		this.setAlignment(Pos.CENTER);
		this.setId("hBox");
		
		this.getChildren().addAll(startTime, progress, endTime);
		this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	}
	
	public Label getStartTime() {
		return startTime;
	}
	
	public Label getEndTime() {
		return endTime;
	}
	
	public Slider getProgress() {
		return progress;
	}

}