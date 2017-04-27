
package org.jimmycollins.networktraffic.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import javafx.scene.control.Alert;
import org.jimmycollins.networktraffic.model.DataExport;
import org.jimmycollins.networktraffic.model.ExportCommand;

/**
 * Command Pattern Implementation - Concrete Command
 */
public class ExportHtmlCommand implements ExportCommand
{
    DataExport Data;

    /**
     * Constructor
     * @param theData The data to export
     */
    public ExportHtmlCommand(DataExport theData)
    {
        Data = theData;
    }

    /**
     * Logic to export to HTML
     */
    @Override
    public void execute()
    {
        String fileName = "NetflowAnalysisOutput_" + Utility.GenerateDateStamp() + ".html";
        String outputFile = System.getProperty("user.home") + "\\Desktop" + "\\" + fileName;
        
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"))) 
        {
            writer.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
            writer.write("<html>");
            writer.write("<head>");
            writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            writer.write("<title>Netflow Analysis Output</title>");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<center>");

            writer.write("<h1>Netflow Analysis Export</h1>");
            
            // Top Source IP Addresses            
            writer.write("<h2>Top Source IP Addresses</h2>");
            writer.write("<table border=\"1\">");
            writer.write("<tr>");
            writer.write("<th>IP</th>");
            writer.write("<th>Count</th> ");
            writer.write("</tr>");

            for (Map.Entry<String, Integer> entry : Data.SourceIpData.entrySet())
            {
                writer.write("<tr>");
                writer.write("<td>" + entry.getKey() + "</td>");
                writer.write("<td>" + entry.getValue() + "</td>");
                writer.write("</tr>");
            }
            
            writer.write("</table>");

            // Top Destination IP Addresses
            writer.write("<h2>Top Destination IP Addresses</h2>");
            writer.write("<table border=\"1\">");
            writer.write("<tr>");
            writer.write("<th>IP</th>");
            writer.write("<th>Count</th> ");
            writer.write("</tr>");

            for (Map.Entry<String, Integer> entry : Data.DestinationIpData.entrySet())
            {
                writer.write("<tr>");
                writer.write("<td>" + entry.getKey() + "</td>");
                writer.write("<td>" + entry.getValue() + "</td>");
                writer.write("</tr>");
            }
            
            writer.write("</table>");

            // Top Source Ports
            writer.write("<h2>Top Source Ports</h2>");
            writer.write("<table border=\"1\">");
            writer.write("<tr>");
            writer.write("<th>Port</th>");
            writer.write("<th>Count</th> ");
            writer.write("</tr>");

            for (Map.Entry<String, Integer> entry : Data.SourcePortData.entrySet())
            {
                writer.write("<tr>");
                writer.write("<td>" + entry.getKey() + "</td>");
                writer.write("<td>" + entry.getValue() + "</td>");
                writer.write("</tr>");
            }
            
            writer.write("</table>");

            // Top Destination Ports
            writer.write("<h2>Top Destination Ports</h2>");
            writer.write("<table border=\"1\">");
            writer.write("<tr>");
            writer.write("<th>Port</th>");
            writer.write("<th>Count</th> ");
            writer.write("</tr>");

            for (Map.Entry<String, Integer> entry : Data.DestinationPortData.entrySet())
            {
                writer.write("<tr>");
                writer.write("<td>" + entry.getKey() + "</td>");
                writer.write("<td>" + entry.getValue() + "</td>");
                writer.write("</tr>");
            }
            
            writer.write("</table>");

            writer.write("</center>");
            writer.write("</body>");
            writer.write("</html>");

            LogUtil.Log(Alert.AlertType.INFORMATION, "Export Complete", "Data was exported to\n '" + outputFile + "'");
        }    
        catch(IOException ex)
        {
            LogUtil.Log(Alert.AlertType.ERROR, "Export Error", ex.getMessage());
        }        
    }	
}