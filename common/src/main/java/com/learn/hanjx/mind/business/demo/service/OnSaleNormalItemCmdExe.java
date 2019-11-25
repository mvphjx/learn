package com.learn.hanjx.mind.business.demo.service;

import com.learn.hanjx.mind.business.demo.bzo.OnSaleContext;
import com.learn.hanjx.mind.business.demo.bzo.Response;
import com.learn.hanjx.mind.business.demo.bzo.OnSaleNormalItemCmd;
import com.learn.hanjx.mind.business.demo.phase.init.OnSaleContextInitPhase;
import com.learn.hanjx.mind.business.demo.phase.check.OnSaleDataCheckPhase;
import com.learn.hanjx.mind.business.demo.phase.process.OnSaleProcessPhase;

/**
 *商品上架流程
 */
public class OnSaleNormalItemCmdExe
{
    private OnSaleContextInitPhase onSaleContextInitPhase;
    private OnSaleDataCheckPhase onSaleDataCheckPhase;
    private OnSaleProcessPhase onSaleProcessPhase;

    public Response execute(OnSaleNormalItemCmd cmd) {

        OnSaleContext onSaleContext = init(cmd);

        checkData(onSaleContext);

        process(onSaleContext);

        return Response.buildSuccess();
    }

    private OnSaleContext init(OnSaleNormalItemCmd cmd) {
        return onSaleContextInitPhase.init(cmd);
    }

    private void checkData(OnSaleContext onSaleContext) {
        onSaleDataCheckPhase.check(onSaleContext);
    }

    private void process(OnSaleContext onSaleContext) {
        onSaleProcessPhase.process(onSaleContext);
    }
}
