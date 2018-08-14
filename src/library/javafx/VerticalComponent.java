package library.javafx;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * This is a {@link VBox} with a constructor capable of taking an array of {@link Node} and add it directly to the {@link VBox}
 */
public class VerticalComponent extends VBox {
    /**
     * Constructor of the {@link VerticalComponent}
     * @param nodes an array of {@link Node} to add directly in the {@link VBox}
     */
    public VerticalComponent(Node ... nodes){
        super();
        getChildren().addAll(nodes);
    }
}
