package notes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import javafx.scene.control.Menu;

public class ModesTest {

	@Test
	public void testLabelChange() {
		Modes modes = new Modes();
		Menu label = new Menu();
		modes.labelChange(modes.getSpace() + "Label", label);
		assertEquals(modes.getSpace() + modes.getSpace() + "Label", modes.getMode());
	}

	@Test
	public void testZoomSetup() {
		Modes modes = new Modes();
		double dy1 = 1.0;
		double dy2 = -1.0;
		double zoomFactor = 0.0;
		zoomFactor = modes.setupZoomFactor(dy1);
		assertEquals(zoomFactor, 0.95);
		zoomFactor = modes.setupZoomFactor(dy2);
		assertEquals(zoomFactor, 1.05);
	}

	@Test
	public void testcalculateTranslateX() {
		Modes modes = new Modes();
		double layoutX1 = 408.1;
		double layoutX2 = 407.9;
		double centerX = 408.0;
		double dy1 = 1.0;
		double dy2 = -1.0;
		double translateX = modes.calculateTranslateX(layoutX1, centerX, dy1);
		assertEquals(translateX, -5.0);
		translateX = modes.calculateTranslateX(layoutX1, centerX, dy2);
		assertEquals(translateX, 5.0);
		translateX = modes.calculateTranslateX(layoutX2, centerX, dy1);
		assertEquals(translateX, 5.0);
		translateX = modes.calculateTranslateX(layoutX2, centerX, dy2);
		assertEquals(translateX, -5.0);
	}
	
	@Test
	public void testcalculateTranslateY() {
		Modes modes = new Modes();
		double layoutY1 = 419.6;
		double layoutY2 = 419.4;
		double centerY = 419.5;
		double dy1 = 1.0;
		double dy2 = -1.0;
		double translateX = modes.calculateTranslateX(layoutY1, centerY, dy1);
		assertEquals(translateX, -5.0);
		translateX = modes.calculateTranslateX(layoutY1, centerY, dy2);
		assertEquals(translateX, 5.0);
		translateX = modes.calculateTranslateX(layoutY2, centerY, dy1);
		assertEquals(translateX, 5.0);
		translateX = modes.calculateTranslateX(layoutY2, centerY, dy2);
		assertEquals(translateX, -5.0);
	}
}
