package Rest

import grails.converters.*

class RestCustomerController {

     def index = {
         switch(request.method){
            
            case "POST":      
            
            def customer = new BankServices.Customer()
            customer.accountID = request.XML.Customer.account_id             
            customer.autoPayLimit = request.XML.Customer.autopay_limit
            
            if (customer.save()){
                response.status = 201 // Created
                render customer as XML
            }
            else{
                response.status = 500 //Internal Server Error
                render "Could not create new Customer due to errors:\n ${customer.errors}"      
            }
            break            
            
            case "GET":
            
            if(params.accountID){
                // render params.accountID
                def customer = BankServices.Customer.findByAccountID(params.accountID)        
                
                if (customer){
                    
                    render(contentType:"text/xml"){
                        BankServices{
                            Customer{                                                                                                                
                            "account_id"(customer.accountID)
                            "autopay_limit"(customer.autoPayLimit)
                            }     
                        }                        
                    }
                }
                else{
                    response.status = 404 //Not Found
                    render "The accountID ${params.accountID} not found."
                }
            }
            else{
                def clist = BankServices.Customer.list()        
                render(contentType:"text/xml")
                {            
                    BankServices
                    {
                        Customers
                        {
                            for(c in clist)
                            {
                                Customer
                                {
                            "account_id"(c.accountID)
                            "autopay_limit"(c.autoPayLimit)
                                }
                            }        
                        }
                    }
                }
            }            
            break
            
            case "PUT":
            
            def customer = BankServices.Customer.findByAccountID(request.XML.Customer.account_id.toString()) 
            
            if (customer){
                //customer.accountID = request.XML.Customer.account_id             
                customer.autoPayLimit = request.XML.Customer.autopay_limit
                
                if(customer.save()){
                    response.status = 200 // OK                
                    render customer as XML
                }
                else{
                    response.status = 500 //Internal Server Error
                    render "Could not edit the customer due to errors:\n ${customer.errors}"                
                }
            }
            else{
                response.status = 400 //Bad Request
                render "Could not find the Customer $request.XML.Customer.account_id due to errors:\n ${customer.errors}"
            }
            
            break
            
            case "DELETE":
            
            if(params.accountID){
                def customer = BankServices.Customer.findByAccountID(params.accountID)
                if(customer){
                    customer.delete()
                    render "Successfully Deleted $params.accountID.\n"
                }
                else{
                    response.status = 404 //Not Found
                    render "The accountID ${params.accountID} not found.\n"
                }
            }
            else{
                response.status = 400 //Bad Request
                render "DELETE request must be passed a valid accountId."
                render "\nExample: /rest/customer/127643bhdjb\n"
            }
            
            break
        }
    }    
    
    def xmlList = {
        render BankServices.Customer.list() as XML
    }
    
    def customXmlList = {
        
        def clist = BankServices.Customer.list()        
        render(contentType:"text/xml")
        {            
            BankServices
            {
                Customers
                {
                    for(c in clist)
                    {
                        Customer
                        {
                            "account_id"(c.accountID)
                            "autopay_limit"(c.autoPayLimit)
                        }
                    }        
                }
            }
        }
    }
    
    def xmlShow = {
        def customer = BankServices.Customer.findByAccountID(params.accountID)        
        
        if (customer){
            
            render(contentType:"text/xml"){
                BankServices{
                    Customer{                                                                                                                
                        "account_id"(customer.accountID)
                        "autopay_limit"(customer.autoPayLimit)
                    }     
                }                        
            }
        }
        else{
            response.status = 404 //Not Found
            render "The accountID ${params.accountID} not found."
        }
    }     
}