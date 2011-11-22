
<%@ page import="BankServices.Bill" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'bill.label', default: 'Bill')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="bill.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: billInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="bill.amount.label" default="Amount" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: billInstance, field: "amount")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="bill.biller.label" default="Biller" /></td>
                            
                            <td valign="top" class="value"><g:link controller="biller" action="show" id="${billInstance?.biller?.id}">${billInstance?.biller?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="bill.customer.label" default="Customer" /></td>
                            
                            <td valign="top" class="value"><g:link controller="customer" action="show" id="${billInstance?.customer?.id}">${billInstance?.customer?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="bill.dueDate.label" default="Due Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${billInstance?.dueDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="bill.paid.label" default="Paid" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: billInstance, field: "paid")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
              <g:link action="pay" id="${billInstance.id}">Pay</g:link>
            </div>
        </div>
    </body>
</html>
