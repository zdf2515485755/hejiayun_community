package com.zdf.hejiayunweb.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zdf.internalcommon.constant.HjyCommunityConstant;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *@Description Automatic implementation of database table field assignment
 *@Author mrzhang
 *@Date 2024/4/3 01:33
 */
@Component
public class DataBaseMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createBy", String.class, HjyCommunityConstant.UPDATE_INSERT_AUTHOR);
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateBy", String.class, HjyCommunityConstant.UPDATE_INSERT_AUTHOR);
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateBy", HjyCommunityConstant.UPDATE_INSERT_AUTHOR, metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
