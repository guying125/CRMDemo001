<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<SCRIPT language=javascript>
	// 提交当前分页查询表单
	function to_page(page){
		if(page){
			$("#page").val(page);
		}
		document.customerForm.submit();
		
	}
	
	// 页面的加载
	
 	/* $(function(){
		// 发送ajax请求
		var url = "${pageContext.request.contextPath }/dict_findByCode.action";
		
		// 获取客户级别
		var param = {"dict_type_code":"006"};
		
		$.post(url, param, function(data){
			// 遍历
			$(data).each(function(i,n){
				alert(i + " : " + n.dict_id + " :: " + n.dict_item_name);
			});
		}, "json");
		
	});  */
	
	/* $(function(){
		// 发送ajax请求
		var url = "${pageContext.request.contextPath }/dict_findByCode.action";
		
		// 获取客户级别
		var param = {"dict_type_code":"006"};
		$.post(url,param,function(data){
			// 遍历
			$(data).each(function(i,n){
				// jQuery 的  DOM 操作
				$("#levelID").append("<option value='" + n.dict_id + "'>" + n.dict_item_name + "</option>");
				//alert(i + " : " + n.dict_id + " :: " + n.dict_item_name);
			});
		},"json");
	}); */
	
 	 $(function(){
		// 发送ajax请求
		var url = "${pageContext.request.contextPath }/dict_findByCode.action";
		
		// 获取客户级别
		var param = {"dict_type_code":"006"};
		$.post(url,param,function(data){
			var vsId = "${model.level.dict_id}";
			// 遍历
			$(data).each(function(){
				// 先获取值栈中的值，使用el表达式
				//alert(vsId);
				if(vsId == this.dict_id){	// 值栈中的id值和当前遍历的id值相同，让其被选中
					$("#levelID").append("<option value='" + this.dict_id + "' selected >" + this.dict_item_name + "</option>"); 
				} else{
					// jQuery 的  DOM 操作
					//$("#levelID").append("<option value='" + this.dict_id + "'>" + this.dict_item_code + "</option>");
					$("#levelID").append("<option value='" + this.dict_id + "'>" + this.dict_item_name + "</option>");
				}
			});
		},"json");
		
		// 获取来源
		param = {"dict_type_code":"002"};
		$.post(url,param,function(data){
			var vsId = "${model.source.dict_id}";
			// 遍历
			$(data).each(function(i,n){
				// i 表示迭代的下标，n 遍历的对象
				//alert(i+" : "+n.dict_item_code);
				//alert(this.dict_item_code);
				if(vsId == n.dict_id){	// 值栈中的id值和当前遍历的id值相同，让其被选中
					$("#sourcelID").append("<option value='" + n.dict_id + "' selected >" + n.dict_item_name + "</option>");
				}else{
					// jQuery 的  DOM 操作
					//$("#sourcelID").append("<option value='" + n.dict_id + "'>" + n.dict_item_code + "</option>");
					$("#sourcelID").append("<option value='" + n.dict_id + "'>" + n.dict_item_name + "</option>");
				}
			});
		},"json");
		
	});  
</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm"
		action="${pageContext.request.contextPath }/customer_findByPage.action"
		method=post>
		
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 客户列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>客户名称：</TD>
													<TD>
														<INPUT class=textbox id=sChannel2 style="WIDTH: 80px" maxLength=50 name="cust_name" value="${model.cust_name }">
													</TD>
													
													<TD>客户级别：</TD>
													<TD>
														<select name="level.dict_id" id="levelID">
															<option value="">--请选择--</option>
														</select>
													</TD>
													
													<TD>客户来源：</TD>
													<TD>
														<select name="source.dict_id" id="sourcelID">
															<option value="">--请选择--</option>
														</select>
													</TD>
													
													
													<TD>
														<INPUT class=button id=sButton2 type=submit value=" 筛选 " name=sButton2>
													</TD>
													
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>客户名称</TD>
													<TD>客户级别</TD>
													<TD>客户来源</TD>
													<TD>联系人</TD>
													<TD>电话</TD>
													<TD>手机</TD>
													<TD>操作</TD>
												</TR>
												<c:forEach items="${pageBean.beanList }" var="customer">
												<TR
													style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD>${customer.cust_name }</TD>
													
													<TD>${customer.level.dict_item_name }</TD>
													<TD>${customer.source.dict_item_name }</TD>
													
													<TD>${customer.cust_linkman }</TD>													
													<TD>${customer.cust_phone }</TD>
													<TD>${customer.cust_mobile }</TD>
													<TD>
													<a href="${pageContext.request.contextPath }/customer_initUpdate.action?cust_id=${customer.cust_id}">修改</a>
													&nbsp;&nbsp;
													<a href="${pageContext.request.contextPath }/customer_delete.action?cust_id=${customer.cust_id}" onclick="return window.confirm('确定删除客户  ${customer.cust_name }?')">删除</a>
													</TD>
												</TR>
												
												</c:forEach>

											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD>
										<!-- 静态引入 -->
										<%@ include file="/jsp/page.jsp" %>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
