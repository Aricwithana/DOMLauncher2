module.exports = {

    calls:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Missed Comm calls Error:'+error)}, 
            "missedcomm", 
            'calls',
            [{timing:args.timing, callback:args.callback, flag:args.flag}]
        );
    }, 
    
    sms:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Missed Comm sms Error:'+error)}, 
            "missedcomm", 
            'sms',
            [{timing:args.timing, callback:args.callback, flag:args.flag}]
        );
    }, 
    
    check:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Missed Comm check Error:'+error)}, 
            "missedcomm", 
            'check',
            [{callback:args.callback, flag:args.flag}]
        );
    }    
};