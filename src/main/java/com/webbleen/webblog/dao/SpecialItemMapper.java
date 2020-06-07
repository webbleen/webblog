package com.webbleen.webblog.dao;

import com.webbleen.webblog.entity.PageDo;
import com.webbleen.webblog.entity.SpecialItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpecialItemMapper {

    List<SpecialItem> getSpecialItemList();

    List<Object> findPageDo(PageDo pageDo);

    String queryMaxCode();

    int findPageDoCount(PageDo pageDo);
}
