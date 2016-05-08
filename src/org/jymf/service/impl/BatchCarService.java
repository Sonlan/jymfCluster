package org.jymf.service.impl;

import java.math.BigDecimal;

import org.jymf.dao.LabelInfoMapper;
import org.jymf.service.IBatchCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 夜间对汽配模式的处理，属于Batch业务
 * @author Cqs
 *
 */
@Service("batchCarService")
public class BatchCarService implements IBatchCarService {

	@Autowired
    private LabelInfoMapper labelInfoDao;
	
	@Override
	public void updateBatchCarRun(BigDecimal companyId, BigDecimal workMode) {
		labelInfoDao.updateCarPackageLabel(companyId);
	}

}
