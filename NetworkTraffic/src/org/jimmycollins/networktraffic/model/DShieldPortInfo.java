package org.jimmycollins.networktraffic.model;

public class DShieldPortInfo
{
    private String Port;
    
    // Total number of records on this port
    private String Records;
    
    // Number of unique destination IP addresses
    private String Targets;
    
    // Number of unique attack-originating IPs
    private String Sources;
    
    // The name of the service
    private String ServiceName;   // TODO: Need to modify DShieldIpInfo in Remote.. to use XPATH to grab this
    

    public String getPort() {
        return Port;
    }

    public void setPort(String Port) {
        this.Port = Port;
    }

    public String getRecords() {
        return Records;
    }

    public void setRecords(String Records) {
        this.Records = Records;
    }

    public String getTargets() {
        return Targets;
    }

    public void setTargets(String Targets) {
        this.Targets = Targets;
    }

    public String getSources() {
        return Sources;
    }

    public void setSources(String Sources) {
        this.Sources = Sources;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String ServiceName) {
        this.ServiceName = ServiceName;
    }
    
}
