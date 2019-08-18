package com.learn.hanjx.mind.business.demo.phase.process;

import com.learn.hanjx.mind.business.demo.bzo.OnSaleContext;
import com.learn.hanjx.mind.business.demo.bzo.offer.SupplierItem;

/**
 * 执行阶段
 */
public class OnSaleProcessPhase
{

    private PublishOfferStep publishOfferStep;
    private BackOfferBindStep backOfferBindStep;
    //省略其它step

    public void process(OnSaleContext onSaleContext){
        SupplierItem supplierItem = onSaleContext.getSupplierItem();

        // 生成OfferGroupNo
        //generateOfferGroupNo(supplierItem);

        // 发布商品
        //publishOffer(supplierItem);

        // 前后端库存绑定 backoffer域
        //bindBackOfferStock(supplierItem);

        // 同步库存路由 backoffer域
        //syncStockRoute(supplierItem);

        // 设置虚拟商品拓展字段
        //setVirtualProductExtension(supplierItem);

        // 发货保障打标 offer域
        //markSendProtection(supplierItem);

        // 记录变更内容ChangeDetail
        //recordChangeDetail(supplierItem);

        // 同步供货价到BackOffer
        //syncSupplyPriceToBackOffer(supplierItem);

        // 如果是组合商品打标，写扩展信息
        //setCombineProductExtension(supplierItem);

        // 去售罄标
        //removeSellOutTag(offerId);

        // 发送领域事件
        //fireDomainEvent(supplierItem);

        // 关闭关联的待办事项
        //closeIssues(supplierItem);
    }
}
