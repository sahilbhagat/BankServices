package BankServices

class DraftController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [draftInstanceList: Draft.list(params), draftInstanceTotal: Draft.count()]
    }

    def create = {
        def draftInstance = new Draft()
        draftInstance.properties = params
        return [draftInstance: draftInstance]
    }

    def save = {
        def draftInstance = new Draft(params)
        if (draftInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'draft.label', default: 'Draft'), draftInstance.id])}"
            redirect(action: "show", id: draftInstance.id)
        }
        else {
            render(view: "create", model: [draftInstance: draftInstance])
        }
    }

    def show = {
        def draftInstance = Draft.get(params.id)
        if (!draftInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'draft.label', default: 'Draft'), params.id])}"
            redirect(action: "list")
        }
        else {
            [draftInstance: draftInstance]
        }
    }

    def edit = {
        def draftInstance = Draft.get(params.id)
        if (!draftInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'draft.label', default: 'Draft'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [draftInstance: draftInstance]
        }
    }

    def update = {
        def draftInstance = Draft.get(params.id)
        if (draftInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (draftInstance.version > version) {
                    
                    draftInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'draft.label', default: 'Draft')] as Object[], "Another user has updated this Draft while you were editing")
                    render(view: "edit", model: [draftInstance: draftInstance])
                    return
                }
            }
            draftInstance.properties = params
            if (!draftInstance.hasErrors() && draftInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'draft.label', default: 'Draft'), draftInstance.id])}"
                redirect(action: "show", id: draftInstance.id)
            }
            else {
                render(view: "edit", model: [draftInstance: draftInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'draft.label', default: 'Draft'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def draftInstance = Draft.get(params.id)
        if (draftInstance) {
            try {
                draftInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'draft.label', default: 'Draft'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'draft.label', default: 'Draft'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'draft.label', default: 'Draft'), params.id])}"
            redirect(action: "list")
        }
    }
}
