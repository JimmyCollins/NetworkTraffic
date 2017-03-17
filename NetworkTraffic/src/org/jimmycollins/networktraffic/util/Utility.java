
package org.jimmycollins.networktraffic.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Provides common utility functions
 */
public class Utility 
{
    /**
     * Parse an Integer from this string or return a default value 
     * @param value The string to attempt to parse
     * @return This string as an Integer, or -1 in error
     */
    public static int ParseInt(String value)
    {
        if(value == null || value.isEmpty())
        {
            return -1;
        }
        
        try
        {
            return Integer.parseInt(value);
        }
        catch(NumberFormatException nfe)
        {
            LogUtil.Log(LogUtil.LogLevel.INFO, "Could not parse '" + value + "' as an Integer, exception was: " + nfe.getMessage());
            return -1;
        }
    }
     
    /**
     * Parse an IP address from this string or return a default value
     * @param value The string to attempt to parse
     * @return This string as an InetAddress, or null in error
     */
    public static InetAddress ParseInetAddress(String value)
    {
        if(value == null || value.isEmpty())
        {
            return null;
        }
        
        try
        {
            return InetAddress.getByName(value);
        }
        catch(UnknownHostException uhe)
        {
            LogUtil.Log(LogUtil.LogLevel.INFO, "Could not parse '" + value + "' as an InetAddress, exception was: " + uhe.getMessage());
            return null;
        }
    }
     
    /**
     * Get the top X elements in an ArrayList
     * @param list The list to analyze
     * @param elementCount The number of elements to return
     * @return A new map with the top X elements from the given strings list (containing the element and the number of times it occurs)
     */
    public static Map<String, Integer> GetTopElements(ArrayList<String> list, int elementCount)   
    {
        Map<String,Integer> map = new HashMap<>();
        
        for(int i = 0; i < list.size(); i++)
        {            
            Integer count = map.get(list.get(i)); // Autoboxing
            map.put(list.get(i), count == null ? 1 : count + 1);   
        }
        
        // Sort the map by value
        map = SortMapByValue(map);
        
        // Get and return the elements
        Map<String,Integer> topElements = new HashMap<>();
        
        for (Entry<String, Integer> entry : map.entrySet())
        {
            if (topElements.size() > elementCount)
            {
                break;
            }
             
            topElements.put(entry.getKey(), entry.getValue());
        }
        
       return topElements;
    }
    
    /**
     * Converts bytes to megabytes
     * @param bytes The bytes to convert
     * @return The given bytes values as megabytes
     */
    public static long BytesToMb(long bytes) 
    {
        long sizeInMb = bytes / (1024 * 1024);
        return sizeInMb;
    }
      
    /**
     * Sorts a map by value (largest in ascending order)
     * @param <K> The key type
     * @param <V> The value type
     * @param mapToSort The map to sort
     * @return The map sorted by value, largest in ascending order
     */
    private static <K, V extends Comparable<? super V>> Map<K, V> SortMapByValue(Map<K, V> mapToSort)
    {
        List<Map.Entry<K, V>> list = new LinkedList<>(mapToSort.entrySet());
        
        // Sort it by largest value ascending (uses Lambda expression)
        Collections.sort(list, (Map.Entry<K,V> o1, Map.Entry<K,V> o2) -> 
                (o2.getValue()).compareTo(o1.getValue()));

        Map<K, V> sortedMap = new LinkedHashMap<>();
        
        list.forEach((entry) ->
        {
            sortedMap.put( entry.getKey(), entry.getValue());
        });
        
        return sortedMap;
    }
}
