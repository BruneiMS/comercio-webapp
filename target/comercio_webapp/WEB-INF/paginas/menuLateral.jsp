<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/produto" var="produto"></spring:url>
<spring:url value="/cliente" var="cliente"></spring:url>
	
<div class="col-lg-3 mt-5">

	<!-- <h1 class="my-4">Loja Resource</h1> -->
	<div class="list-group">
		<a href="${produto }" class="list-group-item">Produto</a>
		<a href="${cliente }" class="list-group-item">Cliente</a>
	</div>

 </div>