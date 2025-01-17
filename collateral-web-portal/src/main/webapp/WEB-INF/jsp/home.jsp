<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home Page</title>
<link rel="stylesheet" href="/portal/css/bootstrap.min.css">
<link rel="stylesheet" href="/portal/css/name.css/" type="text/css" />
<script src="/portal/js/bootstrap.min.js"></script>
</head>
<body>
	<%-- <jsp:include page="./common/nav.jsp" /> --%>
	<%@ include file="common/header.jsp"%>

	<div class="container">

	<h1>
	
	<button type="button" class="btn btn-secondary btn-lg btn-block">ACTIVITIES</button></h1>


<div class="sub-container">
		<div class="row">
			<div class="col-lg-3">
			<a href="/portal/customerloan">
				<img class="image" src="/portal/image/img1.jpg" alt="Snow"
					style="width: 300px; height: 300px;">
					<p>Customer Loan Details</p></a>
			</div>
			<div class="col-lg-3">
			<a href="/portal/savecollateral">
				<img class="image" src="/portal/image/img3.jpg" alt="Mountains"
					style="width: 300px; height: 300px;">
					<p>Save the Collateral Details</p></a>
			</div>
			<div class="col-lg-3">
			<a href="/portal/riskassessment">
				<img class="image" src="/portal/image/img2.jpg" alt="Forest"
					style="width: 300px; height: 300px;">
					<p>Risk Assessment</p></a>
			</div>
			<div class="col-lg-3">
				<a href="/portal/getAll">
					<img class="image" src="/portal/image/img6.jpg" alt="Loan Application"
						style="width: 300px; height: 300px;">
					<p>Accept/Reject applications</p></a>
				</div>
			

		</div>


		
	</div>
	</div>
	
	<%--  <p>${sessionScope.token}</p> --%>
	<%@ include file="common/footer.jsp"%>
</body>
</html>