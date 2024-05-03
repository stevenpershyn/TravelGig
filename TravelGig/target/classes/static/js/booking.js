$(document).ready(function() {
	$(".cancelAction, .completeAction, .reviewAction").css({cursor: "pointer", color: "blue", textDecoration: "underline"});
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "http://localhost:8282/viewBooking",
		success: function(result) {
			$("#bookingList tr").not(".header").remove();
			$.each(result, function(key1, value1) {
				var actionsHtml = "<td class='actions'>" +
					"<span class='cancelAction'>Cancel</span> | " +
					"<span class='completeAction'>Complete</span> | " +
					"<span class='reviewAction'>Reviews</span>" +
					"</td>";
				$("#bookingList").append("<tr><td>" + value1.bookingId + "</td><td>" + value1.hotelId + "</td><td>" + value1.hotelRoomId + "</td><td>" + value1.noRooms + "</td><td>" + value1.checkInDate.substring(0, 10) + "</td><td>" + value1.checkOutDate.substring(0, 10) + "</td><td>" + value1.bookedOnDate.substring(0, 10) + "</td><td>" + value1.status + "</td><td>" + value1.price + "</td><td>" + value1.discount + "</td><td>" + value1.roomType + "</td>" + actionsHtml + "<td></td></tr>")
			});
		},
		error: function(e) {

		}
	});
	$("#bookingList").on("click", ".reviewAction", function() {
		var bookingRow = $(this).closest("tr");
		var bookingId = bookingRow.find("td:first").text();
		var reviewTd = bookingRow.find("td:last");
		$.ajax({
			type: "GET",
			contentType: "application/json",
			url:"http://localhost:8282/booking/"+bookingId,
			success:function(result){
				console.log(result);
				console.log(result.review.text);
				reviewTd.text(result.review.text);
			},
			error:function(e){
				
			}
		});
	});
	$("#bookingList").on("click", ".cancelAction", function() {
		var bookingRow = $(this).closest("tr");
		var bookingId = bookingRow.find("td:first").text();
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url:"http://localhost:8282/saveCancelBooking/"+bookingId,
			success: function (){
				location.reload();
			},
			error: function(e){
				location.reload();
			}
		});
	});
	$("#bookingList").on("click", ".completeAction", function() {
		var bookingRow = $(this).closest("tr");
		var bookingId = bookingRow.find("td:first").text();
		$("#review_bookingId").val(bookingId);
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url:"http://localhost:8282/saveCompleteBooking/"+bookingId,
			success: function (){
				$("#reviewModal").modal('show');
			},
			error: function(e){
				location.reload();
			}
		});
	});
	$("#reviewModal").on("click", "#saveReviewBtn", function(){
		var bookingId = parseInt($("#review_bookingId").val());
		var starRating = parseFloat($("#starRating").val());
		var text = $("#reviewText").val();
		var review = {"text":text, "overallRating":starRating};
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:8282/review",
			data: JSON.stringify(review),
			dataType: 'json',
			success: function(result){
				var reviewId = parseInt(result.reviewId);
				$.ajax({
					type: "GET",
					contentType: "application/json",
					url:"http://localhost:8282/booking/"+bookingId,
					success:function(bookingResult){
						bookingResult.review = {"reviewId": reviewId, "text": text, "overallRating": starRating};
						$.ajax({
							type: "POST",
							contentType: "application/json",
							url: "http://localhost:8282/booking",
							data: JSON.stringify(bookingResult),
							success: function(){
								location.reload();
							},
							error: function(e){
								location.reload();
							}
						});
					},
					error: function(e){
						location.reload();
					}
				});
			},
			error: function(e){
				location.reload();
			}
		});
	});
});