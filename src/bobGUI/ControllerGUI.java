package bobGUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Raphael on 06/03/2016 for project-sysdev.
 */

public class ControllerGUI {

    public MenuBar menuBar;
    public MenuItem close;
    public MenuItem mConnect;
    public MenuItem mSave;
    public MenuItem mLoad;
    public MenuItem about;
    public ImageView kinectView;
    public Button map;
    public Button up;
    public Button down;
    public Button left;
    public Button right;
    public Button dRight;
    public Button dLeft;
    public TextArea textFeedback;
    public Button connect;
    public Button save;
    public Button load;
    //private SmartCar sm;
    public boolean isConnected = false;
    public boolean isDriving = false;
    public boolean isMapping = false;
    public FileChooser fileChooser;

    /**
     * Method to handle events like mapping, load and save.
     * @param event
     */
    public void handle(ActionEvent event) {
       if(event.getSource().equals(map)) {
           if (!isMapping) {
               textFeedback.clear();
               textFeedback.setText("Hi I'm mapping!");
               isMapping = true;
           }
           else if (isMapping) {
               textFeedback.clear();
               textFeedback.setText("stop mapping!");
               isMapping = false;
           }
       }
        else if (event.getSource().equals(load) || event.getSource().equals(mLoad)) {
           fileChooser = new FileChooser();
           fileChooser.setTitle("Open a map");
           fileChooser.showOpenDialog(menuBar.getScene().getWindow());
       }
       else if (event.getSource().equals(save) || event.getSource().equals(mSave)) {
           fileChooser = new FileChooser();

           //Set extension filter
           FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
           fileChooser.getExtensionFilters().add(extFilter);

           //Show save file dialog
           File file = fileChooser.showSaveDialog(menuBar.getScene().getWindow());

           if(file != null){
               SaveFile("Map", file);
           }
       }
    }

    /**
     * Method to shutdown the GUI
     */
    public void closeApplication() {
        menuBar.getScene().getWindow().hide();
    }

    /**
     * Method to apply shadow effect to buttons
     * @param event
     */
    public void shadow(Event event) {
        if(event.getSource().equals(connect))connect.setEffect(new DropShadow());
        if(event.getSource().equals(save))save.setEffect(new DropShadow());
        if(event.getSource().equals(load))load.setEffect(new DropShadow());

    }

    /**
     * Method to take off shadow effect of the buttons
     * @param event
     */
    public void shadowOff(Event event) {
        if(event.getSource().equals(connect))connect.setEffect(null);
        if(event.getSource().equals(save))save.setEffect(null);
        if(event.getSource().equals(load))load.setEffect(null);

    }

    /**
     * Method to handle the keylisteners when a key is pressed.
     * @param event
     */
    public void keyListenersPressed(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                //sm.setSpeed(50);
                isDriving = true;
                textFeedback.clear();
                textFeedback.setText("up pressed");
                event.consume();
                break;
            case S:
                //sm.setSpeed(-50);
                isDriving = true;
                textFeedback.clear();
                textFeedback.setText("down pressed");
                event.consume();
                break;
            case A:
                if (isDriving)//sm.setAngle(-90);
                ;
                else if (!isDriving)// sm.rotate(-90);
                ;
                textFeedback.clear();
                textFeedback.setText("left pressed");
                event.consume();
                break;
            case D:
                if (isDriving)//sm.setAngle(90);
                    ;
                else if (!isDriving)// sm.rotate(90);
                    ;
                textFeedback.clear();
                textFeedback.setText("right pressed");
                event.consume();
                break;
            case Q:
                if (isDriving)//sm.setAngle(-45);
                    ;
                else if (!isDriving)// sm.rotate(-45);
                    ;
                textFeedback.clear();
                textFeedback.setText("left diagonal pressed");
                event.consume();
                break;
            case E:
                if (isDriving)//sm.setAngle(45);
                    ;
                else if (!isDriving)// sm.rotate(45);
                    ;
                textFeedback.clear();
                textFeedback.setText("right diagonal pressed");
                event.consume();
                break;
            case Z:
                map.fire();
        }

    }

    /**
     * Method to handle keylisteners when a key is released.
     * @param event
     */
    public void keyListenersReleased(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                //sm.setSpeed(0);
                isDriving = false;
                textFeedback.clear();
                textFeedback.setText("up released");
                event.consume();
                break;
            case S:
                //sm.setSpeed(0);
                isDriving = false;
                textFeedback.clear();
                textFeedback.setText("down released");
                event.consume();
                break;
            case A:
                if (isDriving)//sm.setAngle(0);
                    ;
                else if (!isDriving)// sm.rotate(0);
                    ;
                textFeedback.clear();
                textFeedback.setText("left released");
                event.consume();
                break;
            case D:
                if (isDriving)//sm.setAngle(0);
                    ;
                else if (!isDriving)// sm.rotate(0);
                    ;
                textFeedback.clear();
                textFeedback.setText("right released");
                event.consume();
                break;
            case Q:
                if (isDriving)//sm.setAngle(0);
                    ;
                else if (!isDriving)// sm.rotate(0);
                    ;
                textFeedback.clear();
                textFeedback.setText("left diagonal released");
                event.consume();
                break;
            case E:
                if (isDriving)//sm.setAngle(0);
                    ;
                else if (!isDriving)// sm.rotate(0);
                    ;
                textFeedback.clear();
                textFeedback.setText("right diagonal released");
                event.consume();
                break;
        }
    }

    /**
     * Method to set InnerShadow effect to buttons.
     * @param event
     */
    public void setFocused(Event event) {
        if(event.getSource().equals(connect))connect.setEffect(new InnerShadow());
        if(event.getSource().equals(save))save.setEffect(new InnerShadow());
        if(event.getSource().equals(load))load.setEffect(new InnerShadow());
        if(event.getSource().equals(up))up.setEffect(new InnerShadow());
        if(event.getSource().equals(down))down.setEffect(new InnerShadow());
        if(event.getSource().equals(left))left.setEffect(new InnerShadow());
        if(event.getSource().equals(right))right.setEffect(new InnerShadow());

    }

    /**
     * Method to set connection with the smartcar.
     */
    public void connect() {
        if(!isConnected) {
            connect.setStyle("-fx-background-color: linear-gradient(#ff6767, #ff1a1a),        " +
                    "radial-gradient(center 50% -40%, radius 200%, #ff4d4d 45%, #ff0000 50%); " +
                    "-fx-background-insets: 0, 1; " +
                    "-fx-text-fill: #f5f5f5; " +
                    "-fx-background-radius: 25 0 0 0;");
            connect.setText("Disconnect");
            mConnect.setText("Disconnect");
            //sm = new SmartCar(ip, port);
            isConnected = true;

        }
        else{
            connect.setStyle("-fx-background-color: linear-gradient(#f0ff35, #a9ff00),        " +
                    "radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%); " +
                    "-fx-background-insets: 0, 1; " +
                    "-fx-text-fill: #395306; " +
                    "-fx-background-radius: 25 0 0 0;");
            connect.setText("Connect");
            mConnect.setText("Connect");
            //sm = null;
            isConnected = false;
        }
    }

    /**
     * Method to handle mouseReleased events on the smartcar control
     * @param event
     */
    public void mouseReleased(MouseEvent event) {

            if (event.getSource() == up) {
                //sm.setSpeed(0);
                isDriving = false;
                textFeedback.clear();
                textFeedback.setText("up released");
                event.consume();
            }
            else if (event.getSource() == down) {
                //sm.setSpeed(0);
                isDriving = false;
                textFeedback.clear();
                textFeedback.setText("down released");
                event.consume();
            }
            else if (event.getSource() == left) {
                if (isDriving)//sm.setAngle(0);
                    ;
                else if (!isDriving)// sm.rotate(0);
                    ;
                textFeedback.clear();
                textFeedback.setText("left released");
                event.consume();
            }
            else if (event.getSource() == right) {
                if (isDriving)//sm.setAngle(0);
                    ;
                else if (!isDriving)// sm.rotate(0);
                    ;
                textFeedback.clear();
                textFeedback.setText("right released");
                event.consume();
            }
            else if (event.getSource() == dRight) {
                if (isDriving)//sm.setAngle(0);
                    ;
                else if (!isDriving)// sm.rotate(0);
                    ;
                textFeedback.clear();
                textFeedback.setText("dRight released");
                event.consume();
            }
            else if (event.getSource() == dLeft) {
                if (isDriving)//sm.setAngle(0);
                    ;
                else if (!isDriving)// sm.rotate(0);
                    ;
                textFeedback.clear();
                textFeedback.setText("dLeft released");
                event.consume();
            }
    }

    /**
     * Method to handle mousePressed events on the smartcar control.
     * @param event
     */
    public void mousePressed(MouseEvent event) {
        if (event.getSource() == up) {
            //sm.setSpeed(50);
            isDriving = false;
            textFeedback.clear();
            textFeedback.setText("up pressed");
            event.consume();
        }
        else if (event.getSource() == down) {
            //sm.setSpeed(-50);
            isDriving = false;
            textFeedback.clear();
            textFeedback.setText("down pressed");
            event.consume();
        }
        else if (event.getSource() == left) {
            if (isDriving)//sm.setAngle(-90);
                ;
            else if (!isDriving)// sm.rotate(-90);
                ;
            textFeedback.clear();
            textFeedback.setText("left pressed");
            event.consume();
        }
        else if (event.getSource() == right) {
            if (isDriving)//sm.setAngle(90);
                ;
            else if (!isDriving)// sm.rotate(90);
                ;
            textFeedback.clear();
            textFeedback.setText("right pressed");
            event.consume();
        }
        else if (event.getSource() == dLeft) {
            if (isDriving)//sm.setAngle(-45);
                ;
            else if (!isDriving)// sm.rotate(-45);
                ;
            textFeedback.clear();
            textFeedback.setText("dLeft pressed");
            event.consume();
        }
        else if (event.getSource() == dRight) {
            if (isDriving)//sm.setAngle(45);
                ;
            else if (!isDriving)// sm.rotate(45);
                ;
            textFeedback.clear();
            textFeedback.setText("dRight pressed");
            event.consume();
        }
    }

    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }

    }
}