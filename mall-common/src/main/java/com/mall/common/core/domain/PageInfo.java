package com.mall.common.core.domain;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页信息类.
 *
 * @author 钟舒艺
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> implements Serializable {

    private static final long serialVersionUID = -4828260563069859319L;

    /**
     * 总记录数.
     */
    @ApiModelProperty("总记录数")
    private long total;

    /**
     * 列表数据.
     */
    @ApiModelProperty("列表数据")
    private transient List<T> items;
}
