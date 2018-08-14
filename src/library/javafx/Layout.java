package library.javafx;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class contains usefull methods to pack elements in a certain layout
 *
 * For instance, the method {@link #hCenter(Node)} can be used to horizontally center an element in the scene
 */
public class Layout {

    /**
     * Make a layout with spacers and nodes
     * @param nodes an array containing all the elements to align vertically
     * @return a {@link javafx.scene.layout.VBox} containing all the nodes
     */
    public static VBox vLayout(Node ... nodes){
        return new VerticalComponent(nodes);
    }

    /**
     * Make a layout with spacers and nodes
     * @param nodes an array containing all the elements to align horizontally
     * @return a {@link javafx.scene.layout.HBox} containing all the nodes
     */
    public static HBox hLayout(Node ... nodes){
        return new HorizontalComponent(nodes);
    }

    /**
     * Center vertically a node
     * @param node the node to center vertically
     * @return a {@link javafx.scene.layout.VBox} containing a {@link VerticalSpacer}, the node, then another {@link VerticalSpacer}
     */
    public static VBox vCenter(Node node){
        return vLayout(new VerticalSpacer(), node, new VerticalSpacer());
    }

    /**
     * Center horizontally a node
     * @param node the node to center horizontally
     * @return a {@link javafx.scene.layout.HBox} containing a {@link HorizontalSpacer}, the node, then another {@link HorizontalSpacer}
     */
    public static HBox hCenter(Node node){
        return hLayout(new HorizontalSpacer(), node, new HorizontalSpacer());
    }
}
