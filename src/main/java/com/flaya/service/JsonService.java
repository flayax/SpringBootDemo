package com.flaya.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flaya.domain.param.QueGateResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class JsonService {
    public QueGateResp read(String s) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        QueGateResp queGateResp = mapper.readValue(s, QueGateResp.class);
        log.info(queGateResp.toString());
        return queGateResp;
    }
}
