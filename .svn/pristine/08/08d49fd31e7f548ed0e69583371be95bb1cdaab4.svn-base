package org.jymf.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.modules.mapper.JsonMapper;
import org.jymf.dao.ProductTypeMapper;
import org.jymf.entity.ProductType;
import org.jymf.service.IProductTypeService;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productTypeService")
public class ProductTypeServiceImpl implements IProductTypeService{

	@Autowired
	private ProductTypeMapper productTypeDao;
	
	@Override
	public PageView queryProductType(PageView pageView,BigDecimal companyId ,BigDecimal typePid,
			                         BigDecimal workMode) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("paging", pageView);
		map.put("companyId", companyId);
		map.put("typePid", typePid);

		List<ProductType> list = productTypeDao.query(map);
		for(ProductType p : list){
			map.put("typePid", p.getId());
			p.setChildTypeCount(productTypeDao.getChildCount(map));
		}
		
		pageView.setRecords(list);

		return pageView;
	}

	@Override
	public boolean isProductTypeRepeated(BigDecimal companyId,
			                             ProductType productType, String type, BigDecimal workMode) {
		boolean flag=false;
		HashMap<Object,Object> map = new HashMap<Object,Object>();
		map.put("companyId", companyId);
		map.put("t", productType);
		
		List<ProductType> list =  productTypeDao.findTypeByName(map);
		
		if(type.equals("add")){
			if(list.size()==0){
				flag=true;
			}
		}else if(type.equals("edit")){
			if(list.size()==1){
				flag=true;
			}
		}
		
		return flag;
	}

	@Override
	public boolean add(ProductType productType,BigDecimal companyId, BigDecimal workMode) {
		Map<Object,Object> map=new HashMap<Object,Object>();
		map.put("companyId", companyId);
		map.put("t", productType);
		if(productTypeDao.insert(map)>0){
			return true;
		}else{
			return false;	
		}
	}

	@Override
	public ProductType findProductTypeByTypeId(BigDecimal typeId,
			                                   BigDecimal companyId, BigDecimal workMode) {
		Map<Object,Object> map=new HashMap<Object,Object>();
		map.put("companyId", companyId);
		
		ProductType productType=new ProductType();
		productType.setId(typeId);
		map.put("t", productType);
		
		List<ProductType> list = productTypeDao.findTypeById(map);
		
		if(list.size()==1){
			productType=list.get(0);
		}else{
			productType=new ProductType();
		}
		
		return productType;
	}

	@Override
	public boolean edit(ProductType productType, BigDecimal companyId,
						BigDecimal workMode) {
		
		boolean flag = false;
		
		Map<Object,Object> map=new HashMap<Object,Object>();
		map.put("companyId", companyId);
		map.put("t", productType);
		
		List<ProductType> list = productTypeDao.findTypeByName(map);
		if(list.size()==0 || (list.size()==1 && list.get(0).getId().compareTo(productType.getId())==0 )){
			flag=(productTypeDao.update(map)==1);
		}
		return flag;
	}

	@Override
	public String getAllJsonType(BigDecimal companyId, BigDecimal workMode) {
		List<ProductType> list = productTypeDao.findAll(companyId);
		List<Map<String,Object>> treeList = new ArrayList<Map<String,Object>>();
		for(ProductType type : list){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("id", type.getId());
			map.put("pId", type.getParentId());
			map.put("name", type.getTypeName());
			treeList.add(map);
		}
		
		JsonMapper jm = new JsonMapper();
		return jm.toJson(treeList);
	}

	@Override
	public List<List<String>> getAllTypeList(BigDecimal companyId, BigDecimal workMode) {
		
		List<List<String>> dataList = new ArrayList<List<String>>();
		List<ProductType> typesList = productTypeDao.findAll(companyId);
		if(null==typesList){
			List<String> singalList = new ArrayList<String>();
			singalList.add("无数据");
			
			dataList.add(singalList);
		}else{
			for(ProductType type : typesList){
				List<String> singalList = new ArrayList<String>();
				singalList.add(type.getId().toString());
				singalList.add(type.getParentId().toString());
				singalList.add(type.getTypeName());
				
				dataList.add(singalList);
			}
		}
		return dataList;
	}
}
