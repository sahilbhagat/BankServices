package BankServices

class Draft {
    
        String customerID
	Integer amount
	String favor
	String branch
	String receivingMode
        static constraints={
                customerID(blank:false)
                favor(blank:false)
                amount(min:1)
                branch(blank:false)
                receivingMode(inList:['Post','Bank'],blank:false)
        }
        
}