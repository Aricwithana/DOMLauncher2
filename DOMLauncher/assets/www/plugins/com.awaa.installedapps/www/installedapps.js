cordova.define("com.awaa.installedapps.installedapps", function(require, exports, module) { module.exports = {

    getApps:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Installed Apps getApps Error:'+error)}, 
            "installedapps", 
            'getApps',
            []
        );
    },
    
    getIcons:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Installed Apps getIcons Error:'+error)}, 
            "installedapps", 
            'getIcons',
            []
        );
    },
    
    getIcon:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Installed Apps getIcons Error:'+error)}, 
            "installedapps", 
            'getIcons',
            [{package:args.package}]
        );
    }    
};
});
