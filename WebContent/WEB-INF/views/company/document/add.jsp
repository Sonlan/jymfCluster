<%@page import="ch.qos.logback.core.Context"%>
<%@ page import="org.jymf.utils.Constants"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"  src="${ctx}/static/jquery-validation/1.11.1/additional.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#company-document").addClass("active");        
    });

    function addFile() {
        var count = document.getElementById("pictureCount").value;

        document.getElementById("pictureCount").value = ++document.getElementById("pictureCount").value;

        //判断图片的大小
        var file = $("#img" + count)[0].files[0];
        var fileval = $("#img" + count);
        var maxSize =<%=Constants.PHOTO_MAX_SIZE%> * 4;

        if (file.size > maxSize) {
            fileval.val("");
            alert("图片不能超过240K，请重新选择！");
            fileval.parent().parent().remove();
            //添加tr
            addTr($("#pictureCount").val());
            return;
        }
        //添加tr
        addTr($("#pictureCount").val());
    }

    //添加tr
    function addTr(val) {
        var testTbl = document.getElementById("tab");
        //添加tr
        var newTr = testTbl.insertRow();
        newTr.innerHTML = '<td style="text-align: left;"><input  type="file" id="img'
                + val
                + '" name="docImgs"  onkeydown="return false;" onchange="addFile();" class="txt"></td><td id='+val+'><a onClick="getDel(this,'
                + val + ')" href="#">删除</a></td>';
    }

    //删除tr
    function getDel(k, j) {
        var td = k.parentNode;
        var tr = td.parentNode;
        var tab = tr.parentNode;
        //判断图片的值是否为空
        var files = $("#img" + Number(j)).val();
        if (files == "") {
            alert('不可删除');
            return;
        }
        if (tab.rows.length > 1) {
            var answer = confirm("确定要删除该文件?");
            if (answer) {
                tab.removeChild(tr);
            }
        }
    }
</script>
<style type="text/css">
.tab {
    text-align: center;
    border-collapse: collapse;
    line-height: 25px;
    border: 1px solid #E0EEE0;
    width: 600px;
}

.tab td {
    border: 1px #E0EEE0 solid;
}
/*table,td的高度*/
.tab .table_1_tr td {
    height: 25px;
}
</style>
<title>合同添加</title>

</head>
<body>
<div class="main_div" style="margin-left: 200px;">
<form:form id="addform" modelAttribute="document" action="${ctx}/company/document/add" method="post"
    enctype="multipart/form-data">
    <input type="hidden" value="0" name="pictureCount" id="pictureCount" autocomplete="off"/>
    <input type="hidden" name="id" value="${document.id}"/>
    <div class="add_top">
        <div class="head_describe">合同基本信息</div>
        <table id="table_2" class="jymf_table2 table_border">
            <tr class="table_1_tr">
                <td width="15%">合同ID</td><td width="35%">${document.id}</td>
                <td width="15%">配件数量 </td><td width="35%">${document.count}</td>
            </tr>
            <tr class="table_1_tr">
                <td>生成时间</td><td>${document.createDate}</td>
                <td>车牌号</td><td>${document.carNo}</td>
            </tr>
            <tr class="table_1_tr">
                <td>首次消费日期</td><td>${document.consTime}</td>
                <td>消费地区</td><td>${document.consArea}</td>
            </tr>
        </table>
        
        <div class="head_describe">配件明细</div>
        <table id="table_1" class="jymf_table table_border">
            <tr id="table_1_titlebar">
                <td class="jymf_td row_1" rowspan="1" colspan="1">序号</td>
                <td class="jymf_td row_1" rowspan="1" colspan="1">追溯码</td>
                <td class="jymf_td row_1" rowspan="1" colspan="1">产品名称</td>
                <td class="jymf_td row_1" rowspan="1" colspan="1">生产商</td>
                <td class="jymf_td row_1" rowspan="1" colspan="1">适用车型</td>
            </tr>
            <c:forEach var="label" items="${document.labels}"  varStatus="status">
            <tr class="jymf_tr table_1_tr">
                <td>${status.index + 1}</td>
                <td>${label.id}</td>
                <td>${label.productName}</td>
                <td>${label.producer}</td>
                <td>${label.carType}</td>
            </c:forEach>
        </table>
    </div>
    
    <div>
        <div class="head_describe">上传合同副本</div>
        <table id="tab" class="tab" style="margin: 20px 0px 50px 0px;">
            <tr>
                <td style="text-align: left;">
                    <input id="img0" type="file" name="docImgs" onkeydown="return false;" onchange="addFile();" class="txt" autocomplete="off"/>
                </td>
                <td id="1">
                    <a onClick='getDel(this)' href='#'>&nbsp;&nbsp;删除&nbsp;&nbsp;</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="add_btn">
        <table>
            <tr>
                <td><input class="ok_button" type="submit" value=" " /></td>
                <td><input class="cancer_btn" type="button"
                    onclick="javascript:window.location.href='javascript:history.go(-1)'" />
                </td>
            </tr>
        </table>
    </div>
</form:form>
</div>
</body>
</html>