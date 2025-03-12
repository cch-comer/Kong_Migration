package com.kong.Kong.consumer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ConsumerRepository {

    private final Logger logger = LoggerFactory.getLogger(ConsumerRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getConsumerList(String wsId) {

        String sql = "SELECT c.id\n" +
                "     , c.created_at\n" +
                "     , c.updated_at\n" +
                "     , c.username\n" +
                "     , c.custom_id\n" +
                "     , c.tags\n" +
                "     , c.ws_id\n" +
                "FROM consumers c\n" +
                "WHERE c.ws_id = CAST(? AS UUID)";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql,wsId);

        if(resultList.isEmpty())
            logger.error("resultList size = 0, SQL : {}",sql);
        // 에러

        return resultList;
    }
}
