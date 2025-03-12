package com.kong.Kong.service;


import com.kong.Kong.util.UtilService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final Logger logger = LoggerFactory.getLogger(ServiceService.class);

    private final UtilService utilService;

    public List<Map<String, Object>> getServiceList(String wsId) {
        List<Map<String, Object>> resultList = serviceRepository.getServiceList(wsId);

        for (Map<String, Object> row : resultList) {
            row.put("tags", row.get("tags") != null ? utilService.pgArraytoList(row.get("tags")) : null);
            row.put("created_at",row.get("created_at") != null ? utilService.timeStamp((Timestamp) row.get("created_at")) : null);
            row.put("updated_at",row.get("updated_at") != null ? utilService.timeStamp((Timestamp) row.get("updated_at")) : null);
        }

        return resultList;
    }
}
