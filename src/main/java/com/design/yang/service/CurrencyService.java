package com.design.yang.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.design.yang.BaseResponse.BasePageInfo;
import com.design.yang.Util.HttpClientUtil;
import com.design.yang.dto.CurDetails;
import com.design.yang.dto.CurSymbol;
import com.design.yang.dto.CurrencySymbol;
import com.design.yang.dto.SystemConfig;
import com.design.yang.mapper.CurDetailsMapper;
import com.design.yang.mapper.CurSymbolMapper;
import com.design.yang.redisService.BaseReidsService;
import com.design.yang.redisService.CurDetailRedisService;
import com.design.yang.redisService.MapRedisService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-18 10:54
 */
@Service
public class CurrencyService {
    @Autowired
    CurDetailRedisService curDetailRedisService;
    @Autowired
    BaseReidsService<String> symbolRedisService;
    @Autowired
    MapRedisService mapRedisService;
    @Autowired
    CurDetailsMapper curDetailsMapper;
    @Autowired
    CurSymbolMapper curSymbolMapper;

    @Autowired
    SystemConfigService systemConfigService;

    public void getDetailFromWeb(){
        String needCurrencySymbol = symbolRedisService.get("need_currency_symbol");
        if(needCurrencySymbol == null || needCurrencySymbol.equals("")){
            List<CurSymbol> curSymbols = curSymbolMapper.getRequestCur();
            StringBuffer buffer = new StringBuffer();
            for(CurSymbol curSymbol : curSymbols) {
                buffer.append(curSymbol.getName() + ",");
            }
            needCurrencySymbol = buffer.toString();
            symbolRedisService.add("need_currency_symbol",needCurrencySymbol);
        }
        SystemConfig currencyDataUrl = systemConfigService.getConfigByKey("currencydata.url");
        String apiUrl = currencyDataUrl.getValue()+needCurrencySymbol;
        JSONObject head = new JSONObject();
        try {
            JSONObject rev = HttpClientUtil.getJSON(apiUrl,head,JSONObject.class);
            JSONArray data = rev.getJSONArray("data");
            List<CurDetails> details = data.toJavaList(CurDetails.class);
            curDetailRedisService.add("curreny_details",details);
            setMapRedis(details);
            curDetailsMapper.insertCurDetails(details);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<CurDetails> getNewDetail(String symbol){
        List<CurDetails> detailsList = curDetailRedisService.get("curreny_details");
        if(detailsList == null || detailsList.size() == 0){
            detailsList = curDetailsMapper.getLastCurDetails();
            curDetailRedisService.add("curreny_details",detailsList);
        }
        if(symbol==null || symbol.equals("")) {
            return detailsList;
        }
        List<CurDetails> curDetails = new ArrayList<>();
        for(CurDetails details : detailsList){
            if(details.getSymbol().indexOf(symbol) > -1){
                curDetails.add(details);
            }
        }
        return curDetails;
    }

    public void setMapRedis(List<CurDetails> details){
        Map<String,CurDetails> map = new HashMap<>();
        for(CurDetails curDetails : details){
            map.put(curDetails.getSymbol(),curDetails);
        }
        mapRedisService.add("curreny_details_map",map);
    }

    public void getRateFromWeb(){
        SystemConfig currencyDataUrl = systemConfigService.getConfigByKey("currencyrate.url");
        String apiUrl = currencyDataUrl.getValue();
        JSONObject head = new JSONObject();
        try {
            JSONObject rev = HttpClientUtil.getJSON(apiUrl,head,JSONObject.class);
            JSONObject rates = rev.getJSONObject("data").getJSONObject("rates");
            Map<String,Double> map = rates.toJavaObject(Map.class);
            mapRedisService.add("USDT_rate",map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<CurSymbol> getRequestCur(){
        CurSymbol symbol = new CurSymbol();
        symbol.setSymbol("USDT");
        symbol.setAlias("USDT");
        symbol.setName("USDT");
        List<CurSymbol> list = curSymbolMapper.getRequestCur();
        list.add(0,symbol);
        return list;
    }

    public BasePageInfo<CurrencySymbol> getAllCur(int pageNum, int pageSize , CurrencySymbol sym){
        if(sym == null){
            sym = new CurrencySymbol();
        }
        Page<CurrencySymbol> page = PageHelper.startPage(pageNum,pageSize);
        List<CurrencySymbol> symbols = curSymbolMapper.getAllCur(sym);
        BasePageInfo basePageInfo = new BasePageInfo<CurrencySymbol>(page.getPageNum(),
                page.getPageSize(),page.getTotal(),page.getResult());
        return basePageInfo;
    }

    public void enableCur(String cur){
        CurrencySymbol symbol = new CurrencySymbol();
        symbol.setSymbol(cur);
        symbol.setStatus("Y");
        curSymbolMapper.updateCurrencySym(symbol);
        symbolRedisService.remove("need_currency_symbol");
    }

    public void forbiddenCur(String cur){
        CurrencySymbol symbol = new CurrencySymbol();
        symbol.setSymbol(cur);
        symbol.setStatus("N");
        curSymbolMapper.updateCurrencySym(symbol);
        symbolRedisService.remove("need_currency_symbol");
    }

    public List<CurSymbol> getSupportCur(){
        return curSymbolMapper.getRequestCur();
    }


}
