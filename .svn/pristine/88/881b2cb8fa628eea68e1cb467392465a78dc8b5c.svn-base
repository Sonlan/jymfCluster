package org.jymf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.dao.AreaMapper;
import org.jymf.dao.CompanyMonitorMapper;
import org.jymf.entity.Area;
import org.jymf.service.DatabaseSynchronize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DatabaseSynchronizeImpl implements DatabaseSynchronize {
	@Autowired
	private AreaMapper areaDao;
	@Autowired
	private CompanyMonitorMapper companyMonitorDao;
	@Override
	public boolean dbSynchronize() {
		try {
			List<Area> areaList = areaDao.getAllObjects();
			for(int i=0;i<areaList.size();i++){
				int len =  areaList.get(i).getName().split("-").length;
				//排除几个特殊的地名
				if(areaList.get(i).getName().contains("云南省-红河哈尼族彝族自治州-红河县")) continue;
				if(areaList.get(i).getName().contains("云南省-临沧市-云县")) continue;
				if(areaList.get(i).getName().contains("云南省-文山壮族苗族自治州-文山市")) continue;
				if(areaList.get(i).getName().contains("云南省-大理白族自治州-大理市")) continue;
				String name = areaList.get(i).getName().split("-")[len-1].replace("-", "%")
                        .replace("省", "%")
                        .replace("市", "%")
                        .replace("州", "%")
                        .replace("县", "%")
                        .replace("区", "%");
				if(name.contains("自治")) name = name.substring(0, 2);
				Map<String , Object> map = new HashMap<String, Object>();
				map.put("name", name);
				map.put("id", areaList.get(i).getId());
				map.put("level", areaList.get(i).getLevel()+"");
				companyMonitorDao.updateByNameLike(map);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
