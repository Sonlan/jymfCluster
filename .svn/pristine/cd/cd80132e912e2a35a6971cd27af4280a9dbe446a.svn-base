package org.jymf.entity;
import org.core.modules.mapper.JsonMapper;

public class Depict {

	/**
     * 公司描述
     * json格式
     */
    private String depicts;
  
	

	public String getDepicts() {
		return depicts;
	}

	public void setDepicts(String depicts) {
		this.depicts = depicts;
	}

	public String toJson(){
		removeNull(depicts);
		
		JsonMapper json =new JsonMapper();
		String jsonStr = json.toJson(this);
		return jsonStr;
	}
	
	public Depict jsonToPara(String jsonStr){
		JsonMapper json =new JsonMapper();
		Depict para = json.fromJson(jsonStr, Depict.class);
		return para;
	}
	
	private void removeNull(String plst){
		if(null == plst){
		    return;
		}
	}
}
