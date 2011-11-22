package BankServices

import grails.test.*

class BillerTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

     void testempty()
    {
        mockForConstraintsTests(Biller)
        def biller=new Biller()
        assertFalse biller.validate()
        assertEquals "nullable",biller.errors["account_ID"]
        assertEquals "nullable",biller.errors["category"]
        assertEquals "nullable",biller.errors["name"]
    }
    void testblank()
    {
        mockForConstraintsTests(Biller)
        def biller=new Biller(name:"",category:"",account_ID:"")
        assertFalse biller.validate()
        assertEquals "blank",biller.errors["account_ID"]
        assertEquals "blank",biller.errors["category"]
        assertEquals "blank",biller.errors["name"]
        
        def biller1=new Biller(name:"sahil",category:"",account_ID:"")
        assertFalse biller1.validate()
        assertEquals "blank",biller1.errors["account_ID"]
        assertEquals "blank",biller1.errors["category"]
        
        def biller2=new Biller(name:"sahil",category:"Telephone",account_ID:"")
        assertFalse biller2.validate()
        assertEquals "blank",biller2.errors["account_ID"]
        
        def biller3=new Biller(name:"",category:"Telephone",account_ID:"sahil bhagat")
        assertFalse biller3.validate()
        assertEquals "blank",biller3.errors["name"]
    }
    void testinlist()
    {
        mockForConstraintsTests(Biller)
        def biller=new Biller(name:"sahil",category:"bhagat",account_ID:"sahilbhagat")
        assertFalse biller.validate()
        assertEquals "inList",biller.errors["category"]
        
        def biller1=new Biller(name:"sahil",category:"Telephone",account_ID:"telephone")
        assertTrue biller1.validate()
        
        def biller2=new Biller(name:"sahil",category:"Electricity",account_ID:"electricity")
        assertTrue biller2.validate()
        
        def biller3=new Biller(name:"sahil",category:"Others",account_ID:"other")
        assertTrue biller3.validate()
    }
    
    void testSomething() {
        def testInstances=[new Biller(name:"sahil",category:"Telephone",account_ID:"telephone")]
        mockDomain(Biller,testInstances)
        def biller=new Biller(name:"sahil_bhagat",category:"Telephone",account_ID:"telephone")
        assertFalse biller.validate()
        assertEquals "unique",biller.errors["account_ID"]
        
        def biller1=new Biller(name:"sahil",category:"Telephone",account_ID:"electricity")
        assertTrue biller1.validate()
        
        def biller2=new Biller(name:"sahil",category:"Telephone",account_ID:"others")
        assertTrue biller2.validate()
    }
}
