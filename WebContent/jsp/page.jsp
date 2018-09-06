<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<SPAN id=pagelink>

	<DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
		共[<B>${pageBean.totalCount}</B>]条记录,共[<B>${pageBean.totalPage}</B>]页
		,每页显示 <select name="pageSize">

			<option value="2"
				<c:if test="${pageBean.pageSize==2 }">selected</c:if>>2</option>
			<option value="3"
				<c:if test="${pageBean.pageSize==3 }">selected</c:if>>3</option>
		</select> 条

		<c:if test="${pageBean.pageCode > 1 }">
													[<A href="javascript:to_page(${pageBean.pageCode-1})">前一页</A>]
												</c:if>
		<B>${pageBean.pageCode}</B>

		<c:if test="${pageBean.pageCode < pageBean.totalPage }">
													[<A href="javascript:to_page(${pageBean.pageCode+1})">后一页</A>] 
												</c:if>
		&nbsp;&nbsp;&nbsp;&nbsp; 到第 <input type="text" size="3" id="page"
			name="pageCode" /> 页 <input type="button" value="Go"
			onclick="to_page()" />
	</DIV>
</SPAN>