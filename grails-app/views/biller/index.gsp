<
<%@ page import="BankServices.Biller" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'biller.label', default: 'Biller')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="show" action="create"><g:message code="Add Biller" args="[entityName]" /></g:link></span>
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
                            <g:sortableColumn property="id" title="${message(code: 'biller.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="accountID" title="${message(code: 'biller.accountID.label', default: 'Account ID')}" />
                        
                            <g:sortableColumn property="category" title="${message(code: 'biller.category.label', default: 'Category')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'biller.name.label', default: 'Name')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${billers}" status="i" var="billers">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${billers.id}">${fieldValue(bean: billers, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: billers, field: "accountID")}</td>
                        
                            <td>${fieldValue(bean: billers, field: "category")}</td>
                        
                            <td>${fieldValue(bean: billers, field: "name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
