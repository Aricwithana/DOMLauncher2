cordova.define("com.awaa.cellsignal.cellsignal", function(require, exports, module) { module.exports = {

    enable:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Cell Signal Error:'+error)}, 
            "cellsignal", 
            'enable',
            [{callback:args.callback}]
        );
    }, 
    
    disable:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('Cell Signal Error:'+error)}, 
            "cellsignal", 
            'disable',
            [{callback:args.callback}]
        );
    },     
};
});
