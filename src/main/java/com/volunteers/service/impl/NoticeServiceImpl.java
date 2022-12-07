package com.volunteers.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteers.entity.Notice;
import com.volunteers.dao.NoticeMapper;
import com.volunteers.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public Notice findNoticeById(Integer id) throws Exception {
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return noticeMapper.selectOne(wrapper);
    }

    @Override
    public List<Notice> findNoticeList() throws Exception {
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("releaseDate");
        return noticeMapper.selectList(wrapper);

    }

    @Override
    public int deleteNotice(Integer id) throws Exception {
        return noticeMapper.deleteById(id);
    }

    /**
     * 发布社区公告
     * @param notice
     * @return
     * @throws Exception
     */
    @Override
    public int addNotice(Notice notice) throws Exception {
        return noticeMapper.insert(notice);
    }

    /**
     * 修改社区公告
     * @param notice
     * @return
     * @throws Exception
     */
    @Override
    public int updateNotice(Notice notice) throws Exception {
        return noticeMapper.updateById(notice);
    }
}
