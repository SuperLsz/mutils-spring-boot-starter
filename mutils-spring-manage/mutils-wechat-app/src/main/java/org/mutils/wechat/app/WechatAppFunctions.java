package org.mutils.wechat.app;

import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.ParseException;
import org.jdom.JDOMException;
import org.mutils.wechat.app.model.AppOrderPayModel;
import org.mutils.wechat.app.model.AppRefundModel;
import org.mutils.wechat.wechatpay.core.WeChatPayFunctions;
import org.mutils.wechat.wechatpay.core.model.RefundReturnModel;

import cn.minsin.core.exception.MutilsErrorException;

/**
 * * APP 移动端的API
 * @author mintonzhang
 * @date 2019年2月12日
 * @since 0.2.0
 */
public class WechatAppFunctions extends WeChatPayFunctions {

	
	/**
	 * 创建APP支付的请求参数 APP将用其发起微信支付
	 * 
	 * @param model 下单时的包装对象
	 * @return APP能发起的请求的包装内容
	 * @throws JDOMException 
	 * @throws MutilsErrorException 
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws Exception 
	 */
	public static Map<String, String> createAppPayParamter(AppOrderPayModel model) throws ParseException, IOException, MutilsErrorException, JDOMException {
			Map<String, String> doXMLParse = createUnifiedOrder(model);
			checkMap(doXMLParse);
			SortedMap<String, String> sortMap = new TreeMap<>();
			String appId = doXMLParse.get("appid");
			String nonceStr = doXMLParse.get("nonce_str");
			String prepayid = doXMLParse.get("prepay_id");
			String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
			sortMap.put("appid", appId);
			sortMap.put("partnerid", payconfig.getPartnerId());
			sortMap.put("noncestr", nonceStr);
			sortMap.put("package", "Sign=WXPay");
			sortMap.put("timestamp", timeStamp);
			sortMap.put("prepayid", prepayid);
			sortMap.put("sign", createSign(sortMap));
			return sortMap;
	}
	
	/**
	 * 发起退款申请
	 * 
	 * @param model
	 * @return
	 * @throws MutilsErrorException
	 */
	public static RefundReturnModel createAppRefundParamter(AppRefundModel model) throws MutilsErrorException {
		return createRefundRequest(model);
	}
}
