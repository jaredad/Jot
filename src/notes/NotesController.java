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
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

@SuppressWarnings("unused")
public class NotesController {

	private Modes modes;
	private Files file;

	@FXML
	private MenuItem zoom;

	@FXML
	private MenuItem moveTextbox;

	@FXML
	private MenuItem textbox;

	@FXML
	private Menu menuFile;

	@FXML
	private MenuItem SaveCanvas;

	@FXML
	private MenuBar bar;

	@FXML
	private MenuItem loadCanvas;


	@FXML
	private MenuItem moveCanvasV;

	@FXML
	private MenuItem moveCanvasH;

	@FXML
	private MenuItem newCanvas;

	@FXML
	private Pane pane;

	@FXML
	private Pane mainPane;

	@FXML
	private MenuItem saveCanvasAs;

	@FXML
	private Menu label;

	@FXML
	private MenuItem clear;


	@FXML
	public void initialize() {
		bar.prefWidthProperty().bind(mainPane.widthProperty());
		pane.prefWidthProperty().bind(mainPane.widthProperty());
		pane.prefHeightProperty().bind(mainPane.heightProperty());
		modes = new Modes();
		file = new Files();

	}


	public void modeChange () {
		textbox.setOnAction(event -> {
			modes.labelChange("Textbox Mode",label);
			modes.modeReset(pane);
			pane.setOnMouseClicked(e -> {
				modes.createTextbox(pane, e.getX(),e.getY());
			});});


		moveTextbox.setOnAction(event -> { 
			modes.labelChange("Move Textbox Mode", label);
			modes.moveTextbox(pane);
		});

		moveCanvasV.setOnAction(event -> {
			modes.labelChange("Move Canvas Vertical Mode", label);
			modes.moveCanvasSetup(pane,true);
		});

		moveCanvasH.setOnAction(event -> {
			modes.labelChange("Move Canvas Horizontal Mode", label);
			modes.moveCanvasSetup(pane,false);
		});

		clear.setOnAction(event -> {
			modes.labelChange("No Mode Selected", label);
			modes.modeReset(pane);
		});

		zoom.setOnAction(event -> {
			modes.labelChange("Zoom In/Out Mode", label);
			modes.zoom(pane);
		});


		SaveCanvas.setOnAction(event -> {
			file.saveSetup(pane);
		});

		loadCanvas.setOnAction(event -> {
			file.loadSetup(pane,label);
		});

		saveCanvasAs.setOnAction(event -> {
			file.newSaveSetup(pane);
		});

		newCanvas.setOnAction(event -> {
			modes.labelChange("No Mode Selected", label);
			modes.modeReset(pane);
			file.newFile(pane);
		});
	}
}
