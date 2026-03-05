package com.example.discoveryservice;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RegistryService {

    private final Map<String, List<String>> registry = new ConcurrentHashMap<>();

    public void register(String serviceName, String url) {
        registry.compute(serviceName, (k, v) -> {
            if (v == null) v = new ArrayList<>();
            if (!v.contains(url)) v.add(url);
            return v;
        });
    }

    public List<String> lookup(String serviceName) {
        List<String> urls = registry.get(serviceName);
        if (urls == null || urls.isEmpty()) return null;
        return urls;
    }
}