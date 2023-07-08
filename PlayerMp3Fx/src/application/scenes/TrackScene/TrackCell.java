package application.scenes.TrackScene;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import business.MP3Player;
import business.Track;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TrackCell extends ListCell<Track> {
	HBox mainRoot;
	
	private VBox root;
	private Label titleLabel;
	private Label artistLabel;
	
	private ImageView coverView;
	private Image image;
	private byte[] file;
	private MP3Player player;
	private BufferedImage cover;
	private File outputfile;
	
	public TrackCell(MP3Player player) {
		this.player = player;
		mainRoot = new HBox();
		root = new VBox();
		coverView = new ImageView();
		coverView.setFitHeight(50);
		coverView.setFitWidth(50);
		
		titleLabel = new Label();
		titleLabel.getStyleClass().addAll("info-label");
		artistLabel = new Label();
		artistLabel.getStyleClass().addAll("info-label");
		root.getChildren().addAll(titleLabel, artistLabel);
		mainRoot.getChildren().addAll(coverView, root);
		this.setGraphic(mainRoot);
	}
	
	@Override
	public void updateItem(Track item, boolean empty) {
		super.updateItem(item, empty);
		
		
		if(!empty) {
			titleLabel.setText(item.getTitle());
			artistLabel.setText(item.getInterpret());
			setCover(item);
		}
		
		
	}
	
	public void setCover(Track item) {
		file = item.getCoverFile();
		safeImage(file);
		image = new Image(new File("saved.png").toURI().toString());
		coverView.setImage(image);
		
	}
	
	public void safeImage(byte[] file) {

        try {
        	cover = ImageIO.read(new ByteArrayInputStream(file));
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
        outputfile = new File ("saved.png");
       try {
           ImageIO.write(cover, "png", outputfile);
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }


   }
}
