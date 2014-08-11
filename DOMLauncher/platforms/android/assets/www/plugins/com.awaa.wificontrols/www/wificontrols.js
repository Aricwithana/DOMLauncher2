cordova.define("com.awaa.wificontrols.wificontrols", function(require, exports, module) { module.exports = {

    enable:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Wifi Controls Error:'+error)}, 
            "wificontrols", 
            'enable',
            [{callback:args.callback}]
        );
    }, 
    
    disable:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Wifi Controls Error:'+error)}, 
            "wificontrols", 
            'disable',
            [{callback:args.callback}]
        );
    }, 
    
    check:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Wifi Controls Error:'+error)}, 
            "wificontrols", 
            'check',
            [{callback:args.callback}]
        );
    }, 
    
    toggle:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Wifi Controls Error:'+error)}, 
            "wificontrols", 
            'toggle',
            [{callback:args.callback}]
        );
    },
    
    stop:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Wifi Controls Error:'+error)}, 
            "wificontrols", 
            'stop',
            []
        );
    },    
};
});
