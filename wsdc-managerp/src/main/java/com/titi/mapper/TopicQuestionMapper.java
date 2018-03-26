package com.titi.mapper;

import java.util.List;

import com.titi.entity.AnswerLikeFormMap;
import com.titi.entity.TopicPicFormMap;
import com.titi.entity.TopicQuestionFormMap;
import com.titi.mapper.base.BaseMapper;

/**
 * 健康问答----问题 Dao层接口
 * @author 曾雄
 *
 */
public interface TopicQuestionMapper extends BaseMapper{

	//分页查询问题列表
	public List<TopicQuestionFormMap> findQuestionPage(TopicQuestionFormMap topicQuestionFormMap);

	//健康问答图片
	public List<TopicPicFormMap> getTopicPic(String id);
	
	//删除问答图片
	public void deleteTopicImg(String id);
	
	//保存问答图片
	public void saveTopicImg(TopicPicFormMap topicPicFormMap);
	
	//健康回答点赞人员列表
	public List<AnswerLikeFormMap> selectAnswerLikeList(AnswerLikeFormMap map);
	
}
