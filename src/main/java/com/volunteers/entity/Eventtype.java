package com.volunteers.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
@TableName("t_eventtype")
public class Eventtype implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "typeId", type = IdType.AUTO)
    private Integer typeId;

    @TableField("typeName")
    private String typeName;

    @TableField("orderNo")
    private Integer orderNo;

    public Integer getEventCount() {
        return eventCount;
    }

    public void setEventCount(Integer eventCount) {
        this.eventCount = eventCount;
    }

    @TableField(exist = false)
    private Integer eventCount;


    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "Eventtype{" +
        "typeId=" + typeId +
        ", typeName=" + typeName +
        ", orderNo=" + orderNo +
        "}";
    }
}
