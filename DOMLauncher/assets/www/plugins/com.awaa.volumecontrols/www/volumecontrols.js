cordova.define("com.awaa.volumecontrols.volumecontrols", function(require, exports, module) { module.exports = {

    check:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success,  
            function(error){alert('Volume Controls Check Error:'+error)}, 
            "volumecontrols", 
            'check',
            [{stream:(!args.stream) ? null : args.stream, flag:(!args.flag) ? null : args.flag, toast:null}]
        );
    },
    
    up:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Volume Controls Up Error:'+error)}, 
            "volumecontrols", 
            'up',
            [{stream:(!args.stream) ? null : args.stream, flag:null, toast:(!args.toast) ? null : args.toast}]
        );
    },   
    
    down:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Volume Controls Down Change Error:'+error)}, 
            "volumecontrols", 
            'down',
            [{stream:(!args.stream) ? null : args.stream, flag:null, toast:(!args.toast) ? null : args.toast}]
        );
    },
    
    percentage:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Volume Controls Percentage Error:'+error)}, 
            "volumecontrols", 
            'percentage',
            [{stream:args.stream, value:args.value, flag:null, toast:(!args.toast) ? null : args.toast}]
        );
    },    
    
    mute:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Volume Controls Mute Error:'+error)}, 
            "volumecontrols", 
            'mute',
            [{toast:(!args.toast) ? null : args.toast}]
        );
    },

    vibrate:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Volume Controls Vibrate Mode Error:'+error)}, 
            "volumecontrols", 
            'vibrate',
            [{stream:null, flag:null, toast:null}]
        );
    },
    
    silent:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Volume Controls Silent Mode Error:'+error)}, 
            "volumecontrols", 
            'silent',
            [{stream:null, flag:null, toast:null}]
        );
    }, 

    normal:function(args) {
        cordova.exec(
           (!args.success) ? null : args.success, 
            function(error){alert('Volume Controls Normal Mode Error:'+error)}, 
            "volumecontrols", 
            'normal',
            [{stream:null, flag:(!args.flag) ? null : args.flag, toast:null}]
        );
    },
};
});
