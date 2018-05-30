package notes;

import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

public class Modes {

	private String space = "                                                                                                        ";
	private String mode;
	private int num_of_boxes = 0;
	private double nodeX = 0;
	private double nodeY = 0;

	public void labelChange(String labelName, Menu label) {
		mode = space + labelName;
		label.setText(mode);
	}

	public void createTextbox(Pane pane, double x, double y) {
		TextArea textArea = new TextArea();
		textArea.setPrefWidth(200);
		textArea.setPrefHeight(200);
		textArea.setLayoutX(x);
		textArea.setLayoutY(y);
		textArea.setWrapText(true);
		num_of_boxes++;
		pane.getChildren().add(textArea);
	}

	public void moveTextbox(Pane pane) {
		modeReset(pane);
		for (Node node : pane.getChildren()) {
			if (node instanceof TextArea) {
				dragAndDrop(pane, (TextArea) node);
			}
		}
	}

	public void dragAndDrop(Pane pane, TextArea node) {
		Node textAreaContent = node.lookup(".content");

		textAreaContent.setOnMousePressed(event -> {
			nodeX = textAreaContent.getLayoutX() - 50;
			nodeY = textAreaContent.getLayoutY() - 50;
		});

		textAreaContent.setOnMouseDragged(event -> {
			double dragX = event.getSceneX();
			double dragY = event.getSceneY();
			node.setLayoutX(nodeX + dragX);
			node.setLayoutY(nodeY + dragY);
		});
	}

	public void moveCanvasSetup(Pane pane, boolean isVertical) {
		modeReset(pane);
		pane.setOnScroll(event -> {
			if (isVertical) {
				scrollVertical(pane, event);
			} else {
				scrollHorizontal(pane, event);
			}
		});
	}

	public void scrollVertical(Pane pane, ScrollEvent event) {
		for (Node node : pane.getChildren()) {
			if (node instanceof TextArea) {
				((TextArea) node).setTranslateY(node.getTranslateY() + event.getDeltaY());
			}
		}
	}

	public void scrollHorizontal(Pane pane, ScrollEvent event) {
		for (Node node : pane.getChildren()) {
			if (node instanceof TextArea) {
				((TextArea) node).setTranslateX(node.getTranslateX() + event.getDeltaY());
			}
		}
	}

	public void zoom(Pane pane) {
		modeReset(pane);
		pane.setOnScroll(event -> {

			double delta_y = event.getDeltaY();
			double translateFactorX = 0;
			double translateFactorY = 0;
			double centerX = pane.getScene().getWindow().getWidth() / 2;
			double centerY = pane.getScene().getWindow().getHeight() / 2;
			double zoomFactor = setupZoomFactor(delta_y);

			for (Node node : pane.getChildren()) {
				if (node instanceof TextArea) {
					double x = node.getLayoutX();
					double y = node.getLayoutY();
					translateFactorX = calculateTranslateX(x,centerX,delta_y);
					translateFactorY = calculateTranslateY(y,centerY, delta_y);					
					if (!((((TextArea) node).getWidth() < 41 && delta_y > 0)
							|| (((TextArea) node).getWidth() > 300) && delta_y < 0)) {
						((TextArea) node).setTranslateX(node.getTranslateX() + translateFactorX);
						((TextArea) node).setTranslateY(node.getTranslateY() + translateFactorY);
						((TextArea) node).setPrefWidth(((TextArea) node).getWidth() * zoomFactor);
						((TextArea) node).setPrefHeight(((TextArea) node).getHeight() * zoomFactor);
					}

				}
			}
		});
	}
	
	public double setupZoomFactor(double dy) {
		double zoomFactor = 1.05;
		if (dy > 0) {
			zoomFactor = 2.0 - zoomFactor;
		}
		return zoomFactor;
	}
	
	public double calculateTranslateX(double x, double centerX, double delta_y) {
		double translateFactorX = 0.0;
		if ((x < centerX) && (delta_y < 0)) {
			translateFactorX = -5.0;
		} else if ((x > centerX) && (delta_y < 0)) {
			translateFactorX = 5.0;
		} else if ((x < centerX) && (delta_y > 0)) {
			translateFactorX = 5.0;
		} else if ((x > centerX) && (delta_y > 0)) {
			translateFactorX = -5.0;
		}
		return translateFactorX;
	}
	
	public double calculateTranslateY(double y, double centerY, double delta_y) {
		double translateFactorY = 0.0;
		if ((y < centerY) && (delta_y < 0)) {
			translateFactorY = -5.0;
		} else if ((y > centerY) && (delta_y < 0)) {
			translateFactorY = 5.0;
		} else if ((y< centerY) && (delta_y > 0)) {
			translateFactorY = 5.0;
		} else if ((y > centerY) && (delta_y > 0)) {
			translateFactorY = -5.0;
		}
		
		return translateFactorY;
	}
	

	public void modeReset(Pane pane) {
		for (Node node : pane.getChildren()) {
			if (node instanceof TextArea) {
				Node textAreaContent = node.lookup(".content");
				textAreaContent.setOnMouseDragged(null);
				((TextArea) node).setOnMouseClicked(null);
				((TextArea) node).setOnMouseDragged(null);
				((TextArea) node).setPrefWidth(200);
				((TextArea) node).setPrefHeight(200);
				pane.setOnMouseMoved(null);
				pane.setOnMouseClicked(null);
				pane.setOnScroll(null);
				pane.requestFocus();
			}
		}
	}

	public String getSpace() {
		return space;
	}

	public String getMode() {
		return mode;
	}

	public int numberOfBoxes() {
		return num_of_boxes;
	}
}
