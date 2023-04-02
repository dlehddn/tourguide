$(document).ready(function() {
	$('#recommend-btn').click(function() {
		var region = $('#travel-region').val();
		var days = $('#travel-days').val();

		if (region == "" || days == "") {
			alert("여행지역과 여행일수를 입력해주세요.");
			return;
		}

		$.ajax({
			type: "GET",
			url: "/recommend",
			data: {
				region: region,
				days: days
			},
			success: function(response) {
				$('#result').html(response);
			},
			error: function(xhr, status, error) {
				alert("에러 발생: " + error);
			}
		});
	});
});
