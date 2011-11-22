package BankServices

class Biller {  
    
    String category
    String name
    String accountID
    static constraints={
        accountID(unique:true,blank:false)
        name(blank:false)
        category(inList:['Telephone','Electricity','Others'],blank:false)
    }
    String toString() {"${this.name}"}

    
}