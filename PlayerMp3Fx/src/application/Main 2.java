package application;

import Enums.Scenes;

import java.util.HashMap;
import java.util.Map;

import application.scenes.ViewController;
import application.scenes.MainScene.MainSceneController;
import application.scenes.TrackScene.TrackSceneController;
import business.MP3Player;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
 

public class Main extends Application {
	
	private Stage primaryStage;
	private Map<Scenes, Pane> scenes;
	
	private MP3Player player;
	
	@Override
	public void init() {
		this.player = new MP3Player();
		
		scenes = new HashMap<>();
		ViewController controller;
		
		controller = new MainSceneController(player, this);
		//controllerTracks = new TrackSceneController(player, this);
		scenes.put(Scenes.FIRST_SCENE, controller.getRootView());
		controller = new TrackSceneController(player, this);
		scenes.put(Scenes.SECOND_SCENE, controller.getRootView());
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,500,650);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			switchScene(Scenes.FIRST_SCENE);
			
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() {
		System.exit(0);
	}
	
	public void switchScene(Scenes toScene) {
		Scene scene = primaryStage.getScene();
		
		if (scenes.containsKey(toScene)) {
			scene.setRoot(scenes.get(toScene));
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
