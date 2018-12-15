package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * 接口类
 *
 * @Author:Jackie
 * @Date:Created in 2018-12-14 10:54:22
 */
public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(long parentId);
}
