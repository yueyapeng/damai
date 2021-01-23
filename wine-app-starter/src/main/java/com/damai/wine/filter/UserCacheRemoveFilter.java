package com.damai.wine.filter;

import com.damai.wine.common.ContextDataHolder;
import com.damai.wine.utils.AppUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author yueyp
 * @Date 2020-12-23 20:58
 * @Description 过滤器，请求结束后删除当前线程对应的缓存信息。
 */
@Component
@ServletComponentScan(basePackages = {"com.damai.wine.filter"})
@WebFilter(urlPatterns = "*", filterName = "userCacheRemoveFilter")
public class UserCacheRemoveFilter implements Filter {

	private static Logger logger = LoggerFactory.getLogger(UserCacheRemoveFilter.class);

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		// 请求进入前先将 ContextDataHolder 缓存的用户信息 remove
		ContextDataHolder.getInstance().remove();
		String traceId = ContextDataHolder.getInstance().getTraceId();
		if (StringUtils.isBlank(traceId)){
			traceId = UUID.randomUUID().toString().replaceAll("-", "");
			ContextDataHolder.getInstance().setTraceId(traceId);
		}
		String ip = AppUtils.getIpAddr(request);
		logger.info("{}进入系统IP地址:[{}]", traceId, ip);
		ContextDataHolder.getInstance().setIp(ip);
		ContextDataHolder.getInstance().setUrl(request.getRequestURI());
		filterChain.doFilter(request, servletResponse);
		// 请求结束后也将 ContextDataHolder 缓存的用户信息 remove
		ContextDataHolder.getInstance().remove();
	}

	@Override
	public void destroy() {

	}

}
