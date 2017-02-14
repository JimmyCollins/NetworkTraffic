package org.jimmycollins.networktraffic.model;

import java.net.InetAddress;

/**
 * Defines an Object representing a network packet
 * @author Jimmy Collins
 */
public class NetworkPacket {
    
    // Instance Variables
    
    private String Date;
    
    private String Duration;
    
    private String Protocol;
    
    private String SourceIP;
    
    private String SourcePort;
    
    private String Direction;
    
    private String DestinationIP;
    
    private String DestinationPort;
    
    private String State;
    
    private String SourceTypeOfService;
    
    private String DestinationTypeOfService;
    
    private String TotalPackets;
    
    private String TotalBytes;
    
    private String SourceBytes;
    
    private String Label;
    
    // Constructor
    
    public NetworkPacket()
    {
        
    }
    
    // Getters
    
    public String getDate() {
        return Date;
    }
    
    public String getDuration() {
        return Duration;
    }
    
    public String getProtocol() {
        return Protocol;
    }
    
    public String getSourceIP() {
        return SourceIP;
    }
    
    public String getSourcePort() {
        return SourcePort;
    }
    
    public String getDirection() {
        return Direction;
    }
    
    public String getDestinationIP() {
        return DestinationIP;
    }
    
    public String getDestinationPort() {
        return DestinationPort;
    }
    
    public String getState() {
        return State;
    }
    
    public String getSourceTypeOfService() {
        return SourceTypeOfService;
    }
    
    public String getDestinationTypeOfService() {
        return DestinationTypeOfService;
    }
    
    public String getTotalPackets() {
        return TotalPackets;
    }
    
    public String getTotalBytes() {
        return TotalBytes;
    }
    
    public String getSourceBytes() {
        return SourceBytes;
    }
    
    public String getLabel() {
        return Label;
    }
    
    // Setters
    
    public void setDate(String date) {
        this.Date = date;
    }
    
    public void setDuration(String duration) {
        this.Duration = duration;
    }
    
    public void setProtocol(String protocol) {
        this.Protocol = protocol;
    }
    
    public void setSourceIP(String sourceIP) {
        this.SourceIP = sourceIP;
    }
    
    public void setSourcePort(String sourcePort) {
        this.SourcePort = sourcePort;
    }
    
    public void setDirection(String direction) {
        this.Direction = direction;
    }
    
    public void setDestinationIP(String destinationIP) {
        this.DestinationIP = destinationIP;
    }
    
    public void setDestinationPort(String destinationPort) {
        this.DestinationPort = destinationPort;
    }
    
    public void setState(String state) {
        this.State = state;
    }
    
    public void setSourceTypeOfService(String sourceTypeOfService) {
        this.SourceTypeOfService = sourceTypeOfService;
    }
    
    public void setDestinationTypeOfService(String destinationTypeOfService) {
        this.DestinationTypeOfService = destinationTypeOfService;
    }
    
    public void setTotalPackets(String totalPackets) {
        this.TotalPackets = totalPackets;
    }
    
    public void setTotalBytes(String totalBytes) {
        this.TotalBytes = totalBytes;
    }
    
    public void setSourceBytes(String sourceBytes) {
        this.SourceBytes = sourceBytes;
    }
    
    public void setLabel(String label) {
        this.Label = label;
    }
}