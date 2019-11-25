package com.learn.hanjx.mind.business.demo.phase.check;

import com.learn.hanjx.mind.business.demo.bzo.OnSaleContext;
import com.learn.hanjx.mind.business.demo.bzo.offer.SupplierItem;
import com.learn.hanjx.mind.business.demo.bzo.offer.BackOffer;

/**
 * 校验阶段
 */
public class OnSaleDataCheckPhase
{
    public void check(OnSaleContext onSaleContext)
    {
    }

    /**
     * 举个例子，在上架过程中，有一个校验是检查库存的，
     * 其中对于组合品（CombineBackOffer）其库存的处理会和普通品不一样。
     * 原来的代码是这么写的：
     *
     * @param supplierItem
     * @throws Exception
     */
    private void checkOld(SupplierItem supplierItem) throws Exception
    {
        boolean isCombineProduct = supplierItem.isCombProductQuote();

        // 仓库检查
        if (supplierItem.getWarehouseType() == "AliWarehouse")
        {
            // 仓库 关联检查 quote warehosue check
            if (supplierItem.getWarehouseIdList() == null && !isCombineProduct)
            {
                throw new Exception("亲，不能发布Offer，请联系仓配运营人员，建立品仓关系！");
            }
            // inventory amount check  库存金额 检查
            Long sellableAmount = 0L;
            if (!isCombineProduct)
            {
                //普通校验库存逻辑 normalBiz
                sellableAmount = 0L;
            } else
            {
                //组套商品 校验逻辑  backOfferQueryService
                sellableAmount = 0L;
            }
            if (sellableAmount < 1)
            {
                throw new Exception("亲，实仓库存必须大于0才能发布，请确认已补货.\r[id:" + supplierItem.getId() + "]");
            }
        }
    }

    /**
     * 如果我们在系统中引入领域模型之后，其代码会简化为如下：
     *
     * @param supplierItem
     * @throws Exception
     */
    private void checkNew(SupplierItem supplierItem) throws Exception
    {
        //领域模型
        BackOffer backOffer = new BackOffer();
        if (backOffer.isCloudWarehouse())
        {
            return;
        }

        if (backOffer.isNonInWarehouse())
        {
            throw new Exception("亲，不能发布Offer，请联系仓配运营人员，建立品仓关系！");
        }

        if (backOffer.getStockAmount() < 1)
        {
            throw new Exception("亲，实仓库存必须大于0才能发布，请确认已补货.\r[id:" + backOffer.getSupplierItem() + "]");
        }


    }
}
