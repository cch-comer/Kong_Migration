package com.kong.Kong.consumer;

import com.kong.Kong.util.UtilService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    private final ConsumerRepository consumerRepository;
    private final UtilService utilService;

    public List<Map<String, Object>> getConsumerList(String wsId) {
        List<Map<String, Object>> resultList = consumerRepository.getConsumerList(wsId);

        for (Map<String, Object> row : resultList) {
            row.put("tags", row.get("tags") != null ? utilService.pgArraytoList(row.get("tags")) : null); // 얘가 pgArray로 반환되고 null 처리에다가 추가로 빈 값일때도 처리가 필요함.
            row.put("created_at",row.get("created_at") != null ? utilService.timeStamp((Timestamp) row.get("created_at")) : null);
            row.put("updated_at",row.get("updated_at") != null ? utilService.timeStamp((Timestamp) row.get("updated_at")) : null);
        }

        return resultList;
    }
}
