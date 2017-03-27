package org.jimmycollins.networktraffic;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class NetworkTraffic extends Application {
    
    /**
     * Creates the main stage
     * @param stage The main stage
     * @throws Exception If an exception occurs
     */
    @Override
    public void start(Stage stage) throws Exception 
    {
        Locale locale = new Locale("en", "US");
        ResourceBundle bundle = ResourceBundle.getBundle("ResourcesBundle", locale);
        Parent root = FXMLLoader.load(getClass().getResource("view/NewUserInterface.fxml"), bundle);
        
        stage.setTitle(bundle.getString("maintitle"));
 
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        
        // Example of Lambda expression (could also use an Anonymous Class here)
        stage.setOnCloseRequest((WindowEvent we) -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle(bundle.getString("alertheader"));
            alert.setContentText(bundle.getString("exitconfirmation"));
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.CANCEL)
            {
                we.consume();
            }      
        });
        
    }

    /**
     * The main application entry point
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
