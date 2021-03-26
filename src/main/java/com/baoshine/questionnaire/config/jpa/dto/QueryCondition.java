package com.baoshine.questionnaire.config.jpa.dto;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: QueryCondition <br/>
 * Function: 查询条件 <br/>
 * Date: 2017年12月26日 下午5:02:59 <br/>
 *
 * @author Luke
 */
public interface QueryCondition extends Serializable {
	/**
	 * Convert this query condition to JPQL condition expression.
	 *
	 * @param parameters
	 *            the condition parameter
	 * @return the constructed JPQL condition expression
	 */
	String toSQL(List<Parameter> parameters);
}
