
package org.jimmycollins.networktraffic.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
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
     * Parse a Date/Time from this string or return a default value 
     * @param value The string to attempt to parse
     * @return This string as a Date/Time, or null in error
     */
    public static LocalDateTime ParseDateTime(String value)
    {
        if(value == null || value.isEmpty())
        {
            return null;
        }
        
        try
        {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy/MM/dd HH:mm:ss")
                .appendFraction(ChronoField.MICRO_OF_SECOND, 0, 6, true)
                .toFormatter();
            
            LocalDateTime dateTime = LocalDateTime.parse(value, formatter);
            return dateTime;
        }
        catch(Exception pe)
        {
            LogUtil.Log(LogUtil.LogLevel.INFO, "Could not parse '" + value + "' as an Date, exception was: " + pe.getMessage());
            return null;
        }
    }
    
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
     * Generate a timestamp
     * @return A timestamp as a string
     */
    public static String GenerateTimestamp()
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.toString();
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


