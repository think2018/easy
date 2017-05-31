package com.cheng.core.util;

public enum SMSTypeEnum
{
	/**
	 * 未知类型短信
	 */
	UNKNOW(0x0000),
	/**
	 * 验证手机号的真实性，发送验证码
	 */
	MOBILE_VERIFY(0x0001),
	/**
	 * 一般的提示短信
	 */
	COMMON_TIPS(0x0002),
	/**
	 * 用户重置密码,新密码短信发出
	 */
	RESET_PASSWORD(0x0004),
	/**
	 * 好友推荐邀请潜在用户
	 */
	FRIEND_RECOMMEND_INVITE(0x0005),
	/**
	 * 企业平台邀请
	 */
	SJB_WEB_INVITE(0x0006);
	
	private int value;

	private SMSTypeEnum(int value)
	{
		this.value = value;
	}

	public int value()
	{
		return this.value;
	}

	public static SMSTypeEnum valueOf(int value)
	{
		switch (value)
		{
		case 0x0001:
			return MOBILE_VERIFY;
		case 0x0002:
			return COMMON_TIPS;
		case 0x0004:
			return RESET_PASSWORD;
		case 0x0005:
			return FRIEND_RECOMMEND_INVITE;
		case 0x0006:
			return SJB_WEB_INVITE;
		default:
			return UNKNOW;
		}
	}

}
