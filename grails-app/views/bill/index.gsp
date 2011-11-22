<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.  To change this template, choose Tools | Templates

-->

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'bill.label', default: 'Bill')}" />
        <title>Bill</title>
    </head>
  <body>   
    <h1>Welcome to bill section.</h1>
    
    <div class="buttons">
    <span class="menuButton"> 
      <g:link action="list">Pay/View Bills</g:link> 
    </span>
    
    <span class="menuButton"> 
      <g:link controller="biller" action="list">Manage Biller</g:link> 
    </span>
    
    <span class="menuButton"> 
      <g:link action="history">Payment History</g:link> 
    </span>
    </div>
    
    <div class="pageBody">
     <p>Welcome to this section. Here you can manage your account. <br />
    Here you will be able to look at your bills, pay the unpaid bills, view bills history.<br />
    Besides, you can also manage the list of billers here by adding or deleting them. <br />
    Moreover, creating drafts will also become easy with the easy-to-use e-pay option. <br />
    </p>
    </div>
    
  </body>
</html>