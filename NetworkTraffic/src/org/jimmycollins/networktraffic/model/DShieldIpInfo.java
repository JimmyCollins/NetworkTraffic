package org.jimmycollins.networktraffic.model;

/**
 * Represents IP information from the DShield API
 */
public class DShieldIpInfo 
{
    /**
     * Total number of packets blocked from this IP
     */
    private String IP;
    
    /**
     * Number of unique destination IP addresses for these packets
     */
    private String TotalBlockedPackets;
      
    /**
     * Number of unique destination IP addresses for these packets
     */
    private String Attacks;
    
    /**
     * Country
     */
    private String Country;

    /**
     * Get the IP Address
     * @return 
     */
    public String GetIP() 
    {
        return IP;
    }

    /**
     * Set the IP address
     * @param IP The IP address to set to
     */
    public void SetIP(String IP) 
    {
        this.IP = IP;
    }

    /**
     * Get the total no. of blocked packets
     * @return The total no. of blocked packets
     */
    public String GetTotalBlockedPackets() 
    {
        return TotalBlockedPackets;
    }

    /**
     * Set the total no. of blocked packets
     * @param TotalBlockedPackets Number to set value to
     */
    public void SetTotalBlockedPackets(String TotalBlockedPackets) 
    {
        this.TotalBlockedPackets = TotalBlockedPackets;
    }

    /**
     * Get the number of unique destination IP addresses for these packets
     * @return Total number of attacks
     */
    public String GetAttacks() 
    {
        return Attacks;
    }

    /**
     * Set the number of unique destination IP addresses for these packets
     * @param Attacks Number to set value to
     */
    public void SetAttacks(String Attacks) 
    {
        this.Attacks = Attacks;
    }

    /**
     * Get the Country
     * @return The Country
     */
    public String GetCountry() 
    {
        return Country;
    }

    /**
     * Set the Country
     * @param Country The country to set the value to
     */
    public void SetCountry(String Country) 
    {
        this.Country = Country;
    }
}
