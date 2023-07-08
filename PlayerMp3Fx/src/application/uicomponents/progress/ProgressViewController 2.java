package application.uicomponents.progress;

import java.util.concurrent.TimeUnit;

import application.scenes.ViewController;
import business.MP3Player;
import business.Track;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class ProgressViewController extends ViewController{
	
	private Label startTime;
	private Label endTime;
	private Slider progress;
	private String timePassed;
	private int timeRemained;
	private String leftTime;
	private int trackLength;
	
	private MP3Player player;
	
	public ProgressViewController(MP3Player player) {
		
		this.player = player;
		
		ProgressView view = new ProgressView();
		
		startTime = view.getStartTime();
		endTime = view.getEndTime();
		progress = view.getProgress();
		
		rootView = view;
		
		initialize();
	}

	@Override
	public void initialize() {
		
		player.timePassedProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				
				Platform.runLater(new Runnable(){
                    public void run() {
                    	timePassed = String.format("%02d:%02d ", 
                    		    TimeUnit.MILLISECONDS.toMinutes(newValue.intValue()),
                    		    TimeUnit.MILLISECONDS.toSeconds(newValue.intValue()) - 
                    		    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(newValue.intValue()))
                    		);
                    	
                    	timeRemained = trackLength - newValue.intValue();
                    	
                    	leftTime = String.format("%02d:%02d ", 
                    		    TimeUnit.MILLISECONDS.toMinutes(timeRemained),
                    		    TimeUnit.MILLISECONDS.toSeconds(timeRemained) - 
                    		    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeRemained))
                    		);
                    	
                    	startTime.setText(timePassed);
                    	endTime.setText(leftTime);
                    	progress.setValue(newValue.intValue());
                    	if(timeRemained < 1000) {
                    		player.skip();
                    	}
                    	
                    }
				});
				
			}
			
		});
		
		player.getTrackProp().addListener(new ChangeListener<Track>() {

			@Override
			public void changed(ObservableValue<? extends Track> observable, Track oldValue, Track newValue) {
				progress.setMax(newValue.getLength() * 1000);
				trackLength = newValue.getLength() * 1000;
				
			}
			
		});
	}
	
	public Pane getRootView() {
		return rootView;
	}

}
