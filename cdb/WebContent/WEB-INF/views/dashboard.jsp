<%@ page language="java" contentType="text/html" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="/cdb/dashboard"> Application
				- Computer Database </a>
				<div class="pull-right">
	      		  <a href="?lang=en"><fmt:message key="label.lang.en" /></a>
	      		  <a href="?lang=fr"><fmt:message key="label.lang.fr" /></a>
	        </div>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${computerNumber} <fmt:message key="label.dashboard.computerFound"/></h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
				
					<form id="searchForm" action="?search=${ search }" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="<fmt:message key="label.dashboard.searchName"/>" value="${ search }"/> 
						<input type="submit" id="searchsubmit" value="<fmt:message key="label.dashboard.searchNameFilter"/>"
							class="btn btn-primary" />
					
						<a class="btn btn-default" id="sortList"
							href="?sortedOn=id"><fmt:message key="label.dashboard.initFilter"/></a>
					</form>
				</div>
				
				<div class="pull-right">
				
					
					<a class="btn btn-success" id="addComputer"
						href="/cdb/addComputer"><fmt:message key="label.dashboard.add"/></a> 
					<a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();"><fmt:message key="label.dashboard.edit"/></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="/cdb/dashboard" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - 
							<a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();"> 
								<i class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						
						<th><a href="?sortedOn=computerName" onclick="">
									<fmt:message key="label.dashboard.computerName"/></a></th>
									
						<th><a href="?sortedOn=introduced" onclick="">
									<fmt:message key="label.dashboard.introducedDate"/></a></th>
						<!-- Table header for Discontinued Date -->
						<th><a href="?sortedOn=discontinued" onclick="">
									<fmt:message key="label.dashboard.discontinuedDate"/></a></th>
						<!-- Table header for Company -->
						<th><a href="?sortedOn=company" onclick="">
									<fmt:message key="label.dashboard.company"/></a></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
						<c:forEach items="${computerList}" var="current">
							<tr>
								<td class="editMode">
									<input type="checkbox" name="cb"
										class="cb" value="${ current.getId() }">	
								</td>
								<td><a href="/cdb/editComputer?id=${ current.getId() }" onclick="">
									<c:out value="${ current.getName()}" /></a>
								</td>
								<td><c:out value="${current.getDateSortie()}" /></td>
								<td><c:out value="${current.getDateRetrait()}" /></td>
								<td><c:out value="${current.getCompany()}" /></td>
	
							</tr>
						</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
			
				<li>
					<a href="?page=${1}" aria-label="Previous" > 
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
				
				<c:if test="${page != 1}">
					<li>
						<a href="?page=${page-1}" aria-label="Previous"> 
							<span aria-hidden="true"><fmt:message key="label.dashboard.previous"/></span>
						</a>
					</li>
				</c:if>
				
				<c:forEach var="i" begin="0" end="4" step="1">
					<c:if test="${(index-2+i <= maxPage) && (index-2+i > 0)}">
						<li><a href="?page=${index+i-2}">${index+i-2}</a></li>
					</c:if>
				</c:forEach>
				
				<c:if test="${page != maxPage}">
				<li>
					<a href="?page=${page+1}" aria-label="Next"> 
						<span aria-hidden="true"><fmt:message key="label.dashboard.next"/></span>
					</a>
				</li>
				</c:if>
				
				<li>
					<a href="?page=${maxPage}" aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
				
			</ul>
		
		<div class="btn-group btn-group-sm pull-right" role="group">
			<form action="">
				<button type="submit" class="btn btn-default" name="nbEltsParPage" value="10">10</button>
				<button type="submit" class="btn btn-default" name="nbEltsParPage" value="50">50</button>
				<button type="submit" class="btn btn-default" name="nbEltsParPage" value="100">100</button>
			</form>
		</div>
	</div>
	</footer>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>