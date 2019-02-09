package com.lucafaggion
import com.bertramlabs.plugins.selfie.Attachment

class Product {
    String identifier
    String name
    String description
    Date creation_date
    int quantity
    int price
    Attachment photo
    Boolean featured
    
    String toString(){
        "${name}"
    } 

    static belongsTo = [category: ProductCategory]

    static attachmentOptions = [
        photo: [
        styles: [
            thumb: [width: 50, height: 50, mode: 'fit'],
            medium: [width: 200, height: 200, mode: 'scale'],
            large: [width: 400, height: 400, mode: 'scale']
            ]
        ]
    ]

    static embedded = ['photo']

    static constraints = {
        name()
        price()
        quantity()
        description()
        identifier()
        photo contentType: ['png','jpg'], fileSize:1024*1024 // 1mb
        featured nullable: true,defaultValue: false
    }

}
