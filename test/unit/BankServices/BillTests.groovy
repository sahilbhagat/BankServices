package BankServices
import java.text.SimpleDateFormat
import grails.test.*

class BillTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testempty()
    {
        mockForConstraintsTests(Bill)
        def bill=new Bill()
        assertFalse bill.validate()
        assertEquals "nullable",bill.errors["billNumber"]
        assertEquals "nullable",bill.errors["biller"]
        assertEquals "nullable",bill.errors["amount"]
        assertEquals "nullable",bill.errors["dueDate"]
        assertEquals "nullable",bill.errors["paid"]
    }
    void testblank()
    {
        mockForConstraintsTests(Bill)
        def biller_ex=new Biller(name:"sahil",category:"Telephone",account_ID:"electricity")
        def df = new SimpleDateFormat("MM/dd/yyyy")
        def bill=new Bill(billNumber:"",biller:biller_ex,amount:100,paid:0)
        bill.dueDate=df.parse("12/31/2011")
        assertFalse bill.validate()
        assertEquals "blank",bill.errors["billNumber"]
        
        def bill2=new Bill(billNumber:"sahil",biller:biller_ex,amount:100,paid:0)
        bill2.dueDate=df.parse("12/31/2011")
        //assertTrue bill2.validate()
    }
    void testmin()
    {
        mockForConstraintsTests(Bill)
        def biller_ex=new Biller(name:"sahil",category:"Telephone",account_ID:"electricity")
        def df = new SimpleDateFormat("MM/dd/yyyy")
        def bill=new Bill(billNumber:"sahilbhagat",biller:biller_ex,amount:1,paid:0)
        bill.dueDate=df.parse("12/31/2011")
        assertFalse bill.validate()
        assertEquals "min",bill.errors["amount"]
    }
    void testdueDate()
    {
        mockForConstraintsTests(Bill)
        def biller_ex=new Biller(name:"sahil",category:"Telephone",account_ID:"electricity")
        def df = new SimpleDateFormat("MM/dd/yyyy")
        def bill=new Bill(billNumber:"sahilbhagat",biller:biller_ex,amount:1,paid:0)
        bill.dueDate=df.parse("1/1/2010")
        assertFalse bill.validate()
        assertEquals "validator",bill.errors["dueDate"]
    }
    void testinList()
    {
        mockForConstraintsTests(Bill)
        def biller_ex=new Biller(name:"sahil",category:"Telephone",account_ID:"electricity")
        def bill2=new Bill(billNumber:"sahil",biller:biller_ex,amount:100,paid:10)
        def df = new SimpleDateFormat("MM/dd/yyyy")
        bill2.dueDate=df.parse("12/31/2011")
        assertFalse bill2.validate()
        assertEquals "inList",bill2.errors["paid"]
    }
    void testSomething() {
        def biller_ex=new Biller(name:"sahil",category:"Telephone",account_ID:"electricity")
        def bill=new Bill(billNumber:"sahil",biller:biller_ex,amount:100,paid:0)
        def df = new SimpleDateFormat("MM/dd/yyyy")
        bill.dueDate=df.parse("12/31/2011")
        def testInstances=[bill]
        mockDomain(Bill,testInstances)
        assertEquals(1,Bill.count())
        
        def bill2=new Bill(billNumber:"sahil",biller:biller_ex,amount:100,paid:0)
        bill2.dueDate=df.parse("12/31/2011")
        assertFalse bill2.validate()
        assertEquals "unique",bill2.errors["billNumber"]
        
        def bill3=new Bill(billNumber:"sahil_bhagat",biller:biller_ex,amount:100,paid:0)
        bill3.dueDate=df.parse("12/31/2011")
        assertFalse bill3.validate()
       // assertEquals "validator",bill3.errors["dueDate"]
    }
}
