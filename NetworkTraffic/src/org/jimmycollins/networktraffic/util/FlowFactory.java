
package org.jimmycollins.networktraffic.util;

import org.jimmycollins.networktraffic.model.Flow;

/**
 * Provides a factory method for creating Flow objects
 */
public class FlowFactory {
    
    private enum FlowType {
	TCP, UDP, ICMP, ARP, RTP, PIM, IPXSPX, RTCP, IGMP, IPV6, IPV6ICMP, UDT, ESP, UNAS, RARP
    }
    
    /**
     * Create a new Flow
     * @param type The type of the flow (based on the network protocol()
     * @return A new Flow object with the given type
     */
    public Flow CreateFlow(String type)
    {
        Flow flow = null;

        switch (type) 
        {
            case "tcp":
                flow = new Flow(FlowType.TCP);
                break;
            case "udp":
                flow = new Flow(FlowType.UDP);
                break;
            case "icmp":
                flow = new Flow(FlowType.ICMP);
                break;
            case "arp":
                flow = new Flow(FlowType.ARP);
                break;
            case "rtp":
                flow = new Flow(FlowType.RTP);
                break;
            case "pim":
                flow = new Flow(FlowType.PIM);
                break;
            case "ipx/spx":
                flow = new Flow(FlowType.IPXSPX);
                break;
            case "rtcp":
                flow = new Flow(FlowType.RTCP);
                break;
            case "igmp":
                flow = new Flow(FlowType.IGMP);
                break;
            case "ipv6":
                flow = new Flow(FlowType.IPV6);
                break;
            case "ipv6-icmp":
                flow = new Flow(FlowType.IPV6ICMP);
                break;
            case "udt":
                flow = new Flow(FlowType.UDT);
                break;
            case "esp":
                flow = new Flow(FlowType.ESP);
                break;
            case "unas":
                flow = new Flow(FlowType.UNAS);
                break;
            case "rarp":
                flow = new Flow(FlowType.RARP);
                break;
            default:
                break;
        }

        return flow;
    }
}
