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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.jimmycollins.networktraffic.model.DShieldApi;
import org.jimmycollins.networktraffic.model.DShieldIpInfo;
import org.jimmycollins.networktraffic.model.DShieldPortInfo;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
    public DShieldIpInfo Ip(String ip)
    {
        String blocked = "0";
        String attacks = "0";
        String country = "Unknown";
        
        try
        {
            URL url = new URL(ApiBaseUrl + "ip/" + ip);
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
                // Blocked Packets from this IP
                if("count".equals(nodes.item(i).getNodeName().toLowerCase()))
                {
                    if(nodes.item(i).getTextContent().isEmpty())
                        blocked = "0";
                    else
                        blocked = nodes.item(i).getTextContent();
                }
                
                // Number of unique destination IP addresses for these packets
                if("attacks".equals(nodes.item(i).getNodeName().toLowerCase()))
                {
                    if(nodes.item(i).getTextContent().isEmpty())
                        attacks = "0";
                    else
                        attacks = nodes.item(i).getTextContent();
                }
                
                // Country
                if("ascountry".equals(nodes.item(i).getNodeName().toLowerCase()))
                {
                    if(nodes.item(i).getTextContent().isEmpty())
                        country = "Unknown";
                    else
                        country = nodes.item(i).getTextContent();
                }                        
            }
            
            DShieldIpInfo info = new DShieldIpInfo(ip, blocked, attacks, country);
            
            return info;
        }
        catch(IOException | ParserConfigurationException | SAXException ex)
        {
            LogUtil.Log(LogUtil.LogLevel.SEVERE, ex.toString());
            LogUtil.Log(Alert.AlertType.ERROR, "Network Traffic Analyzer", "Infocon() Error - " + ex.getMessage());
        }
        
        return null; // FIXME: Review this
    }
    
    
    @Override
    public DShieldPortInfo Port(String port)
    {
        String records = "0";
        String targets = "0";
        String sources = "0";
             
        try
        {          
            URL url = new URL(ApiBaseUrl + "port/" + port);
            URLConnection connection = url.openConnection();

            InputStream responseStream = connection.getInputStream();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(responseStream);
            
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("port/data");
            
            Node dataNode = (Node)expr.evaluate(doc, XPathConstants.NODE);
            
            NodeList nodes = dataNode.getChildNodes();
            
            for(int i=0; i<nodes.getLength(); i++)
            {
                if("records".equals(nodes.item(i).getNodeName().toLowerCase()))
                {
                    records = nodes.item(i).getTextContent();
                }
                
                if("targets".equals(nodes.item(i).getNodeName().toLowerCase()))
                {
                    targets = nodes.item(i).getTextContent();
                }
                
                if("sources".equals(nodes.item(i).getNodeName().toLowerCase()))
                {
                    sources = nodes.item(i).getTextContent();
                }       
            }
            
            DShieldPortInfo portInfo = new DShieldPortInfo(port, records, targets, sources);
            
            return portInfo;
        }
        catch(IOException | ParserConfigurationException | SAXException ex)
        {
            LogUtil.Log(LogUtil.LogLevel.SEVERE, ex.toString());
            LogUtil.Log(Alert.AlertType.ERROR, "Network Traffic Analyzer", "Port() Error - " + ex.getMessage());
        }
        catch (XPathExpressionException ex) 
        {
            //Logger.getLogger(RemoteDShieldApi.class.getName()).log(Level.SEVERE, null, ex);
            LogUtil.Log(Alert.AlertType.ERROR, "Network Traffic Analyzer", "Port() Error - " + ex.getMessage());
        }
        
        return null; // FIXME: Review this
    }
    
}