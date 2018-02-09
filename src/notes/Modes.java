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

	public void labelChange(String labelName, Menu label) {
		mode = space + labelName;
		label.setText(mode);
	}

	public void createTextbox(Pane pane,double x, double y) {
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
				dragAndDrop(pane, node);
			}
		}
	}


	public void dragAndDrop(Pane pane, Node node) {
		node.setOnMouseClicked(event-> {
			pane.setOnMouseMoved(event2-> {
				pane.setOnMouseClicked(event3-> {
					node.setLayoutX(event3.getSceneX());
					node.setLayoutY(event3.getSceneY());	
					pane.requestFocus();
				});});});
	}

	public void moveCanvasSetup(Pane pane, boolean isVertical) {
		modeReset(pane);
		pane.setOnScroll(event-> {
			if(isVertical) {
				scrollVertical(pane, event);
			}
			else {
				scrollHorizontal(pane, event);
			}
		});
	}

	public void scrollVertical(Pane pane, ScrollEvent event) {
		for (Node node : pane.getChildren()) {
			if (node instanceof TextArea) {
				((TextArea)node).setTranslateY(node.getTranslateY() + event.getDeltaY());
			}
		}
	}

	public void scrollHorizontal(Pane pane, ScrollEvent event) {
		for (Node node : pane.getChildren()) {
			if (node instanceof TextArea) {
				((TextArea)node).setTranslateX(node.getTranslateX() + event.getDeltaY());
			}
		}
	}

	public void zoom(Pane pane) {
		modeReset(pane);
		pane.setOnScroll(event-> {

			double zoomFactor = 1.05;
			double delta_y = event.getDeltaY();
			double translateFactorX = 0;
			double translateFactorY = 0;
			double centerX = pane.getScene().getWindow().getWidth()/2;
			double centerY = pane.getScene().getWindow().getHeight()/2;

			if(delta_y>0) {
				zoomFactor = 2.0-zoomFactor;
			}


			for (Node node : pane.getChildren()) {
				if (node instanceof TextArea) {
					if ((node.getLayoutX() < centerX)&&(delta_y<0)) {
						translateFactorX = -5.0;
					} else if ((node.getLayoutX() > centerX)&&(delta_y<0)) {
						translateFactorX = 5.0;
					} else if ((node.getLayoutX() < centerX)&&(delta_y>0)) {
						translateFactorX = 5.0;
					} else if ((node.getLayoutX() > centerX)&&(delta_y>0)) {
						translateFactorX = -5.0;
					}

					if ((node.getLayoutY() < centerY)&&(delta_y<0)) {
						translateFactorY = -5.0;
					} else if ((node.getLayoutY() > centerY)&&(delta_y<0)) {
						translateFactorY = 5.0;
					} else if ((node.getLayoutY() < centerY)&&(delta_y>0)) {
						translateFactorY = 5.0;
					} else if ((node.getLayoutY() > centerY)&&(delta_y>0)) {
						translateFactorY = -5.0;
					}

					if(!((((TextArea)node).getWidth()<41 && delta_y>0)|| (((TextArea)node).getWidth()>300) && delta_y<0)){
						((TextArea)node).setTranslateX(node.getTranslateX()+translateFactorX);
						((TextArea)node).setTranslateY(node.getTranslateY()+translateFactorY);
						((TextArea) node).setPrefWidth(((TextArea)node).getWidth()*zoomFactor);
						((TextArea) node).setPrefHeight(((TextArea)node).getHeight()*zoomFactor);
					}

				}
			}
		});

	}

	public void modeReset(Pane pane) {
		for (Node node : pane.getChildren()) {
			if (node instanceof TextArea) {
				((TextArea)node).setOnMouseClicked(null);
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

