package com.design.yang.controller;

import com.design.yang.BaseResponse.BaseResponse;
import com.design.yang.Util.AppSecurityContext;
import com.design.yang.constantClass.Constant;
import com.design.yang.dto.EntrustInfo;
import com.design.yang.service.EntrustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-21 14:39
 */
@RestController
@RequestMapping("/api/entrust")
public class EntrustController {
    @Autowired
    EntrustService entrustService;

    @RequestMapping("/buy")
    public BaseResponse buyEntrust(@RequestBody EntrustInfo info){
        Long id = Long.valueOf(AppSecurityContext.getUserId());
        String dir = Constant.BUY_DIRECTION;
        info.setUserId(id);
        info.setDirection(dir);
        info.setCompleted(Double.valueOf(0));
        entrustService.fixedBuy(info);
        return new BaseResponse();
    }

    @RequestMapping("/sell")
    public BaseResponse sellEntrust(@RequestBody EntrustInfo info){
        Long id = Long.valueOf(AppSecurityContext.getUserId());
        String dir = Constant.SELL_DIRECTION;
        info.setUserId(id);
        info.setDirection(dir);
        info.setCompleted(Double.valueOf(0));
        entrustService.fixedSell(info);
        return new BaseResponse();
    }

    @RequestMapping("/revoke")
    public BaseResponse revokeEntrust(@RequestBody EntrustInfo info){
        Long id = Long.valueOf(AppSecurityContext.getUserId());
        info.setUserId(id);
        entrustService.revoke(info);
        return new BaseResponse();
    }

    @GetMapping("/tra")
    public BaseResponse getAllEntByCur(HttpServletRequest request){
        String cur = request.getParameter("cur");
        Map<String, List> map =  entrustService.getSamEnt(cur);
        return new BaseResponse(map);
    }

    @RequestMapping("/current")
    public BaseResponse getCurEntrust(){
        Long id = Long.valueOf(AppSecurityContext.getUserId());
        List<EntrustInfo> infos = entrustService.getCurrentEntrust(id);
        return new BaseResponse(infos);
    }

    @RequestMapping("/history")
    public BaseResponse getHisEntrust(@RequestBody(required = false) EntrustInfo info){
        Long id = Long.valueOf(AppSecurityContext.getUserId());
        if(info == null) info = new EntrustInfo();
        info.setUserId(id);
        return new BaseResponse(entrustService.getHisEntrust(info));
    }

    public BaseResponse getDetailedTransaction(@RequestBody(required = false) EntrustInfo info){
        return null;
    }
}
