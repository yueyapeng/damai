package com.damai.wine.rpcservice;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yueyp
 * @date 2020-12-15
 * @detail
 */
public class BaseService {
	/**
	 * 
	 * @author yueyp
	 * @param sourceList
	 * @param targetClazz
	 * @return
	 */
	public <E, P> List<P> listTransition(List<E> sourceList, Class<? extends P> targetClazz){
		List<P> targetList  = new ArrayList<>();
		try {
			if(null != sourceList && sourceList.size()>0) {
				for(E source : sourceList){
					P target = targetClazz.newInstance();
					BeanUtils.copyProperties(source, target);
					targetList.add(target);
				}
			}
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return targetList;
	}

	public <E, P> P copyProperties(Object source, Class<? extends P> targetClazz) {
		if(source == null){
			return null;
		}
		try {
			P target = targetClazz.newInstance();
			BeanUtils.copyProperties(source, target);
			return target;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
