
package org.jimmycollins.networktraffic.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Defines a class that represents IP data returned from the DShield API
 */
public class DShieldIpInfo 
{
    private SimpleStringProperty IP;
    
    private SimpleStringProperty FQDN;
    
    private SimpleStringProperty Blocked;
    
    private SimpleStringProperty Attacks;
    
    private SimpleStringProperty Country;
    
    /**
     * Constructor
     * @param ip The IP address
     * @param fqdn The fully qualified domain name
     * @param blocked The total number of packets blocked from this IP
     * @param attacks The number of unique destination IP addresses for these packets (i.e. targets)
     * @param country The country of origin
     */  
    public DShieldIpInfo(String ip, String fqdn, String blocked, String attacks, String country)
    {
        this.IP = new SimpleStringProperty(ip);
        this.FQDN = new SimpleStringProperty(fqdn);
        this.Blocked = new SimpleStringProperty(blocked);
        this.Attacks = new SimpleStringProperty(attacks);
        this.Country = new SimpleStringProperty(country);
    }
    
    /**
     * Set the IP address
     * @param IP The IP address
     */
    public void SetIP(SimpleStringProperty IP)
    {
        this.IP = IP;
    }
    
    /**
     * Get the IP address
     * @return The IP address
     */
    public SimpleStringProperty IPProperty()
    {
        return IP;
    }
    
    /**
     * Set the FQDN
     * @param FQDN The FQDN
     */
    public void SetFqdn(SimpleStringProperty FQDN) 
    {
        this.FQDN = FQDN;
    }
    
    /**
     * Get the FQDN
     * @return The FQDN
     */
    public SimpleStringProperty FQDNProperty()
    {
        return FQDN;
    }

    /**
     * Get the no. of blocked IP's
     * @return The no. of blocked IP's
     */
    public SimpleStringProperty BlockedProperty() 
    {
        return Blocked;
    }

    /**
     * Set the no. of IP's blocked
     * @param Blocked The count of IP's blocked
     */
    public void SetBlocked(SimpleStringProperty Blocked) 
    {
        this.Blocked = Blocked;
    }

    /**
     * Get the no. of IP's blocked 
     * @return The count of IP's blocked
     */
    public SimpleStringProperty AttacksProperty() 
    {
        return Attacks;
    }

    /**
     * Set the number of unique destination IP addresses
     * @param Attacks The number of unique destination IP addresses
     */
    public void SetAttacks(SimpleStringProperty Attacks) 
    {
        this.Attacks = Attacks;
    }

    /**
     * Get the country
     * @return The country of origin
     */
    public SimpleStringProperty CountryProperty() 
    {
        return Country;
    }

    /**
     * Set the country
     * @param Country The country
     */
    public void SetCountry(SimpleStringProperty Country) 
    {
        this.Country = Country;
    }    
}