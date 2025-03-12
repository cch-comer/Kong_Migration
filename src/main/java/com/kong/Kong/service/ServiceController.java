package com.kong.Kong.service;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/service")
public class ServiceController {

    private final ServiceService serviceService;
    private final Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @RequestMapping(value = "/list")
    public List<Map<String, Object>> getServiceList(@RequestBody Map<String, Object> map) {
        String wsId = map.get("wsId") != null ? map.get("wsId").toString() : "";
        return serviceService.getServiceList(wsId);
    }
}
