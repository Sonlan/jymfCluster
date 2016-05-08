package org.jymf.utils;

import java.io.File;
import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class Constants {

	/** 公司图片类型 */
	public static final String PHOTO_TYPE_COMPANY = "company";

	/** 合同图片类型 */
	public static final String PHOTO_TYPE_PRODUCT = "product";
	
	/** 产品图片类型 */
	public static final String PHOTO_TYPE_DOCUMENT = "document";
			
	/** 图片显示高度 */
	public static final int PHOTO_IMG_HEIGHT = 150;

	/** 图片显示宽度 */
	public static final int PHOTO_IMG_WIDTH = 150;

	/** 图片最大值 60K = 60 * 1024 */
	public static final int PHOTO_MAX_SIZE = 61440;
	
	/** 管理员用户ID  */
	public static final String SESSION_ADMIN="jymfadmin";
	
	/** 公司用户登录 */
	public static final String SESSION_COMPANY_USER="companyuser";

	/** 公司基本信息 Session 传值使用 */
	public static final String SESSION_COMPANY_BASE="companybase";
	
	/** 公司产品类型信息 Session 传值使用 */
	public static final String SESSION_PRODUCT_TYPE="producttype";
	
	/** 管理员信息 Session 传值使用 
	 * update by wfj 2015.5.26
	 */
	public static final String SESSION_ADMINUSER="adminuser";
	
	/** 产品后退 */
	public static final String SESSION_PRODUCT_BACK="productback";
	
	/** 标签为主导的batch处理标识 */
	public static final String BATCH_LABEL_RUN="batchLabelFirstRun";
	
	/** 出库为主导的batch处理标识 */
	public static final String BATCH_OUTPUT_RUN="batchOutputFirstRun";
	
	/** 代理商基本信息 Session 传值使用 */
	public static final String SESSION_AGENT="agent";
	
	public static final BigDecimal DEFAULT_WORK_MODE = new BigDecimal(-1);
	
	/** 产品模式 **/
	public static final BigDecimal WM_CP = new BigDecimal(0);
	
	/** 厂家模式 **/
	public static final BigDecimal WM_CJ = new BigDecimal(1);
	
	/** 商城模式 **/
	public static final BigDecimal WM_SHCH = new BigDecimal(2);
	
	/** 汽配模式 **/
	public static final BigDecimal WM_QP = new BigDecimal(3);
	
	/** 出版物模式 **/
	public static final BigDecimal WM_CBW = new BigDecimal(4);
	
	/** 佛教模式 **/
	public static final BigDecimal WM_FJ= new BigDecimal(5);
	
	/** 酒类模式 **/
	public static final BigDecimal WM_JL = new BigDecimal(6);
	
	/** 快销品模式 **/
	public static final BigDecimal WM_KXP = new BigDecimal(7);
	
	/** 烟草模式 **/
	public static final BigDecimal WM_YC = new BigDecimal(9);
	
	/** 红酒城模式 **/
	public static final BigDecimal WM_HJC = new BigDecimal(10);
	
	/**服务器图片存储相对路径*/
	public static final String CACHE_FOLDER=String.format("static%scache_img%s",File.separator,File.separator);
	
	/**excel 模版所在地*/
	public static final String EXCEL_FOLDER=String.format("static%smodel%s%s",File.separator,File.separator,"labelApplyList.xlsx");
	
	/**
	 * 各种追溯模式静态变量，用于展示下拉列表框
	 * <br>0=产品追溯模式
	 * <br>1=厂家追溯模式
	 * <br>2=商城追溯模式
	 * <br>3=汽配追溯模式
	 * <br>4=出版物追溯模式
	 * <br>5=联盟追溯模式
	 * <br>6=酒类追溯模式
	 * <br>7=快消品追溯模式
	 * <br>9=烟草追溯模式
	 * <br>10=红酒城追溯模式
	 * <br>update by wfj @ 2015-3-25 
	 * */
	public static final LinkedHashMap<String, String> workModelMap=new LinkedHashMap<String,String>();
	 
	static{
		workModelMap.put("0", "产品追溯模式"); 
		workModelMap.put("1", "厂家追溯模式");
		workModelMap.put("2", "商城追溯模式");
		workModelMap.put("3", "汽配追溯模式");
		workModelMap.put("4", "出版物追溯模式");
	    workModelMap.put("5", "联盟追溯模式");
	    workModelMap.put("6", "酒类追溯模式");
	    workModelMap.put("7", "快消品追溯模式");
	    workModelMap.put("9", "烟草追溯模式");
	    workModelMap.put("10", "红酒城追溯模式");
	}
		
	public static final LinkedHashMap<String, String> monitorModeMap=new LinkedHashMap<String,String>();
	 
	static{
		monitorModeMap.put("-1", "全模式"); 
		monitorModeMap.put("0", "产品追溯模式"); 
		monitorModeMap.put("1", "厂家追溯模式");
		monitorModeMap.put("2", "商城追溯模式");
		monitorModeMap.put("3", "汽配追溯模式");
		monitorModeMap.put("4", "出版物追溯模式");
		monitorModeMap.put("5", "联盟追溯模式");
		monitorModeMap.put("6", "酒类追溯模式");
		monitorModeMap.put("7", "快消品追溯模式");
		monitorModeMap.put("9", "烟草追溯模式");
		monitorModeMap.put("10", "红酒城追溯模式");
	}
	
	/** 产品检索排序 */
	public static final LinkedHashMap<String, String> orderByMap=new LinkedHashMap<String,String>();
	 
	static{
		orderByMap.put("0", "默认"); 
		orderByMap.put("1", "激活数量从大到小");
		orderByMap.put("2", "激活数量从小到大");
	}
	
	/** 消费者模式说明 */
	public static final LinkedHashMap<String, String> consumeTypeMap=new LinkedHashMap<String,String>();
	
	static{
		consumeTypeMap.put("1", "验证码、三次死亡");
		consumeTypeMap.put("2", "验证码、永远不死亡");
		consumeTypeMap.put("3", "刮刮乐、三次死亡");
		consumeTypeMap.put("4", "刮刮乐、永远不死亡");
		consumeTypeMap.put("5", "扫描直接出结果");
	}
}
