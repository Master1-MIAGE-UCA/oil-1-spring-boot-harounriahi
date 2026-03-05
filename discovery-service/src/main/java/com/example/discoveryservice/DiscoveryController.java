package com.example.discoveryservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DiscoveryController {

    private final RegistryService registryService;

    @PostMapping("/registry")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest req) {

        registryService.register(req.getServiceName(), req.getUrl());

        System.out.println("REGISTERED: " + req.getServiceName() + " -> " + req.getUrl());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/discovery/{serviceName}")
    public ResponseEntity<List<String>> discovery(@PathVariable String serviceName) {

        List<String> urls = registryService.lookup(serviceName);

        if (urls == null || urls.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(urls);
    }
}