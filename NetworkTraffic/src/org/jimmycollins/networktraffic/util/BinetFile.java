package org.jimmycollins.networktraffic.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.jimmycollins.networktraffic.model.Flow;
import org.jimmycollins.networktraffic.model.FlowFileStats;
import org.jimmycollins.networktraffic.model.ParsableFile;

public class BinetFile extends ParsableFile {
    
    public BinetFile(File file)
    {
        super(file);
    }
     
    @Override
    public FlowFileStats ParseFile(FlowFileStats stats) 
    {   
        try
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
                        
                        Flow flow = FlowFactory.CreateFlow(fields[2]);
                        flow.SetSourceHost(Utility.ParseInetAddress(fields[3]));
                        flow.SetDestinationHost(Utility.ParseInetAddress(fields[6]));
                        flow.SetSourcePort(Utility.ParseInt(fields[4]));
                        flow.SetDestinationPort(Utility.ParseInt(fields[7]));
                        
                        stats.AddPackets(Utility.ParseInt(fields[11]));
                        stats.AddFlow(flow);
                        stats.IncrementFlowCounter();
                    }                
                }
                catch(Exception ex)  // TODO - Create custom exception
                {
                    stats.IncrementUnparsableFlowCounter();
                    //Utility.Alert(AlertType.ERROR, "Error", ex.toString());
                    // TODO: Log exception (have log tab on UI?)
                }
                }
            });
            t.setDaemon(true); // End thread if app closes
            t.start();
            
    }
    catch(Exception ex)
    {
        
    }
    
    return stats;
}
}