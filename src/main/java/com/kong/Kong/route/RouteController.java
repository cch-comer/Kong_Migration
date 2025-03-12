package com.kong.Kong.route;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/route")
public class RouteController {

    private final RouteService routeService;
    private final Logger logger = LoggerFactory.getLogger(RouteController.class);

    @RequestMapping(value = "/list")
    public List<Map<String, Object>> getServiceList(@RequestBody Map<String, Object> map) {
        String wsId = map.get("wsId") != null ? map.get("wsId").toString() : "";
        return routeService.getRouteList(wsId);
    }
}
