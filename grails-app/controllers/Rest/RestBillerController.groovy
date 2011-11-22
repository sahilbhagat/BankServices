package Rest

import grails.converters.*

class RestBillerController {

    def index = {
         switch(request.method){
            
            case "POST":      
            
            def biller = new BankServices.Biller()
            biller.accountID = request.XML.Biller.account_id 
            biller.name = request.XML.Biller.name
            biller.category = request.XML.Biller.category          
            
            if (biller.save()){
                response.status = 201 // Created
                render biller as XML
            }
            else{
                response.status = 500 //Internal Server Error
                render "Could not create new Biller due to errors:\n ${biller.errors}"      
            }
            break            
            
            case "GET":
            
            if(params.accountID){
            //    render params.accountID
                def biller = BankServices.Biller.findByAccountID(params.accountID)        
                
                if (biller){
                    
                    render(contentType:"text/xml"){
                        BankServices{
                            Biller{                                                        
                            "category"(biller.category)
                            "name"(biller.name)
                            "account_id"(biller.accountID)
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
                def blist = BankServices.Biller.list()        
                render(contentType:"text/xml"){
                    BankServices{
                        Billers{
                            for(b in blist){
                                Biller(category:b.category){
                                    // "category"(b.category)
                                   "name"(b.name)
                                    "account_id"(b.accountID)
                                }
                            }        
                        }
                    }
                }
            }
            break
            
            case "PUT":
            def biller = BankServices.Biller.findByAccountID(request.XML.Biller.account_id.toString()) 
            
            //biller.accountID = request.XML.Biller.account_id 
            biller.name = request.XML.Biller.name
            biller.category = request.XML.Biller.category
            
            if(biller.save()){
                response.status = 200 // OK
                render biller as XML
            }
            else{
                response.status = 500 //Internal Server Error
                render "Could not edit the Biller due to errors:\n ${biller.errors}"
            }
            break
            
            case "DELETE":
            
            if(params.accountID){
                def biller = BankServices.Biller.findByAccountID(params.accountID)
                if(biller){
                    biller.delete()
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
                render "\nExample: /rest/biller/127643bhdjb\n"
            }
            
            break
        }
    }    
    
    def xmlList = {
        render BankServices.Biller.list() as XML
    }
    
    def customXmlList = {
         def blist = BankServices.Biller.list()        
         render(contentType:"text/xml"){
            BankServices{
                Billers{
                    for(b in blist){
                        biller(category:b.category){
                            // "category"(b.category)
                        "name"(b.name)
                        "account_id"(b.accountID)
                        }
                    }        
                }
            }
        }
    }
    
    def xmlShow = {
        def biller = BankServices.Biller.findByAccountID(params.accountID)
        
        render(contentType:"text/xml"){
            BankServices{
                Biller{  
                   "category"(biller.category)
                   "name"(biller.name)
                   "account_id"(biller.accountID)
                }     
            }                        
        }
    }     
}