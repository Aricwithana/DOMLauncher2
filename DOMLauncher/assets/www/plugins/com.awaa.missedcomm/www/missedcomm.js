cordova.define("com.awaa.missedcomm.missedcomm", function(require, exports, module) { module.exports = {

    calls:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Missed Comm calls Error:'+error)}, 
            "missedcomm", 
            'calls',
            [{timing:(!args.timing) ? null : args.timing, callback:(!args.callback) ? null : args.callback, flag:(!args.flag) ? null : args.flag}]
        );
    }, 

    sms:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Missed Comm sms Error:'+error)}, 
            "missedcomm", 
            'sms',
            [{timing:(!args.timing) ? null : args.timing, callback:(!args.callback) ? null : args.callback, flag:(!args.flag) ? null : args.flag}]
        );
    }, 
    
    check:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Missed Comm check Error:'+error)}, 
            "missedcomm", 
            'check',
            [{timing:(!args.timing) ? null : args.timing, callback:(!args.callback) ? null : args.callback, flag:(!args.flag) ? null : args.flag}]
        );
    }    
};
});
