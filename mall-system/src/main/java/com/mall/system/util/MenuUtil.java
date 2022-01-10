package com.mall.system.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.mall.system.entity.UmsMenu;
import com.mall.system.vo.RouterMeta;
import com.mall.system.vo.RouterVo;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * 菜单构建工具类.
 *
 * @author 钟舒艺
 **/
@Slf4j
public final class MenuUtil {

    private MenuUtil() {
    }

    /**
     * 获取处理好后的路由.
     *
     * @param menus 从数据库中直接查出来的菜单信息
     * @return 处理好的
     */
    public static List<RouterVo> getRouter(final List<UmsMenu> menus) {
        return setRedirect(buildRouters(menus, 0L));
    }

    /**
     * 构建菜单树.
     *
     * @param menus 菜单列表
     * @return 处理后菜单树
     */
    public static List<Tree<Long>> getMenuList(final List<UmsMenu> menus) {
        final TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("order");
        treeNodeConfig.setNameKey("title");
        return TreeUtil.build(menus, 0L, treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getOrderNo());
            tree.setName(treeNode.getTitle());
            tree.putAll(BeanUtil.beanToMap(treeNode));
        });
    }

    /**
     * 构建权限树结构.
     *
     * @param menus 菜单
     * @return 树结构
     */
    public static List<Tree<Long>> buildPermTree(final List<UmsMenu> menus) {
        final TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("order");
        return TreeUtil.build(menus, 0L, treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getOrderNo());
            tree.setName(treeNode.getTitle());
        });
    }


    /**
     * 构建路由树.
     *
     * @param menus    菜单
     * @param parentId 父id
     * @return 菜单树
     */
    private static List<RouterVo> buildRouters(final List<UmsMenu> menus, final Long parentId) {

        final LinkedList<RouterVo> routers = new LinkedList<>();

        if (menus == null || menus.isEmpty()) {
            return Collections.emptyList();
        }

        for (final UmsMenu menu : menus) {
            if (!menu.getParentId().equals(parentId)) {
                continue;
            }
            final RouterVo router = BeanUtil.toBean(menu, RouterVo.class);
            router.setName(router.getPath().substring(0, 1).toUpperCase() + router.getPath().substring(1));
            if (menu.getParentId().equals(0L)) {
                router.setPath("/" + router.getPath());
            }
            final RouterMeta meta = BeanUtil.toBean(menu, RouterMeta.class);

            meta.setIgnoreKeepAlive(menu.getIgnoreKeepAlive().equals(0));
            meta.setAffix(menu.getAffix().equals(0));
            meta.setHideBreadcrumb(menu.getHideBreadcrumb().equals(0));
            meta.setHideChildrenInMenu(menu.getHideChildrenInMenu().equals(0));
            meta.setHideTab(menu.getHideTab().equals(0));
            meta.setHideMenu(menu.getHideMenu().equals(0));
            router.setMeta(meta);

            router.setChildren(buildRouters(menus, menu.getId()));
            if (CollUtil.isEmpty(router.getChildren())) {
                router.setChildren(null);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 设置重定向路由.
     *
     * @param routers 路由
     * @return 设置好后的路由
     */
    private static List<RouterVo> setRedirect(final List<RouterVo> routers) {
        for (final RouterVo router : routers) {
            final StringBuilder redirect = new StringBuilder(router.getPath());
            RouterVo i = router;
            while (CollUtil.isNotEmpty(i.getChildren())) {
                i = i.getChildren().get(0);
                redirect.append("/").append(i.getPath());
            }
            if (CollUtil.isNotEmpty(router.getChildren())) {
                router.setRedirect(redirect.toString());
            }
        }
        return routers;
    }
}
