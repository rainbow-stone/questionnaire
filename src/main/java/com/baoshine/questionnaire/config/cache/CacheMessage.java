package com.baoshine.questionnaire.config.cache;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CacheMessage implements Serializable {

	private static final long serialVersionUID = -1729095957381553626L;

	private String cacheName;

	private Object key;

}