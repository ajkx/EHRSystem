<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/menu.css" />
		<script type="text/javascript">
			$(document).ready(function () {
				$(".sub-module li").click(function(){
					$('.sub-module .active').removeClass("active");
					$(this).addClass("active");
				});

				$('.collapse').on("show.bs.collapse",function(){
					$(this).siblings("div").addClass("on");
				});

				$('.collapse').on("hide.bs.collapse",function(){
					$(this).siblings("div").removeClass("on");
				});
			});
		</script>
	</head>
	<body>
		<div id="menu">
			<ul id="module">
				<c:forEach items="${menu}" var="map" varStatus="status">
					<li>
						<div class="module-name" data-toggle="collapse" data-parent="#module" href="#col${status.index}">
							<i class="item-icon ${map.key.icon}" aria-hidden="true"></i>${map.key.name}
							<i class="icon-angle-down fa fa-angle-down" aria-hidden="true"></i>
						</div>
						<ul class="sub-module collapse" id="col${status.index}">
						<c:forEach items="${map.value}" var="list">
							<li><a class="link-item" href="${list.url}" target="content">${list.name}</a></li>
						</c:forEach>
						</ul>
					</li>
				</c:forEach>
			</ul>
		</div>
	</body>

</html>