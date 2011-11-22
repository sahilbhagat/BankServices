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
        assertEquals "nullable",biller.errors["accountID"]
        assertEquals "nullable",biller.errors["category"]
        assertEquals "nullable",biller.errors["name"]
    }
    void testblank()
    {
        mockForConstraintsTests(Biller)
        def biller=new Biller(name:"",category:"",accountID:"")
        assertFalse biller.validate()
        assertEquals "blank",biller.errors["accountID"]
        assertEquals "blank",biller.errors["category"]
        assertEquals "blank",biller.errors["name"]
        
        def biller1=new Biller(name:"sahil",category:"",accountID:"")
        assertFalse biller1.validate()
        assertEquals "blank",biller1.errors["accountID"]
        assertEquals "blank",biller1.errors["category"]
        
        def biller2=new Biller(name:"sahil",category:"Telephone",accountID:"")
        assertFalse biller2.validate()
        assertEquals "blank",biller2.errors["accountID"]
        
        def biller3=new Biller(name:"",category:"Telephone",accountID:"sahil bhagat")
        assertFalse biller3.validate()
        assertEquals "blank",biller3.errors["name"]
    }
    void testinlist()
    {
        mockForConstraintsTests(Biller)
        def biller=new Biller(name:"sahil",category:"bhagat",accountID:"sahilbhagat")
        assertFalse biller.validate()
        assertEquals "inList",biller.errors["category"]
        
        def biller1=new Biller(name:"sahil",category:"Telephone",accountID:"telephone")
        assertTrue biller1.validate()
        
        def biller2=new Biller(name:"sahil",category:"Electricity",accountID:"electricity")
        assertTrue biller2.validate()
        
        def biller3=new Biller(name:"sahil",category:"Others",accountID:"other")
        assertTrue biller3.validate()
    }
    
    void testSomething() {
        def testInstances=[new Biller(name:"sahil",category:"Telephone",accountID:"telephone")]
        mockDomain(Biller,testInstances)
        def biller=new Biller(name:"sahil_bhagat",category:"Telephone",accountID:"telephone")
        assertFalse biller.validate()
        assertEquals "unique",biller.errors["accountID"]
        
        def biller1=new Biller(name:"sahil",category:"Telephone",accountID:"electricity")
        assertTrue biller1.validate()
        
        def biller2=new Biller(name:"sahil",category:"Telephone",accountID:"others")
        assertTrue biller2.validate()
    }
}
