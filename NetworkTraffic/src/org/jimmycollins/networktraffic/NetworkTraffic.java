package org.jimmycollins.networktraffic;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class NetworkTraffic extends Application {
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        Locale locale = new Locale("en", "US");
        ResourceBundle bundle = ResourceBundle.getBundle("ResourcesBundle", locale);
        Parent root = FXMLLoader.load(getClass().getResource("view/MainUI.fxml"), bundle);
        
        stage.setTitle(bundle.getString("maintitle"));
 
        stage.setScene(new Scene(root));
        stage.show();
        
        // TODO:
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
          @Override
          public void handle(WindowEvent we) {
              System.out.println("Stage is closing");
          }
        });
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
