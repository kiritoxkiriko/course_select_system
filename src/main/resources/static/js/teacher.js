$(".btn-delete").click(function () {
    var courseId=Number($(this).attr("courseId"))
    $.ajax({
            async: false,
            data: {courseId},
            type: "POST",
            dataType: 'json',
            timeout: 1000,
            cache: false,
            error: function () {
                alert("请求失败");
            },
            success: function (data) {
                console.log('删除成功')
                if(!data){
                    alert("删除失败")
                }else{
                    alert("删除成功")
                    window.location.reload()
                }
            },
            url: "/api/course/remove",
        }
    )
})