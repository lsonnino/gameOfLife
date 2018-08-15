package gol.gui;

import com.thizzer.jtouchbar.JTouchBar;
import com.thizzer.jtouchbar.common.Color;
import com.thizzer.jtouchbar.common.ImagePosition;
import com.thizzer.jtouchbar.item.TouchBarItem;
import com.thizzer.jtouchbar.item.view.TouchBarButton;
import gol.Main;

public class TouchBar extends JTouchBar {
    private TouchBarButton startButton, darkmodeButton;

    public TouchBar(){
        super();

        this.setCustomizationIdentifier("MyJavaFXJavaTouchBar");

        startButton = new TouchBarButton();
        startButton.setTitle("Start");
        startButton.setAction(event -> {
            if(Main.getGui().isStarted()){
                Main.getGui().stop();
            }
            else {
                Main.getGui().start();
            }

            refreshStatus();
        });
        startButton.setBezelColor(Color.GRAY);

        darkmodeButton = new TouchBarButton();
        darkmodeButton.setTitle("Dark");
        darkmodeButton.setAction(event -> {
            if(Main.getParam().isDarkmode()){
                Main.getGui().setDarkmode(false);
            }
            else {
                Main.getGui().setDarkmode(true);
            }

            refreshStatus();
        });
        darkmodeButton.setBezelColor(Color.GRAY);

        refreshStatus();

        this.addItem(new TouchBarItem(TouchBarItem.NSTouchBarItemIdentifierFlexibleSpace));
        this.addItem(new TouchBarItem("start", startButton));
        this.addItem(new TouchBarItem("dark", darkmodeButton));
        this.addItem(new TouchBarItem(TouchBarItem.NSTouchBarItemIdentifierFlexibleSpace));
    }

    public void refreshStatus(){
        // startButton
        if(Main.getGui().isStarted()){
            startButton.setTitle("Stop");
            startButton.setBezelColor(Color.SYSTEM_BLUE);
        }
        else {
            startButton.setTitle("Start");
            startButton.setBezelColor(Color.GRAY);
        }

        // darkmodeButton
        if(Main.getParam().isDarkmode()){
            darkmodeButton.setTitle("Light");
            darkmodeButton.setBezelColor(Color.SYSTEM_BLUE);
        }
        else {
            darkmodeButton.setTitle("Dark");
            darkmodeButton.setBezelColor(Color.GRAY);
        }
    }
}
