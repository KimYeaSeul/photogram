// (1) 회원정보 수정
function update(event, userId) {
    event.preventDefault(); //폼태그 액션을 막는것
    //
    let data = $("#profileUpdate").serialize();

    $.ajax({
        type:"PUT",
        url:`/api/user/${userId}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(res=>{ // HTTPStatus 상태코드 200번대
        console.log("성공 ", res);
        location.href=`/user/${userId}`;
    }).fail(error =>{ // HTTPStatus 상태코드 200번대 아닐때
        if(error.data == null){
            alert(error.responseJSON.message);
        }else{
            alert(JSON.stringify(error.responseJSON.data));
        }
        // console.log("실패 ", error.responseJSON.data.name);
    });
}