package com.design.yang.entrustCore;

import com.design.yang.Util.DataEqual;
import com.design.yang.constantClass.Constant;
import com.design.yang.dto.EntrustInfo;
import com.design.yang.dto.EntrustTransaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-19 10:58
 */
public class Entrust {
    List<EntrustQueue> buyList = new LinkedList<>();
    List<EntrustQueue> sellList = new LinkedList<>();

    public List<EntrustQueue> getBuyList() {
        return buyList;
    }

    public void setBuyList(List<EntrustQueue> buyList) {
        this.buyList = buyList;
    }

    public List<EntrustQueue> getSellList() {
        return sellList;
    }

    public void setSellList(List<EntrustQueue> sellList) {
        this.sellList = sellList;
    }

    //    EntrustQueue buyMarketQueue = new EntrustQueue(Double.MAX_VALUE);
//    EntrustQueue sellMarketQueue = new EntrustQueue(Double.MAX_VALUE);

    public List<EntrustDetail> addFixedBuy(EntrustInfo info){
        Double entrustPrice = info.getPrice();
        List<EntrustDetail> transactions = new ArrayList<>();
//        List<EntrustTransaction> mark = sellMarketQueue.transaction(info);
//        if(mark.size() > 0) transactions.addAll(mark);
        for(EntrustQueue queue : sellList){
            Double surplus = info.getNumber();
            if(queue.getPrice() > entrustPrice) break; //当买的价格小于卖时退出
            if(DataEqual.equal(surplus,Double.valueOf(0))) break;
            if(surplus <= queue.total()){
                transactions.addAll(queue.transaction(info));
                break;
            }else {
                transactions.addAll(queue.transaction(info));
            }
        }
        if(info.getNumber() > 0){
            insertBuyList(info);
        }
        return transactions;
    }

    public List<EntrustDetail> addFixedSell(EntrustInfo info){
        Double entrustPrice = info.getPrice();
        List<EntrustDetail> transactions = new ArrayList<>();
//        List<EntrustTransaction> mark = buyMarketQueue.transactionByVolumn(info);
//        if(mark.size() > 0) transactions.addAll(mark);
        for(EntrustQueue queue : buyList){
            Double surplus = info.getNumber();
            if(queue.getPrice() < entrustPrice) break; //当买的价格小于卖时退出
            if(DataEqual.equal(surplus,Double.valueOf(0))) break;
            if(surplus <= queue.total()){
                transactions.addAll(queue.transaction(info));
                break;
            }else {
                transactions.addAll(queue.transaction(info));
            }
        }
        if(info.getNumber() > 0){
            insertSellList(info);
        }
        return transactions;
    }

    public List<EntrustTransaction> addMarketBuy(EntrustInfo info){
        Double traPrice = Double.MAX_VALUE;
        info.setPrice(traPrice);
        List<EntrustTransaction> transactions = new ArrayList<>();
        for(EntrustQueue queue : sellList){
            Double surplus = info.getNumber();
            if(DataEqual.equal(surplus,Double.valueOf(0))) break;
            if(surplus <= queue.total()){
//                transactions.addAll(queue.transactionByVolumn(info));
                break;
            }else {
//                transactions.addAll(queue.transactionByVolumn(info));
            }
        }
        if(info.getNumber() > 0){
//            insertBuyQueue(info);
        }
        return transactions;
    }

    public List<EntrustTransaction> addMarketSell(EntrustInfo info){
        Double traPrice = Double.MAX_VALUE;
        info.setPrice(traPrice);
        List<EntrustTransaction> transactions = new ArrayList<>();
        for(EntrustQueue queue : buyList){
            Double surplus = info.getNumber();
            if(DataEqual.equal(surplus,Double.valueOf(0))) break;
            if(surplus <= queue.total()){
//                transactions.addAll(queue.transaction(info));
                break;
            }else {
//                transactions.addAll(queue.transaction(info));
            }
        }
        if(info.getNumber() > 0){
//            insertSellQueue(info);
        }
        return transactions;
    }

//    private void insertBuyQueue(EntrustInfo info){
//        this.buyMarketQueue.addEntrust(info);
//    }
//
//    private void insertSellQueue(EntrustInfo info){
//        this.sellMarketQueue.addEntrust(info);
//    }

    private void insertBuyList(EntrustInfo info){
        Double infoPrice = info.getPrice();
        if(this.buyList.size() == 0){
            EntrustQueue queue = new EntrustQueue(infoPrice);
            queue.addEntrust(info);
            this.buyList.add(queue);
            return;
        }
        Iterator<EntrustQueue> iter = this.buyList.iterator();
        int i = 0;
        while (iter.hasNext()){
            EntrustQueue buyQueue = iter.next();
            Double buyQueuePrice = buyQueue.getPrice();
            if(infoPrice > buyQueuePrice){
                EntrustQueue queue = new EntrustQueue(infoPrice);
                queue.addEntrust(info);
                this.buyList.add(i,queue);
                return;
            }
            if(DataEqual.equal(infoPrice,buyQueuePrice)){
                buyQueue.addEntrust(info);
                return;
            }
            i++;
        }
        EntrustQueue queue = new EntrustQueue(infoPrice);
        queue.addEntrust(info);
        this.buyList.add(queue);
    }

    private void insertSellList(EntrustInfo info){
        Double infoPrice = info.getPrice();
        if(this.sellList.size() == 0){
            EntrustQueue queue = new EntrustQueue(infoPrice);
            queue.addEntrust(info);
            this.sellList.add(queue);
            return;
        }
        Iterator<EntrustQueue> iter = this.sellList.iterator();
        int i = 0;
        while (iter.hasNext()){
            EntrustQueue sellQueue = iter.next();
            Double sellQueuePrice = sellQueue.getPrice();
            if(infoPrice < sellQueuePrice){
                EntrustQueue queue = new EntrustQueue(infoPrice);
                queue.addEntrust(info);
                this.sellList.add(i,queue);
                return;
            }
            if(DataEqual.equal(infoPrice,sellQueuePrice) ){
                sellQueue.addEntrust(info);
                return;
            }
            i++;
        }
        EntrustQueue queue = new EntrustQueue(infoPrice);
        queue.addEntrust(info);
        this.sellList.add(queue);
    }

    public EntrustInfo revokeEntrust(EntrustInfo info){
//        if(info.getCondition().equals(Constant.MARKET_TRANSACTION)){
//            EntrustQueue queue;
//            if(info.getDirection().equals(Constant.BUY_DIRECTION)){
//                queue = this.buyMarketQueue;
//            }else queue = this.sellMarketQueue;
//            return queue.revoke(info.getEntrustId());
//
//        }else {
            List<EntrustQueue> queues;
            if(info.getDirection().equals(Constant.BUY_DIRECTION)){
                queues = this.buyList;
            }else queues = this.sellList;
            for(EntrustQueue queue : queues){
                if(DataEqual.equal(queue.getPrice(),info.getPrice())){
                    return queue.revoke(info.getEntrustId());
                }
            }
            return null;
//        }
    }

    public List<SamEntrust> toBuySamEnt(){
        List<SamEntrust> samEntrusts = new ArrayList<>();
        for(EntrustQueue buy : buyList){
            if(DataEqual.equal(buy.total(),Double.valueOf(0))) continue;
            SamEntrust samEntrust = new SamEntrust(buy.getPrice(),buy.total());
            samEntrusts.add(samEntrust);
        }
        return samEntrusts;
    }

    public List<SamEntrust> toSellSamEnt(){
        List<SamEntrust> samEntrusts = new ArrayList<>();
        for(EntrustQueue buy : sellList){
            if(DataEqual.equal(buy.total(),Double.valueOf(0))) continue;
            SamEntrust samEntrust = new SamEntrust(buy.getPrice(),buy.total());
            samEntrusts.add(samEntrust);
        }
        return samEntrusts;
    }

}
