package BankServices

import grails.test.*

class CustomerTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testempty()
    {
        mockForConstraintsTests(Customer)
        def customer=new Customer()
        assertFalse customer.validate()
        assertEquals "nullable",customer.errors["accountID"]
        assertEquals "nullable",customer.errors["autoPayLimit"]
    }
    void testblank()
    {
        mockForConstraintsTests(Customer)
        def customer=new Customer(accountID:"",autoPayLimit:1000)
        assertFalse customer.validate()
        assertEquals "blank",customer.errors["accountID"]
    }
    void testnegative()
    {
        mockForConstraintsTests(Customer)
        def customer=new Customer(accountID:"sahil",autoPayLimit:-10000)
        assertFalse customer.validate()
        assertEquals "min", customer.errors["autoPayLimit"]
    }
    void testSomething() {
        def testInstances=[new Customer(accountID:"sahil",autoPayLimit:1000)]
        mockDomain(Customer,testInstances)
        assertEquals(1,Customer.count())
        def customer=new Customer(accountID:"sahil",autoPayLimit:1000)
        assertFalse customer.validate()
        assertEquals "unique", customer.errors["accountID"]
        def secondcustomer=new Customer(accountID:"sahil bhagat",autoPayLimit:1000)
        assertTrue secondcustomer.validate()
    }
}
