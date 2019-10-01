package com.design.yang.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.design.yang.AppException.AppChildrenException.AppTestException;
import com.design.yang.BaseResponse.BaseResponse;
import com.design.yang.dto.CurDetails;
import com.design.yang.dto.EntrustTransaction;
import com.design.yang.dto.TestArea;
import com.design.yang.mapper.AreaTestMapper;
import com.design.yang.mapper.CurDetailsMapper;
import com.design.yang.mapper.EntrustTransactionMapper;
import com.design.yang.mapper.RoleMenuMapper;
import com.design.yang.redisService.TestAreaRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class TestController {

    @Autowired
    CurDetailsMapper curDetailsMapper;

    @Autowired
    EntrustTransactionMapper entrustTransactionMapper;

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + authentication.getName();
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }

    @GetMapping("/test/e")
    public BaseResponse getT() {
        //for debug
        throw new AppTestException();
    }

    @RequestMapping("/api/public/area")
    public BaseResponse getAllArea(){
        String jsonStr = "[\n" +
                "        {\n" +
                "            \"timestamps\": 1555553267699,\n" +
                "            \"name\": \"bitcoin\",\n" +
                "            \"symbol\": \"BTC\",\n" +
                "            \"price\": 5382.7568,\n" +
                "            \"price_usd\": 5382.7568,\n" +
                "            \"price_btc\": 1,\n" +
                "            \"volume\": 691249.63,\n" +
                "            \"volume_usd\": 3442021886.82,\n" +
                "            \"display_volume\": 2647190.5,\n" +
                "            \"reported_volume\": 2647190.5,\n" +
                "            \"reported_volume_usd\": 13947751000,\n" +
                "            \"market_cap_usd\": 95012451000,\n" +
                "            \"high\": 5387.4108,\n" +
                "            \"low\": 5315.501,\n" +
                "            \"hist_high\": 20089,\n" +
                "            \"hist_low\": 0,\n" +
                "            \"change_hourly\": 0.0043,\n" +
                "            \"change_daily\": 0.01,\n" +
                "            \"change_weekly\": -0.0066,\n" +
                "            \"change_monthly\": 0.3371\n" +
                "        },\n" +
                "        {\n" +
                "            \"timestamps\": 1555553248077,\n" +
                "            \"name\": \"siacoin\",\n" +
                "            \"symbol\": \"SC\",\n" +
                "            \"price\": 0.003176366,\n" +
                "            \"price_usd\": 0.003176366,\n" +
                "            \"price_btc\": 5.9064617e-7,\n" +
                "            \"volume\": 1135199000,\n" +
                "            \"volume_usd\": 3599683.05,\n" +
                "            \"display_volume\": 1135297300,\n" +
                "            \"reported_volume\": 1135297300,\n" +
                "            \"reported_volume_usd\": 3599995.4,\n" +
                "            \"market_cap_usd\": 128073890,\n" +
                "            \"high\": 0.00320408,\n" +
                "            \"low\": 0.00308395,\n" +
                "            \"hist_high\": 0.111708,\n" +
                "            \"hist_low\": 0,\n" +
                "            \"change_hourly\": 0.0169,\n" +
                "            \"change_daily\": -0.0031,\n" +
                "            \"change_weekly\": -0.0904,\n" +
                "            \"change_monthly\": 0.1649\n" +
                "        }\n" +
                "]";
        JSONArray array = JSONArray.parseArray(jsonStr);
        List<CurDetails> details = array.toJavaList(CurDetails.class);
        curDetailsMapper.insertCurDetails(details);
        return new BaseResponse(details);
    }

    @RequestMapping("/api/public/menu")
    public BaseResponse getMenu(){
        EntrustTransaction transaction = new EntrustTransaction(1L,
                1L,
                1.0,
                1.0,
                new Date());
        log.info(""+new Date());
        List<EntrustTransaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        entrustTransactionMapper.insertTransaction(transactions);
        return new BaseResponse();
    }
}
