function deleteTableRow(url) {
    $(document).on("click", ".delete", function() {
        var tr = $(this).parents("tr");
        $.ajax({
            url: url + tr.find("td").eq(0).text(),
            type: "GET",
            beforeSend: function () {
                $('html').css('cursor', 'wait');
            },
            success: function (result) {
                $('html').css('cursor', 'auto');
                tr.remove();
                console.log(result.resultCode);
                successAlert("삭제를 완료했습니다.")
            },
            error: function (result) {
                $('html').css('cursor', 'auto');
                console.log(result.responseText);
                failAlert("삭제에 실패했습니다.")
            }
        });
    });
}