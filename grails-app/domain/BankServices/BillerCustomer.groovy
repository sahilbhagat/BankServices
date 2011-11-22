package BankServices

class BillerCustomer {
    
    Customer customer
    Biller biller
    
    static belongsTo =[customer:Customer, biller:Biller]
}
