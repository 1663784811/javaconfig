package cn.cyyaw.config.admin.service.impl;

import cn.cyyaw.config.admin.service.GGoodsService;
import cn.cyyaw.config.table.table.dao.goods.GDetailsDao;
import cn.cyyaw.config.table.table.dao.goods.GGoodsDao;
import cn.cyyaw.config.table.table.dao.goods.GPhotoDao;
import cn.cyyaw.config.table.table.dao.goods.GSkuDao;
import cn.cyyaw.config.table.table.entity.goods.GDetails;
import cn.cyyaw.config.table.table.entity.goods.GGoods;
import cn.cyyaw.config.table.table.entity.goods.GPhoto;
import cn.cyyaw.config.table.table.entity.goods.GSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GGoodsServiceImpl implements GGoodsService {


    @Autowired
    private GGoodsDao gGoodsDao;


    @Autowired
    private GPhotoDao gPhotoDao;

    @Autowired
    private GSkuDao gSkuDao;

    @Autowired
    private GDetailsDao gDetailsDao;
    /**
     * 保存商品
     *
     * @param gGoods
     * @param photoList
     * @param skuList
     * @param gDetails
     * @return
     */
    @Override
    public GGoods saveGoods(GGoods gGoods, List<GPhoto> photoList, List<GSku> skuList, GDetails gDetails) {
        // === 第一步: 保存商品数据
        GGoods goods = gGoodsDao.save(gGoods);
        String goodsTid = goods.getTid();
        // === 第二步: 保存图片列表
        for(int i=0;i<photoList.size();i++){
            GPhoto gPhoto = photoList.get(i);
            if(null == gPhoto.getCreatetime()){
                gPhoto.setCreatetime(new Date());
            }
            gPhoto.setGoodsid(goodsTid);
            gPhotoDao.save(gPhoto);
        }
        //==== 第三步：保存sku
        for(int i=0;i<skuList.size();i++){
            GSku sku = skuList.get(i);
            if(null == sku.getCreatetime()){
                sku.setCreatetime(new Date());
            }
            sku.setGoodsid(goodsTid);
            gSkuDao.save(sku);
        }
        //==== 第四步：保存商品详情
        if(null == gDetails.getCreatetime()){
            gDetails.setCreatetime(new Date());
        }
        gDetailsDao.save(gDetails);
        return goods;
    }


}
