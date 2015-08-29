<%--
  Created by IntelliJ IDEA.
  User: spiritinlife
  Date: 8/29/15
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app>
  <head>
    <title>Ebay</title>

      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.min.js"></script>

  </head>
  <body>
      <div>
          <label>Name:</label>
          <input type="text" ng-model="yourName" placeholder="Enter a name here">
          <hr>
          <h1>Hello {{yourName}}!</h1>
      </div>
  </body>
</html>
