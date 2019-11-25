package com.learn.hanjx.mind.business.demo.controller;

import com.learn.hanjx.mind.business.demo.bzo.OnSaleNormalItemCmd;
import com.learn.hanjx.mind.business.demo.service.OnSaleNormalItemCmdExe;

public class OnSaleCtrl
{
    public static void main(String[] args)
    {
        OnSaleNormalItemCmdExe onSaleNormalItemCmdExe = new OnSaleNormalItemCmdExe();
        onSaleNormalItemCmdExe.execute(new OnSaleNormalItemCmd());
    }
}
