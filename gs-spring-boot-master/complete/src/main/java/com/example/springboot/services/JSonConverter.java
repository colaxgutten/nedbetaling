package com.example.springboot.services;

import com.example.springboot.models.InnbetalingModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JacksonJsonParser;

import java.util.HashMap;
import java.util.Map;

public class JSonConverter {
    public String convertInnbetalingListTilJson(InnbetalingModel[] innbetalinger) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = Map.of("nedbetalingsplan", Map.of("innbetalinger", innbetalinger));
        String jsonString = "";
        String s = "";
        try {
            s = objectMapper.writeValueAsString(map);
            jsonString += s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
