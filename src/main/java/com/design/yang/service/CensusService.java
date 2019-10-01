package com.design.yang.service;


import com.design.yang.Util.FormatData;
import com.design.yang.dto.CurrencyTrade;
import com.design.yang.dto.PeopleNumber;
import com.design.yang.dto.TradeNumber;
import com.design.yang.dto.TradeVolumn;
import com.design.yang.mapper.UserCensusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-05-28 15:19
 */

@Service
public class CensusService {
    @Autowired
    UserCensusMapper userCensusMapper;

    public List<PeopleNumber> newUserData(){
        List<String> months = FormatData.getMonths();
        List<PeopleNumber> peopleNumberList = userCensusMapper.censusNumber();
        List<PeopleNumber> peopleNumbers = new ArrayList<>();
        for(String month : months){
            PeopleNumber number = new PeopleNumber(0,month);
            for(int i = 0;i < peopleNumberList.size();i++){
                if(month.equals(peopleNumberList.get(i).getMonths())){
                    number.setNumber(peopleNumberList.get(i).getNumber());
                    break;
                }
            }
            peopleNumbers.add(number);
        }
        return peopleNumbers;
    }

    public List<TradeVolumn> TradeVolData(String cur){
        List<String> months = FormatData.getMonths();
        List<TradeVolumn> tradeVolumnList = userCensusMapper.censusVolumn(cur);
        List<TradeVolumn> tradeVolumns = new ArrayList<>();
        for(String month : months){
            TradeVolumn volumn = new TradeVolumn(0,month);
            for(int i = 0;i < tradeVolumnList.size();i++){
                if(month.equals(tradeVolumnList.get(i).getMonths())){
                    volumn.setVol(tradeVolumnList.get(i).getVol());
                    break;
                }
            }
            tradeVolumns.add(volumn);
        }
        return tradeVolumns;
    }

    public List<TradeNumber> censusTradeNumber(String cur , String time){
        return userCensusMapper.censusTradeNumber(cur,time);
    }

    public List<CurrencyTrade> censusCur(String time){
        return userCensusMapper.censusCur(time);
    }
}
