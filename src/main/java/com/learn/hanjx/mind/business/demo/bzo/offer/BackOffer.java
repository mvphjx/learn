package com.learn.hanjx.mind.business.demo.bzo.offer;

/**
 * 后端商品领域模型
 *
 * 领域模型
 * <p>
 * 更加贴近现实的对象模型
 * 更加清晰地还原了业务语义，同时多态也消除了原来的if-else判断
 */
public class BackOffer
{
    public boolean isCloudWarehouse()
    {
        return false;
    }

    public boolean isNonInWarehouse()
    {
        return false;
    }

    public int getStockAmount()
    {
        return 0;
    }

    public SupplierItem getSupplierItem()
    {
        return null;
    }
}
