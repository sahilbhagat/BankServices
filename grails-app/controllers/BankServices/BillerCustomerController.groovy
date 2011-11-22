package BankServices

class BillerCustomerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [billerCustomerInstanceList: BillerCustomer.list(params), billerCustomerInstanceTotal: BillerCustomer.count()]
    }

    def create = {
        def billerCustomerInstance = new BillerCustomer()
        billerCustomerInstance.properties = params
        return [billerCustomerInstance: billerCustomerInstance]
    }

    def save = {
        def billerCustomerInstance = new BillerCustomer(params)
        if (billerCustomerInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'billerCustomer.label', default: 'BillerCustomer'), billerCustomerInstance.id])}"
            redirect(action: "show", id: billerCustomerInstance.id)
        }
        else {
            render(view: "create", model: [billerCustomerInstance: billerCustomerInstance])
        }
    }

    def show = {
        def billerCustomerInstance = BillerCustomer.get(params.id)
        if (!billerCustomerInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'billerCustomer.label', default: 'BillerCustomer'), params.id])}"
            redirect(action: "list")
        }
        else {
            [billerCustomerInstance: billerCustomerInstance]
        }
    }

    def edit = {
        def billerCustomerInstance = BillerCustomer.get(params.id)
        if (!billerCustomerInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'billerCustomer.label', default: 'BillerCustomer'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [billerCustomerInstance: billerCustomerInstance]
        }
    }

    def update = {
        def billerCustomerInstance = BillerCustomer.get(params.id)
        if (billerCustomerInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (billerCustomerInstance.version > version) {
                    
                    billerCustomerInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'billerCustomer.label', default: 'BillerCustomer')] as Object[], "Another user has updated this BillerCustomer while you were editing")
                    render(view: "edit", model: [billerCustomerInstance: billerCustomerInstance])
                    return
                }
            }
            billerCustomerInstance.properties = params
            if (!billerCustomerInstance.hasErrors() && billerCustomerInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'billerCustomer.label', default: 'BillerCustomer'), billerCustomerInstance.id])}"
                redirect(action: "show", id: billerCustomerInstance.id)
            }
            else {
                render(view: "edit", model: [billerCustomerInstance: billerCustomerInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'billerCustomer.label', default: 'BillerCustomer'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def billerCustomerInstance = BillerCustomer.get(params.id)
        if (billerCustomerInstance) {
            try {
                billerCustomerInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'billerCustomer.label', default: 'BillerCustomer'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'billerCustomer.label', default: 'BillerCustomer'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'billerCustomer.label', default: 'BillerCustomer'), params.id])}"
            redirect(action: "list")
        }
    }
}
