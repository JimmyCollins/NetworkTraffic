
package org.jimmycollins.networktraffic.model;

import java.util.List;

/**
 * Remote Proxy Implementation - Subject
 */
public interface DShieldApi 
{
    public String Infocon();

    public DShieldIpInfo Ip(String ip);
    
    public DShieldPortInfo Port(String port);
    
    public List<DShieldPortHistoryInfo> PortHistory(String port);
}