cordova.define("com.awaa.doml.doml", function(require, exports, module) { module.exports = {

    getDMD:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('DOMLauncher API getDMD Error:'+error)}, 
            "doml", 
            'getDMD',
            []
        );
    },
    
    setDMD:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('DOMLauncher API setDMD Error:'+error)}, 
            "doml", 
            'setDMD',
            [{name:args.name}]
        );                
    }   
};

document.addEventListener("deviceready", cordovaBack, false);

function cordovaBack(){
    document.addEventListener("backbutton", function(){}, false);
}
});
