package org.jimmycollins.networktraffic.model;

// Remote Proxy Implementation - Subject

import java.util.List;


public interface DShieldApi 
{
    public String Infocon();

    public DShieldIpInfo Ip(String ip);
    
    public DShieldPortInfo Port(String port);
    
    public List<DShieldPortHistoryInfo> PortHistory(String port);
}