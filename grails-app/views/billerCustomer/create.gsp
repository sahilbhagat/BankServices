

<%@ page import="BankServices.BillerCustomer" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'billerCustomer.label', default: 'BillerCustomer')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${billerCustomerInstance}">
            <div class="errors">
                <g:renderErrors bean="${billerCustomerInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="billerID"><g:message code="billerCustomer.billerID.label" default="Biller ID" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: billerCustomerInstance, field: 'billerID', 'errors')}">
                                    <g:textField name="billerID" value="${fieldValue(bean: billerCustomerInstance, field: 'billerID')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="customerID"><g:message code="billerCustomer.customerID.label" default="Customer ID" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: billerCustomerInstance, field: 'customerID', 'errors')}">
                                    <g:textField name="customerID" value="${fieldValue(bean: billerCustomerInstance, field: 'customerID')}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
