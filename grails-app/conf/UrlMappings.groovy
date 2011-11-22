class UrlMappings {

	static mappings = {
        
        "/biller/xmlList"(controller:"restBiller", action:"customXmlList")
        "/rest/biller/$accountID?"(controller:"restBiller", action:"index")
        
        "/customer/xmlList"(controller:"restCustomer", action:"customXmlList")
        "/rest/customer/$accountID?"(controller:"restCustomer", action:"index")
        
        "/rest/customer-biller/$accountID"(controller:"restCustomerBiller", action:"index")
        
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
                
                
                
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
