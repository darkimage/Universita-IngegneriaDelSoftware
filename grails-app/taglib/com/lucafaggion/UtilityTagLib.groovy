package com.lucafaggion

class UtilityTagLib {
    static defaultEncodeAs = [taglib:'none']
    OrderslogicService orderslogicService
    ProductCategoryLogicService productCategoryLogicService
    ProductService productService
    def springSecurityService
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def formButtonImg = { attribs ->
        def name = attribs['name']
        def value = attribs['value']
        def image = attribs['image']
        def dir = attribs['dir']
        def id = attribs['id']
        def width= attribs['width'] ? attribs['width'] : 16
        def height = attribs['height'] ? attribs['height'] : 16
        def imageAttribs = [name:image,path:dir,w:width,h:height]
        def button = [value:value,name:name,id:id]
        out << g.render(template:'/templates/formButtonImg',model:[data:button,image:imageAttribs])
    }

    def displayErrors = { attribs ->
        def domain = attribs['domain']
        out << g.render(template:'/templates/displayErrors',model:[domain:domain])
    }

    def displayFlashMsg = { attribs ->
        def flash = attribs['flash']
        out << g.render(template:'/templates/displayFlashMsg',model:[flash:flash])
    }

    def selectFormInput = { attribs->
        def domain = attribs['domain']
        def property = attribs['property']
        def domaininstance = attribs['instance']
        def required = attribs['required']
        def name = attribs['name']
        def id = attribs['id']
        def instance = grailsApplication.getArtefact("Domain",domain)?.getClazz()?.get(1)
        def list = (instance) ? instance.list() : []
        def value = (domaininstance) ? domaininstance[name].id : list[property][0]
        out << g.render(template:'/templates/selectFormInput',model:[list:list,name:name,id:id,property:property,instance:domaininstance,required:required,value:value])
    }

    def selectUserRoleInput = {attribs ->
        def domain = attribs['domain']
        def property = attribs['property']
        def required = attribs['required']
        def name = attribs['name']
        def id = attribs['id']
        def value = (attribs['value']) ? attribs['value'] : 1
        def instance = grailsApplication.getArtefact("Domain",domain)?.getClazz()?.get(1)
        def list = (instance) ? instance.list() : []
        out << g.render(template:'/templates/selectFormInput',model:[list:list,name:name,id:id,property:property,required:required,value:value])
    }

    def displayPagination = { attribs ->
        def count = attribs['count']
        out << g.render(template:'/templates/displayPagination',model:[count:count])
    }

    def formInput = { attribs, body ->
        def cssclass = attribs['class']
        def type = attribs['type']
        type = (type) ? type : 'input'
        def prependclass = attribs['prependclass']
        def spanclass = attribs['spanclass']
        def append = attribs['append']
        def id = attribs['id']
        def code = attribs['code']
        def required = (attribs['required'] == 'required') ? true : false
        out << g.render(template:'/templates/formInput',model:[css:[container:cssclass,prepend:prependclass,span:spanclass],required:required,id:id,body:body(),append:append,type:type,code:code])
    }

    def cartCount = {
        def count = orderslogicService.getUserShoppingCart().size()
        count = (count <= 9) ? count : "+9"
        out << g.render(template:'/templates/cartCount',model:[count:count])
    }

    def productCategoryPath = { attribs ->
        def productid = attribs['productid']
        def separator = attribs['separator']
        def category = productCategoryLogicService.productCategoryService.get(productService.get(productid).category.id).name
        out << g.render(template:'/templates/productPath',model:[cat:category,sep:separator.decodeHTML()])
    }

    def newformatDate = { attribs ->
        def format = attribs['format']
        def date = attribs['date']
        String newformat = date.format( format )
        out << newformat
    }

    def showProductStatus = { attribs ->
        def product = attribs['product']
        def available = true
        if(product.quantity == 0 ){
            available = false
        }
        out << g.render(template:'/templates/productStatus',model:[product:product,available:available])
    }

    def toShoppingCart = { attribs ->
        def product = attribs['product']
        def action = attribs['action']
        def controller = attribs['controller']
        params.remove('lastController')
        params.remove('id')
        params.remove('quantity')
        params.remove('lastAction')
        params.remove('format')
        params.remove('addtocart')
        if(product.quantity != 0){
            out<< g.render(template:'/templates/toShoppingCart',model:[id:product.id,action:action,controller:controller,max:product.quantity])
        }
    }

    def formDateInput = { attribs ->
        def id = attribs['id']
        def css = attribs['class'] 
        def required = attribs['required']
        def value = attribs['value']
        out << g.render(template:'/templates/formDateInput',model:[css:css,id:id,required:required,value:value])
    }

    def renderForm = { attribs ->
        def inst = attribs['instance']
        def template = attribs['template']
        def type = attribs['type']
        out << g.render(template:template,model:[domain_instance:inst,inputtype:type])
    }


    def setValueRequired = { attribs-> 
        def value = attribs['value']
        def property = attribs['fieldproperty']
        def defaultval = attribs['defaultval']
        out << ((value == null) ? defaultval : value[property])
    }

    def isInputRequired = { attribs ->
        def type = attribs['type']
        def required = attribs['required']
        out << ((type=="create") ? ((required) ? "required" : "") : "")
    }

    def featuredSlide = { attribs ->
        def collection = attribs['collection']
        out << g.render(template:'/templates/featuredSlide',model:[collection:collection])
    }

    def formatDateforInput = { attribs ->
        def value = attribs['value'].toString()
        try{
            def date =  Date.parse("yyyy-mm-dd",value)
            def formdate = date.format("yyyy-mm-dd")
            out << formdate
        }catch (Exception e) {
           out << ""
       }
    }

    def displayRow = { attribs,body -> 
        def code =  attribs['code']
        out << g.render(template:'/templates/displayRow',model:[code:code,body:body()])
    }

    def renderWithTemplate = {attribs ->
        def value = attribs['value']
        def template = attribs['template']
        out << g.render(template:template,model:[value:value])
    }
}
