package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 商品查询service
 *
 * @Author:Jackie
 * @Date:Created in 2018-12-13 11:58:01
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper descMapper;

    @Override
    public TbItem getItemById(long itemId) {
        TbItem tbItem = null;
//        TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
        TbItemExample example = new TbItemExample();
        // 创建查询条件
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);

        List<TbItem> items = itemMapper.selectByExample(example);

        if (items != null && items.size() > 0) {
            tbItem = items.get(0);
        }
        return tbItem;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        // 执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);
        // 取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);

        // 返回处理结果
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public TaotaoResult createItem(TbItem item, String desc) {

        //生成商品id
        long itemId = IDUtils.genItemId();

        //补全TbItem属性
        item.setId(itemId);

        //'商品状态，1-正常 2-下架 3-删除'
        item.setStatus((byte) 1);

        //创建时间和更新时间
        Date date=new Date();
        item.setCreated(date);
        item.setUpdated(date);

        //插入商品表
        itemMapper.insert(item);

        //商品描述
        TbItemDesc itemDesc=new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        item.setCreated(date);
        item.setUpdated(date);
        descMapper.insert(itemDesc);

        return TaotaoResult.ok();
    }
}
