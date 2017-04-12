
package org.jimmycollins.networktraffic.model;


public class DShieldIpInfo 
{
    // total number of packets blocked from this IP
    private String IP;
    
    // number of unique destination IP addresses for these packets
    private String TotalBlockedPackets;
    
    // number of unique destination IP addresses for these packets
    private String Attacks;
    
    // country
    private String Country;

    public String GetIP() 
    {
        return IP;
    }

    public void SetIP(String IP) 
    {
        this.IP = IP;
    }

    public String GetTotalBlockedPackets() 
    {
        return TotalBlockedPackets;
    }

    public void SetTotalBlockedPackets(String TotalBlockedPackets) 
    {
        this.TotalBlockedPackets = TotalBlockedPackets;
    }

    public String GetAttacks() 
    {
        return Attacks;
    }

    public void SetAttacks(String Attacks) 
    {
        this.Attacks = Attacks;
    }

    public String GetCountry() 
    {
        return Country;
    }

    public void SetCountry(String Country) 
    {
        this.Country = Country;
    }   
    
}
