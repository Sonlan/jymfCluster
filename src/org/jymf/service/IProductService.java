package org.jymf.service;

import java.math.BigDecimal;
import java.util.List;

import org.jymf.entity.Product;
import org.jymf.entity.ProductConfig;
import org.jymf.utils.ImgFile;
import org.jymf.utils.PageView;

/**
 * 产品类型操作接口
 * @author lxg
 * @date   2014年05月20日
 */
public interface IProductService {
	 /**
     * 通过主键获取记录
     * @param id 产品ID
     * @param companyId 产品所在公司的ID
     * @param isImg 是否要查询图片
     *              True 要
     *             False 不要
     * @param workMode:根据追溯模式不同，会访问不同的DB
     * @return
     */
    Product findById(BigDecimal id,BigDecimal companyId,Boolean isImg, BigDecimal workMode);
    
    /**
     * 获取指定公司下的所有产品ID
     * @param companyId
     * @param workMode:根据追溯模式不同，会访问不同的DB
     * @return
     */
    List<BigDecimal> findAllId(BigDecimal companyId, BigDecimal workMode);
    
    /**
     * 分页处理
     * 根据追溯模式不同，会访问不同的DB
     * @param pageView
     * @param product
     * @return
     */
    PageView query(PageView pageView, Product product,BigDecimal workMode);
    
    /**
     * 分页处理,商户单选画面
     * @param pageView
     * @param product
     * @param workMode:根据追溯模式不同，会访问不同的DB
     * @return
     */
    PageView selectProduct(PageView pageView, Product product, BigDecimal workMode);
    /**
     * 产品类型信息
     * @param product
     * @param cacheFolder 服务器存放上传图片的绝对路径
     * @return 
     *        True  有跟新
     *       False  无更新
     * @param workMode:根据追溯模式不同，会访问不同的DB
     */
    boolean update(Product product,String cacheFolder,BigDecimal workMode);
    
    /**
     * 添加产品类型
     * @param product
     * @param cacheFolder 服务器存放上传图片的绝对路径
     * @param workMode:根据追溯模式不同，会访问不同的DB
     */
    void add(Product product, String companyName,String companyUrl,String cacheFolder,BigDecimal workMode);

    String findProductName(BigDecimal productId,String companyId,BigDecimal workMode);
    
    /**
     * 查询所有产品信息用于导出数据
     * update by wfj 2015.5.6
     */
    List<List<String>> getAllProductList(BigDecimal companyId,BigDecimal workMode);
    
    /**
     * 查询酒类产品箱/瓶参数、盘/箱参数
     */
    ProductConfig getProductPara(long workMode);
    
/*    *//**
	 * 克隆产品时克隆图片，将原产品图片转移至临时文件目录下
	 * update by wfj 2015.5.28
	 *//*
	public void copyFileTo(List<ImgFile> productImgs,String destPath,BigDecimal companyId,long productId);
	*/
	/**
	 * 发送审核请求邮件
	 * @param companyName
	 * @param productName
	 */
	public void sendMail(String preStr,String companyName,String productName);
	
    /**
     * 分页处理，汽配模式销量统计查询
     * 根据追溯模式不同，会访问不同的DB
     * @param pageView
     * @param product
     * @return
     */
    PageView querySales(PageView pageView, Product product, BigDecimal workMode);
}
