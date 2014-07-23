cordova.define("com.awaa.mobiledata.mobiledata", function(require, exports, module) { module.exports = {

    check:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Mobile Data check Error:'+error)}, 
            "mobiledata", 
            'check',
            []
        );
    },
    
    enable:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Mobile Data enable Error:'+error)}, 
            "mobiledata", 
            'enable',
            []
        );
    },
    
    disable:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Mobile Data disable Error:'+error)}, 
            "mobiledata", 
            'disable',
            []
        );
    },
    
    toggle:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Mobile Data toggle Error:'+error)}, 
            "mobiledata", 
            'toggle',
            []
        );
    }     
};
});
