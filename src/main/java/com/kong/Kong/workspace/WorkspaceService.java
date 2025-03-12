package com.kong.Kong.workspace;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final Logger logger = LoggerFactory.getLogger(WorkspaceService.class);

    public List<Map<String, Object>> getAllWorkspaceInfo() {
        return workspaceRepository.getAllWorkspaceInfo();
    }

    public Map<String, Object> createWorkspace(String name, String color) {
        return workspaceRepository.createWorkspace(name,color);
    }

}
