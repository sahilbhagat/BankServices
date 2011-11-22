package BankServices

class BillerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {                 
        Integer id = params.custID
        //def billerID = BillerCustomer.executeQuery("select bc.billerID from BillerCustomer bc where bc.customerID = ?",[101])
        def billers = Biller.getAll()
        return [billers: billers, custID: params.custID]
    }    

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [billerInstanceList: Biller.list(params), billerInstanceTotal: Biller.count()]
    }

    def create = {
        def billerInstance = new Biller()
        billerInstance.properties = params
        return [billerInstance: billerInstance]
    }

    def save = {
        def billerInstance = new Biller(params)
        if (billerInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'biller.label', default: 'Biller'), billerInstance.id])}"
            redirect(action: "show", id: billerInstance.id)
        }
        else {
            render(view: "create", model: [billerInstance: billerInstance])
        }
    }

    def show = {
        def billerInstance = Biller.get(params.id)
        if (!billerInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'biller.label', default: 'Biller'), params.id])}"
            redirect(action: "list")
        }
        else {
            [billerInstance: billerInstance]
        }
    }

    def edit = {
        def billerInstance = Biller.get(params.id)
        if (!billerInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'biller.label', default: 'Biller'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [billerInstance: billerInstance]
        }
    }

    def update = {
        def billerInstance = Biller.get(params.id)
        if (billerInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (billerInstance.version > version) {
                    
                    billerInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'biller.label', default: 'Biller')] as Object[], "Another user has updated this Biller while you were editing")
                    render(view: "edit", model: [billerInstance: billerInstance])
                    return
                }
            }
            billerInstance.properties = params
            if (!billerInstance.hasErrors() && billerInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'biller.label', default: 'Biller'), billerInstance.id])}"
                redirect(action: "show", id: billerInstance.id)
            }
            else {
                render(view: "edit", model: [billerInstance: billerInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'biller.label', default: 'Biller'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def billerInstance = Biller.get(params.id)
        if (billerInstance) {
            try {
                billerInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'biller.label', default: 'Biller'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'biller.label', default: 'Biller'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'biller.label', default: 'Biller'), params.id])}"
            redirect(action: "list")
        }
    }
}
