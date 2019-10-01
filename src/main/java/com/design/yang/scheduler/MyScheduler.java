package com.design.yang.scheduler;

import com.design.yang.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-17 21:32
 */
@Component
public class MyScheduler {
    @Autowired
    CurrencyService currencyService;
    @Scheduled(fixedRate = 60000)
    public void currencyDetailTask(){
        currencyService.getDetailFromWeb();
        currencyService.getRateFromWeb();
    }

}
