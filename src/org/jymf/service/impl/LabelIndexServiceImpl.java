package org.jymf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import org.jymf.dao.LabelIndexMapper;
import org.jymf.entity.LabelIndex;
import org.jymf.service.ILabelIndexService;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jymf.common.LableUtil;
/**
 * 标签索引操作实现类
 * @author cqs
 * @date   2014年05月09日
 */
@Service("labelIndexService")
public class LabelIndexServiceImpl implements ILabelIndexService{
    @Autowired
    private LabelIndexMapper labelIndexDao;

    @Override
    public PageView query(PageView pageView, LabelIndex labelIndex) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", labelIndex);
        
        List<LabelIndex> list = labelIndexDao.query(map);
        for(LabelIndex label : list){
        	label.setStartTid(this.getLabelId(label.getStartTid()));
        	label.setEndTid(this.getLabelId(label.getEndTid()));
        }
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public LabelIndex findById(long id) {
    	LabelIndex labelIndex=labelIndexDao.selectByPrimaryKey(new BigDecimal(id));
    	labelIndex.setStartTid(this.getLabelId(labelIndex.getStartTid()));
    	labelIndex.setEndTid(this.getLabelId(labelIndex.getEndTid()));
        return labelIndex;
    }

    @Override
    public void update(LabelIndex labelIndex) {
    	int len = labelIndex.getStartTid().length();
    	labelIndex.setStartTid(labelIndex.getStartTid().substring(0,len-4));
    	labelIndex.setEndTid(labelIndex.getEndTid().substring(0,len-4));
    	if(labelIndex.getStartTid().length()==18 && labelIndex.getEndTid().length()==18){
    		labelIndexDao.updateIndexS(labelIndex);
    	}else{
            labelIndexDao.updateIndex(labelIndex);
    	}
    }

    @Override
    public void add(LabelIndex labelIndex) {
    	int len = labelIndex.getStartTid().length();
    	labelIndex.setStartTid(labelIndex.getStartTid().substring(0,len-4));
    	labelIndex.setEndTid(labelIndex.getEndTid().substring(0,len-4));
    	if(labelIndex.getStartTid().length()==18 && labelIndex.getEndTid().length()==18){
    		labelIndexDao.insertIndexS(labelIndex);
    	}else{
            labelIndexDao.insertIndex(labelIndex);
    	}
    }

    /**
     * 判断标签是否被注册过
     * @param labelId
     * @param id 当前记录的ID，Add=0，Update=当前记录
     * @return
     *        True 注册过
     *       False 没有被注册过||当前更新的记录
     */
	@Override
	public boolean checkLableId(String labelId,long id) {
		labelId = labelId.substring(0,labelId.length()-4);
		LabelIndex labelIndex = labelIndexDao.findByLabelId(labelId);
		// null false
		// !null && labelIndex.id=id false
		// !null && labelIndex.id!= id true
		return null!=labelIndex && 0!=labelIndex.getId().compareTo(new BigDecimal(id));
	}

	/**
	 * 根据输入的顺序号，获取追溯码
	 * @param id
	 * @return
	 */
	private String getLabelId(String id){
		String labelId = LableUtil.getCheckNum(id);
		return labelId;
	}
	
	@Override
	public PageView query(PageView pageView, LabelIndex labelIndex,	long agentId) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", labelIndex);
        map.put("agentId", agentId);
        
        List<LabelIndex> list = labelIndexDao.query(map);
        for(LabelIndex label : list){
        	label.setStartTid(this.getLabelId(label.getStartTid()));
        	label.setEndTid(this.getLabelId(label.getEndTid()));
        }
        pageView.setRecords(list);
        return pageView;
	}
}
