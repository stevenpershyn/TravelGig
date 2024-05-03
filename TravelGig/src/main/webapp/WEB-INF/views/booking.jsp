<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false" %> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<head>
<meta charset="ISO-8859-1">
<title>Booking Display Page</title>
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='../js/booking.js'></script>
</head>
<body>
<div class="container">
<a href='login?logout'>Logout</a>
<a href='home'>Home</a>
</div>
<center>
<h1>See all the Bookings</h1>
<h2></h2>
</h3>
<p/>
<p/>	
<div id="listBooking">
	<table id='bookingList' border="1"><tr class='header'><th>Booking Id</th><th>Hotel Id</th><th>Hotel Room Id</th><th> Number of Rooms</th><th>Check In Date</th><th>Check Out Date</th><th>Booked Date</th><th>Status</th><th>Price</th><th>Discount</th><th>Room Type</th><th>Actions</th><th>Review</th>
</div>
</center>
<div class="modal" id="reviewModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Hotel Review</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<form>
					<div><input class="form-control" type="hidden" id="review_bookingId"/></div>
					<div class="form-group">
						<label for="starRating">Star Rating</label>
						<select class="form-control" id="starRating" required>
							<option value="">Select Star Rating</option>
							<option value="0.5">0.5</option>
							<option value="1.0">1.0</option>
							<option value="1.5">1.5</option>
							<option value="2.0">2.0</option>
							<option value="2.5">2.5</option>
							<option value="3.0">3.0</option>
							<option value="3.5">3.5</option>
							<option value="4.0">4.0</option>
							<option value="4.5">4.5</option>
							<option value="5.0">5.0</option>
						</select>
					</div>
					<div class="form-group">
						<label for="reviewText">Comment:</label>
						<textarea id="reviewText" name="comment" rows="4" cols="50"></textarea>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<input style="margin-top:25px" class="btn btn-saveReviewForm form-control btn-primary" type="button" id="saveReviewBtn" value="Save"/>
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>