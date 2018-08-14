package library.javafx;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * This class extends {@link Region}. It is a {@link Region} with a HBox.setHGrow with always {@link Priority}
 */
public class HorizontalSpacer extends Region {
    /**
     * Constructor of the class {@link HorizontalSpacer}
     */
    public HorizontalSpacer(){
        super();
        HBox.setHgrow(this, Priority.ALWAYS);
        setPickOnBounds(false);
    }
}
