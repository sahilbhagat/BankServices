
<%@ page import="BankServices.Draft" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'draft.label', default: 'Draft')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'draft.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="amount" title="${message(code: 'draft.amount.label', default: 'Amount')}" />
                        
                            <g:sortableColumn property="branch" title="${message(code: 'draft.branch.label', default: 'Branch')}" />
                        
                            <th><g:message code="draft.customer.label" default="Customer" /></th>
                        
                            <g:sortableColumn property="favor" title="${message(code: 'draft.favor.label', default: 'Favor')}" />
                        
                            <g:sortableColumn property="receivingMode" title="${message(code: 'draft.receivingMode.label', default: 'Receiving Mode')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${draftInstanceList}" status="i" var="draftInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${draftInstance.id}">${fieldValue(bean: draftInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: draftInstance, field: "amount")}</td>
                        
                            <td>${fieldValue(bean: draftInstance, field: "branch")}</td>
                        
                            <td>${fieldValue(bean: draftInstance, field: "customer")}</td>
                        
                            <td>${fieldValue(bean: draftInstance, field: "favor")}</td>
                        
                            <td>${fieldValue(bean: draftInstance, field: "receivingMode")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${draftInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
