package com.design.yang.entrustCore;

import com.design.yang.dto.EntrustInfo;
import com.design.yang.dto.EntrustTransaction;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-20 18:58
 */
public class EntrustDetail {
    EntrustTransaction transaction;
    EntrustInfo info;

    public EntrustDetail(EntrustTransaction transaction, EntrustInfo info) {
        this.transaction = transaction;
        this.info = info;
    }

    public EntrustDetail() {
    }

    public EntrustTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(EntrustTransaction transaction) {
        this.transaction = transaction;
    }

    public EntrustInfo getInfo() {
        return info;
    }

    public void setInfo(EntrustInfo info) {
        this.info = info;
    }
}
