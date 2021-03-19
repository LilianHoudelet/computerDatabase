<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
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
			<a class="navbar-brand" href="/cdb/ComputerServlet"> Application
				- Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${computerNumber} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">



					<a class="btn btn-success" id="addComputer"
						href="/cdb/AddComputerServlet">Add Computer</a> <a
						class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
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
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${ComputerList}" var="current">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="0"></td>
							<td><a href="/cdb/editComputer.jsp" onclick="">
								<c:out value="${current.getName()}" /></a>
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
					<a href="?page=${1}" aria-label="Previous" name="numPage" value="1"> 
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
				
				<c:if test="${page == 1}">
					<li>
						<a href="?page=${page-1}" aria-label="Previous" name="numPage" value="${page-1}"> 
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
				</c:if>
				
				<c:forEach var="i" begin="0" end="4" step="1">
					<li><a href="?page=${index+i-2}" name="numPage" value="${index+i-2}">${index+i-2}</a></li>
				</c:forEach>
				
				<c:if test="${page != maxPage}">
					<a href="?page=${page+1}" aria-label="Next" value="${page+1}"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				</c:if>
				
				<li>
					<a href="?page=${maxPage}" aria-label="Next" name="numPage" value="${page}"> 
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