package org.jymf.service;

import org.jymf.entity.Logs;
import org.jymf.utils.PageView;

/**
 * 日志信息操作接口
 * @author lxg
 * @date   2014年7月29日
 */
public interface ILogsService {

	/**
     * 分页处理
     * @param pageView
     * @param logs
     * @return
     */
    PageView query(PageView pageView, Logs logs);
}
