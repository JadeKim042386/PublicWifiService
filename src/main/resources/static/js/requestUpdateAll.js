function requestUpdateAll() {
    $.ajax({
        url: "/public_wifi/apiUpdateAll",
        type: "GET",
        beforeSend: function () {
            $('html').css('cursor', 'wait');
        },
        success: function (result) {
            $('html').css('cursor', 'auto');
            console.log(result.resultCode);
            successAlert("업데이트를 완료했습니다.")
        },
        error: function (result) {
            $('html').css('cursor', 'auto');
            console.log(result.resultCode);
            failAlert("업데이트에 실패했습니다.")
        }
    });
}

function successAlert(message) {
    Swal.fire({
        icon: 'success',
        text: message
    })
}

function failAlert(message) {
    Swal.fire({
        icon: 'error',
        text: message
    })
}
