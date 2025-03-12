package com.kong.Kong.route;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
@RequiredArgsConstructor
public class RouteRepository {

    private final Logger logger = LoggerFactory.getLogger(RouteRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getRouteList(String wsId) {

        String sql = "SELECT r.id\n" +
                "     , r.created_at\n" +
                "     , r.updated_at\n" +
                "     , r.name\n" +
                "     , r.service_id\n" +
                "     , r.protocols\n" +
                "     , r.methods\n" +
                "     , r.hosts\n" +
                "     , r.paths\n" +
                "     , r.tags\n" +
                "     , r.ws_id\n" +
                "FROM routes r\n" +
                "WHERE r.ws_id = CAST(? AS UUID)";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql,wsId);

        if(resultList.isEmpty())
            logger.error("resultList size = 0, SQL : {}",sql);
        // 에러


        return resultList;
    }

}
