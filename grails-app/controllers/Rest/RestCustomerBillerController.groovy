package Rest

class RestCustomerBillerController {

    def index = {        
        if (params.accountID){
            def customer = BankServices.Customer.findByAccounID(params.accountID)
            
            if (customer){
                def cbList = BankServices.CustomerBiller.findAllByCustomer(customer)
                
                if (cbList){
                    def biller = 
                    render(contentType:"text/xml"){
                        BankServices{
                            Customer(account_id:customer.accountID, autopay_limit:customer.autoPayLimit){                                
                                for(biller in cbList.Biller){
                                    Biller{  
                                        "category"(biller.category)
                                        "name"(biller.name)
                                        "account_id"(biller.accountID)
                                    }     
                                }
                            }
                        }                        
                    }
                }
                else{
                    response.status = 404 //Not Found
                    render "The customer accountID ${params.accountID} is not registered for BankServices.\n"
                }
            }
            else{
                def biller = BankServices.Customer.findByAccounID(params.accountID)
                
                if (biller){
                    
                }
                else{
                    response.status = 404 //Not Found
                    render "The accountID ${params.accountID} not found.\n"
                }
            }
        }
        else{
            response.status = 400 //Bad Request
            render "Customer Biller request must be passed a valid accountId."
            render "\nExample: /rest/customer-biller/127643bhdjb\n"
        }
    }
}
