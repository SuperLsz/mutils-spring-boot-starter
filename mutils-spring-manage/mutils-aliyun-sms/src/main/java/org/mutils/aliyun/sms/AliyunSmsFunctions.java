package org.mutils.aliyun.sms;

import org.mutils.aliyun.sms.model.AliyunQueryModel;
import org.mutils.aliyun.sms.model.AliyunSendSmsModel;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.AliyunSmsConfig;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.rule.FunctionRule;

/**
 * 阿里云短信
 * 
 * @author mintonzhang
 * @date 2019年1月17日
 * @since 0.2.5
 */
public class AliyunSmsFunctions extends FunctionRule {

	private static final AliyunSmsConfig config = InitConfig.loadConfig(AliyunSmsConfig.class);

	/**
	 * 发送短信给单个用户
	 * 
	 * @param model
	 * @return
	 * @throws ServerException
	 * @throws ClientException
	 * @throws MutilsErrorException
	 */
	public static SendSmsResponse sendSingleSms(AliyunSendSmsModel model)
			throws ServerException, ClientException, MutilsErrorException {

		return initClient(1000, 1000).getAcsResponse(model.toSendSmsRequest());
	}

	/**
	 * 发送短信给多个用户
	 * 
	 * @param model 发送短信的model
	 * @return
	 * @throws ServerException
	 * @throws ClientException
	 * @throws MutilsErrorException mutils框架内自定义异常
	 */
	public static SendBatchSmsResponse sendBatchSms(AliyunSendSmsModel model)
			throws ServerException, ClientException, MutilsErrorException {

		return initClient(1000, 1000).getAcsResponse(model.toSendBatchSmsRequest());
	}

	/**
	 * 	查询某个号码的最近30天的使用情况
	 * @param model
	 * @return
	 * @throws ServerException
	 * @throws ClientException
	 */
	public static QuerySendDetailsResponse querySendDetails(AliyunQueryModel model)
			throws ServerException, ClientException {
		return initClient(1000, 1000).getAcsResponse(model.toQuerySendDetailsRequest());
	}

	protected static IAcsClient initClient(long defaultConnectTimeout, long defaultReadTimeout) {
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(defaultConnectTimeout));
		System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(defaultReadTimeout));

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", config.getAccessKeyId(),
				config.getAccessKeySecret());
		DefaultProfile.addEndpoint("cn-hangzhou", config.getProduct(), config.getDomain());
		return new DefaultAcsClient(profile);
	}
}
