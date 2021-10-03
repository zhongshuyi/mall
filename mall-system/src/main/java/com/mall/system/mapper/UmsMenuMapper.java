package com.mall.system.mapper;

import com.mall.common.core.domain.entity.UmsMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.system.bo.query.MenuQueryBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author 钟舒艺
 * @since 2021-09-14
 */
public interface UmsMenuMapper extends BaseMapper<UmsMenu> {

    /**
     * 查询权限列表
     *
     * @param userId 用户id
     * @return 权限列表set
     */
    Set<String> selectPermsByUserId(Long userId);

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<UmsMenu> selectMenuListByUserId(@Param("userId" ) Long userId);
}
