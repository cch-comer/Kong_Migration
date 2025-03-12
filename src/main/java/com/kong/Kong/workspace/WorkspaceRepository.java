package com.kong.Kong.workspace;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
@RequiredArgsConstructor
public class WorkspaceRepository {

    private final Logger logger = LoggerFactory.getLogger(WorkspaceRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllWorkspaceInfo() {

        String sql = "SELECT w.id\n" +
                "     , w.name\n" +
                "     , w.comment\n" +
                "     , w.created_at\n" +
                "     , w.meta\n" +
                "     , w.config\n" +
                "     , w.updated_at\n" +
                "     , COALESCE(s.serviceCount, 0) AS serviceCount\n" +
                "     , COALESCE(c.consumerCount, 0) AS consumerCount\n" +
                "FROM workspaces w\n" +
                "LEFT JOIN (\n" +
                "    SELECT ws_id, COUNT(1) AS serviceCount\n" +
                "    FROM services\n" +
                "    GROUP BY ws_id\n" +
                ") s ON w.id = s.ws_id\n" +
                "LEFT JOIN (\n" +
                "    SELECT ws_id, COUNT(1) AS consumerCount\n" +
                "    FROM consumers\n" +
                "    GROUP BY ws_id\n" +
                ") c ON w.id = c.ws_id";

        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);

        if(resultList.isEmpty())
            logger.error("resultList size = 0, SQL : {}",sql);
            // 에러


        return resultList;
    }

    public Map<String, Object> createWorkspace(String name, String color) {
        Map<String, Object> resultMap = new HashMap<>();
        UUID id = UUID.randomUUID();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        map.put("color", color);
        String upMeta = null;
        try {
            upMeta = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String sql = "INSERT INTO WORKSPACES (ID, NAME, META) VALUES (?,?,?::jsonb)";
        int response = jdbcTemplate.update(sql, id, name, upMeta);
        if(response < 1) {
            resultMap.put("result","error");
            logger.error("createWorkspace error, SQL : {}",sql);
        }
        return resultMap;
    }

}
