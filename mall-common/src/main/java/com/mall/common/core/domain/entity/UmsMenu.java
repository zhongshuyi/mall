package com.mall.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;


/**
 * 菜单表
 *
 * @author 钟舒艺
 * @since 2021-09-22
 */
@Data
@Accessors(chain = true)
public class UmsMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id" , type = IdType.AUTO)
    private Long id;

    /**
     * 父菜单id
     */
    private Long parentId;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单排序
     */
    private Integer orderNo;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 菜单名字
     */
    private String title;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 是否忽略缓存(0 忽略,1 不忽略)
     */
    private Integer ignoreKeepAlive;

    /**
     * 是否固定标签(0不固定,1固定)
     */
    private Integer affix;

    /**
     * 图标
     */
    private String icon;

    /**
     * 内嵌iframe的地址
     */
    private String frameSrc;

    /**
     * 该路由切换的动画名
     */
    private String transitionName;

    /**
     * 是否在面包屑上隐藏该路由(0隐藏,1不隐藏)
     */
    private Integer hideBreadcrumb;

    /**
     * 是否隐藏所有子菜单(0隐藏,1不隐藏)
     */
    private Integer hideChildrenInMenu;

    /**
     * 当前路由不再标签页显示 (0隐藏,1不隐藏)
     */
    private Integer hideTab;

    /**
     * 当前路由不再菜单显示 (0隐藏,1不隐藏)
     */
    private Integer hideMenu;

    /**
     * 是否是外链( 0是,1不是)
     */
    private Integer isLink;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改者
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 菜单类型( 0 目录,1 菜单,2 按钮)
     */
    private Integer menuType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态(0启用 1禁用)
     */
    private Integer status;

    /**
     * 是否是外链 0是,1不是
     */
    private Integer isFrame;


}