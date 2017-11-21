<%--
  Created by IntelliJ IDEA.
  User: hcia
  Date: 2017/11/16
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>
         文件上传首页
    </title>
  <%@ include file="common/header.jsp"%>
</head>
<body>
<div class="container">
<%@ include file="common/navbar.jsp"%>

  <div class="row">
    <%@ include file="common/leftbar.jsp"%>
    <div class="span9">
      <h1>
        菜鸟数据excel上传
      </h1>
      <div class="hero-unit">
        <%--<h1>--%>
          <%--Welcome!--%>
        <%--</h1>--%>
        <p>
          <%--<a class="toggle-link" href="#new-file"><i class="icon-plus"></i> New File</a>--%>
          <form id="new-file" action="cai/springUpload" class="form-horizontal " method="post"  enctype="multipart/form-data">
            <fieldset>
              <%--<legend>New File</legend>--%>
              <%--<div class="control-group">--%>
                <%--<label class="control-label" for="filename">Title</label>--%>
                <%--<div class="controls">--%>
                  <%--<input id="filename" type="text" class="input-xlarge" id="input01" />--%>
                <%--</div>--%>
              <%--</div>--%>
              <div class="control-group">
                <%--<label class="control-label" for="fileInput">File</label>--%>
                <div class="controls">
                  <%--<div  class=" col-md-12">--%>
                    <input class="input-file"  name="file" type="file" />
                    <input class="input-file"  name="file" type="file" />
                    <input class="input-file"  name="file" type="file" />
                    <input class="input-file"  name="file" type="file" />
                    <input class="input-file"  name="file" type="file" />
                    <input class="input-file"  name="file" type="file" />
                    <input class="input-file"  name="file" type="file" />
                    <input class="input-file"  name="file" type="file" />
                    <input class="input-file"  name="file" type="file" />
                    <input class="input-file"  name="file" type="file" />
                  <%--</div>--%>
                  <%--<div  class=" col-md-6">--%>
                    <%--<input class="input-file"  name="file" type="file" />--%>
                  <%--</div>--%>
                </div>
                <%--<div class="controls  col-md-6">--%>
                  <%--<input class="input-file"  name="file" type="file" />--%>
                <%--</div>--%>
                <%--<div class="controls col-md-6">--%>
                  <%--<input class="input-file"  name="file" type="file" />--%>
                <%--</div>--%>
                <%--<div class="controls col-md-6">--%>
                  <%--<input class="input-file"  name="file" type="file" />--%>
                <%--</div>--%>
                <%--<div class="controls">--%>
                  <%--<input class="input-file"  name="file" type="file" />--%>
                <%--</div>--%>
                  <%--<div class="controls">--%>
                    <%--<input class="input-file"  name="file" type="file" />--%>
                  <%--</div>--%>
                  <%--<div class="controls">--%>
                    <%--<input class="input-file"  name="file" type="file" />--%>
                  <%--</div>--%>
                  <%--<div class="controls">--%>
                    <%--<input class="input-file"  name="file" type="file" />--%>
                  <%--</div>--%>
              </div>
              <div class="form-actions">
                <button type="submit" class="btn btn-primary">Upload</button>
                <button type="button" class="btn" onclick="cancer()">Cancel</button>
              </div>
            </fieldset>
          </form>
        </p>
          <p>
          <!-- 遍历Map集合 -->
          <table border="1">

              <th>file name</th>
              <th>modify time</th>



          <c:forEach var="me" items="${fileNameMap}">
            <c:url value="/downFile" var="downurl">
              <c:param name="filename" value="${me.key}"></c:param>
            </c:url>
          <tr>
            <td>${me.key}</td>
            <td>${me.value}</td>
            <%--${me.key}<a href="${me.value}">${me.value}</a>--%>
            <%--${me.value}<a href="${downurl}">下载</a>--%>
            <%--<br/>--%>
          </tr>
          </c:forEach>
        </table>
          </p>
      </div>
    </div>
  </div>

</div>
<%@ include file="common/bodyjs.jsp"%>
</body>


</html>

<script type="text/javascript">
  function cancer(){
    window.location.reload();
  }
</script>


