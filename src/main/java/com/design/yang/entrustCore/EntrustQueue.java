package com.design.yang.entrustCore;

import com.design.yang.Util.DataEqual;
import com.design.yang.dto.EntrustInfo;
import com.design.yang.dto.EntrustTransaction;

import java.util.*;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-19 13:40
 */
public class EntrustQueue {
    Double price;
    Double totalNumber = Double.valueOf(0);
    Queue<EntrustInfo> entrustInfos;
    Double volumn = Double.valueOf(0);

    public EntrustQueue(Double price) {
        this.price = price;
        entrustInfos = new LinkedList<>();
    }

    public Double getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Double totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Queue<EntrustInfo> getEntrustInfos() {
        return entrustInfos;
    }

    public void setEntrustInfos(Queue<EntrustInfo> entrustInfos) {
        this.entrustInfos = entrustInfos;
    }

    public Double getVolumn() {
        return volumn;
    }

    public void setVolumn(Double volumn) {
        this.volumn = volumn;
    }

    public EntrustQueue(){

    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean addEntrust(EntrustInfo info){
        if(!DataEqual.equal(info.getPrice(),this.price)) return false;
        this.totalNumber += info.getNumber();
        return entrustInfos.offer(info);
    }

    public List<EntrustDetail> transaction(EntrustInfo info){
        Double traPrice = info.getPrice() > this.price ? this.price : info.getPrice();
        List<EntrustDetail> details = new ArrayList<>();
        Double traNumber = info.getNumber();
        if(traNumber >= this.totalNumber){
            EntrustInfo e;
            while ((e = this.entrustInfos.poll()) != null){
                EntrustTransaction transaction = new EntrustTransaction(info.getEntrustId(),
                        e.getEntrustId(),
                        traPrice,
                        e.getNumber(),
                        new Date());
                EntrustDetail detail = new EntrustDetail(transaction,e);
                details.add(detail);
            }
            info.setNumber(traNumber - this.totalNumber);
            this.totalNumber = Double.valueOf(0);

        }else {
            while (traNumber > 0){
                EntrustInfo e = this.entrustInfos.peek();
                Double eNumber = e.getNumber();
                if(traNumber >= eNumber){
                    traNumber = traNumber - eNumber;
                    EntrustTransaction transaction = new EntrustTransaction(info.getEntrustId(),
                            e.getEntrustId(),
                            traPrice,
                            eNumber,
                            new Date());
                    EntrustDetail detail = new EntrustDetail(transaction,e);
                    details.add(detail);
                    this.entrustInfos.remove();
                }else {
                    e.setNumber(eNumber - traNumber);
                    EntrustTransaction transaction = new EntrustTransaction(info.getEntrustId(),
                            e.getEntrustId(),
                            traPrice,
                            traNumber,
                            new Date());
                    traNumber = Double.valueOf(0);
                    EntrustDetail detail = new EntrustDetail(transaction,e);
                    details.add(detail);
                }
            }
            this.totalNumber = this.totalNumber - info.getNumber();
            info.setNumber(Double.valueOf(0));
        }
        return details;
    }

//    public List<EntrustTransaction> transactionByVolumn(EntrustInfo info) {
//        Double infoPrice = info.getPrice();
//        info.setPrice(this.price);
//        Double volumn = info.getVolume();
//        info.setNumber(volumn/this.price);
//        List<EntrustTransaction> transactions = transaction(info);
//        info.setVolume(info.getNumber()*this.price);
//        info.setPrice(infoPrice);
//        return transactions;
//    }
//
//    public List<EntrustTransaction> buyMarketTransaction(EntrustInfo info) {
//
//        return null;
//    }



    public Double total(){
        return this.totalNumber;
    }

    public EntrustInfo revoke(Long id){
        Iterator<EntrustInfo> iter = entrustInfos.iterator();
        while (iter.hasNext()){
            EntrustInfo info = iter.next();
            if(info.getEntrustId() == id){
                iter.remove();
                this.totalNumber -= info.getNumber();
                return info;
            }
        }
        return null;
    }
}
