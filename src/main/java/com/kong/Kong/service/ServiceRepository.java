package com.kong.Kong.service;

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
public class ServiceRepository {

    private final Logger logger = LoggerFactory.getLogger(ServiceRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getServiceList(String wsId) {

        String sql = "SELECT s.id\n" +
                "     , s.created_at\n" +
                "     , s.updated_at\n" +
                "     , s.name\n" +
                "     , s.retries\n" +
                "     , s.protocol\n" +
                "     , s.host\n" +
                "     , s.port\n" +
                "     , s.path\n" +
                "     , s.tags\n" +
                "     , s.ws_id\n" +
                "     , s.enabled\n" +
                "FROM services s\n" +
                "WHERE s.ws_id = CAST(? AS UUID)";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql,wsId);

        if(resultList.isEmpty())
            logger.error("resultList size = 0, SQL : {}",sql);
        // 에러


        return resultList;
    }

}
