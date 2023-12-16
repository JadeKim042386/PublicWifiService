function deleteTableRow(url, redirect_url) {
    $(document).on("click", ".delete", function() {
        var tr = $(this).parents("tr");
        var length = $(this).parents("tbody").find("tr").length;
        $.ajax({
            url: url + tr.find("td").eq(0).text(),
            type: "GET",
            beforeSend: function () {
                $('html').css('cursor', 'wait');
            },
            success: function (result) {
                tr.remove();
                $('html').css('cursor', 'auto');
                if (length === 1) {
                    var page = parseInt($('.page-link.disabled')[1].text);
                    page = Math.max(page - 1, 0);
                    successAlertThenRedirect("삭제를 완료했습니다.", redirect_url + '?page=' + page);
                } else {
                    successAlert("삭제를 완료했습니다.");
                }
                console.log(result.resultCode);
            },
            error: function (result) {
                $('html').css('cursor', 'auto');
                console.log(result.responseText);
                failAlert("삭제에 실패했습니다.")
            }
        });
    });
}