package org.jimmycollins.networktraffic.model;

import javafx.beans.property.SimpleStringProperty;

public class DShieldPortInfo
{
    private SimpleStringProperty Port;
    
    // Total number of records on this port
    private SimpleStringProperty Records;
    
    // Number of unique destination IP addresses
    private SimpleStringProperty Targets;
    
    // Number of unique attack-originating IPs
    private SimpleStringProperty Sources;
    
    
    public DShieldPortInfo(String port, String records, String targets, String sources)
    {
        this.Port = new SimpleStringProperty(port);
        this.Records = new SimpleStringProperty(records);
        this.Targets = new SimpleStringProperty(targets);
        this.Sources = new SimpleStringProperty(sources);
    }
    
    

    public SimpleStringProperty PortProperty() 
    {
        return Port;
    }

    public void setPort(SimpleStringProperty Port) 
    {
        this.Port = Port;
    }

    public SimpleStringProperty RecordsProperty() 
    {
        return Records;
    }

    public void setRecords(SimpleStringProperty Records) 
    {
        this.Records = Records;
    }

    public SimpleStringProperty TargetsProperty() 
    {
        return Targets;
    }

    public void setTargets(SimpleStringProperty Targets) 
    {
        this.Targets = Targets;
    }

    public SimpleStringProperty SourcesProperty() 
    {
        return Sources;
    }

    public void setSources(SimpleStringProperty Sources) 
    {
        this.Sources = Sources;
    }
    
}
