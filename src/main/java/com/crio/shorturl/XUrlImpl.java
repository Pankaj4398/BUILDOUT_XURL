package com.crio.shorturl;

import java.util.HashMap;
import java.util.UUID;

public class XUrlImpl implements XUrl{

    //private String url;
    //private static int hit;
    private HashMap<String, String> map;
    private HashMap<String, String> reverseMap;

    private HashMap<String, Integer> hits;

    public XUrlImpl(){
        map = new HashMap<>();
        reverseMap = new HashMap<>();
        hits = new HashMap<>();
    }

    public String registerNewUrl(String longUrl){
        if(map.containsKey(longUrl)){
            return map.get(longUrl);
        }
        String shortUrl = "http://short.url/";
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 9);
        shortUrl += uuid;
        map.put(longUrl, shortUrl);
        reverseMap.put(shortUrl, longUrl);
        return shortUrl;
    }

    public String getUrl(String shortUrl){
        if(!(reverseMap.containsKey(shortUrl))){
            return null;
        }
        String longUrl = reverseMap.get(shortUrl);
        
        Integer oldValue = hits.get(longUrl);
        if (oldValue == null){
            hits.put(longUrl, 1);
        } else{
            hits.put(longUrl, oldValue + 1);
        }

        return longUrl;
    }

    public String delete(String longUrl){
        String url = map.get(longUrl);
        map.remove(longUrl);
        reverseMap.remove(url);
        return url;
    }

    //Overload Method
    public String registerNewUrl(String longUrl, String shortUrl){

        if(reverseMap.containsKey(shortUrl)){
            return null;       
        }
        map.put(longUrl, shortUrl);
        reverseMap.put(shortUrl, longUrl);
        return shortUrl;
    }

    public Integer getHitCount(String longUrl){
        //return hit;
        if(!(map.containsKey(longUrl))){
            return 0;
        }
        return hits.get(longUrl);
    }

}