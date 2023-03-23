

chat();


function chat() {
    $.ajax({
        url: "/postchat",
        type: "POST",
        data: "Please recommend today's 00000 ",

        success: function(response){
            let answer1 = JSON.stringify(response[0])
            let answer2= JSON.parse(answer1)
            const answer3= answer2.text
            
            document.querySelector('.box').innerHTML = answer3;
            
        }
    })
}