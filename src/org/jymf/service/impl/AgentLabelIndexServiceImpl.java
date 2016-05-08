package org.jymf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jymf.dao.AgentLabelIndexMapper;
import org.jymf.entity.AgentLabelIndex;
import org.jymf.service.IAgentLabelIndexService;
import org.jymf.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jymf.common.LableUtil;
/**
 * 标签索引操作实现类
 * @author cqs
 * @date   2016年03月10日
 */
@Service("agentLabelIndexService")
public class AgentLabelIndexServiceImpl implements IAgentLabelIndexService{
    @Autowired
    private AgentLabelIndexMapper labelIndexDao;

    @Override
    public PageView query(PageView pageView, String agentId) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", agentId);
        
        List<AgentLabelIndex> list = labelIndexDao.query(map);
        for(AgentLabelIndex label : list){
        	label.setStartTid(this.getLabelId(label.getStartTid()));
        	label.setEndTid(this.getLabelId(label.getEndTid()));
        }
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public AgentLabelIndex findById(long id) {
    	AgentLabelIndex labelIndex=labelIndexDao.selectByPrimaryKey(id);
    	labelIndex.setStartTid(this.getLabelId(labelIndex.getStartTid()));
    	labelIndex.setEndTid(this.getLabelId(labelIndex.getEndTid()));
        return labelIndex;
    }

    @Override
    public void update(AgentLabelIndex labelIndex) {
    	int len = labelIndex.getStartTid().length();
    	labelIndex.setStartTid(labelIndex.getStartTid().substring(0,len-4));
    	labelIndex.setEndTid(labelIndex.getEndTid().substring(0,len-4));
        labelIndexDao.updateByPrimaryKeySelective(labelIndex);
    }

    @Override
    public void add(AgentLabelIndex labelIndex) {
    	int len = labelIndex.getStartTid().length();
    	labelIndex.setStartTid(labelIndex.getStartTid().substring(0,len-4));
    	labelIndex.setEndTid(labelIndex.getEndTid().substring(0,len-4));
        labelIndexDao.insertSelective(labelIndex);
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
		
		//TODO
		AgentLabelIndex labelIndex = null;//labelIndexDao.findByLabelId(labelId);
		// null false
		// !null && labelIndex.id=id false
		// !null && labelIndex.id!= id true
		return null!=labelIndex && 0!=labelIndex.getId().compareTo(id);
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
}
