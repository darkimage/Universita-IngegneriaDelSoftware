package com.lucafaggion

class UtilityTagLib {
    static defaultEncodeAs = [taglib:'none']
    OrderslogicService orderslogicService
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

    def cartCount = {
        def count = orderslogicService.getUserShoppingCart().size()
        count = (count <= 9) ? count : "+9"
        out << g.render(template:'/templates/cartCount',model:[count:count])
    }
}
