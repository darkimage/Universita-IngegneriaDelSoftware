package com.lucafaggion

class UtilityTagLib {
    static defaultEncodeAs = [taglib:'none']
    OrderslogicService orderslogicService
    ProductCategoryService productCategoryService
    def springSecurityService
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def formButtonImg = { attribs ->
        def name = attribs['name']
        def value = attribs['value']
        def image = attribs['image']
        def dir = attribs['dir']
        def width= attribs['width'] ? attribs['width'] : 16
        def height = attribs['height'] ? attribs['height'] : 16
        def imageAttribs = [name:image,path:dir,w:width,h:height]
        def button = [value:value,name:name]
        out << g.render(template:'/templates/formButtonImg',model:[data:button,image:imageAttribs])
    }

    def selectFormInput = { attribs->
        def domain = attribs['domain']
        def property = attribs['property']
        def name = attribs['name']
        def id = attribs['id']
        def instance = grailsApplication.getArtefact("Domain",domain)?.getClazz()?.get(1)
        def list = instance.list()
        out << g.render(template:'/templates/selectFormInput',model:[list:list,name:name,id:id,property:property])
    }

    def formInput = { attribs, body ->
        def cssclass = attribs['class']
        def type = attribs['type']
        type = (type) ? type : 'input'
        def prependclass = attribs['prependclass']
        def spanclass = attribs['spanclass']
        def append = attribs['append']
        def id = attribs['id']
        def required =  Boolean.valueOf(attribs['required'])
        out << g.render(template:'/templates/formInput',model:[css:[container:cssclass,prepend:prependclass,span:spanclass],required:required,id:id,body:body(),append:append,type:type])
    }

    def cartCount = {
        def count = orderslogicService.getUserShoppingCart().size()
        count = (count <= 9) ? count : "+9"
        out << g.render(template:'/templates/cartCount',model:[count:count])
    }

    def productCategoryPath = { attribs ->
        def productid = attribs['productid']
        def separator = attribs['separator']
        def category = productCategoryService.getCategoryName(Product.get(productid).category.id.toInteger())
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
        if(product.quantity != 0){
            out<< g.render(template:'/templates/toShoppingCart',model:[id:product.id,action:action,controller:controller,max:product.quantity])
        }
    }

}
