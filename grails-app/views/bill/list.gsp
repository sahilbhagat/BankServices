
<%@ page import="BankServices.Bill" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'bill.label', default: 'Bill')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'bill.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="amount" title="${message(code: 'bill.amount.label', default: 'Amount')}" />
                        
                            <th><g:message code="bill.biller.label" default="Biller" /></th>
                        
                            <th><g:message code="bill.customer.label" default="Customer" /></th>
                        
                            <g:sortableColumn property="dueDate" title="${message(code: 'bill.dueDate.label', default: 'Due Date')}" />
                        
                            <g:sortableColumn property="paid" title="${message(code: 'bill.paid.label', default: 'Paid')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${billInstanceList}" status="i" var="billInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                          
                       <g:if test="${billInstance.dueDate.compareTo(new Date())>=0}">
                          <g:if test="${billInstance.paid == 0}">
                            <td><g:link action="show" id="${billInstance.id}">${fieldValue(bean: billInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: billInstance, field: "amount")}</td>
                        
                            <td>${fieldValue(bean: billInstance, field: "biller")}</td>
                        
                            <td>${fieldValue(bean: billInstance, field: "customer")}</td>
                        
                            <td><g:formatDate date="${billInstance.dueDate}" /></td>
                        
                            <td><g:link action="show" id="${billInstance.id}">Pay</g:link></td>
                          </g:if>
                       </g:if>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${billInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
