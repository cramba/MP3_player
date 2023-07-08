package application.scenes.MainScene;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Enums.Scenes;
import application.Main;
import application.scenes.ViewController;
import business.MP3Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MainSceneController extends ViewController{
	private Label titleLabel;
	private Label interpretLabel;
	private Button showTracks;
	
	private ImageView coverView;
	private Image image;
	private BufferedImage cover;
	private byte[] file;
	private File outputfile;
	
	private Main main;
	private MP3Player player;
	
	public MainSceneController(MP3Player player, Main main) {
		
		this.player = player;
		this.main = main;
		
		MainScene view  = new MainScene(player);
		titleLabel = view.getTitle();
		interpretLabel = view.getInterpret();
		showTracks = view.getShowTracks();
		
		coverView = view.getCoverView();
		
		rootView = view;
		setCover();
		initialize();
	}
	
	public void initialize() {
		
		
		titleLabel.setText(player.getTrackName());
		interpretLabel.setText(player.getInterpret());
		
		player.actTrackNameProperty().addListener(
				new ChangeListener<String>() {
				
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							String newValue) {
						titleLabel.setText(newValue);
						setCover();
						
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
		
		
		showTracks.addEventHandler(ActionEvent.ACTION, 
				event -> {
					main.switchScene(Scenes.SECOND_SCENE);
				}
			);
	}
	
	
	
	public void setCover() {
		file = player.getCoverFile();
		safeImage(file);
		image = new Image(new File("saved.png").toURI().toString());
		coverView.setImage(image);
		
	}
	
	public Pane getRootView() {
		return rootView;
	}
	
	public void safeImage(byte[] file) {

        try {
        	cover = ImageIO.read(new ByteArrayInputStream(file));
       } catch (IOException e) {
           e.printStackTrace();
       }
        outputfile = new File ("saved.png");
       try {
           ImageIO.write(cover, "png", outputfile);
       } catch (IOException e) {
           e.printStackTrace();
       }


   }

}
