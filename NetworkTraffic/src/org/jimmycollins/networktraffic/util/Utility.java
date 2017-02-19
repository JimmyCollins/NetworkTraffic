
package org.jimmycollins.networktraffic.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Utility {
    
    public static void Alert(AlertType type, String header, String message)
    {
        Alert alert = new Alert(type);
        alert.setTitle("Network Traffic Analyzer");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.show();
    }
    
    // TODO: Rewrite
    public static String getMostUsedIpAddress(List<String> ipAddress)
    {
        Collections.sort(ipAddress); 
        String temp = ""; 
        String mostCommon = "";
        
        int max = 0;
        int num = 0;
        for (String string : ipAddress) 
        {
          if (string.equals(temp)) 
          {
            num++;
          } 
          else {
            if (num>max) 
            {
              max = num;
              mostCommon = string;
            }
            num = 1;
            temp = string;
          }
        }
        return mostCommon;
    }
    
    // TODO: Rewrite
    public static <T> T mostCommon(List<T> list)
    {
        Map<T, Integer> map = new HashMap<>();

        for (T t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        Entry<T, Integer> max = null;

        for (Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }

        return max.getKey();
   }
    
    //todo rewrite
    public static Map<String, Integer> GetTopElements(ArrayList<String> list)   
    {
        Map<String,Integer> map = new HashMap<String, Integer>();
        
        for(int i=0;i<list.size();i++){            
            Integer count = map.get(list.get(i));       
            map.put(list.get(i), count==null?1:count+1);   //auto boxing and count
        }
        
        //sort it by largest value ascending
        map = sortByValue(map);
        
        Map<String,Integer> top5 = new HashMap<String, Integer>();
        
        for (Entry<String, Integer> entry : map.entrySet())
        {
             if (top5.size() > 4) break;
               top5.put(entry.getKey(), entry.getValue());
        }
        

        
       return top5;
    }
    
    //todo rewrite http://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values-java
    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<K, V>();
        
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        
        return result;
    }
    
}
