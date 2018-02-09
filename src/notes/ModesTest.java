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
		assertEquals(modes.getSpace()+ modes.getSpace() + "Label", modes.getMode());
    }
}
    
