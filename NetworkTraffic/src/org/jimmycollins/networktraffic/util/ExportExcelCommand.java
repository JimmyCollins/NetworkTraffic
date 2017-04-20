
package org.jimmycollins.networktraffic.util;

import org.jimmycollins.networktraffic.model.DataExport;
import org.jimmycollins.networktraffic.model.ExportCommand;

// Command Pattern Implementation - Concrete Command

public class ExportExcelCommand implements ExportCommand
{
    DataExport Data;

    public ExportExcelCommand(DataExport theData)
    {
        Data = theData;
    }

    @Override
    public void execute()
    {
        String desktop = System.getProperty("user.home") + "\\Desktop" + "\\";
        
        System.out.println("Exporting to Excel Command!!!");
        System.out.println("Desktop: " + desktop);
    }
}