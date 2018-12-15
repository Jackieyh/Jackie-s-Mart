package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

/**
 * 接口类
 *
 * @Author:Jackie
 * @Date:Created in 2018-12-13 11:56:35
 */
public interface ItemService {

    TbItem getItemById(long itemId);

    EasyUIDataGridResult getItemList(int page, int rows);

    TaotaoResult createItem(TbItem item, String desc);
}
