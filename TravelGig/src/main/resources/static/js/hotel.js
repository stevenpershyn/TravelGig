$(document).ready(function() {
	$("#searchBtn").click(function() {
		var searchLocation = $("#searchLocation").val();
		$.ajax({
			type: "GET",
			contentType: "application/json",
			url: "http://localhost:8282/findHotel/"+searchLocation,
			success: function(result) {
				$("#hotelTbl tr").not(".header").remove();
				$.each(result, function(key1, value1) {
					var amenities = value1.amenities.map(function(amenity){
						return amenity.name;
					}).join(", ");
					$("#hotelTbl").append("<tr><td>" + value1.hotelName + "</td><td>" + value1.averagePrice + "</td><td>" + value1.starRating + "</td><td>" + amenities + "</td><td><img class='imgLink' height='300' width='300' src="+value1.imageURL+"></td></tr>")
				});
			},
			error: function(e) {

			}
		});
	});
	
	var response;
	
	
	$("#hotelTbl").on('click','.imgLink', function(){
		var hotelName = $(this).parent().parent().children("td").eq(0).text();
		var searchText = $("#searchLocation").val();
		$.ajax({
			type:"GET",
			contentType: "application/json",
			url: "http://localhost:8282/findHotel/"+searchText,
			success: function(result){
				response = result;
				var hotelRoomTypes = response[0].hotelRooms;
				var hotelId = response[0].hotelId;
				
				$("#myModal").modal("toggle");
				$("#modal_hotelId").val(hotelId);
				$("#modal_hotelName").val(hotelName);
				$("#modal_noGuests").val($("#noGuests").val());
				$("#modal_checkInDate").val($("#checkInDate").val());
				$("#modal_checkOutDate").val($("#checkOutDate").val());
				$("#modal_noRooms").val($("#noRooms").val());
				$("#select_roomTypes").empty();
				for(var i=0; i < hotelRoomTypes.length; i++){
					$("#select_roomTypes").append($("<option />").val(hotelRoomTypes[i].type.name).text(hotelRoomTypes[i].type.name));
				}
			},
			error: function(e) {
				
			}
		});
	});
	
	var originalGuestForm = $("#guestDetailsModal form").clone();
	var numGuests;
	
	//Figure out where this will go in the project
	$("#myModal").on('click', '#guestBtn', function() {
		numGuests = parseInt($("#modal_noGuests").val());
		if(!isNaN(numGuests) && numGuests > 0){
			originalGuestForm.find("input, select").val("");
			$("#guestDetailsModal .modal-body").empty();
			for(var i = 0; i < numGuests; i++){
				var guestForm = originalGuestForm.clone();
				guestForm.attr('id', 'guestForm_' + i);
				guestForm.find(".modal-title").text("Guest Details - Guest " + (i + 1));
				$("#guestDetailsModal .modal-body").append(guestForm);
				if(i < numGuests - 1){
					var divider = $("<hr>");
                	divider.css("border-top", "2px solid black");
					$("#guestDetailsModal .modal-body").append(divider);
				}
			}
			$("#guestDetailsModal").modal('show');
		} else {
			alert("Please enter a valid number of guests.");
		}
 	});
 	
 	
 	var guestArray = [];
 	$("#guestDetailsModal").on('click', '#prepareBookingBtn', function(){
		 for(var i = 0; i < numGuests; i++){
		 	var firstName = $("#guestForm_" + i + " #guestFirstName").val();
		 	var lastName = $("#guestForm_" + i + " #guestLastName").val();
		 	var gender = $("#guestForm_" + i + " #guestGender").val();
		 	var age = parseInt($("#guestForm_" + i + " #guestAge").val());
		 	var guest = {"firstName": firstName, "lastName": lastName, "gender": gender, "age": age};
		 	guestArray.push(guest);
		 };
		 
		 
		 $("#guestDetailsModal").modal('hide');
		 var hotelId = parseInt($("#modal_hotelId").val());
		 var hotelRoomTypes = response[0].hotelRooms;
		 var hotelRoomId = hotelRoomTypes.find(room => room.type.name === $("#select_roomTypes").val()).hotelRoomId;
		 var hotelName = $("#modal_hotelName").val();
		 var noGuests = parseInt($("#modal_noGuests").val());
		 var noRooms = parseInt($("#modal_noRooms").val());
		 var checkInDate = new Date($("#modal_checkInDate").val());
		 var checkOutDate = new Date($("#modal_checkOutDate").val());
		 var diff = checkOutDate - checkInDate;
		 var dayDiff = diff / (1000 * 60 * 60 * 24);
		 var formatCheckInDate = checkInDate.toISOString();
		 var formatCheckOutDate = checkOutDate.toISOString();
		 var roomType = $("#select_roomTypes").val();
		 var price = 0;
		 var averagePrice = response[0].averagePrice;
		 var discount = response[0].discount * averagePrice / 100;
		 var nights = dayDiff;
		 var basePrice = averagePrice * noRooms * nights;
		 price = basePrice - discount;
		 formatCheckInDate = formatCheckInDate.substring(0,10);
		 formatCheckOutDate = formatCheckOutDate.substring(0,10);
		 console.log(price);
		 console.log(nights);
		 
		 $.ajax({
			 type: "GET",
			 url: "/getMobile",
			 success: function(mobile){
				 $("#booking_customerMobile").val(mobile);
			 },
			 error: function(error){
				 console.error("Error fetching mobile number: " + error);
			 }
		 });
		 
		 $("#booking_hotelId").val(hotelId);
		 $("#booking_hotelRoomId").val(hotelRoomId);
		 $("#booking_hotelName").val(hotelName);
		 $("#booking_noGuests").val(noGuests);
		 $("#booking_guests").val(guestArray);
		 $("#booking_noRooms").val(noRooms);
		 $("#booking_checkInDate").val(formatCheckInDate);
		 $("#booking_checkOutDate").val(formatCheckOutDate);
		 $("#booking_roomType").val(roomType);
		 $("#booking_discount").text(discount.toFixed(2));
		 $("#booking_price").text(price.toFixed(2));
		 
		 $("#bookingHotelRoomModal").modal();
		 console.log(hotelId);
		 
	 })
	 
	 $("#bookingHotelRoomModal").on('click', '#editBookingBtn', function(){
		 function toggleEditInputs(enable){
			var noRoomsInput = document.getElementById("booking_noRooms");
			var checkInDateInput = document.getElementById("booking_checkInDate");
        	var checkOutDateInput = document.getElementById("booking_checkOutDate");
        	var roomTypeInput = document.getElementById("booking_roomType");
        	
        	noRoomsInput.readOnly = !enable;
        	checkInDateInput.readOnly = !enable;
        	checkOutDateInput.readOnly = !enable;
        	roomTypeInput.readOnly = !enable; 
		 }
		 toggleEditInputs(true);
	 });
	 $("#bookingHotelRoomModal").on('click', '#saveBookingBtn', function(){
		 var userName;
		 $.ajax({
			 type: "GET",
			 url: "/getUsername",
			 success: function(username){
				 userName = username;
			 
		 var userEmail;
		 $.ajax({
			 type: "GET",
			 url: "/getEmail",
			 success: function(email){
				userEmail = email;
				var hotelId = parseInt($("#booking_hotelId").val());
		 		var hotelRoomId = parseInt($("#booking_hotelRoomId").val());
		 		var noRooms = parseInt($("#booking_noRooms").val());
		 		var customerMobile = $("#booking_customerMobile").val();
		 		var price = parseFloat($("#booking_price").text());
		 		var discount = parseFloat($("#booking_discount").text());
		 		var checkInDate = $("#booking_checkInDate").val();
		 		var checkOutDate = $("#booking_checkOutDate").val();
		 		var roomType = $("#booking_roomType").val();
		 		var bookedOnDate = new Date();
		 		var booking = {"hotelId": hotelId, "hotelRoomId": hotelRoomId, "noRooms": noRooms, "guests": guestArray,
		 		"checkInDate": checkInDate, "checkOutDate": checkOutDate, "bookedOnDate": bookedOnDate,
		 		"status": "upcoming", "price": price, "discount": discount, "customerMobile": customerMobile, "roomType": roomType,
		 		"userName": userName, "userEmail": userEmail, "taxRateInPercent": 0.07, "finalCharges": 0,
		 		"bonanzaDiscount": 0, "totalSavings": 0, "review": null};
		 		console.log(guestArray);
		 		$.ajax({
			 		type:"POST",
			 		contentType: "application/json",
			 		url: "http://localhost:8282/booking",
			 		data: JSON.stringify(booking),
			 		dataType: 'json',
			 		success: function(result){
				 		var bookingId = parseInt(result.bookingId);
				 		console.log("Booking: " + bookingId);
				 		$("#bookingHotelRoomModal").modal('hide');
				 		$("#myModal").modal('hide');
			 		},
			 		error: function(error){
				 		console.error("Error saving booking:", error);
			 		}
			 
		 		});
			 },
			 error: function(error){
				 console.error("Error fetching user name: " + error);
			 }
		 });
		 },
			 error: function(error){
				 console.error("Error fetching user name: " + error);
			 }
		 });
	 });	
	 
	
	$("#filterBtn").click(function() {
		var priceRange = parseInt($("#priceRange").val());
		var tblRow = $("#hotelTbl tr").not(".header");
		$(tblRow).show();
		
		var starRatingSelected = false;
		var amenitiesSelected = [];
		
		$.each($(".star_rating"), function(key, value) {
    		if ($(this).prop('checked') == true) {
     		 starRatingSelected = true;
      		 return false; 
    		}
  		});
  		$.each($(".hotel_amenity:checked"), function(key, value) {
    		amenitiesSelected.push($(this).val());
  		});
		$.each(tblRow, function(key1, value1) {
			var flag = 0;
			var flag1 = 0;
			var hotelPrice = parseInt($(value1).children("td").eq("1").text());
			var hotelRating = parseInt($(value1).children("td").eq("2").text());
			var amenities = $(value1).children("td").eq("3").text();
			
			if (hotelPrice > priceRange){
				$(this).hide();
			} else {
				if(!starRatingSelected || starRatingSelected && hotelRating == parseInt($(".star_rating:checked").val())){
					flag = 1;
				}
				
				var hotelAmenities = amenities.split(", ");
				
				if(amenitiesSelected.length === 0){
					flag1 = 1;
				} else {
					$.each(amenitiesSelected, function(key, selectedAmenity){
						if(hotelAmenities.indexOf(selectedAmenity) !== -1){
							flag1 = 1;
							return false;
						}
					});
				}
				if(flag == 0 || flag1 == 0){
					$(this).hide();
				}
			}
		});	
	});
});