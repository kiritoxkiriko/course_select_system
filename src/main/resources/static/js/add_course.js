$("#form").submit(function () {
    var weekStr=""
    var jsonObj=$(this).serializeArray()
    $(this).find(".week-checkbox").each(function (i) {
        if($(this).is(":checked")){
            var weekNum=$(this).val();
            weekStr+=(i==0?"":" ")+weekNum
        }
    })
    var tempObj={name:"daysOfWeek",value:weekStr}
    jsonObj.push(tempObj)
    $.ajax({
            async: false,
            data: jsonObj,
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
                    alert("添加成功")
                    window.location.assign("/professor")
                }
            },
            url: "/api/course/add",
        }
    )
    return false
})