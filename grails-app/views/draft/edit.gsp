

<%@ page import="BankServices.Draft" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'draft.label', default: 'Draft')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${draftInstance}">
            <div class="errors">
                <g:renderErrors bean="${draftInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${draftInstance?.id}" />
                <g:hiddenField name="version" value="${draftInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="amount"><g:message code="draft.amount.label" default="Amount" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: draftInstance, field: 'amount', 'errors')}">
                                    <g:textField name="amount" value="${fieldValue(bean: draftInstance, field: 'amount')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="branch"><g:message code="draft.branch.label" default="Branch" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: draftInstance, field: 'branch', 'errors')}">
                                    <g:textField name="branch" value="${draftInstance?.branch}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="customer"><g:message code="draft.customer.label" default="Customer" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: draftInstance, field: 'customer', 'errors')}">
                                    <g:select name="customer.id" from="${BankServices.Customer.list()}" optionKey="id" value="${draftInstance?.customer?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="favor"><g:message code="draft.favor.label" default="Favor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: draftInstance, field: 'favor', 'errors')}">
                                    <g:textField name="favor" value="${draftInstance?.favor}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="receivingMode"><g:message code="draft.receivingMode.label" default="Receiving Mode" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: draftInstance, field: 'receivingMode', 'errors')}">
                                    <g:textField name="receivingMode" value="${draftInstance?.receivingMode}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
