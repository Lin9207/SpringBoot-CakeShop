package com.cyan.service;

import com.cyan.mapper.GoodsMapper;
import com.cyan.mapper.GoodsrecordsMapper;
import com.cyan.mapper.RecommendMapper;
import com.cyan.pojo.Goods;
import com.cyan.pojo.Goodsrecords;
import com.cyan.pojo.Recommend;
import com.cyan.service.inteface.GoodsService;
import com.cyan.service.inteface.GoodsrecordsService;
import com.cyan.service.inteface.OrderitemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private RecommendMapper recommendMapper;
    @Resource
    private OrderitemService orderitemService;
    @Autowired
    private GoodsrecordsMapper goodsrecordsMapper;

    @Override
    @Transactional
    public int deleteByPrimaryKey(Integer id) {

        logger.info("执行删除订单-ID=" + id);
        /*订单项中存在 不能删除*/
        if (orderitemService.selectByGoodId(id).size() > 0) {
            logger.info("订单删除失败");
            return 0;
        }

        logger.info("删除相关订单项");
        /*推荐表中删除*/
        recommendMapper.deleteByGoodId(id);
        goodsMapper.deleteByPrimaryKey(id);

        logger.info("删除订单完成");
        return 1;
    }

    @Override
    public int insert(Goods record) {
        return goodsMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(Goods record) {
        return goodsMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Goods record) {
        return goodsMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(Goods record) {
        return goodsMapper.insertSelective(record);
    }

    @Override
    public Goods selectByPrimaryKey(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods record) {
        return goodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Goods record) {
        return goodsMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<Goods> list) {
        return goodsMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Goods> list) {
        return goodsMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Goods> list) {
        return goodsMapper.batchInsert(list);
    }

    @Override
    public Goods getScrollGoods() {
        // 传递推荐商品类型ID查询,返回Recommend推荐表
        logger.info("获取条幅商品");
        List<Recommend> list = recommendMapper.selectByTypeId(1);
        logger.info("获取成功");
        // 根据推荐表查询商品返回,条幅商品只有一个 所以只获取index-0的商品
        return goodsMapper.selectByRecommendId(list.get(0));
    }

    @Override
    public List<Goods> getHotGoodsList() {
        // 传递推荐商品类型ID查询,返回Recommend推荐表
        logger.info("获取热销商品");
        List<Recommend> list = recommendMapper.selectByTypeId(2);
        // 根据推荐表查询商品返回
        logger.info("获取成功");
        return goodsMapper.selectByRecommendIds(list);
    }

    @Override
    public List<Goods> getNewGoodsList() {
        // 传递推荐商品类型ID查询,返回Recommend推荐表
        logger.info("获取推荐商品");
        List<Recommend> list = recommendMapper.selectByTypeId(3);
        // 根据推荐表查询商品返回
        logger.info("获取成功");
        return goodsMapper.selectByRecommendIds(list);
    }

    @Override
    public PageInfo<Goods> getSearchGoods(String searchName, Integer pageNum, Integer pageSize) {
        logger.info("根据表字段name模糊查询商品");
        logger.info("name = " + searchName);
        /*设置分页*/
        Page<?> page = PageHelper.startPage(pageNum, pageSize, true);
        /*查询*/
        List<Goods> list = goodsMapper.getSearchGoods(searchName);
        logger.info("查询成功");
        /*返回分页查询对象*/
        return new PageInfo<Goods>(list, pageSize);
    }

    @Override
    public PageInfo<Goods> selectByTypeId(Integer typeId, Integer pageNum, Integer pageSize) {
        logger.info("根据表字段name模糊查询商品");
        logger.info("typeId = " + typeId);
        /*设置分页*/
        Page<?> page = PageHelper.startPage(pageNum, pageSize, true);
        /*查询*/
        List<Goods> list = goodsMapper.selectByTypeId(typeId);
        logger.info("查询成功");
        /*返回分页查询对象*/
        return new PageInfo<Goods>(list, pageSize);
    }

    /*根据Recommend推荐表查询 - type(0-所有 1-条幅/1-热销/2-新品)*/
    @Override
    public PageInfo<Goods> selectByRecommendType(Integer type, Integer pageNum, Integer pageSize) {
        logger.info("根据Recommend推荐表查询");
        logger.info("type = " + type);
        /*设置分页*/
        Page<?> page = PageHelper.startPage(pageNum, pageSize, true);
        /*查询*/
        List<Goods> list = goodsMapper.selectByRecommendType(type);
        for (Goods good : list) {
            good.setScroll(isScroll(good.getId()));
            good.setHot(isHot(good.getId()));
            good.setNew(isNew(good.getId()));
        }
        logger.info("查询成功");
        /*返回分页查询对象*/
        return new PageInfo<Goods>(list, pageSize);
    }

    // 判断是都为条幅商品
    @Override
    public boolean isScroll(Integer goodId) {
        Recommend recommend = recommendMapper.isRecommend(1, goodId);
        return recommend != null;
    }

    // 判断是都为热销商品
    @Override
    public boolean isHot(Integer goodId) {
        Recommend recommend = recommendMapper.isRecommend(2, goodId);
        return recommend != null;
    }

    // 判断是都为新品商品
    @Override
    public boolean isNew(Integer goodId) {
        Recommend recommend = recommendMapper.isRecommend(3, goodId);
        return recommend != null;
    }

    // 根据商品记录表 获取推荐商品
    @Override
    public List<Goods> selectBySrecords(Integer userid,Integer limitSize) {

        List<Goods> list = new ArrayList<>();

        List<Goodsrecords> goodsrecordsList = goodsrecordsMapper.selectByUserID(userid, 10);
        for (Goodsrecords gs : goodsrecordsList) {
            Goods goods = goodsMapper.selectByPrimaryKey(gs.getGoodsId());
            list.add(goods);
        }
        return list;
    }

}
