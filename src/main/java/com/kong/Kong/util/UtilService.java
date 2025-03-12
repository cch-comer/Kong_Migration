package com.kong.Kong.util;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class UtilService {

    private final Logger logger = LoggerFactory.getLogger(UtilService.class);

    public String timeStamp(Timestamp timestamp) {
        return new SimpleDateFormat("MMMM dd, yyyy, h:mm a", Locale.ENGLISH).format(timestamp);
    }

    public List<String> pgArraytoList(Object tagsObj) {
        List<String> result = new ArrayList<>();
        try {
            Array tagsArray = (Array) tagsObj;
            String[] tags = (String[]) tagsArray.getArray();
            result = List.of(tags);
        } catch (SQLException e) {
            logger.error("UtilService Error", e);
        }
        if(result.isEmpty())
            result = null;
        return result;
    }
}
