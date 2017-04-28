
package org.jimmycollins.networktraffic.model;

/**
 * Defines a class that represents the history of a Port within the DShield API
 */
public class DShieldPortHistoryInfo 
{
    private String Date;  
    
    private String Records;   
    
    private String Targets;  
    
    private String Sources;
    
    /**
     * Constructor
     * @param date The date of the record
     * @param records The number of records for this Port
     * @param targets The number of targets for this Port
     * @param sources 
     */
    public DShieldPortHistoryInfo(String date, String records, String targets, String sources)
    {
        this.Date = date;
        this.Records = records;
        this.Targets = targets;
        this.Sources = sources;
    }

    /**
     * Get the date
     * @return The date
     */
    public String getDate() 
    {
        return Date;
    }

    /**
     * Set the date
     * @param Date The date value to use
     */
    public void setDate(String Date) 
    {
        this.Date = Date;
    }

    /**
     * Get the count of records
     * @return The number of records for this port
     */
    public String getRecords() 
    {
        return Records;
    }

    /**
     * Set the count of records
     * @param Records The value to use for records
     */
    public void setRecords(String Records) 
    {
        this.Records = Records;
    }

    /**
     * Set the count of targets
     * @return The count of targets
     */
    public String getTargets() 
    {
        return Targets;
    }

    /**
     * Set the count of targets
     * @param Targets The value to use for targets
     */
    public void setTargets(String Targets) 
    {
        this.Targets = Targets;
    }

    /**
     * Get the count of sources
     * @return The number of sources
     */
    public String getSources()
    {
        return Sources;
    }

    /**
     * Set the count of sources
     * @param Sources The value to use for sources
     */
    public void setSources(String Sources) 
    {
        this.Sources = Sources;
    }
}
