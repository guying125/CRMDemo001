<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE><s:property value="#saleVisit==null?'添加':'修改'" />客户拜访</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>

<!-- 日期插件，使用jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/datepicker/jquery.datepick.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/datepicker/jquery.datepick.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/datepicker/jquery.datepick-zh-CN.js"></script>

<script type="text/javascript">
	$(function(){
		//使用class属性处理  'yy-mm-dd' 设置格式"yyyy/mm/dd"
		$('#timeId').datepick({dateFormat: 'yy-mm-dd'}); 
		$('#nextTimeId').datepick({dateFormat: 'yy-mm-dd'}); 
		var svId = "${saleVisit.customer.cust_id}";
		// 发送ajax请求
		var url = "${pageContext.request.contextPath }/customer_findAll.action";
		$.post(url,function(data){
			$(data).each(function(){
				if(this.cust_id == svId){
					$("#customerId").append("<option value='"+this.cust_id+"' selected='selected'>"+this.cust_name+"</option>");
				}else{
					$("#customerId").append("<option value='"+this.cust_id+"' >"+this.cust_name+"</option>");
				}
			});
		},"json");
	});
	
	$(function(){
		// 发送异步请求，获取到所有的用户
		var url = "${pageContext.request.contextPath }/user_findAll.action";
		// var param = ; 
		var uid = "${existUser.user_id}";
		$.post(url, function(data){
			$(data).each(function(i,n) {
				if(n.user_id == uid){
					$("#userId").append("<option value='"+ n.user_id +"' selected >"+ n.user_name +"</option>");
				}else{
					//$("#userId").append("<option value='"+ n.user_id +"'>"+ n.user_name +"</option>");
				}
			});
		}, "json");
	});
</script>

<style type="text/css">
	#userName {
		background-color: #DDDDDD;
	}
</style>

</HEAD>
<BODY>
	<FORM id=form1 name=form1 action="${pageContext.request.contextPath }/saleVisit_save.action" method=post>
		<!-- 隐藏域回显当前编辑的拜访记录id -->
		<input  type="hidden"  name="visit_id" value="${saleVisit.visit_id}"  />
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
								<TD class=manageHead>当前位置：客户拜访管理 &gt; <s:property value="#saleVisit==null?'添加':'修改'" />客户拜访</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=5  border=0>
							<tr>
								<td>客户名称：</td>
								<td>
									<select name="customer.cust_id" id="customerId">
										<!-- <option value="">--请选择--</option> -->
									</select>
								</td>
								
								<TD>业 务 员：</TD>
								<c:if test="${saleVisit==null }">
									<td>
										<select name="user.user_id" id="userId">
											<!-- <option value="">--请选择--</option> -->
										</select>
									</td>
								</c:if>
								<!-- 确保业务员不能被修改 -->
								<c:if test="${saleVisit!=null }">
									<td>
										
										<input type="hidden" name="user.user_id" id="uId" value="${saleVisit.user.user_id }" />
										<input name="user.user_name" id="userName" value="${saleVisit.user.user_name }" readonly="readonly" />
										<%-- <select name="user.user_id" id="userId">
											<!-- <option value="">--请选择--</option> -->
										</select> --%>
									</td>
								</c:if>
								
							</tr>
							
							<TR>
								<td>被拜访人：</td>
								<td>
									<INPUT class=textbox style="WIDTH: 180px" maxLength=50 name="visit_interviewee" value="${saleVisit.visit_interviewee }">
								</td>
								<td>拜访时间：</td>
								<td>
									<INPUT class=textbox id="timeId" style="WIDTH: 180px" name="visit_time" readonly="readonly" value="${saleVisit.visit_time_s }">
								</td>
							</TR>
							
							<TR>
								<td>拜访地点：</td>
								<td>
									<INPUT class=textbox style="WIDTH: 180px" maxLength=50 name="visit_addr" value="${saleVisit.visit_addr }">
								</td>
								<td>下次拜访时间 ：</td>
								<td>
									<INPUT class=textbox id="nextTimeId" style="WIDTH: 180px" maxLength=50  readonly="readonly" name="visit_nexttime" value="${saleVisit.visit_nexttime_s }">
								</td>
								
							</TR>
							
							<TR>	
								<td>拜访详情 ：</td>
								<td>
									<textarea rows="5" cols="26" name="visit_detail" >${saleVisit.visit_detail }</textarea>
								</td>
							</TR>
							
							<tr>
								<td></td>
								<td>
									<INPUT class=button id=sButton2 type=submit value="保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
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
