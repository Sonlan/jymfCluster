package org.jymf.menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;

import org.core.modules.mapper.JsonMapper;
import org.jymf.utils.FileManager;
import org.springframework.util.ResourceUtils;

public class MenuCompany {
	    
	public static Menus loadMenu(BigDecimal companyId,BigDecimal mode,boolean hasEquipment,boolean hasInspect)  {
		
		File cfgFile;
		try {
			String path = String.format("classpath:companymenu/company%s.json", mode);
			cfgFile = ResourceUtils.getFile(path);
		} catch (FileNotFoundException e) {
			return null;
		}	
		FileManager fileManager = new FileManager();
		String jsonStr = fileManager.readFile(cfgFile);

		JsonMapper json =new JsonMapper();
		Menus menus = json.fromJson(jsonStr, Menus.class);
		
		if(!hasEquipment){
			for(int i = menus.getMenus().size()-1;i>=0;i--){
				Menu menu=menus.getMenus().get(i);
    			//如果没有设备，menu 设备，稽查，稽查查询，不显示
    			// 设备管理  id: company-equipment
    	        // 稽查终端  id: company-inspect-device
    	        // 窜货查询  id: company-acInfo
            	if(menu.getId().equals("company-equipment") || 
            			menu.getId().equals("company-inspect-device")||
            			menu.getId().equals("company-acInfo")){
            		menus.getMenus().remove(menu);
            	}
            	// 厂家模式以下操作不考虑
            	if(mode.equals(new BigDecimal(1))){
            		continue;
            	}
            	if(null == menu.getSubmenu()){
            		continue;
            	}
            	/*for(int j = menus.getMenus().size()-1;j>=0;j--){
    				Menu m=menus.getMenus().get(j);
            		//出库统计 id: company-count-out
            		if(m.getId().equals("company-count-out")){
            			menu.getSubmenu().remove(m);
            		}
            	}*/
            	removeCountOut(menu);
            }
		}
		if(!hasInspect){
			for(int i = menus.getMenus().size()-1;i>=0;i--){
				Menu menu=menus.getMenus().get(i);
    			//如果没有稽查，menu 稽查查询，不显示
    	        // 窜货查询  id: company-acInfo
            	//if(menu.getId().equals("company-inspect-device")||
            	//		menu.getId().equals("company-acInfo")){
				if(menu.getId().equals("company-acInfo")){
            		menus.getMenus().remove(menu);
            	}
            }
		}
		
		for(int i = menus.getMenus().size()-1;i>=0;i--){
			Menu menu=menus.getMenus().get(i);
            if(null == menu.getCompanyIds()){
            	continue;
            }
            
			if(!menu.getCompanyIds().contains(companyId.toString())){
	       		menus.getMenus().remove(menu);
        	}
        }
		
		// 358000059以为的公司不需要添加菜单(虫草培育)
		for (Menu menu : menus.getMenus()) {
			if (companyId.compareTo(new BigDecimal("358000059"))!=0 && "company-cultivation".equals(menu.getId())) {
				menus.getMenus().remove(menu);
			}
		}
		
        return menus;
	}
	
	private static void removeCountOut(Menu menu){
		for(int j = menu.getSubmenu().size()-1;j>=0;j--){
			Menu m=menu.getSubmenu().get(j);
			if(null==m.getSubmenu()){
				//出库统计 id: company-count-out
	    		if(m.getId().equals("company-count-out")){
	    			menu.getSubmenu().remove(m);
	    		}
			}else{
				removeCountOut(m);
			}
    		
    	}
	}
}
