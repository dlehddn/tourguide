logincheck()
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
				$('.rinfo').html(response);
			},
			error: function(xhr, status, error) {
				alert("에러 발생: " + error);
			}
		});
	});
});

function recommendinfo(){

	let rinfo = document.querySelector('.rinfo').innerHTML;
	let rid = document.querySelector('.rid').value;

	console.log(rinfo, rid)
     $.ajax({
        type: "POST",
        url: "/setinfo",
		data:{"rinfo" : rinfo, "rid" : rid},
        success:function(response){
           if(response!==0){
                alert("여행정보 저장에 성공했습니다.")
                
            }else{alert("여행정보 저장에 실패했습니다.")}  
        },
        error: function (e) {
            
            console.log("ERROR : ", e);
        }
    }); 
}
function logincheck() {
	$.ajax({
		type: "GET",
		url: "/logincheck",
		success:function(response){
			if(response != "0") {
			return;
			}
			else {
				alert(response)
				alert("로그인 후 이용해주세요")
                location.href = "/member/login"
			}
		}

		})
}