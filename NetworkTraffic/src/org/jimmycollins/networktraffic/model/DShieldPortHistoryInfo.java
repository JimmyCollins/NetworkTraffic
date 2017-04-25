
package org.jimmycollins.networktraffic.model;

public class DShieldPortHistoryInfo 
{
    private String Date;
    
    private String Records;
    
    private String Targets;
    
    private String Sources;
    
    public DShieldPortHistoryInfo(String date, String records, String targets, String sources)
    {
        this.Date = date;
        this.Records = records;
        this.Targets = targets;
        this.Sources = sources;
    }

    /*public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
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
    }*/
    
    
}
