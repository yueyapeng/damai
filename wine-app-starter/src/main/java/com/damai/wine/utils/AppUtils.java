package com.damai.wine.utils;

import com.damai.wine.common.ContextDataHolder;
import com.damai.wine.common.ResponseResultCode;
import com.damai.wine.common.constants.ConfigConstants;
import com.damai.wine.exception.WineException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author yueyp
 */
public class AppUtils {

    private static Logger logger = LoggerFactory.getLogger(AppUtils.class);

    /**
     * 获取真实的IP地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(ip) && ip.contains(",")) {
            String[] ips = ip.split(",");
            ip = ips[ips.length - 1];
        }
        return org.apache.commons.lang3.StringUtils.isBlank(ip) ? "" : ip.trim();
    }


    public static void writeJson(HttpServletResponse response, String json) throws WineException {
        String traceId = ContextDataHolder.getInstance().getTraceId();
        logger.info("{}返回结果信息:{}", traceId, json);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        setHeaderAttribute(response);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(json);
        } catch (Exception e) {
            logger.warn("{}write json io 异常", traceId, e);
            throw new WineException(ResponseResultCode.FAILED);
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }

    }

    public static void setHeaderAttribute(HttpServletResponse response) {
//        response.setHeader(AppConstant.VERSION, ContextDataHolder.getInstance().getVersion());
        response.setHeader(ConfigConstants.TRACEID, ContextDataHolder.getInstance().getTraceId());
        response.setHeader(ConfigConstants.TOKEN_FILE, ContextDataHolder.getInstance().getToken());
//        response.setHeader(AppConstant.IS_CRYPT, ContextDataHolder.getInstance().getCrypt());
//        response.setHeader(AppConstant.REQUEST_NO, ContextDataHolder.getInstance().getRequestNo());
    }


    /*public static String readReqContent(HttpServletRequest request) throws WineException {
        String traceId = ContextDataHolder.getInstance().getTraceId();

        ByteArrayOutputStream ms = null;
        String respText = request.getQueryString();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(respText)) {
            logger.warn("{}不支持的请求方式，请用POST方式请求，并且请求内容需经过url encode,编码方式为utf-8", traceId);
            throw new WineException(ResultCode.REQUEST_METHOD_ERROR);
        }
        try {
            InputStream resStream = request.getInputStream();
            ms = new ByteArrayOutputStream();
            byte[] buf = new byte[4096];
            int count;
            while ((count = resStream.read(buf, 0, buf.length)) > 0) {
                ms.write(buf, 0, count);
            }
            resStream.close();
            respText = new String(ms.toByteArray(), AppConstant.CODE_FORMAT);
        } catch (IOException e) {
            logger.error("{}获取请求数据流异常", traceId, e);
            throw new WineException(SysErrorCode.REQUEST_METHOD_ERROR);
        }
        if (org.apache.commons.lang3.StringUtils.isBlank(respText)) {
            logger.warn("{}请求内容不能为空", traceId);
            throw new WineException(SysErrorCode.REQUEST_BODY_FORMAT_ERROR);
        }
        logger.info("{}请求参数：{}", traceId, respText);
        String decoder;
        try {
            decoder = URLDecoder.decode(respText, AppConstant.CODE_FORMAT);
        } catch (UnsupportedEncodingException e) {
            logger.warn("{}url decode 异常：", traceId, e);
            throw new WineException(SysErrorCode.REQUEST_BODY_FORMAT_ERROR);
        }
        logger.info("{} decode后参数:{}", traceId, decoder);

        return decoder;
    }*/
}
