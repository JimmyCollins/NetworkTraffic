package org.jimmycollins.networktraffic.util;

import java.util.List;
import org.jimmycollins.networktraffic.model.DShieldApi;
import org.jimmycollins.networktraffic.model.DShieldIpInfo;
import org.jimmycollins.networktraffic.model.DShieldPortHistoryInfo;
import org.jimmycollins.networktraffic.model.DShieldPortInfo;

/**
 * Remote Proxy Implementation - Proxy
 */
public class DShieldApiProxy implements DShieldApi
{
    private final RemoteDShieldApi RemoteApi;

    /**
     * Constructor
     */
    public DShieldApiProxy()
    {
        RemoteApi = new RemoteDShieldApi();
    }

    /**
     * Gets the current ISC Threat Level
     * @return The current ISC Threat Level
     */
    @Override
    public String Infocon()
    {
        return RemoteApi.Infocon();
    }

    /**
     * Gets information about the specified IP from the DShield API
     * @param ip The IP to query
     * @return Information about the specified IP 
     */
    @Override
    public DShieldIpInfo Ip(String ip)
    {
        return RemoteApi.Ip(ip);
    }
    
    /**
     * Gets information about the specified port from the DShield API
     * @param port The port to query
     * @return Information about the specified port 
     */
    @Override
    public DShieldPortInfo Port(String port)
    {
        return RemoteApi.Port(port);
    }
    
    /**
     * Gets historical information about the specified port from the DShield API
     * @param port The port to query
     * @return Information about the specified port (last 30 days if available)
     */
    @Override
    public List<DShieldPortHistoryInfo> PortHistory(String port)
    {
        return RemoteApi.PortHistory(port);
    }
}