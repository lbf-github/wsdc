<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/games/edit.js"></script>

<style type="text/css">
	.col-sm-3 {
		width: 15%;
		float: left;
	}

	.col-sm-9 {
		width: 85%;
		float: left;
	}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/game/editEntity.shtml">
		<input type="hidden" class="form-control" value="${ad.id}"
			name="adFormMap.id" id="id">
		
		<section class="panel panel-default">
			
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-3 control-label">游戏图片</label>
					<div class="col-sm-9"  id="activityPic">
						<c:if test="${not empty ad.game_img}">
							<input id="activityUpload" type="button" value="上传" class="btn btn-primary marR10" onclick="fileField.click()" disabled=false>
							<input id="removeImg" type="button" value="移除" class="btn btn-danger marR10">
							<input type="file" name="fileField" style="opacity:0;" id="fileField" onchange="uploadlogo(this);"/>
						</c:if>
						<c:if test="${empty ad.game_img}">
							<input id="activityUpload" type="button" value="上传" class="btn btn-primary marR10" onclick="fileField.click()">
							<input id="removeImg" type="button" value="移除" class="btn btn-danger marR10" disabled>
							<input type="file" name="fileField" style="opacity:0;" id="fileField" onchange="uploadlogo(this);"/>
						</c:if>
					</div>
					<div id="activity_img">
						<c:if test="${not empty ad.game_img}">
							<img style="height:100px;width:100px;margin-left:100px" src="<%=image_url %>${ad.game_img}">
						</c:if>
						
						<input value="${ad.game_img}" name="adFormMap.game_img" type="hidden">
					</div>
				</div>
			
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">游戏名称</label>
					<div class="col-sm-9">
						<input type="text" class="form-control"
							placeholder="请输入游戏名称" name="adFormMap.game_name" id="title" value="${ad.game_name}" minlength="2" maxlength="10" required />
					</div>
				</div>

				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">玩家数</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkSeq" digits="true" min="1" max="99999999"
							   placeholder="请输入数字" name="adFormMap.player_num" value="${ad.player_num}"/>
					</div>
				</div>

				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">安装包大小(M)</label>
					<div class="col-sm-9">
						<input type="text" class="form-control"
							   placeholder="请输入游戏名称" name="adFormMap.size"  value="${ad.size}" minlength="2" maxlength="10" required/>
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">游戏简介</label>
					<div class="col-sm-9">
						<input type="text" class="form-control"
							   placeholder="请输入游戏名称" name="adFormMap.intro"  value="${ad.intro}"/>
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">游戏类别</label>
					<div class="col-sm-9">
						<input type="text" class="form-control"
							   placeholder="请输入游戏类别" name="adFormMap.game_type"  value="${ad.game_type}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">链接地址</label>
					<div class="col-sm-9">
						<input class="form-control"
							placeholder="请输入链接地址" name="adFormMap.url" id="url" value="${ad.url}" type="url"/>
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">是否热门</label>
					<div class="col-sm-9">
						<div class="btn-group m-r">
							<button data-toggle="dropdown"
								class="btn btn-sm btn-default dropdown-toggle">
								<span class="dropdown-label"><c:if test="${ad.hot eq 0}">否</c:if><c:if test="${ad.hot eq 1}">是</c:if></span>
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu dropdown-select">
								<li class=""><a href="#"><input type="radio"
										name="adFormMap.hot" value="0"<c:if test="${ad.hot eq 0}"> checked="checked"</c:if>>否</a></li>
								<li class="active"><a href="#"><input type="radio"
										name="adFormMap.hot" value="1" <c:if test="${ad.hot eq 1}"> checked="checked"</c:if>>是</a></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">是否最新</label>
					<div class="col-sm-9">
						<div class="btn-group m-r">
							<button data-toggle="dropdown" class="btn btn-sm btn-default dropdown-toggle">
								<span class="dropdown-label"><c:if test="${ad.newest eq 0}">否</c:if><c:if test="${ad.newest eq 1}">是</c:if></span>
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu dropdown-select">
								<li class=""><a href="#"><input type="radio" name="adFormMap.newest" value="0">
									<c:if test="${ad.newest eq 0}">checked="checked"</c:if>>否</a>
								</li>
								<li class="active"><a href="#"><input type="radio" name="adFormMap.newest" value="1"
									<c:if test="${ad.newest eq 1}">checked="checked"</c:if>>是</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">排序</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkSeq" digits="true" min="1" max="99999999"
							placeholder="请输入数字" name="adFormMap.sort" id="sort" value="${ad.sort}"/>
					</div>
				</div>
				
			</div>
			<footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> 
	</section>
	</form>
</body>
</html>