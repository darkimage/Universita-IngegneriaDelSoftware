environments {
  production {
    grails {
      plugin {
        selfie {
          storage {
            path = 'uploads/:class/:id/:propertyName/' //This configures the storage path of the files being uploaded by domain class name and property name and identifier in GORM
            bucket = 'uploads'
            providerOptions {
              provider = 'local' // Switch to s3 if you wish to use s3 and install the karman-aws plugin
              basePath = 'storage'
              baseUrl  = 'http://54.93.249.148:8080/storage'
              //accessKey = "KEY" //Used for S3 Provider
              //secretKey = "KEY" //Used for S3 Provider
            }
          }
        }
      }
    }
  }
  development{
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
              //accessKey = "AKIAJ272K53WJUWKF54Q" //Used for S3 Provider
              //secretKey = "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" //Used for S3 Provider
            }
          }
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

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.lucafaggion.auth.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.lucafaggion.auth.UserRole'
grails.plugin.springsecurity.authority.className = 'com.lucafaggion.auth.Role'
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
  [pattern: '/storage/**',     access: ['permitAll']],
  [pattern: '/dbconsole/**',   access: ['permitAll']],
  [pattern: '/login/**',       access: ['permitAll']],
  [pattern: '/logout/**',      access: ['permitAll']],
  [pattern: '/register/**',    access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

