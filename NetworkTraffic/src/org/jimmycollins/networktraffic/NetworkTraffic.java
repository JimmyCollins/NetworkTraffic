package org.jimmycollins.networktraffic;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
          public void handle(WindowEvent we) 
          {
              Alert alert = new Alert(AlertType.CONFIRMATION);
              alert.setTitle(bundle.getString("alertheader"));
              alert.setContentText(bundle.getString("exitconfirmation"));

              Optional<ButtonType> result = alert.showAndWait();
              if (result.get() == ButtonType.OK)
              {
                   System.exit(0);
              }
              else 
              {
                  we.consume();
              }
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
