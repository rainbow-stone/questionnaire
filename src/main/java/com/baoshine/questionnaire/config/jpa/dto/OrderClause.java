package com.baoshine.questionnaire.config.jpa.dto;

import com.baoshine.questionnaire.vo.request.SearchRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

/**
 * Represents the oder by clause.
 */
public class OrderClause implements JpaFragment {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -1565273778927207593L;

    /**
     * Represent the SQL key word 'ORDER BY'.
     */
    private static final String SQL_ORDER_BY = " ORDER BY ";

    /**
     * Order by clause template.
     */
    private static final String ORDER_BY_CLAUSE_TEMPLATE = SQL_ORDER_BY + "%1s %2s";

    /**
     * The requested sort column.If orderByExpression is used, then this field will be ignored.
     */
    private String sortColumn;

    /**
     * The requested sort order.If orderByExpression is used, then this field will be ignored.
     */
    private Sort.Direction sortOrder;

    /**
     * Order by expression.If it is used,then sort column and sort order will be ignored.
     */
    private String orderByExpression;

    /**
     * Constructor with parameters.
     *
     * @param sortColumn the sort column
     * @param sortOrder  the sort order
     */
    private OrderClause(String sortColumn, Sort.Direction sortOrder) {
        this.sortColumn = sortColumn;
        this.sortOrder = sortOrder;
    }

    /**
     * Constructor with parameters.
     *
     * @param orderByExpression the order by expression
     */
    private OrderClause(String orderByExpression) {
        this.orderByExpression = orderByExpression;
    }

    /**
     * Constructor with parameters.
     *
     * @param filter the search filter to construct the order by clause.
     */
    private OrderClause(SearchRequest filter, String rootPath) {
        this.sortColumn = filter.getSortColumn(rootPath);
        this.sortOrder = filter.getSortOrder();
        this.orderByExpression = filter.getOrderByExpression(rootPath);
    }

    /**
     * This method is used to create OrderByClause instance.
     *
     * @param sortColumn the sort column
     * @param sortOrder  the sort order
     * @return the OrderByClause instance.
     */
    public static OrderClause create(String sortColumn, Sort.Direction sortOrder) {
        return new OrderClause(sortColumn, sortOrder);
    }

    /**
     * This method is used to create OrderByClause instance.
     *
     * @param orderByExpression the order by expression
     * @return the OrderByClause instance.
     */
    public static OrderClause create(String orderByExpression) {
        return new OrderClause(orderByExpression);
    }

    /**
     * This method is used to create OrderByClause instance.
     *
     * @param filter the search filter to construct the order by clause.
     * @return the OrderByClause instance.
     */
    public static OrderClause create(SearchRequest filter, String rootPath) {
        return new OrderClause(filter, rootPath);
    }

    /**
     * This method is used to create the order by clause.
     *
     * @param column the column to sort
     * @param order  the sort order
     * @return order by SQL
     */
    private static String createOrderByClause(String column, Sort.Direction order) {
        if (StringUtils.isEmpty(column)) {
            return "";
        }
        return String.format(ORDER_BY_CLAUSE_TEMPLATE, column, order == null ? Sort.Direction.ASC : order);
    }

    /**
     * This method is used to generate the SQL script for order by clause.
     *
     * @return the order by SQL script
     */
    public String toSQL() {
        if (StringUtils.isNotEmpty(orderByExpression)) {
            return SQL_ORDER_BY + orderByExpression;
        }
        return createOrderByClause(sortColumn, sortOrder);
    }

}
