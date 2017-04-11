package org.jimmycollins.networktraffic.util;

import org.jimmycollins.networktraffic.model.DShieldApi;

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
    public String Ip()
    {
        return RemoteApi.Ip();
    }
}
