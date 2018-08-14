package library.javafx;

import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * This class extends {@link Region}. It is a {@link Region} with a VBox.setVGrow with always {@link Priority}
 */
public class VerticalSpacer extends Region {
    /**
     * Constructor of the class {@link VerticalSpacer}
     */
    public VerticalSpacer(){
        super();
        VBox.setVgrow(this, Priority.ALWAYS);
        setPickOnBounds(false);
    }
}
