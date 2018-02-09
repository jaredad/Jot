package notes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Files {

	private int textareaCount;
	private File currentFile;
	private String line;
	private TextArea textArea;

	public void newFile(Pane pane) {
		pane.getChildren().clear();
	}

	public void newSaveSetup(Pane pane) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Canvas As...");
		fileChooser.setInitialFileName("New Canvas");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		File file = fileChooser.showSaveDialog(pane.getScene().getWindow());
		try {
			file.createNewFile();
			writeToFile(pane,file);
		} catch (IOException e) {
			saveSetup(pane);
		}
	}

	public void writeToFile(Pane pane, File file) throws IOException {
		FileWriter writer = new FileWriter(file.getAbsolutePath());
		PrintWriter print = new PrintWriter(writer);
		int count = countTextAreas(pane);
		print.println("Save/Load Information for Notes.java");
		print.println(Integer.toString(count));
		printLists(print, pane);
		print.close();
		currentFile = file;
	}

	public int countTextAreas(Pane pane) {
		textareaCount = 0;
		for (Node node : pane.getChildren()) {
			if (node instanceof TextArea) {
				textareaCount++;
			}
		}
		return textareaCount;
	}

	public void printLists(PrintWriter print, Pane pane) {
		for (Node node : pane.getChildren()) {
			if (node instanceof TextArea) {
				List<Object> textareaInfo = new ArrayList<Object>();
				textareaInfo.add(((TextArea)node).getText());
				textareaInfo.add(((TextArea)node).getLayoutX());
				textareaInfo.add(((TextArea)node).getLayoutY());
				textareaInfo.add(((TextArea)node).getPrefWidth());
				textareaInfo.add(((TextArea)node).getPrefHeight());
				print.println(textareaInfo);
			}
		}
	}

	public void saveSetup(Pane pane) {
		try {
			writeToFile(pane,currentFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadSetup(Pane pane, Menu label) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load Canvas");
		fileChooser.setInitialFileName("");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		File file = fileChooser.showOpenDialog(pane.getScene().getWindow());
		readFileSetup(file, label, pane);
	}

	public void readFileSetup(File file, Menu label, Pane pane) {
		try {
			FileReader fileReader = new FileReader(file.getAbsolutePath());
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			try {
				line = bufferedReader.readLine();
				if (line.equals("Save/Load Information for Notes.java")) {
					loadFile(file,bufferedReader,pane);
				} else {
					createAlert(file.getName()+" is not a Notes File.");
				}
			} catch (IOException e) {
				createAlert(file.getName()+" is not a Notes File");
			}
		} catch (FileNotFoundException e) {
			createAlert("File Does Not Exist");
		}
	}

	public void loadFile(File file, BufferedReader br, Pane pane) throws NumberFormatException, IOException {
		textareaCount = Integer.parseInt(br.readLine());
		newFile(pane);
		for(int i = 0; i < textareaCount; i++) {
			List<Object> textAreaList = new ArrayList<Object> (Arrays.asList(br.readLine().split(",")));
			loadTextAreas(pane, textAreaList);
		}
		currentFile = file;
	}

	public void loadTextAreas(Pane pane, List<Object> textareaInfo) {
		textArea = new TextArea();
		textArea.setText(((String) textareaInfo.get(0)).substring(1));
		textArea.setLayoutX((double) Double.parseDouble((String) textareaInfo.get(1)));
		textArea.setLayoutY((double) Double.parseDouble((String) textareaInfo.get(2)));
		textArea.setPrefWidth(200.0);
		textArea.setPrefWidth(200.0);
		textArea.setWrapText(true);
		pane.getChildren().add(textArea);
	}

	public void createAlert(String message) {
		Alert alert = new Alert(AlertType.ERROR, message, ButtonType.OK);
		alert.setWidth(200);
		alert.setHeight(200);
		alert.show();
	}
}
