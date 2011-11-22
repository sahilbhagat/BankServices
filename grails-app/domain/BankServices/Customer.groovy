package BankServices

class Customer {
    
    String accountID
    Integer autoPayLimit
    static constraints={
        accountID(unique:true,blank:false)
        autoPayLimit(min:0)
    }
    String toString() {"${this.accountID}"}
    static hasMany = [bills: Bill]  
}
