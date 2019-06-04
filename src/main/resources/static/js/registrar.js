$("#form-stu").submit(function (e) {
    e.preventDefault()
    $.ajax({
            async: false,
            data: $(this).serializeArray(),
            type: "POST",
            dataType: 'json',
            timeout: 1000,
            cache: false,
            error: function () {
                alert("请求失败");
            },
            success: function (data) {
                console.log('请求成功')
                if(!data){
                    alert("修改失败")
                }else{
                    window.location.reload()
                }
            },
            url: "/api/student/add",
        }
    )
    return false;
})
$("#form-pro").submit(function (e) {
    e.preventDefault()
    $.ajax({
            async: false,
            data: $(this).serializeArray(),
            type: "POST",
            dataType: 'json',
            timeout: 1000,
            cache: false,
            error: function () {
                alert("请求失败");
            },
            success: function (data) {
                console.log('请求成功')
                if(!data){
                    alert("修改失败")
                }else{
                    window.location.reload()
                }
            },
            url: "/api/professor/add",
        }
    )
    return false;
})
$(".btn-stu-delete").click(function () {
    var id=$(this).attr("stu_id")
    $.ajax({
            async: false,
            data: {id:id},
            type: "POST",
            dataType: 'json',
            timeout: 1000,
            cache: false,
            error: function () {
                alert("请求失败");
            },
            success: function (data) {
                console.log('请求成功')
                if(!data){
                    alert("删除失败")
                }else{
                    window.location.reload()
                }
            },
            url: "/api/student/remove",
        }
    )
})
$(".btn-pro-delete").click(function () {
    var id=$(this).attr("pro_id")
    $.ajax({
            async: false,
            data: {id:id},
            type: "POST",
            dataType: 'json',
            timeout: 1000,
            cache: false,
            error: function () {
                alert("请求失败");
            },
            success: function (data) {
                console.log('请求成功')
                if(!data){
                    alert("删除失败")
                }else{
                    window.location.reload()
                }
            },
            url: "/api/professor/remove",
        }
    )
})
