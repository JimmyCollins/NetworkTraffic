
//http://stackoverflow.com/questions/11012989/java-generics-declare-map-value-of-generic-type

package org.jimmycollins.networktraffic.model;

import java.util.HashMap;
import java.util.Map;

public class GenericMap<K,T>
{  
    private HashMap<K, T> map;

    public T getValue(final String key) 
    {
        return map.get(key);
    }
    
    public int size()
    {
        return map.size();
    }
    
  
    
    
}
