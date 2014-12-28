<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<section>
	<div class="container">
		<div class="row">
			<div class="col-sm-3">
				<div class="left-sidebar">

					<jsp:include page="category.jsp" />
					<jsp:include page="brands.jsp" />
					<jsp:include page="price_range.jsp" />

					<div class="shipping text-center">
						<!--shipping-->
						<img src="../images/home/shipping.jpg" alt="" />
					</div>
					<!--/shipping-->

				</div>
			</div>

			<div class="col-sm-9 padding-right">
				<jsp:include page="features_items.jsp" />
				<jsp:include page="category_tab.jsp" />
				<jsp:include page="recommended_items.jsp" />
			</div>
		</div>
	</div>
</section>