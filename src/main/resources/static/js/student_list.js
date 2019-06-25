$(".btn-enter").click(function () {
    var student=$(this).parent()
    var score=student.children("#input-score").val()
    var student_id=student.attr("student_id")
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
                   window.location.reload()
                }
            },
            url: "/api/courseInfo/add",
        }
    )
})

$(".btn-modify").click(function () {
    var student=$(this).parent()
    var elem1="<input type=\"number\" style=\"margin-right: 10px;width: 80px\" id=\"input-score\" placeholder=\"输入成绩\" >"
    var elem2="<button class=\"btn btn-success btn-submit btn-xs\">提交</button>"
    student.children(".btn-modify").remove()
    student.children(".score").remove()
    student.append(elem1)
    student.append(elem2)
})

$(document).on("click",".btn-submit",function () {
    console.log("提交按钮被点击")
    var student=$(this).parent()
    var score=student.children("#input-score").val()
    var student_id=student.attr("student_id")
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