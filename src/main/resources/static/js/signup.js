
function signup(){

    let formData = new FormData(document.querySelector("#signupForm"));
    

     $.ajax({
        type: "POST",
        url: "/member/setmember",
        contentType:false, //기본설정값
        processData: false, //기본설정값
        data:formData,
        success:function(response){
            if(response!==0){
                alert("회원가입이 완료되었습니다.")
                location.href = "/member/login"
            }else{alert("회원가입 실패")}  
        },
        error: function (e) {
            
            console.log("ERROR : ", e);
        }



    }); 





}