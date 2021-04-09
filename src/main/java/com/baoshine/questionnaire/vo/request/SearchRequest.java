package com.baoshine.questionnaire.vo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页查询条件")
@JsonIgnoreProperties(value = {"sortColumn", "sortOrder", "orderByExpression"}, ignoreUnknown = true)
public abstract class SearchRequest implements Serializable {

    private static final long serialVersionUID = -3383140844575115557L;

    @ApiModelProperty("当前页 从0开始")
    private int page = 0;

    @ApiModelProperty("每页条数")
    private int size = 10;

    private List<SortOrder> orders;

    public Pageable buildPageable() {
        if (!CollectionUtils.isEmpty(orders)) {
            List<Sort.Order> orderList = buildOrderList();
            return PageRequest.of(page, size, Sort.by(orderList));
        }
        return PageRequest.of(page, size);
    }

    protected List<Sort.Order> buildOrderList() {
        return orders.stream().map(tmp -> new Sort.Order(Sort.Direction.valueOf(tmp.getDirection()), tmp.getProperty()))
                .collect(Collectors.toList());
    }

    public String getSortColumn(String rootPath) {
        if (CollectionUtils.isEmpty(orders)) {
            return null;
        }
        return rootPath + "." + orders.get(0).getProperty();
    }

    public Sort.Direction getSortOrder() {
        if (CollectionUtils.isEmpty(orders)) {
            return null;
        }
        return EnumUtils.getEnum(Sort.Direction.class, orders.get(0).getDirection());
    }

    public String getOrderByExpression(String rootPath) {
        if (CollectionUtils.isEmpty(orders)) {
            return null;
        }
        List<Sort.Order> orderList = buildOrderList();
        return orderList.stream().map(order -> rootPath + "." + order.getProperty() + " " + order.getDirection())
                .collect(Collectors.joining(", "));
    }
}
