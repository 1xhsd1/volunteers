package com.volunteers.service;

import com.volunteers.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
public interface NoticeService extends IService<Notice> {

    /**
     * 查询所有社区公告
     * @return
     * @throws Exception
     */
    List<Notice> findNoticeList() throws Exception;

    /**
     * 根据id查找公告
     * @return
     * @throws Exception
     */
    Notice findNoticeById(Integer id) throws Exception;

    /**
     * 根据id删除社会公告
     * @return
     * @throws Exception
     */
    int deleteNotice(Integer id) throws Exception;

    /**
     * 新增社区公告
     * @param notice
     * @return
     * @throws Exception
     */
    int addNotice(Notice notice) throws Exception;

    /**
     * 修改社区公告
     * @param notice
     * @return
     * @throws Exception
     */
    int updateNotice(Notice notice) throws Exception;
}
