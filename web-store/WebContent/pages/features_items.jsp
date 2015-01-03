<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="/WEB-INF/mytags.tld" prefix="myTag" %>

<jsp:include page="user/user_scripts.jsp"></jsp:include>
<myTag:printFeaturesItems userId="${sessionScope.userId }"/>	
