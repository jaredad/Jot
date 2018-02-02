package notes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class NotesController {
	
	private String mode;

    @FXML
    private Button scaleUp;

    @FXML
    private MenuItem eraser;

    @FXML
    private MenuItem moveContent;

    @FXML
    private MenuItem textbox;

    @FXML
    private Button scaleDown;

    @FXML
    private Menu menuFile;

    @FXML
    private MenuItem SaveCanvas;

    @FXML
    private MenuBar bar;

    @FXML
    private MenuItem loadCanvas;

    @FXML
    private MenuItem pen;

    @FXML
    private MenuItem moveCanvas;

    @FXML
    private MenuItem newCanvas;

    @FXML
    private AnchorPane pane;

    @FXML
    private MenuItem saveCanvasAs;
    
    @FXML
    private Label label;
    
    
    public void modeChange () {
    	TextArea textArea = new TextArea();
    	textbox.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        mode = "Textbox Mode";
    	        label.setText(mode);
    	        System.out.println(mode);
    	        textArea.setPrefWidth(400);
    	        textArea.setPrefHeight(700);
    	        textArea.setWrapText(true);
    	        AnchorPane.setTopAnchor(textArea, 50.0);
    	        pane.getChildren().addAll(textArea);
    	    }
    	});
    	
    	pen.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        mode = "Drawing Mode";
    	        label.setText(mode);
    	        System.out.println(mode);
    	    }
    	});
    	
    	eraser.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        mode = "Eraser Mode";
    	        label.setText(mode);
    	        System.out.println("Eraser Mode");
    	    }
    	});
    	
    	moveContent.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        mode = "Move Content";
    	        label.setText(mode);
    	        System.out.println("Move Textbox/Drawing Mode");
    	    }
    	});
    	
    	moveCanvas.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        mode = "Move Canvas";
    	        label.setText(mode);
    	        System.out.println("Move Canvas Mode");
    	    }
    	});
    	
    	scaleUp.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        System.out.println("scaled up");
    	    }
    	});
    	
    	scaleDown.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        System.out.println("scaled Down");
    	    }
    	});
    	
    	SaveCanvas.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        System.out.println("Canvas Saved");
    	    }
    	});
    	
    	loadCanvas.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        System.out.println("Load Canvas");
    	    }
    	});
    	
    	saveCanvasAs.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        System.out.println("Save Canvas As...");
    	        if (pane.getChildren().contains(textArea)){
    	        	String saved = textArea.getText();
    	        	
    	        }
    	    }
    	});
    	
    	newCanvas.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        System.out.println("New Canvas");
    	    }
    	});
    	
    	
    }

}
