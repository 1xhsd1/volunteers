$(function () {
    //加载志愿活动类别
    $.get("/eventtype/typelist",function (data) {
        var eventTypeList = $("#eventTypeList");
        //循环遍历json数组
        $(data).each(function () {
            var li = "<li><span><a href='/index.html?type="+this.typeId+"'>"+this.typeName+" ("+this.eventCount+")</a></span></li>";
            //将每一个li标签追加到ul标签
            $(eventTypeList).append(li);
        });
    },"json");

    //按博客日期查询
    $.get("/event/eventDateList",function (data) {
        console.log(data);
        var eventList = $("#dateList");
        //循环遍历json数组
        $(data).each(function () {
            var li = "<li><span><a href='/index.html?releaseDateStr="+this.releaseDateStr+"'>"+this.releaseDateStr+"("+this.eventCount+")</a></span></li>";
            //将每一个li标签追加到ul标签
            $(eventList).append(li);
        });
    },"json");
})