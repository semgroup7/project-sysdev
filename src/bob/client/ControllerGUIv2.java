
package bob.client;
/**
 * Created by Emanuel on 4/1/2016.
 */
import SmartCarInterface.Smartcar;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.stage.FileChooser;
import bob.client.MultipleKeyHandler.MultiKeyEventHandler;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ControllerGUIv2 {

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
    private Smartcar sm;
    public boolean isConnected = false;
    public boolean isDriving = false;
    public boolean isMapping = false;
    public FileChooser fileChooser;
    public File file;
    public Slider speedControl;
    boolean isW, isA, isD, isS = false;




    /**
     * Method to handle events like mapping, load and save.
     * @param event
     */
    public void handle(ActionEvent event) {
        if(event.getSource().equals(map) && isConnected) {
            if (!isMapping) {
                map.setStyle("-fx-background-color: linear-gradient(#ffd65b, #e68400),        " +
                        "linear-gradient(#ffef84, #f2ba44),        " +
                        "linear-gradient(#ffea6a, #efaa22),        " +
                        "linear-gradient(#ffe657 0%, #8cff1a 50%, #72e600 100%),        " +
                        "linear-gradient(from 0% 0% to 15% 50%, #a5ff4d, #59b300); " +
                        "-fx-background-radius: 30; " +
                        "-fx-background-insets: 0,1,2,3,0; " +
                        "-fx-text-fill: #654b00; " +
                        "-fx-font-weight: bold;");
                textFeedback.clear();
                textFeedback.setText("Hi I'm mapping!");
                isMapping = true;
            }
            else if (isMapping) {
                map.setStyle("-fx-background-color: linear-gradient(#ffd65b, #e68400),        " +
                        "linear-gradient(#ffef84, #f2ba44),        " +
                        "linear-gradient(#ffea6a, #efaa22),        " +
                        "linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),        " +
                        "linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0)); " +
                        "-fx-background-radius: 30; " +
                        "-fx-background-insets: 0,1,2,3,0; " +
                        "-fx-text-fill: #654b00; " +
                        "-fx-font-weight: bold;");
                textFeedback.clear();
                textFeedback.setText("Stop mapping!");
                isMapping = false;
            }
        }
        else if (event.getSource().equals(load) || event.getSource().equals(mLoad)) {
            fileChooser = new FileChooser();
            fileChooser.setTitle("Open a map");
            file = fileChooser.showOpenDialog(menuBar.getScene().getWindow());
            if (!file.equals(null)) {
                Image img = new Image(file.toURI().toString());
                kinectView.setImage(img);
            }
        }
        else if (event.getSource().equals(save) || event.getSource().equals(mSave)) {
            fileChooser = new FileChooser();

            //Set extension filter
//           FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
//           fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            file = fileChooser.showSaveDialog(menuBar.getScene().getWindow());

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
     *
     */

    public void keyListenersPressed(KeyEvent event) throws IOException {
        if (!isConnected) {
            textFeedback.clear();
            textFeedback.setText("SmartCar is disconnected!");
        }
        else
        switch (event.getCode()) {
            case W:
                up.setStyle("-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%), " +
                        "linear-gradient(#020b02, #3a3a3a), " +
                        "linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%), " +
                        "linear-gradient(#f5f5f5 0%, #a9c4f5 50%, #6495ed 51%, #3676e8 100%); " +
                        "-fx-background-insets: 0,1,4,5; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-family: Helvetica; " +
                        "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1); " +
                        "-fx-background-radius: 25 25 0 0;");
                isW = true;
                if (isA){
                    sm.setSpeed((int) (speedControl.getValue()));
                    sm.setAngle(-10);
                    textFeedback.setText("forward and left pressed");
                }else if(isD){
                    sm.setSpeed((int) (speedControl.getValue()));
                    sm.setAngle(10);
                    textFeedback.setText("forward and right pressed");
                }else{
                    sm.setSpeed((int) (speedControl.getValue()));
                    textFeedback.clear();
                    textFeedback.setText("forward pressed");
                }
                event.consume();
                break;
            case S:
                down.setStyle("-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),        " +
                        "linear-gradient(#020b02, #3a3a3a),        " +
                        "linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),        " +
                        "linear-gradient(#f5f5f5 0%, #a9c4f5 50%, #6495ed 51%, #3676e8 100%); " +
                        "-fx-background-insets: 0,1,4,5; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-family: Helvetica; " +
                        "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1); " +
                        "-fx-background-radius: 0 0 25 25;");
                isS = true;
                if (isA){
                    sm.setSpeed((int) -(speedControl.getValue()));
                    sm.setAngle(-10);
                    textFeedback.setText("down and left pressed");
                }else if(isD){
                    sm.setSpeed((int) -(speedControl.getValue()));
                    sm.setAngle(10);
                    textFeedback.setText("down and right pressed");
                }else{
                    sm.setSpeed((int) -(speedControl.getValue()));
                    textFeedback.setText("down pressed");
                }
                event.consume();
                break;
            case A:
                left.setStyle("-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),        " +
                        "linear-gradient(#020b02, #3a3a3a),        " +
                        "linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),        " +
                        "linear-gradient(#f5f5f5 0%, #a9c4f5 50%, #6495ed 51%, #3676e8 100%); " +
                        "-fx-background-insets: 0,1,4,5; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-family: Helvetica; " +
                        "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1); " +
                        "-fx-background-radius: 25 0 0 25;");
                isA = true;
                if (isW){
                    sm.setSpeed((int) (speedControl.getValue()));
                    sm.setAngle(-10);
                    textFeedback.setText("forward and left pressed");
                }else if(isS){
                    sm.setSpeed((int) -(speedControl.getValue()));
                    sm.setAngle(-10);
                    textFeedback.setText("back and left pressed");
                }else{
                    sm.rotate(-10);
                    textFeedback.setText("rotate left pressed");
                }
                event.consume();
                break;
            case D:
                right.setStyle("-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),        " +
                        "linear-gradient(#020b02, #3a3a3a),        " +
                        "linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),        " +
                        "linear-gradient(#f5f5f5 0%, #a9c4f5 50%, #6495ed 51%, #3676e8 100%); "+
                        "-fx-background-insets: 0,1,4,5; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-family: Helvetica; " +
                        "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1); " +
                        "-fx-background-radius: 0 25 25 0;");
                isD = true;
                if (isW){
                sm.setSpeed((int) (speedControl.getValue()));
                sm.setAngle(10);
                textFeedback.setText("forward and right pressed");
            }else if(isS){
                sm.setSpeed((int) -(speedControl.getValue()));
                sm.setAngle(10);
                textFeedback.setText("back and right pressed");
            }else{
                sm.rotate(10);
                textFeedback.setText("rotate right pressed");
            }
                event.consume();
                break;
            case R:
                speedControl.increment();
                textFeedback.setText("driving at " + speedControl.getValue());
                break;
            case F:
                speedControl.decrement();
                textFeedback.setText("driving at " + speedControl.getValue());
                break;
            case Z:
                map.fire();
                break;
            case X:
                save.fire();
                break;
            case C:
                load.fire();
                break;
        }
    }

    public void keyListenersReleased(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.V) connect();
        if (!isConnected) {
            textFeedback.clear();
            textFeedback.setText("SmartCar is disconnected!");
        }
        else
            switch (event.getCode()) {
                case W:
                    up.setStyle("-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),        " +
                            "linear-gradient(#020b02, #3a3a3a),        linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),        " +
                            "linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%); " +
                            "-fx-background-insets: 0,1,4,5; " +
                            "-fx-font-weight: bold; " +
                            "-fx-font-family: Helvetica; " +
                            "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1); " +
                            "-fx-background-radius: 25 25 0 0;");
                    isW = false;
                    sm.setSpeed(0);
                    textFeedback.clear();
                    textFeedback.setText("forward released");
                    event.consume();
                    break;
                case S:
                    down.setStyle("-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),        " +
                            "linear-gradient(#020b02, #3a3a3a),        " +
                            "linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),        " +
                            "linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%); " +
                            "-fx-background-insets: 0,1,4,5; " +
                            "-fx-font-weight: bold; " +
                            "-fx-font-family: Helvetica; " +
                            "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1); " +
                            "-fx-background-radius: 0 0 25 25;");
                    isS = false;
                    sm.setSpeed(0);
                    textFeedback.clear();
                    textFeedback.setText("down released");
                    event.consume();
                    break;
                case A:
                    left.setStyle("-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),        " +
                            "linear-gradient(#020b02, #3a3a3a),        " +
                            "linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),        " +
                            "linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%); " +
                            "-fx-background-insets: 0,1,4,5; " +
                            "-fx-font-weight: bold; " +
                            "-fx-font-family: Helvetica; " +
                            "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1); " +
                            "-fx-background-radius: 25 0 0 25;");
                    isA = false;
                    if (isW){
                        sm.setSpeed((int) (speedControl.getValue()));
                    }else if(isS){
                        sm.setSpeed((int) -(speedControl.getValue()));
                    }else sm.rotate(0);
                    textFeedback.clear();
                    textFeedback.setText("left released");
                    event.consume();
                    break;
                case D:
                    right.setStyle("-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),        " +
                            "linear-gradient(#020b02, #3a3a3a),        " +
                            "linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),        " +
                            "linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%); " +
                            "-fx-background-insets: 0,1,4,5; " +
                            "-fx-font-weight: bold; " +
                            "-fx-font-family: Helvetica; " +
                            "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1); " +
                            "-fx-background-radius: 0 25 25 0;");
                    isD = false;
                    if (isW){
                        sm.setSpeed((int) (speedControl.getValue()));
                    }else if(isS){
                        sm.setSpeed((int) -(speedControl.getValue()));
                    }else sm.rotate(0);
                    textFeedback.clear();
                    textFeedback.setText("right released");
                    event.consume();
                    break;
            }
    }








    /**
     * Method to set InnerShadow effect to buttons
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
    public void connect() throws IOException {
        if(!isConnected) {
            connect.setStyle("-fx-background-color: linear-gradient(#ff6767, #ff1a1a),        " +
                    "radial-gradient(center 50% -40%, radius 200%, #ff4d4d 45%, #ff0000 50%); " +
                    "-fx-background-insets: 0, 1; " +
                    "-fx-text-fill: #f5f5f5; " +
                    "-fx-background-radius: 25 0 0 0;");
            connect.setText("Disconnect");
            mConnect.setText("Disconnect");
            sm = new Smartcar("127.0.0.1", 1234);
            textFeedback.setText("Connecting...");
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
            sm.close();
            textFeedback.setText("Disconnecting...");
            isConnected = false;
        }
    }

    /**
     * Method to handle mouseReleased events on the smartcar control
     * @param event
     */
    public void mouseReleased(MouseEvent event) throws IOException {
        if (!isConnected) {
            textFeedback.clear();
            textFeedback.setText("SmartCar is disconnected!");
        }
        else
        if (event.getSource() == up) {
            sm.setSpeed(0);
            isDriving = false;
            textFeedback.clear();
            textFeedback.setText("up released");
            event.consume();
        }
        else if (event.getSource() == down) {
            sm.setSpeed(0);
            isDriving = false;
            textFeedback.clear();
            textFeedback.setText("down released");
            event.consume();
        }
        else if (event.getSource() == left) {
            if (isDriving) sm.setAngle(0);
            else if (!isDriving) sm.rotate(0);

            textFeedback.clear();
            textFeedback.setText("left released");
            event.consume();
        }
        else if (event.getSource() == right) {
            if (isDriving) sm.setAngle(0);
            else if (!isDriving) sm.rotate(0);

            textFeedback.clear();
            textFeedback.setText("right released");
            event.consume();
        }
        else if (event.getSource() == dRight) {
            if (isDriving) sm.setAngle(0);
            else if (!isDriving) sm.rotate(0);

            textFeedback.clear();
            textFeedback.setText("dRight released");
            event.consume();
        }
        else if (event.getSource() == dLeft) {
            if (isDriving) sm.setAngle(0);
            else if (!isDriving) sm.rotate(0);

            textFeedback.clear();
            textFeedback.setText("dLeft released");
            event.consume();
        }
    }

    /**
     * Method to handle mousePressed events on the smartcar control.
     * @param event
     */
    public void mousePressed(MouseEvent event) throws IOException {
        if (!isConnected) {
            textFeedback.clear();
            textFeedback.setText("SmartCar is disconnected!");
        }
        else
        if (event.getSource() == up) {
            sm.setSpeed((int) (speedControl.getValue()));
            isDriving = false;
            textFeedback.clear();
            textFeedback.setText("up pressed");
            event.consume();
        }
        else if (event.getSource() == down) {
            sm.setSpeed((int) -(speedControl.getValue()));
            isDriving = false;
            textFeedback.clear();
            textFeedback.setText("down pressed");
            event.consume();
        }
        else if (event.getSource() == left) {
            if (isDriving) sm.setAngle(-10);
            else if (!isDriving) sm.rotate(-10);

            textFeedback.clear();
            textFeedback.setText("left pressed");
            event.consume();
        }
        else if (event.getSource() == right) {
            if (isDriving) sm.setAngle(10);
            else if (!isDriving) sm.rotate(10);

            textFeedback.clear();
            textFeedback.setText("right pressed");
            event.consume();
        }
        else if (event.getSource() == dLeft) {
            if (isDriving) sm.setAngle(-5);
            else if (!isDriving) sm.rotate(-5);

            textFeedback.clear();
            textFeedback.setText("dLeft pressed");
            event.consume();
        }
        else if (event.getSource() == dRight) {
            if (isDriving) sm.setAngle(5);
            else if (!isDriving) sm.rotate(5);

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