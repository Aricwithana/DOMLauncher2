cordova.define("com.awaa.batterylevel.batterylevel", function(require, exports, module) { module.exports = {

    current:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Battery Level current Error:'+error)}, 
            "batterylevel", 
            'current',
            []
        );
    },  
};
});
