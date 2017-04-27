package org.jimmycollins.networktraffic.model;

import java.util.ArrayList;
import java.util.Map;

/**
 * Part of Template Pattern Implementation
 */
public abstract class TopData
{
    public abstract void Initialize();

    public abstract Map<String,Integer> GetData(ArrayList<Flow> Flows);

    public final Map<String,Integer> GetTopData(ArrayList<Flow> Flows)
    {
        Initialize();
        return GetData(Flows);
    }
}