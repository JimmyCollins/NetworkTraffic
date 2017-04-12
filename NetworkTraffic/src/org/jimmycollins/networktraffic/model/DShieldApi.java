package org.jimmycollins.networktraffic.model;

// Remote Proxy Implementation - Subject

public interface DShieldApi 
{
    public String Infocon();

    public DShieldIpInfo Ip(String ip);
    
    public DShieldPortInfo Port(String port);
}