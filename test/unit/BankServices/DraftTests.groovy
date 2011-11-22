package BankServices

import grails.test.*

class DraftTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

     void testempty()
    {
        mockForConstraintsTests(Draft)
        def draft=new Draft()
        assertFalse draft.validate()
        assertEquals "nullable",draft.errors["customerID"]
        assertEquals "nullable",draft.errors["favor"]
        assertEquals "nullable",draft.errors["branch"]
        assertEquals "nullable",draft.errors["receivingMode"]
        assertEquals "nullable",draft.errors["amount"]
    }
    void testblank()
    {
        mockForConstraintsTests(Draft)
        def draft=new Draft(customerID:"",favor:"",branch:"",receivingMode:"",amount:10)
        assertFalse draft.validate()
        assertEquals "blank",draft.errors["customerID"]
        assertEquals "blank",draft.errors["favor"]
        assertEquals "blank",draft.errors["branch"]
        assertEquals "blank",draft.errors["receivingMode"]
        
        def draft1=new Draft(customerID:"",favor:"sa",branch:"sa",receivingMode:"Post",amount:10)
        assertFalse draft1.validate()
        assertEquals "blank",draft1.errors["customerID"]
        
        def draft2=new Draft(customerID:"sahil",favor:"",branch:"sa",receivingMode:"Bank",amount:10)
        assertFalse draft2.validate()
        assertEquals "blank",draft2.errors["favor"]
        
        def draft3=new Draft(customerID:"sahil",favor:"bhagat",branch:"",receivingMode:"Bank",amount:10)
        assertFalse draft3.validate()
        assertEquals "blank",draft3.errors["branch"]
    }
    void testinlist()
    {
        mockForConstraintsTests(Draft)
        def draft=new Draft(customerID:"sahil",favor:"bhagat",branch:"pathankot",receivingMode:"anything",amount:10)
        assertFalse draft.validate()
        assertEquals "inList",draft.errors["receivingMode"]
        
        def draft1=new Draft(customerID:"sahil",favor:"bhagat",branch:"pathankot",receivingMode:"Post",amount:10)
        assertTrue draft1.validate()
        
        def draft2=new Draft(customerID:"sahil",favor:"bhagat",branch:"pathankot",receivingMode:"Bank",amount:10)
        assertTrue draft2.validate()
    }
    void testnegative()
    {
        mockForConstraintsTests(Draft)
        def draft=new Draft(customerID:"sahil",favor:"bhagat",branch:"pathankot",receivingMode:"Post",amount:-10)
        assertFalse draft.validate()
        assertEquals "min",draft.errors["amount"]
    }
    void testSomething() {
        mockForConstraintsTests(Draft)
        def draft=new Draft(customerID:"sahil",favor:"bhagat",branch:"pathankot",receivingMode:"Post",amount:1000)
        assertTrue draft.validate()
        
        def draft2=new Draft(customerID:"sahil",favor:"bhagat",branch:"pathankot",receivingMode:"Post",amount:1000)
        assertTrue draft2.validate()
    }
}
