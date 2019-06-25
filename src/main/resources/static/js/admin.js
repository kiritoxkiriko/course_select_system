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
            url: "/api/registrar/student/add",
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
            url: "/api/registrar/professor/add",
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
            url: "/api/registrar/student/remove",
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
            url: "/api/registrar/professor/remove",
        }
    )
})
$(".btn-stu-modify").click(function () {
    var parent=$(this).parent().parent()
    var id=parent.find(".id").text()
    var name=parent.find(".name").text()
    var password=parent.find(".password").text()
    var college=parent.find(".college").attr("college_id")
    var parent=$("#form-stu").parent()
    parent.find("input[name='id']").val(id)
    parent.find("input[name='name']").val(name)
    parent.find("input[name='pwd']").val(password)
    parent.find(".option-stu").each(function () {
        var collegeId=$(this).val()
        if(collegeId==college){
            $(this).attr("selected","selected")
        }else {
            $(this).removeAttr("selected")
        }
    })

})
$(".btn-pro-modify").click(function () {
    var parent=$(this).parent().parent()
    var id=parent.find(".id").text()
    var name=parent.find(".name").text()
    var password=parent.find(".password").text()
    var college=parent.find(".college").attr("college_id")
    var parent=$("#form-pro").parent()
    parent.find("input[name='id']").val(id)
    parent.find("input[name='name']").val(name)
    parent.find("input[name='pwd']").val(password)
    parent.find(".option-pro").each(function () {
        var collegeId=$(this).val()
        if(collegeId==college){
            $(this).attr("selected","selected")
        }else {
            $(this).removeAttr("selected")
        }
    })

})
