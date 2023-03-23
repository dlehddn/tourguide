function login() {
   let mid = document.querySelector(".mid").value
   let mpassword = document.querySelector(".mpassword").value

   $.ajax({
        type: "GET",
        url: "/member/getmember",
        data: {
           "mid": mid,
           "mpassword":mpassword
        },
        success:function(response){
            if(response =="1" ){
                alert("로그인에 성공했습니다.")
        
                
            }else if(response == "2"){
                alert("비밀번호가 틀렸습니다.")}
            else alert("존재하지 않는 회원정보입니다.")
        },
        error: function (e) {
            
            console.log("ERROR : ", e);
        }
   })
    
    
    



}