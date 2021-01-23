package com.damai.wine.dao.repository;

import com.damai.wine.dao.mapper.AttachmentMapper;
import com.damai.wine.dao.model.Attachment;
import com.damai.wine.dto.request.attachment.AttachmentQueryDto;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AttachmentRepository {

    @Resource
    private AttachmentMapper attachmentMapper;

    /**
     * 根据用户主键查询附件信息
     * @param id
     * @return
     */
    public Attachment queryByPrimaryKey(String id) {
        return attachmentMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据 realtionId + attachmentType 查询附件信息
     * @param realtionId
     * @param attachmentType
     * @return
     */
    public List<Attachment> queryByRealtionIdAndType(String realtionId, String attachmentType) {
        Attachment queryExample = new Attachment();
        queryExample.setRelationId(realtionId);
        queryExample.setAttachmentType(attachmentType);
        return attachmentMapper.selectByRealtionIdAndType(queryExample);
    }

    /**
     * 根据复合条件查询附件信息
     * @param queryExample
     * @return
     */
    public List<Attachment> queryByExample(AttachmentQueryDto queryExample) {
        return attachmentMapper.selectByExample(queryExample);
    }

    /**
     * 新增附件信息
     * @param record
     */
    public void addAttachment(Attachment record) {
        attachmentMapper.insertSelective(record);
    }

}
