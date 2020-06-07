package com.webbleen.webblog.controller;

import com.webbleen.webblog.dao.SpecialItemMapper;
import com.webbleen.webblog.entity.PageDo;
import com.webbleen.webblog.entity.SpecialItem;
import com.webbleen.webblog.response.SingleResponse;
import com.webbleen.webblog.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpecialItemController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SpecialItemMapper specialItemMapper;

    @RequestMapping("specialItems")
    public Object specialItems(Integer limit, Integer offset) {
        PageDo pageDo = new PageDo(limit, offset);
        logger.info("limit:" + pageDo.getLimit() + ", offset:" + pageDo.getOffset());
        pageDo.setRows(specialItemMapper.findPageDo(pageDo));
        pageDo.setTotal(specialItemMapper.findPageDoCount(pageDo));
        return pageDo;
    }

    @GetMapping(value = "/specialItem/maxCode")
    public Object queryCode() {
        String maxCode = specialItemMapper.queryMaxCode();
        if (StringUtils.isBlank(maxCode)) {
            maxCode = DateUtils.getTime4yyyyMMdd() + "-001";
        } else {
            String dateStr = maxCode.substring(0, maxCode.indexOf("-"));
            if (DateUtils.getTime4yyyyMMdd().equals(dateStr)) {
                String tempStr = maxCode.substring(maxCode.indexOf("-") + 1, maxCode.length());
                Integer temp = Integer.parseInt(tempStr);
                temp++;
                String code = temp < 999 ? (temp < 10 ? ("00" + temp) : (temp < 100 ? "0" + temp : "" + temp)) : temp + "";
                maxCode = DateUtils.getTime4yyyyMMdd() + "-" + code;
            } else {
                maxCode = DateUtils.getTime4yyyyMMdd() + "-001";
            }
        }
        return new SingleResponse(maxCode);
    }

    @PostMapping("specialItems")
    public Object add(@RequestBody SpecialItem specialItem) {
        if (specialItem != null) {
            specialItem.setStatus("1");
        }
        return null;
    }
}
