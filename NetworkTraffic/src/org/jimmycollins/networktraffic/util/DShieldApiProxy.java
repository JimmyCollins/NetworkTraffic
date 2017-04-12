package org.jimmycollins.networktraffic.util;

import org.jimmycollins.networktraffic.model.DShieldApi;
import org.jimmycollins.networktraffic.model.DShieldIpInfo;

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
}
