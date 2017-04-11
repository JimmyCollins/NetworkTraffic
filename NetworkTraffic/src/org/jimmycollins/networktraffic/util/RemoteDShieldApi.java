package org.jimmycollins.networktraffic.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javafx.scene.control.Alert;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jimmycollins.networktraffic.model.DShieldApi;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

// Remote Proxy Implementation - Real Subject

public class RemoteDShieldApi implements DShieldApi
{
    private final String ApiBaseUrl = "https://www.dshield.org/api/";
    

    @Override
    public String Infocon()
    {
        String status = "undefined";
        
        try
        {          
            URL url = new URL(ApiBaseUrl + "infocon");
            URLConnection connection = url.openConnection();

            InputStream responseStream = connection.getInputStream();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(responseStream);
            
            Element rootElement;
            rootElement = doc.getDocumentElement();
            NodeList nodes = rootElement.getChildNodes();
            
            for(int i=0; i<nodes.getLength(); i++)
            {
                if("status".equals(nodes.item(i).getNodeName().toLowerCase()))
                {
                    //System.out.println("Infocon: " + nodes.item(i).getTextContent());
                    status = nodes.item(i).getTextContent();
                }
            }
        }
        catch(IOException | ParserConfigurationException | SAXException ex)
        {
            LogUtil.Log(LogUtil.LogLevel.SEVERE, ex.toString());
            LogUtil.Log(Alert.AlertType.ERROR, "Network Traffic Analyzer", "Infocon() Error - " + ex.getMessage());
        }
        
        return status;  
    }

    @Override
    public String Ip()
    {
        return "";
    }
    
    
    
    // TODO: Ref - http://stackoverflow.com/questions/3058434/xml-parse-file-from-http
    
    private Document ParseXml(InputStream stream) throws Exception
    {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try
        {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);
        }
        catch(IOException | ParserConfigurationException | SAXException ex)
        {
            throw ex;
        }       

        return doc;
    }
}
