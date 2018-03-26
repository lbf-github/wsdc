<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/games/list.js"></script>

	<header class="panel-heading">
		<div class="doc-buttons">
			<c:forEach items="${res}" var="key">
				<span>${key.description}</span>
			</c:forEach>
		</div>
		<form class="form-inline" role="form" id="searchForm">
			<div class="form-group">
				<label>游戏名称:</label>
				<input class="input-medium ui-autocomplete-input" id="game_name" name="gamesFormMap.game_name" />
			</div>
			<div class="form-group">
				<label>是否热门:</label>
				<select id="hot_yn" style="height:26px" name="gamesFormMap.hot">
					<option value="">请选择</option>
					<option value="0">非热门</option>
					<option value="1">热门</option>
				</select>
			</div>
			<div class="form-group">
				<label>是否最新:</label>
				<select id="newest" style="height:27px" name="gamesFormMap.newest">
					<option value="">请选择</option>
					<option value="0">非最新</option>
					<option value="1">最新</option>
				</select>
			</div>

			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
			<a href="javascript:void(0);" class="btn btn-default" id="reset">重置</a>
		</form>
	</header>
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>

