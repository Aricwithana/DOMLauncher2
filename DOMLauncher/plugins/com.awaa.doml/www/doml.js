module.exports = {

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
    }, 
    
    restart:function(args) {
        cordova.exec(
            (!args.success) ? null : args.success, 
            function(error){alert('DOMLauncher API restart Error:'+error)}, 
            "doml", 
            'restart',
            []
        );
    }     
};

var backButton = null;

document.addEventListener("deviceready", cordovaBack, false);

function cordovaBack(){
	if(typeof backButton === 'function'){
		document.addEventListener("backbutton", backButton, false);
	}else{
		document.addEventListener("backbutton", function(){}, false);
	}
}