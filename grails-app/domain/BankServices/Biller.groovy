package BankServices

class Biller {  
    
    String category
    String name
    String account_ID
    static constraints={
        account_ID(unique:true,blank:false)
        name(blank:false)
        category(inList:['Telephone','Electricity','Others'],blank:false)
    }
    String toString() {"${this.name}"}

    
}