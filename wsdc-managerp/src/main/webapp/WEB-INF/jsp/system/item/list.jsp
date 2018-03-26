<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/item/list.js"></script>

	<header class="panel-heading">
		<div class="doc-buttons">
			<c:forEach items="${res}" var="key">
				${key.description}
			</c:forEach>
		</div>
		
		<div class="m-select">
			<label>设置商品分类:</label>
			<select id="classify_id" name="classifyItemFormMap.classify_id" tabindex="-1"></select>
			<input id="confirm" type="button" class="j-select-sure" value="确定"/>
		</div>
		
		<div class="line line-dashed line-lg pull-in"></div>
		<form class="form-inline" role="form" id="searchForm">
			<%--<div class="form-group">
				<label>商品种类:</label>
				<select id="pac_num" style="height:26" name="itemFormMap.pac_num">
					<option value="">请选择</option>
					<option value="1">单品</option>
					<option value="2">套餐</option>
				</select>
			</div>--%>
			
			<%--<div class="form-group" id="province">
				<label>选择地区:</label>
				<select id="province_id" style="height:26" onchange="getCities();" name="itemFormMap.province_id"></select>
			</div>--%>
			
			<div class="form-group" id="city" style="display:none;"></div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label>商品标题:</label>
				<input class="input-medium ui-autocomplete-input" id="title" name="itemFormMap.title" />
			</div>
			
			<div class="form-group">
				<label>是否前台显示:</label>
				<select id="show_yn" style="height:26" name="itemFormMap.show_yn">
					<option value="">请选择</option>
					<option value="0">显示</option>
					<option value="1">不显示</option>
				</select>
			</div>
			
			<div class="form-group">
				<label>是否热卖:</label>
				<select id="sale_hot" name="itemFormMap.sale_hot" style="height:26">
					<option value="">请选择</option>
					<option value="0">非热卖</option>
					<option value="1">热卖</option>
				</select>
			</div>
			
			<div class="form-group">
				<label>是否下架:</label>
				<select id="from_sale" name="itemFormMap.from_sale" style="height:26">
					<option value="">请选择</option>
					<option value="0">未下架</option>
					<option value="1">已下架</option>
				</select>
			</div>
			
			<div class="form-group">
				<label>是否推荐:</label>
				<select id="rec_yn" name="itemFormMap.rec_yn" style="height:26">
					<option value="">请选择</option>
					<option value="0">不推荐</option>
					<option value="1">推荐</option>
				</select>
			</div> 
			
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
			<a href="javascript:void(0);" class="btn btn-default" id="reset">重置</a>
			<a href="javascript:exportGrid.exportData('/item/export.shtml')" class="btn btn-info" >导出excel</a>
		</form>
	</header>
	
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>

