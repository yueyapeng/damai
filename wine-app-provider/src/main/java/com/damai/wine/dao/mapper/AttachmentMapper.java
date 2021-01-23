package com.damai.wine.dao.mapper;

import com.damai.wine.dao.model.Attachment;
import com.damai.wine.dto.request.attachment.AttachmentQueryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttachmentMapper {

    /**
     * 新增附件信息
     * @param record
     * @return
     */
    int insertSelective(Attachment record);

    /**
     * 根据主键id查询附件信息
     * @param id
     * @return
     */
    Attachment selectByPrimaryKey(String id);

    /**
     * 根据 realtionId + attachmentType 查询附件信息
     * @param queryExample
     * @return
     */
    List<Attachment> selectByRealtionIdAndType(Attachment queryExample);

    /**
     * 根据复合查询条件查询附件信息
     * @param queryExample
     * @return
     */
    List<Attachment> selectByExample(AttachmentQueryDto queryExample);
}