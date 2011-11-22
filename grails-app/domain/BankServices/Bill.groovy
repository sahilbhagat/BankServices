package BankServices

class Bill {
    
    String billNumber
    Biller biller
    Integer amount
    Date dueDate
    Integer paid //
    static constraints={
        billNumber(unique:true,blank:false)
        amount(min:10)
        dueDate(validator: {return (it > new Date())})
        paid(inList:[0,1])
    }
        
    static belongsTo = [customer: Customer]    
}