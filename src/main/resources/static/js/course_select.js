const studyProgramId = Number($('.main').attr("study_program_id"))

$('.btn-primary-add').click(function () {
    const courseId = Number($(this).attr("course_id"))
    $.ajax({
            async: false,
            data: {course_id: courseId, study_program_id: studyProgramId},
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
            url: "/api/studyProgram/addPrimaryCourse",
        }
    )
})

$('.btn-secondary-add').click(function () {
    const courseId=Number($(this).attr("course_id"))
    $.ajax({
            async: false,
            data: {course_id: courseId, study_program_id: studyProgramId},
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
            url: "/api/studyProgram/addSecondaryCourse",
        }
    )
})

$('.btn-primary-delete').click(function () {
    const courseId=Number($(this).attr("course_id"))
    $.ajax({
            async: false,
            data: {course_id: courseId, study_program_id: studyProgramId},
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
                    alert("删除失败")
                }else{
                    window.location.reload()
                }
            },
            url: "/api/studyProgram/removePrimaryCourse",
        }
    )
})

$('.btn-secondary-delete').click(function () {
    const courseId=Number($(this).attr("course_id"))
    $.ajax({
            async: false,
            data: {course_id: courseId, study_program_id: studyProgramId},
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
                    alert("删除失败")
                }else{
                    window.location.reload()
                }
            },
            url: "/api/studyProgram/removeSecondaryCourse",
        }
    )
})
$('.btn-submit').click(function () {
    $.ajax({
            async: false,
            data: {study_program_id: studyProgramId},
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
                    alert("提交失败，请检查课程是否选满")
                }else{
                    window.location.reload()
                }
            },
            url: "/api/studyProgram/submit",
        }
    )
})

