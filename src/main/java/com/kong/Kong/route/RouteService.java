package com.kong.Kong.route;

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
public class RouteService {

    private final RouteRepository routeRepository;
    private final UtilService utilService;
    private final Logger logger = LoggerFactory.getLogger(RouteService.class);

    public List<Map<String, Object>> getRouteList(String wsId) {
        List<Map<String, Object>> resultList = routeRepository.getRouteList(wsId);

        for (Map<String, Object> row : resultList) {
            row.put("protocols", row.get("protocols") != null ? utilService.pgArraytoList(row.get("protocols")) : null);
            row.put("methods", row.get("methods") != null ? utilService.pgArraytoList(row.get("methods")) : null);
            row.put("hosts", row.get("hosts") != null ? utilService.pgArraytoList(row.get("hosts")) : null);
            row.put("tags", row.get("tags") != null ? utilService.pgArraytoList(row.get("tags")) : null); // 얘가 pgArray로 반환되고 null 처리에다가 추가로 빈 값일때도 처리가 필요함.
            row.put("paths", row.get("paths") != null ? utilService.pgArraytoList(row.get("paths")) : null);
            row.put("created_at",row.get("created_at") != null ? utilService.timeStamp((Timestamp) row.get("created_at")) : null);
            row.put("updated_at",row.get("updated_at") != null ? utilService.timeStamp((Timestamp) row.get("updated_at")) : null);
        }

        return resultList;
    }

}
