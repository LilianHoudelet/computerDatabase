<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${ computerId }
                    </div>
                    <h1>Edit Computer</h1>
						<form:form action="" method="POST" modelAttribute="AddComputerDTO">
							<fieldset>
								
								<div class="form-group">
									<form:label path="nom">Computer name</form:label> 
									<form:input type="text" class="form-control" id="computerName"
										placeholder="Computer name" name="computerName" path="nom" required="required"
										value = "${computerName}"/>
								</div>
								<div class="form-group">
									<form:label path="dateSortie">Introduced date</form:label> 
									<form:input type="date" class="form-control" id="introduced"
										placeholder="Introduced date" name="introduced" path="dateSortie"
										value = "${introducedDate}"/>
								</div>
								<div class="form-group">
									<form:label path="dateRetrait">Discontinued date</form:label>
									<form:input type="date" class="form-control" id="discontinued"
										placeholder="Discontinued date" name="discontinued" path="dateRetrait"
										value = "${discontinuedDate}"/>
								</div>
							<div class="form-group">
								<form:label path="companyId">Company</form:label> 
								<form:select class="form-control" id="companyId" name="companyId" path="companyId">
									<option value="0"> -- </option>
									<c:forEach items="${companyList}" var="current">
										
										<c:if test="${companyName == current.getName()}">
											<option value="${current.getId()}" selected> ${current.getName()} </option>
										</c:if>
										<c:if test="${companyName != current.getName()}">
											<option value="${current.getId()}"> ${current.getName()} </option>
										</c:if>
									</c:forEach>
								</form:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Update" class="btn btn-primary">
							or <a href="/cdb/dashboard" class="btn btn-default">Cancel</a>
						</div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
    <footer>
		<script src="js/jquery.min.js"></script>
		<script src="js/validation.js"></script>
	</footer>
</body>
</html>