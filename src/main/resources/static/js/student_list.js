$(".btn-enter").click(function () {
    var student=$(this).parent().parent()
    var score=$("#input-score").val()
    var student_id=$(".student").attr("student_id")
    var course_id=$(".main").attr("course_id")
    $.ajax({
            async: false,
            data: {course_id, student_id,score},
            type: "GET",
            dataType: 'json',
            timeout: 1000,
            cache: false,
            error: function () {
                alert("请求失败");
            },
            success: function (data) {
                console.log('请求成功')
                if(!data){
                    alert("添加失败")
                }else{
                   // window.location.reload()
                }
            },
            url: "/api/courseInfo/add",
        }
    )
})

$(".btn-modify").click(function () {
    var elem1="<input type=\"number\" id=\"input-score\" placeholder=\"输入成绩\" style=\"width: 80px\">"
    var elem2="<button class=\"btn btn-success btn-submit btn-xs\">提交</button>"
    $(".btn-modify").remove()
    $(".score").remove()
    $(".th-score").append(elem1)
    $(".th-score").append(elem2)
})

$(".btn-submit").click(function () {
    var score=$("#input-score").val()
    var student_id=$(".student").attr("student_id")
    var course_id=$(".main").attr("course_id")
    $.ajax({
            async: false,
            data: {course_id, student_id,score},
            type: "GET",
            dataType: 'json',
            timeout: 1000,
            cache: false,
            error: function () {
                alert("提交失败");
            },
            success: function (data) {
                console.log('提交成功')
                if(!data){
                    alert("提交失败")
                }else{
                    window.location.reload()
                }
            },
            url: "/api/courseInfo/modify",
        }
    )
})