//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.aier.cloud.biz.service.biz.amcs.law.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;

public abstract class LawBaseEntity<T extends Model> extends Model<T> {
    private static final long serialVersionUID = 4244299905992258927L;
    private static final String ID = "id";
    @TableId(
            value = "id",
            type = IdType.ID_WORKER
    )
    private Long id;

    public LawBaseEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected Serializable pkVal() {
        return this.id;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this == obj) {
            return true;
        } else if (!LawBaseEntity.class.isAssignableFrom(obj.getClass())) {
            return false;
        } else {
            LawBaseEntity other = (LawBaseEntity)obj;
            return this.getId() != null ? this.getId().equals(other.getId()) : false;
        }
    }

    public int hashCode() {
        int hashCode = 17;
        hashCode += null == this.getId() ? 0 : this.getId().hashCode() * 31;
        return hashCode;
    }
}
