cordova.define("com.awaa.brightnesscontrols.brightnesscontrols", function(require, exports, module) { module.exports = {

    check:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Brightness Controls check Error:'+error)}, 
            "brightnesscontrols", 
            'check',
            [{flag:args.flag}]
        );
    }, 
    
    enable:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Brightness Controls enable Error:'+error)}, 
            "brightnesscontrols", 
            'enable',
            []
        );
    }, 
    
    disable:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Brightness Controls disable Error:'+error)}, 
            "brightnesscontrols", 
            'disable',
            []
        );
    },
     
    
    toggle:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Brightness Controls toggle Error:'+error)}, 
            "brightnesscontrols", 
            'toggle',
            []
        );
    }, 
    
    value:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Brightness Controls value Error:'+error)}, 
            "brightnesscontrols", 
            'value',
            [{value:args.value}]
        );
    }, 
    
    up:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Brightness Controls up Error:'+error)}, 
            "brightnesscontrols", 
            'up',
            [{step:args.step}]
        );
    }, 
    
    down:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Brightness Controls down Error:'+error)}, 
            "brightnesscontrols", 
            'down',
            [{step:args.step}]
        );
    }
};
});
