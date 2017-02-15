package org.jimmycollins.networktraffic.model;

import java.net.InetAddress;

/**
 * Defines an Object representing a network packet
 * @author Jimmy Collins
 */
public class NetworkPacket {
    
    // Instance Variables
    
    private String Date;
    
    private Float Duration;
    
    private String Protocol;
    
    private String SourceIP;
    
    private int SourcePort;
    
    private String Direction;
    
    private String DestinationIP;
    
    private int DestinationPort;
    
    private String State;
    
    private int SourceTypeOfService;
    
    private int DestinationTypeOfService;
    
    private int TotalPackets;
    
    private int TotalBytes;
    
    private int SourceBytes;
    
    private String Label;
    
    // Constructor
    
    public NetworkPacket()
    {
        
    }
    
    // Getters
    
    public String getDate() {
        return Date;
    }
    
    public Float getDuration() {
        return Duration;
    }
    
    public String getProtocol() {
        return Protocol;
    }
    
    public String getSourceIP() {
        return SourceIP;
    }
    
    public int getSourcePort() {
        return SourcePort;
    }
    
    public String getDirection() {
        return Direction;
    }
    
    public String getDestinationIP() {
        return DestinationIP;
    }
    
    public int getDestinationPort() {
        return DestinationPort;
    }
    
    public String getState() {
        return State;
    }
    
    public int getSourceTypeOfService() {
        return SourceTypeOfService;
    }
    
    public int getDestinationTypeOfService() {
        return DestinationTypeOfService;
    }
    
    public int getTotalPackets() {
        return TotalPackets;
    }
    
    public int getTotalBytes() {
        return TotalBytes;
    }
    
    public int getSourceBytes() {
        return SourceBytes;
    }
    
    public String getLabel() {
        return Label;
    }
    
    // Setters
    
    public void setDate(String date) {
        this.Date = date;
    }
    
    public void setDuration(Float duration) {
        this.Duration = duration;
    }
    
    public void setProtocol(String protocol) {
        this.Protocol = protocol;
    }
    
    public void setSourceIP(String sourceIP) {
        this.SourceIP = sourceIP;
    }
    
    public void setSourcePort(int sourcePort) {
        this.SourcePort = sourcePort;
    }
    
    public void setDirection(String direction) {
        this.Direction = direction;
    }
    
    public void setDestinationIP(String destinationIP) {
        this.DestinationIP = destinationIP;
    }
    
    public void setDestinationPort(int destinationPort) {
        this.DestinationPort = destinationPort;
    }
    
    public void setState(String state) {
        this.State = state;
    }
    
    public void setSourceTypeOfService(int sourceTypeOfService) {
        this.SourceTypeOfService = sourceTypeOfService;
    }
    
    public void setDestinationTypeOfService(int destinationTypeOfService) {
        this.DestinationTypeOfService = destinationTypeOfService;
    }
    
    public void setTotalPackets(int totalPackets) {
        this.TotalPackets = totalPackets;
    }
    
    public void setTotalBytes(int totalBytes) {
        this.TotalBytes = totalBytes;
    }
    
    public void setSourceBytes(int sourceBytes) {
        this.SourceBytes = sourceBytes;
    }
    
    public void setLabel(String label) {
        this.Label = label;
    }
}