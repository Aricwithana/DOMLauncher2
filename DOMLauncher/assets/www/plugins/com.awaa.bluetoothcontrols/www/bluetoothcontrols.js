cordova.define("com.awaa.bluetoothcontrols.bluetoothcontrols", function(require, exports, module) { module.exports = {

    check:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Bluetooth Controls check Error:'+error)}, 
            "bluetoothcontrols", 
            'check',
            []
        );
    },
    
    enable:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Bluetooth Controls getIcons enable:'+error)}, 
            "bluetoothcontrols", 
            'enable',
            []
        );
    },
    
    disable:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Bluetooth Controls disable Error:'+error)}, 
            "bluetoothcontrols", 
            'disable',
            []
        );
    },
    
    toggle:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Bluetooth Controls toggle Error:'+error)}, 
            "bluetoothcontrols", 
            'toggle',
            []
        );
    }     
};
});
