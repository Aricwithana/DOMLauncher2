cordova.define("com.awaa.launch.launch", function(require, exports, module) { module.exports = {

    app:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Launch App Error:'+error)}, 
            "launch", 
            'app',
            [{package:args.package, activity:(!args.activity) ? null : args.activity}]
        );
    },
    
    setting:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Launch Setting Error:'+error)}, 
            "launch", 
            'setting',
            [{setting:args.setting}]
        );
    }    
};
});
