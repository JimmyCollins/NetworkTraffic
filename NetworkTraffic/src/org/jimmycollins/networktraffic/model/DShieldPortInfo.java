package org.jimmycollins.networktraffic.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Defines a class that represents Port data
 * returned from the DShield API
 */
public class DShieldPortInfo
{
    private SimpleStringProperty Port;
    
    private SimpleStringProperty Records;
    
    private SimpleStringProperty Targets;
    
    private SimpleStringProperty Sources;
      
    /**
     * Constructor
     * @param port The port
     * @param records Total number of records on this port
     * @param targets Number of unique destination IP addresses
     * @param sources Number of unique attack-originating IPs
     */
    public DShieldPortInfo(String port, String records, String targets, String sources)
    {
        this.Port = new SimpleStringProperty(port);
        this.Records = new SimpleStringProperty(records);
        this.Targets = new SimpleStringProperty(targets);
        this.Sources = new SimpleStringProperty(sources);
    }
     
    /**
     * Get the port
     * @return The port
     */
    public SimpleStringProperty PortProperty() 
    {
        return Port;
    }

    /**
     * Set the port
     * @param Port The value to use for port
     */
    public void setPort(SimpleStringProperty Port) 
    {
        this.Port = Port;
    }

    /**
     * Get the records property
     * @return The value of records
     */
    public SimpleStringProperty RecordsProperty() 
    {
        return Records;
    }

    /**
     * Set the value of records
     * @param Records The value to use for records
     */
    public void setRecords(SimpleStringProperty Records) 
    {
        this.Records = Records;
    }

    /**
     * Get the value of targets
     * @return The value of targets
     */
    public SimpleStringProperty TargetsProperty() 
    {
        return Targets;
    }

    /**
     * Set the value of targets
     * @param Targets The value to use for targets
     */
    public void setTargets(SimpleStringProperty Targets) 
    {
        this.Targets = Targets;
    }

    /**
     * Get the sources value
     * @return The sources value
     */
    public SimpleStringProperty SourcesProperty() 
    {
        return Sources;
    }

    /**
     * Set the value of sources
     * @param Sources The value to use for sources
     */
    public void setSources(SimpleStringProperty Sources) 
    {
        this.Sources = Sources;
    }  
}