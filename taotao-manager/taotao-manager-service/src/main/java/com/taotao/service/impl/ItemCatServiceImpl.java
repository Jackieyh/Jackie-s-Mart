package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通类
 *
 * @Author:Jackie
 * @Date:Created in 2018-12-14 10:55:00
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper mapper;

    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        // 根据parentId查询分类列表
        TbItemCatExample example=new TbItemCatExample();

        // 设置查询条件
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        // 执行查询
        List<TbItemCat> list = mapper.selectByExample(example);

        // 数据转换
        List<EasyUITreeNode> resultList=new ArrayList<>();
        for (TbItemCat tbitemCat:list) {
            EasyUITreeNode node=new EasyUITreeNode();
            node.setId(tbitemCat.getId());
            node.setState(tbitemCat.getIsParent()?"closed":"open");
            node.setText(tbitemCat.getName());

            resultList.add(node);
        }
        return resultList;
    }
}
