package com.kong.Kong.workspace;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/workspace")
@RequiredArgsConstructor
public class WorkspaceController {

    private final WorkspaceService workspaceService;
    private final Logger logger = LoggerFactory.getLogger(WorkspaceController.class);

    @RequestMapping(value = "/list")
    public List<Map<String, Object>> getAllWorkspaceInfo() {
        List<Map<String, Object>> list = workspaceService.getAllWorkspaceInfo();
        return list;
    }

    @RequestMapping(value = "/create")
    public Map<String, Object> createWorkspace(@RequestBody Map<String, Object> map) {
        String name = map.get("name").toString();
        String color = map.get("color").toString();
        Map<String, Object> resultMap = workspaceService.createWorkspace(name, color);
        return resultMap;
    }
}
