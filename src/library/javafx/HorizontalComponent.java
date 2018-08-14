package library.javafx;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * This is a {@link HBox} with a constructor capable of taking an array of {@link Node} and add it directly to the {@link HBox}
 */
public class HorizontalComponent extends HBox {
    /**
     * Constructor of the {@link HorizontalComponent}
     * @param nodes an array of {@link Node} to add directly in the {@link HBox}
     */
    public HorizontalComponent(Node ... nodes){
        super();
        getChildren().addAll(nodes);
    }
}