grails {
  plugin {
    selfie {
      storage {
        path = 'uploads/:class/:id/:propertyName/' //This configures the storage path of the files being uploaded by domain class name and property name and identifier in GORM
        bucket = 'uploads'
        providerOptions {
          provider = 'local' // Switch to s3 if you wish to use s3 and install the karman-aws plugin
          basePath = 'storage'
          baseUrl  = 'http://localhost:8080/storage'
          //accessKey = "KEY" //Used for S3 Provider
          //secretKey = "KEY" //Used for S3 Provider
        }
      }
    }
  }
}

grails {
  plugin {
    karman {
      serveLocalStorage = true
      serveLocalMapping = 'storage' // means /storage is base path
      storagePath = 'storage'
    }
  }
}