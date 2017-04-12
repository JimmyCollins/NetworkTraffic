package org.jimmycollins.networktraffic.util;

import org.jimmycollins.networktraffic.model.DShieldApi;
import org.jimmycollins.networktraffic.model.DShieldIpInfo;
import org.jimmycollins.networktraffic.model.DShieldPortInfo;

// Remote Proxy Implementation - Proxy

public class DShieldApiProxy implements DShieldApi
{
    private final RemoteDShieldApi RemoteApi;

    public DShieldApiProxy()
    {
        RemoteApi = new RemoteDShieldApi();
    }

    @Override
    public String Infocon()
    {
        return RemoteApi.Infocon();
    }

    @Override
    public DShieldIpInfo Ip(String ip)
    {
        return RemoteApi.Ip(ip);
    }
    
    @Override
    public DShieldPortInfo Port(String port)
    {
        return RemoteApi.Port(port);
    }
}
