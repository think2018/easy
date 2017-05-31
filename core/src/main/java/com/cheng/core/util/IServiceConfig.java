/**
 * ID: IServiceConfig.java
 * Copyright (c) 2002-2013 Luther Inc.
 * http://xluther.com
 * All rights reserved.
 */
package com.cheng.core.util;

/**
 * 服务定义
 * 
 * @author Kama Luther
 * @version 0.1
 * @since 0.1
 * @.createdate 2014-4-23 下午02:41:56
 * @.modifydate 2014-4-23 下午02:41:56 <DT><B>修改历史记录</B>
 *              <DD>
 * 
 *              </DD></DT>
 */
public interface IServiceConfig
{

	// ----------------------------------------------------------------
	// 错误代码定义
	// ----------------------------------------------------------------

	public static final String ERR_USER_ACCOUNT_INVALID = "USER_ACCOUNT_INVALID";
	public static final String ERR_USER_PASSWORD_INVALID = "USER_PASSWORD_INVALID";
	public static final String ERR_USER_STATE_INVALID = "USER_STATE_INVALID";
	public static final String ERR_USER_HAS_LOGONED = "USER_HAS_LOGONED";
	public static final String ERR_USER_UID_EXISTED = "USER_UID_EXISTED";
	public static final String ERR_USER_UID_RESERVED = "USER_UID_RESERVED";
	public static final String ERR_USER_MOBILE_EXISTED = "USER_MOBILE_EXISTED";
	public static final String ERR_USER_EMAIL_EXISTED = "USER_EMAIL_EXISTED";
	public static final String ERR_USER_NO_POWER = "USER_NO_POWER";

	public static final String ERR_APP_NAME_EXISTED = "APP_NAME_EXISTED";
	public static final String ERR_APP_SERVICE_EXISTED = "APP_SERVICE_EXISTED";
	public static final String ERR_APP_SERVICE_INVALID = "APP_SERVICE_INVALID";
	public static final String ERR_APP_MODULE_EXISTED = "APP_MODULE_EXISTED";
	public static final String ERR_APP_MODULE_INVALID = "APP_MODULE_INVALID";
	public static final String ERR_APP_VERSION_EXISTED = "APP_VERSION_EXISTED";
	public static final String ERR_APP_VERSION_INVALID = "APP_VERSION_INVALID";
	public static final String ERR_APP_VERSION_NOLAST = "APP_VERSION_NOLAST";
	public static final String ERR_APP_ADVERT_EXISTED = "APP_ADVERT_EXISTED";
	public static final String ERR_APP_ADVERT_NOLAST = "APP_ADVERT_NOLAST";

	public static final String ERR_COMPANY_NO_MEMBER = "COMPANY_NO_MEMBER";
	public static final String ERR_USER_TYPE_INVALID = "USER_TYPE_INVALID";
	public static final String ERR_USER_RANK_INVALID = "USER_RANK_INVALID";

	public static final String ERR_GROUP_HAS_BINDAPP = "GROUP_HAS_BINDAPP";
	public static final String ERR_NOT_FIREND="NOT_FIREND";

	public static final String ERR_DEVICE_CODE_EXISTED = "DEVICE_CODE_EXISTED";
	public static final String ERR_PERIPHERAL_CODE_EXISTED = "PERIPHERAL_CODE_EXISTED";

	public static final String ERR_VEHICLE_CODE_EXISTED = "VEHICLE_CODE_EXISTED";
	public static final String ERR_PLATE_CODE_EXISTED = "PLATE_CODE_EXISTED";

	public static final String ERR_IDCODE_INVALID = "IDCODE_INVALID";
	public static final String ERR_CALENDAR_FAILED = "CALENDAR_FAILED";

	public static final String ERR_LBS_REGION_NOT_EXISTS = "LBS_REGION_NOT_EXISTS";
	public static final String ERR_LBS_FAILED = "LBS_FAILED";

	public static final String ERR_CREATE_DB_FAILED = "CREATE_DB_FAILED";
	public static final String ERR_CREATE_TABLE_FAILED = "CREATE_TABLE_FAILED";
	public static final String ERR_INSERT_DATA_FAILED = "INSERT_DATA_FAILED";
	public static final String ERR_UPDATE_DATA_FAILED = "UPDATE_DATA_FAILED";
	public static final String ERR_DELETE_DATA_FAILED = "DELETE_DATA_FAILED";
	public static final String ERR_QUERY_DATA_FAILED = "QUERY_DATA_FAILED";
	public static final String ERR_DATA_NOT_EXISTS = "DATA_NOT_EXISTS";

	public static final String ERR_COMMENT_NOT_EXISTS = "COMMENT_NOT_EXISTS";

	public static final String ERR_PARAM_FORMAT_ERROR = "PARAM_FORMAT_ERROR";

	// ----------------------------------------------------------------

	public static final String DEFAULT_DEVICE_NUM = "DEFAULT_DEVICE_NUM";

	// ----------------------------------------------------------------

	public static final int CODE_USER_KICKOFF = 0x001;
	public static final int CODE_SYSTEM_OPERATE = 0x002;
	public static final String MSG_USER_KICKOFF = "用户在其他终端强制登录.";
	public static final String MSG_SYSTEM_OPERATE = "系统升级操作.";

	// ----------------------------------------------------------------
	//
	// ----------------------------------------------------------------

	// 用户类别
	/**
	 * 于2015年10月9日过期
	 * 新的用法参见用户实体User中枚举UserType
	 */
	@Deprecated
	public static final int USER_TYPE_DRIVER = 1; // 司机用户
	/**
	 * 于2015年10月9日过期
	 * 新的用法参见用户实体User中枚举UserType
	 */
	@Deprecated
	public static final int USER_TYPE_COMPANY = 2; // 企业用户
	/**
	 * 于2015年10月9日过期
	 * 新的用法参见用户实体User中枚举UserType
	 */
	@Deprecated
	public static final int USER_TYPE_MANAGER_COMMON = 8; // 一般管理员(客服人员)
	/**
	 * 于2015年10月9日过期
	 * 新的用法参见用户实体User中枚举UserType
	 */
	@Deprecated
	public static final int USER_TYPE_MANAGER_SUPPER = 9; // 超级管理员

	// 用户状态
	/**
	 * 于2015年10月9日过期
	 * 新的用法参见用户实体User中枚举UserState
	 */
	@Deprecated
	public static final int USER_STATE_DESTORY = 0; // 初始
	/**
	 * 于2015年10月9日过期
	 * 新的用法参见用户实体User中枚举UserState
	 */
	@Deprecated
	public static final int USER_STATE_RUNNING = 1; // 正常
	/**
	 * 于2015年10月9日过期
	 * 新的用法参见用户实体User中枚举UserState
	 */
	@Deprecated
	public static final int USER_STATE_PAUSING = 2; // 暂停

	// 群组成员状态
	public static final int GROUP_MEMBER_STATE_DESTORY = 1; // 已经退出(成员自己退出)
	public static final int GROUP_MEMBER_STATE_ABANDON = 3; // 已经退出(被管理员踢出)
	public static final int GROUP_MEMBER_STATE_RUNNING = 2; // 已加入(正常状态)
	public static final int GROUP_MEMBER_STATE_APPROVALING = 11; // 待加入-已申请(成员已申请加入,待管理员审批)
	public static final int GROUP_MEMBER_STATE_CONFIRMING = 12; // 待加入-已邀请(管理员已邀请加入，待成员确认)
	public static final int GROUP_MEMBER_STATE_DISAPPROVAL = 21; // 已拒绝(成员已申请加入,管理员拒绝)
	public static final int GROUP_MEMBER_STATE_DISCONFIRM = 22; // 已拒绝(管理员已邀请加入，成员拒绝)

	public static final int GROUP_APPLY_GROUP = 30; // 企业A申请与企业B合作,企业A的状态
	public static final int GROUP_BE_APPLY_GROUP = 31; // 企业A申请与企业B合作,企业B的状态
	public static final int GROUP_FEFUSE_GROUP = 32; // 企业A申请与企业B合作,B拒绝,B的状态
	public static final int GROUP_BE_FEFUSE_GROUP = 33; // 企业A申请与企业B合作,被B拒绝,A的状态
	public static final int GROUP_COOPERATE_GROUP = 34; // 企业与企业之间达成合作关系
	public static final int GROUP_REMOVE_GROUP = 35; // 企业A解除与企业B之间的关系,A的状态
	public static final int GROUP_BE_REMOVE_GROUP = 36; // 企业A解除与企业B之间的关系,B的状态

	// 应用种类
	public static final int APP_KIND_SELF = 1; // 自有应用
	public static final int APP_KIND_THIRD = 2; // 第三方应用

	// 应用类别
	public static final int APP_TYPE_PC = 1; // 桌面应用
	public static final int APP_TYPE_WEB = 2; // 网页应用
	public static final int APP_TYPE_AND = 3; // 移动之Android应用
	public static final int APP_TYPE_IOS = 4; // 移动之iOS应用
	public static final int APP_TYPE_WM = 5; // 移动之WindowMobile应用
	public static final int APP_TYPE_H5 = 6; // 默认的群组应用

	// 应用状态
	public static final int APP_STATE_NULL = 0; // 待审批
	public static final int APP_STATE_RUNNING = 1; // 已开通
	public static final int APP_STATE_SUSPEND = 2; // 挂起

	// 应用配置-服务状态
	public static final int SERVICE_STATE_NULL = 0; // 未开通
	public static final int SERVICE_STATE_RUNNING = 1; // 已开通
	public static final int SERVICE_STATE_SUSPEND = 2; // 挂起

	// 应用配置-配置类型
	public static final int MODULE_TYPE_NULL = 0; // 未知
	public static final int MODULE_TYPE_VIEW = 1; // 跳转
	public static final int MODULE_TYPE_CLICK = 2; // 触发

	// 应用配置-配置类别
	public static final int MODULE_KIND_NULL = 0; // 未知
	public static final int MODULE_KIND_USER = 1; // 用户端
	public static final int MODULE_KIND_APP = 2; // 应用端

	// 行政区划级别
	public static final String REGION_LEVEL_C1 = "c0"; // 省级
	public static final String REGION_LEVEL_D1 = "d1"; //
	public static final String REGION_LEVEL_D2 = "d2"; //
	public static final String REGION_LEVEL_D3 = "d3"; //
	public static final String REGION_LEVEL_D4 = "d4"; //
	public static final String REGION_LEVEL_X0 = "x0"; //
	public static final String REGION_LEVEL_X1 = "x1"; //

	// 车辆类别
	public static final int VEHICLE_TYPE_NULL = 0; // 未知
	public static final int VEHICLE_TYPE_OWN = 1; // 自有
	public static final int VEHICLE_TYPE_JOIN = 2; // 加盟
	public static final int VEHICLE_TYPE_EXT = 3; // 外部

	// 设备类别
	public static final int DEVICE_TYPE_NULL = 0; // 未知
	public static final int DEVICE_TYPE_VEHICLE = 1; // 冷链设备
	public static final int DEVICE_TYPE_ZONE = 2; // 冷库设备
	public static final int DEVICE_TYPE_PC = 3; // 电脑设备
	public static final int DEVICE_TYPE_MOBILE = 4; // 移动设备(Android)
	public static final int DEVICE_TYPE_MOBILE_IOS = 5; // 移动设备(IOS)
	public static final int DEVICE_TYPE_WEBKIT = 6; // 移动终端内嵌网页

	// 位置类型
	public static final String LOCATE_TYPE_DEVICE = "device"; // 设备
	// (具体取值见上述设备类型)
	public static final String LOCATE_TYPE_REGION = "region"; // 地区
	public static final String LOCATE_TYPE_COMPANY = "company"; // 企业

	// 告警类别
	public static final int ALERT_TYPE_NULL = 0; // 未知
	public static final int ALERT_TYPE_TEMP = 1; // 温度异常

	// 提醒类别
	public static final int REMIND_TYPE_NULL = 0; // 未知
	public static final int REMIND_TYPE_DRIVE = 11; // 驾驶证到期

	// 外设类型
	public static final int PERIPHERAL_TYPE_NULL = 0; // 未知
	public static final int PERIPHERAL_TYPE_GPS = 1; // GPS
	public static final int PERIPHERAL_TYPE_TEMP = 2; // 温度传感器
	public static final int PERIPHERAL_TYPE_GATE = 3; // 门径

	// 货源类别
	public static final int STOCK_TYPE_NULL = 0; // 未知
	public static final int STOCK_TYPE_ZONE = 1; // 市内配送
	public static final int STOCK_TYPE_REGION = 2; // 干线配送

	// 验证类型
	public static final int CERTIFY_TYPE_USER_CONFIRM = 1; // 用户验证: 用户添加用户
	public static final int CERTIFY_TYPE_GROUP_CONFIRM = 2; // 群组验证:
	// 用户请求加入到群组,或,群组邀请用户加入
	public static final int CERTIFY_TYPE_USER_CERTIFY = 11; // 用户认证:
	// 用户通过邮箱、手机或其他信息进行身份认证
	public static final int CERTIFY_TYPE_GROUP_CERTIFY = 12; // 群组认证:
	// 群组通过上传资料等方式进行实名认证

	// 验证类型
	public static final int CERTIFY_VALUE_INITING = 1;
	public static final int CERTIFY_VALUE_RUNNING = 2;

	// 群组类别
	public static final int GROUP_TYPE_NULL = 0; // 未知
	public static final int GROUP_TYPE_COMPANY = 1; // 配货圈
	public static final int GROUP_TYPE_DIALOG = 2; // 会话

	// 平台服务
	public static final String APP_SERVICE_NAME_CLOADFILE = "cloadfile";
	public static final String APP_SERVICE_NAME_CLOADDBMS = "cloaddbms";
	public static final String APP_SERVICE_NAME_SUPER = "super";

	//
	public static final int USER_RANK_LEVEL_ONE = 5;
	public static final int USER_RANK_LEVEL_TWO = 10;

	//
	public final static byte GEO_WGS84 = 0x01; // World Geodetic System (WGS84)
	public final static byte GEO_GCJ02 = 0x02; // Mars Geodetic System (GCJ02)
	public final static byte GEO_BD09 = 0x03; // Baidu Geodetic System (BD09)

	// 统计类别
	public static final String STATIC_TYPE_STOCKQUERY = "stockquery"; // 查询货源

	// ----------------------------------------------------------------
	// 编码生成定义
	// ----------------------------------------------------------------

	public static final int GK_APP_CODE_LENGTH = 6; // 应用编码长度
	public static final int GK_USER_CODE_LENGTH = 12; // 用户编码长度
	public static final int GK_COMP_CODE_LENGTH = 10; // 企业编码长度
	public static final int GK_GROUP_CODE_LENGTH = 6; // 群组编码长度
	public static final int GK_ALERTRULE_CODE_LENGTH = 6; // 告警规则编码长度
	public static final int GK_ORDER_NUMBER_LENGTH = 16; // 业务订单号长度

	public static final String GK_APP_NAME = "max.app.id";
	public static final String GK_USER_NAME = "max.user.id";
	public static final String GK_COMP_NAME = "max.company.id";
	public static final String GK_GROUP_NAME = "max.group.id";
	public static final String GK_ALERTRULE_NAME = "max.alertrule.id";

	// ----------------------------------------------------------------
	//
	// ----------------------------------------------------------------

	// 数据标签
	public static final String DB_TAG_NAME_USER = "cur.user.tag";
	public static final String DB_TAG_NAME_COMPANY = "cur.company.tag";
	public static final String DB_TAG_NAME_VEHICLE = "cur.vehicle.tag";
	public static final String DB_TAG_NAME_DEVICE = "cur.device.tag";

	// ----------------------------------------------------------------
	//
	// ----------------------------------------------------------------

	public static final int TAG_TYPE_DBOP = 1; // 数据操作
	public static final int TAG_TYPE_MAXID = 2; // 最大键值

	// ----------------------------------------------------------------
	// 缓存对象定义
	// ----------------------------------------------------------------

	public static final String HC_NAME_MAP_HEARTBEAT = "system.map.heartbeat"; // 用户心跳
	public static final String HC_NAME_MAP_SESSION = "system.map.session"; // 会话
	public static final String HC_NAME_MAP_KEY4GEN = "system.map.key4gen"; // 键值生成器
	public static final String HC_NAME_MAP_APPCOUNT = "system.map.appcount"; // 请求次数
	public static final String HC_NAME_MAP_APPSTAMP = "system.map.appstamp"; // 请求时间

	public static final String HC_NAME_MAP_DICTTYPE = "system.map.dicttype";
	public static final String HC_NAME_MAP_DICTITEM = "system.map.dictitem";
	public static final String HC_NAME_MAP_REGION = "system.map.region";
	public static final String HC_NAME_MAP_CAR_CODE = "system.map.car.code";
	public static final String HC_NAME_SET_LOG = "system.map.log";
	public static final String HC_NAME_MAP_USER = "system.map.user";
	public static final String HC_NAME_MAP_APPINFO = "system.map.appinfo";
	public static final String HC_NAME_MAP_APPMODULE = "system.map.appmodule";
	public static final String HC_NAME_MAP_APPSERVICE = "system.map.appservice";
	public static final String HC_NAME_MAP_APPVERSION = "system.map.appversion";
	public static final String HC_NAME_MAP_APPADVERT = "system.map.appadvert";

	public static final String HC_NAME_MAP_DRIVER = "kernel.map.driver";
	public static final String HC_NAME_MAP_COMPANY = "kernel.map.company";
	public static final String HC_NAME_MAP_COMPANY_BIZ_CITY = "kernel.map.company.biz.city";
	public static final String HC_NAME_MAP_COMPANYMEM = "kernel.map.companymember";
	public static final String HC_NAME_MAP_COMPANYLINKER = "kernel.map.companylinker";
	public static final String HC_NAME_MAP_GROUP = "kernel.map.group";
	public static final String HC_NAME_MAP_GROUPMEM = "kernel.map.groupmember";
	public static final String HC_NAME_MAP_GROUPROSTER = "kernel.map.grouproster";
	public static final String HC_NAME_MAP_BASECOMPANY = "kernel.map.basecompany";

	public static final String HC_NAME_MAP_TAG = "runtime.map.tag";
	public static final String HC_NAME_MAP_VEHICLE = "runtime.map.vehicle";
	public static final String HC_NAME_MAP_VEHICLE2COMPANY = "runtime.map.vehicle2company";
	public static final String HC_NAME_MAP_VEHICLE2DRIVER = "runtime.map.vehicle2driver";
	public static final String HC_NAME_MAP_PLATE2FRAME1VEHICLE = "runtime.map.plate2frame1vehicle";

	public static final String HC_NAME_MAP_DEVICE = "runtime.map.device";
	public static final String HC_NAME_MAP_DEVICE2VEHICLE = "runtime.map.device2vehicle";
	public static final String HC_NAME_MAP_DEVICE2ZONE = "runtime.map.device2zone";
	public static final String HC_NAME_MAP_PERIPHERAL = "runtime.map.peripheral";
	public static final String HC_NAME_MAP_PERIPHERAL2DEVICE = "runtime.map.peripheral2device";

	public static final String HC_NAME_MAP_ALERTRULE = "runtime.map.alertrule";
	/**
	 * @deprecated 于2015年12月9日19:50:00过期，不再使用缓存实现
	 */
	public static final String HC_NAME_MAP_STOCK = "runtime.map.stock";

	/**
	 * @deprecated 于2015年12月9日19:50:00过期，不再使用缓存实现
	 */
	public static final String HC_NAME_MAP_STOCK_PUSH_HIS = "runtime.map.stockpushhis";

	/**
	 * @deprecated 于2015年12月9日19:50:00过期，不再使用缓存实现
	 */
	public static final String HC_NAME_MAP_NEW_STOCK="runtime.map.newstock";

	public static final String HC_NAME_MAP_STOCK_EXPECT = "runtime.map.stock.expect";

	public static final String HC_NAME_MAP_ZONE = "runtime.map.zone";
	public static final String HC_NAME_MAP_REMIND = "runtime.map.remind";
	/**
	 * @deprecated 于2015年12月9日19:50:00过期，不再使用缓存实现
	 */
	public static final String HC_NAME_MAP_CONTACT = "runtime.map.contact";
	/**
	 * @deprecated 于2015年12月9日19:50:00过期，不再使用缓存实现
	 */
	public static final String HC_NAME_MAP_COMPANY_DRIVER_INFO = "runtime.map.cmpny.driver.info";
	public static final String HC_NAME_MAP_COMMENT_GRADE = "runtime.map.comment.grade";
	public static final String HC_NAME_MAP_NEW_COMMENT_GRADE = "runtime.map.newcomment.grade";
	
	public static final String HC_NAME_MAP_DEVICELOG_LOC = "runtime.map.devicelog.location"; // 设备最后的位置
	/**
	 * @deprecated 于2015年12月9日19:50:00过期，不再使用缓存实现
	 */
	public static final String HC_NAME_MAP_DEVICELOG_TEMP = "runtime.map.devicelog.temperature"; // 设备最后的温度
	public static final String HC_NAME_MAP_USER_LOC = "runtime.map.user.location"; // 用户最后的位置

	public static final String HC_NAME_USER_OFFLINE_MESSAGE = "runtime.map.usermessage"; // 用户离线消息
	public static final String HC_NAME_USER_OFFLINE_MESSAGE_STATE = "runtime.map.usermessage.state"; // 用户离线消息状态

	public static final String HC_NAME_QUEUE_MESSAGE = "runtime.queue.message"; // 消息队列
	public static final String HC_NAME_QUEUE_CFILE = "runtime.queue.cfile"; // 文件处理队列

	// ----------------------------------------------------------------
	// 操作定义
	// ----------------------------------------------------------------
	public static final int OPER_SUBMIT = 0x0001;
	public static final int OPER_CANCEL = 0x0002;
	public static final int OPER_UPDATE = 0x0003;

	public static final int OPER_DICTTYPE_UPDATE_TAG = 0x1010;

	public static final int OPER_USER_UPDATE_PASSWORD = 0x1021;
	public static final int OPER_USER_UPDATE_CERTIFIED_MEMO = 0x1022;
	public static final int OPER_USER_UPDATE_CERTIFIED_STATE = 0x1023;
	public static final int OPER_USER_UPDATE_LOGININFO = 0x1024;
	public static final int OPER_USER_UPDATE_STATE = 0x1025;
	public static final int OPER_USER_UPDATE_NEED_CONFIRM = 0x1026;
	public static final int OPER_USER_UPDATE_RANK = 0x1027;
	public static final int OPER_USER_UPDATE_CREDIT = 0x1028;
	public static final int OPER_USER_UPDATE_VITALITY = 0x1029;
	public static final int OPER_USER_UPDATE_LOCATE = 0x102A;

	public static final int OPER_APP_UPDATE_STATE = 0x1031;
	public static final int OPER_APP_UPDATE_ACCESS_URL = 0x1032;
	public static final int OPER_APP_UPDATE_SYNC_URL = 0x1033;
	public static final int OPER_APP_UPDATE_LIMIT_NUM = 0x1034;
	public static final int OPER_APP_UPDATE_LIMIT_FREQ = 0x1035;
	public static final int OPER_APP_UPDATE_LIMIT_DATE = 0x1036;
	public static final int OPER_APP_UPDATE_INFO = 0x1037;

	public static final int OPER_COMPANY_BIND_USER = 0x1041;
	public static final int OPER_COMPANY_UNBIND_USER = 0x1042;
	public static final int OPER_COMPANY_UPDATE_HOLDER = 0x1043;
	public static final int OPER_COMPANY_UPDATE_LOCATION = 0x1044;
	public static final int OPER_COMPANY_UPDATE_BIZ_CITY = 0x1045;

	public static final int OPER_GROUP_UPDATE_CERTIFIED_MEMO = 0x1050;
	public static final int OPER_GROUP_UPDATE_CERTIFIED_STATE = 0x1051;
	public static final int OPER_GROUP_UPDATE_APPCODE = 0x1052;
	public static final int OPER_GROUP_UPDATE_NEED_CONFIRM = 0x1053;
	public static final int OPER_GROUP_INSERT_MEMBER = 0x1054;
	public static final int OPER_GROUP_DELETE_MEMBER = 0x1055;
	public static final int OPER_GROUP_UPDATE_MEMBER_STATE = 0x1056;
	public static final int OPER_GROUP_UPDATE_MEMBER_ROSTER = 0x1057;
	public static final int OPER_GROUP_UPDATE_MEMBER_MEMO = 0x1058;
	public static final int OPER_GROUP_INSERT_ROSTER = 0x1059;
	public static final int OPER_GROUP_UPDATE_ROSTER = 0x105A;
	public static final int OPER_GROUP_DELETE_ROSTER = 0x105B;
	public static final int OPER_GROUP_UPDATE_RANK = 0x105C;
	public static final int OPER_GROUP_UPDATE_CREDIT = 0x105D;
	public static final int OPER_GROUP_UPDATE_VITALITY = 0x105E;
	public static final int OPER_GROUP_UPDATE_MEMBER_START_TYPE = 0x105F;
	public static final int OPER_GROUP_UPDATE_MEMBER = 0x1060;
	public static final int OPER_GROUP_UPDATE_CERTIFIED_POWER = 0x1076;

	public static final int OPER_VEHICLE_UPDATE_COMPANY = 0x1061;
	public static final int OPER_VEHICLE_UPDATE_DRIVER = 0x1062;
	public static final int OPER_VEHICLE_UPDATE_PLATEFRAME = 0x1063;
	public static final int OPER_VEHICLE_UPDATE_IMAGE = 0x1064;

	public static final int OPER_DEVICE_UPDATE_VEHICLE = 0x1071;
	public static final int OPER_DEVICE_UPDATE_ZONE = 0x1072;
	public static final int OPER_PERIPHERAL_UPDATE_DEVICE = 0x1073;
	public static final int OPER_DEVICE_UNBIND_VEHICLE = 0x1074;
	public static final int OPER_DEVICE_UPDATE_ALERT = 0x1075;

	public static final int OPER_STOCK_UPDATE_APPLIER = 0x1081;
	public static final int OPER_STOCK_UPDATE_APPLIER_STATE = 0x1082;

	public static final int OPER_CMPNYDRIVERINFO_UPDATE_USERCODE = 0x1091;

	public static final int OPER_STOCKPUSHHIS_UPDATE_STATE = 0x1092;

	public static final int OPER_GROUP_UPDATE_MEMBER_OPENID = 0x1093;

	// ----------------------------------------------------------------

	// ----------------------------------------------------------------

	// 从缓存往数据库写入的线程池大小
	public static final int CACHE_PERSIST_POOL_SIZE = 50;

	// Session会话过期时间
	public static final int SESSION_EXPIRE_TIME = 30 * 60 * 1000; // 30分钟

	// 默认头像
	public static final String LOGO_USER_DEFAULT = "/system/logo-user.png";
	public static final String LOGO_COMP_DEFAULT = "/system/logo-comp.png";
	public static final String LOGO_GROUP_DEFAULT = "/system/logo-group.png";

	// ----------------------------------------------------------------
	//
	// ----------------------------------------------------------------

	// 短信转发账号
	public static final String USERCODE_SMS_TRANSFER = "sys_sms_transfer";

	// ----------------------------------------------------------------
	//
	// ----------------------------------------------------------------

	// ----------------------------------------------------------------
	// 云数据库字段类型定义
	public static final int TYPE_INT = 1;
	public static final int TYPE_FLOAT = 2;
	public static final int TYPE_DOUBLE = 3;
	public static final int TYPE_BOOLEAN = 4;
	public static final int TYPE_STRING = 5;
	public static final int TYPE_TIMELONG = 6;
	public static final int TYPE_TIMESTR = 7;

	// 云库相关字符串分隔符
	public static final String CLOAD_DBMS_PREFIX = "db_user_";
	public static final String CLOAD_DBMS_SEPARATOR_AT = "@";
	public static final String CLOAD_DBMS_SEPARATOR_DOUBLEAT = "@@";
	public static final String CLOAD_DBMS_SEPARATOR_LINE = "-";
	public static final String CLOAD_DBMS_SEPARATOR_DOUBLELINE = "--";

	// ----------------------------------------------------------------

	public static final int CARD_MESSAGE_COTENT_LEN = 31;
	// 位置泛解析时间间隔 10分钟内不解析
	public static final long LOCATION_UPDATE_TIME_SPACE = 10 * 60 * 1000;

	// 好友推荐间隔时间   6个小时之内不推荐
	public static final long RECOM_INTERVAL = 6 * 60 * 60 * 1000;

	// 货源担保   汇率
	public static final long EXCHANGE_RATE = 10000;
	//误差值设置  1秒
	public static final long ERROE_VALUE = 1000;
	//设置倒计时完成时间
	public static final long DELAY_FINISH = 10 * 60 * 1000;
}
