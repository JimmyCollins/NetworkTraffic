package org.jimmycollins.networktraffic.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import org.jimmycollins.networktraffic.ParsingException;
import org.jimmycollins.networktraffic.model.Flow;
import org.jimmycollins.networktraffic.model.FlowFileStats;
import org.jimmycollins.networktraffic.model.ParsableFile;

public class BinetFile extends ParsableFile {
    
    private final FlowFactory Factory;
    
    Locale locale = new Locale("en", "US");
    ResourceBundle resources = ResourceBundle.getBundle("ResourcesBundle", locale);
    
    public BinetFile(File file)
    {
        super(file);
        Factory = new FlowFactory();
    }
     
    @Override
    public FlowFileStats ParseFile(FlowFileStats stats) throws ParsingException
    {   
        File file = super.GetFile();

        // Example of Anonymous inner class
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                try
                {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    reader.readLine(); 
                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {                            
                        String[] fields = line.split(",");

                        Flow flow = Factory.CreateFlow(fields[2]);

                        InetAddress sourceAddress = Utility.ParseInetAddress(fields[3]);
                        InetAddress destinationAddress = Utility.ParseInetAddress(fields[6]);
                        int sourcePort = Utility.ParseInt(fields[4]);
                        int destinationPort = Utility.ParseInt(fields[7]);

                        if(sourceAddress == null || destinationAddress == null)
                        {
                            stats.IncrementUnparsableFlowCounter();
                            continue;
                        }

                        if(sourcePort == -1 || destinationPort == -1)
                        {
                            stats.IncrementUnparsableFlowCounter();
                            continue;
                        }

                        flow.SetSourceHost(sourceAddress);
                        flow.SetDestinationHost(destinationAddress);
                        flow.SetSourcePort(sourcePort);
                        flow.SetDestinationPort(destinationPort);

                        stats.AddPackets(Utility.ParseInt(fields[11]));
                        stats.AddFlow(flow);
                        stats.IncrementFlowCounter();
                    }                
                }
                catch(IOException ex)
                {
                    Logger.Log(Alert.AlertType.ERROR, resources.getString("error"), ex.getMessage() + "\n" + ex.toString());
                }
            }
        });
        t.setDaemon(true); // End thread if app closes
        t.start();
         
    
    return stats;
   }
}