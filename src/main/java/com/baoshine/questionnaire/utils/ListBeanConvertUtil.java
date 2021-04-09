package com.baoshine.questionnaire.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * beanList的转换器
 */
@Slf4j
public class ListBeanConvertUtil {

    public static <T, V> T convert(V v, Class<T> classT) {
        if (v == null) {
            return null;
        }
        T t;
        try {
            t = classT.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("ListBeanConvertUtil build new instance failed", e);
            return null;
        }
        BeanUtils.copyProperties(v, t);
        return t;
    }

    public static <T, V> List<T> convert(List<V> vList, Class<T> classT) {
        if (CollectionUtils.isEmpty(vList)) {
            return Collections.emptyList();
        }
        return vList.stream().map(e ->
                convert(e, classT)
        ).collect(Collectors.toList());
    }

    public static <T, V> T convert(V v, Class<T> classT, String... ignoreProperties) {
        if (v == null) {
            return null;
        }
        T t = null;
        try {
            t = classT.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("ListBeanConvertUtil build new instance failed", e);
            return null;
        }
        BeanUtils.copyProperties(v, t, ignoreProperties);
        return t;
    }

    public static <T, V> List<T> convert(List<V> vList, Class<T> classT, String... ignoreProperties) {
        return vList.stream().map(e ->
                convert(e, classT, ignoreProperties)
        ).collect(Collectors.toList());
    }

    public static <T, V> T convertForOptions(V v, Class<T> classT, CopyOptions copyOptions) {
        if (v == null) {
            return null;
        }
        T t = null;
        try {
            t = classT.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("ListBeanConvertUtil build new instance failed", e);
            return null;
        }
        BeanUtil.copyProperties(v, t, CopyOptions.create().setIgnoreNullValue(true));
        return t;
    }

    public static <T, V> Page<T> convertPage(Page<V> vPage, Class<T> classT, String... ignoreProperties) {
        List<T> content;
        if (vPage.hasContent()) {
            content = ListBeanConvertUtil
                    .convert(vPage.getContent(), classT, ignoreProperties);
        } else {
            content = Collections.emptyList();
        }
        return new PageImpl<>(content, vPage.getPageable(), vPage.getTotalElements());
    }
}
