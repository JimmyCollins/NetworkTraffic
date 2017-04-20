
package org.jimmycollins.networktraffic.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import javafx.scene.control.Alert;
import org.jimmycollins.networktraffic.model.DataExport;
import org.jimmycollins.networktraffic.model.ExportCommand;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
        String fileName = "NetflowAnalysisOutput_" + Utility.GenerateDateStamp() + ".xlsx";
        String outputFile = System.getProperty("user.home") + "\\Desktop" + "\\" + fileName;
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        
        // Source IP Data
        XSSFSheet sourceIpSheet = workbook.createSheet("Source IPs");    
        Row headerRowSourceIp = sourceIpSheet.createRow(0);     
        Cell ipColCellSourceIp = headerRowSourceIp.createCell(0);
        ipColCellSourceIp.setCellValue("IP");     
        Cell countColCellSourceIp = headerRowSourceIp.createCell(1);
        countColCellSourceIp.setCellValue("Count");
        
        int sourceIpDataRowNum = 1;
        for (Map.Entry<String, Integer> entry : Data.SourceIpData.entrySet())
        {
            Row row = sourceIpSheet.createRow(sourceIpDataRowNum++);
            
            Cell ipCell = row.createCell(0);
            ipCell.setCellValue(entry.getKey());
            
            Cell countCell = row.createCell(1);
            countCell.setCellValue(entry.getValue());   
        }
        
        // Destination IP Data
        XSSFSheet destinationIpSheet = workbook.createSheet("Destination IPs");    
        Row headerRowDestinationIp = destinationIpSheet.createRow(0);     
        Cell ipColCellDestinationIp = headerRowDestinationIp.createCell(0);
        ipColCellDestinationIp.setCellValue("IP");     
        Cell countColCellDestinationIp = headerRowDestinationIp.createCell(1);
        countColCellDestinationIp.setCellValue("Count");
        
        int destinationIpDataRowNum = 1;
        for (Map.Entry<String, Integer> entry : Data.DestinationIpData.entrySet())
        {
            Row row = destinationIpSheet.createRow(destinationIpDataRowNum++);
            
            Cell ipCell = row.createCell(0);
            ipCell.setCellValue(entry.getKey());
            
            Cell countCell = row.createCell(1);
            countCell.setCellValue(entry.getValue());   
        }
        
        // Source Port Data
        XSSFSheet sourcePortSheet = workbook.createSheet("Source Ports");    
        Row headerRowSourcePorts = sourcePortSheet.createRow(0);     
        Cell ipColCellSourcePorts = headerRowSourcePorts.createCell(0);
        ipColCellSourcePorts.setCellValue("Port");     
        Cell countColCellSourcePorts = headerRowSourcePorts.createCell(1);
        countColCellSourcePorts.setCellValue("Count");
        
        int sourcePortDataRowNum = 1;
        for (Map.Entry<String, Integer> entry : Data.SourcePortData.entrySet())
        {
            Row row = sourcePortSheet.createRow(sourcePortDataRowNum++);
            
            Cell portCell = row.createCell(0);
            portCell.setCellValue(entry.getKey());
            
            Cell countCell = row.createCell(1);
            countCell.setCellValue(entry.getValue());   
        }
        
        // Destination Port Data
        XSSFSheet destinationPortSheet = workbook.createSheet("Destination Ports");    
        Row headerRowDestinationPorts = destinationPortSheet.createRow(0);     
        Cell ipColCellDestinationPorts = headerRowDestinationPorts.createCell(0);
        ipColCellDestinationPorts.setCellValue("Port");     
        Cell countColCellDestinationPorts = headerRowDestinationPorts.createCell(1);
        countColCellDestinationPorts.setCellValue("Count");
        
        int destinationPortDataRowNum = 1;
        for (Map.Entry<String, Integer> entry : Data.DestinationPortData.entrySet())
        {
            Row row = destinationPortSheet.createRow(destinationPortDataRowNum++);
            
            Cell portCell = row.createCell(0);
            portCell.setCellValue(entry.getKey());
            
            Cell countCell = row.createCell(1);
            countCell.setCellValue(entry.getValue());   
        }
        
        // Write to Excel
        try 
        {
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            workbook.write(outputStream);
            workbook.close();
            
            LogUtil.Log(Alert.AlertType.INFORMATION, "Export Complete", "Data was exported to\n '" + outputFile + "'");
        }
        catch (IOException ex)
        {
            LogUtil.Log(Alert.AlertType.ERROR, "Export Error", ex.getMessage());
        }
    }
}