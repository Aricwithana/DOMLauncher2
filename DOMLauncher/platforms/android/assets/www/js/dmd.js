var missedCallsVal = 0;
var missedSMSVal = 0;

document.addEventListener('deviceready', onload, false);

function onload(){
    document.addEventListener("pause", onPause, false);
    document.addEventListener("resume", onResume, false);
    document.addEventListener("menubutton", toggleSidebar, false);
    appsIconCheck();
    window.cellsignal.enable({callback:'cellularSignal'});
    window.missedcomm.calls({callback:'missedCalls', timing:5000, flag:'enable'});
    window.missedcomm.sms({callback:'missedSMS', timing:5000, flag:'enable'});
    window.doml.getDMD({success:loadDMD});
    domlChecks();
}

//Handle DOMod selections and event.
$(document).on('tap', '#refreshDMD', function(){window.doml.getDMD({success:loadDMD});});
$(document).on('tap', 'li[data-dmd]', setNewDMD);

function loadDMD(returnVal){
    list = $('#domodsPanel .list');
    $(list).empty(); 
    $(list).append('<li><div class="text" id="refreshDMD"><h2>&lt;refresh&gt;</h2></li>');
    $(returnVal.returnVal).each(function(){
        $(list).append('<li data-dmd="'+this+'"><div class="text"><h2>'+this+'</h2></li>');
    });
}

function setNewDMD(){
    window.wificontrols.stop({});
    window.missedcomm.sms({flag:'disable'});
    window.missedcomm.calls({flag:'disable'});
    window.cellsignal.disable({});
    cordova.exec(null, null, "Battery", "stop", []);
    
    var selectedDMD= $(this).attr('data-dmd');
    window.doml.setDMD({name:selectedDMD});
}


//To gen or not to gen icons.
function appsIconCheck(){
    window.requestFileSystem(window.TEMPORARY, 1024*1024, function(fs) {
        fs.root.getDirectory('/data/data/com.awaa.domlauncher/icons', {create: false}, 
            function(dirEntry) {
                window.installedapps.getApps({success:generateApps});
            }, 
            function(){
                window.installedapps.getIcons({success:generateApps});
            });
    }, null);
}

//Missed Communications
function missedCalls(returnVal){
    missedCallsVal = returnVal;
    $('#missedCalls').remove();
    if(returnVal !== 0){
       $('#footerCallouts').append('<li id="missedCalls"><h3>calls - '+returnVal+'</h3></li>');  
    }      
}

function missedSMS(returnVal){
    missedSMSVal = returnVal;
    $('#missedSMS').remove();
    if(returnVal !== 0){
       $('#footerCallouts').append('<li id="missedSMS"><h3>sms - '+returnVal+'</h3></li>');  
    }         
}

//Create App Panel
function generateApps(returnVal){
    var appListArray = JSON.parse(returnVal.returnVal);
    appListArray.sort(compare);
    $('#appPanel').empty();
    $(appListArray).each(function(){
        $(appPanel).append('<div class="app flex" data-appName="'+this.name+'" data-appPackage="'+this.package+'"  data-appPackage="'+this.activity+'"><img src="'+this.path+'"</div>');
    });
    $('#appPanel').append('<div id="appPanelRefresh"><span>&lt;refresh&gt;</span></div>');
}


//Launch Apps
$(document).on('tap', '.app', function(){
    var pkg = $(this).attr('data-appPackage');
    var act = $(this).attr('data-appActivity');
    window.launch.app({package:pkg, activity:act});
});

//Refresh Apps
$(document).on('tap', '#appPanelRefresh span', function(){
    window.installedapps.getIcons({success:generateApps});
});


//Toggle Side Menu
$(document).on('tap', '#mobileMenuToggle, .logo, .sidebarActive #contentPanels', toggleSidebar);

function toggleSidebar(){
    if($('html').hasClass('sidebarActive ')){
        $('html').removeClass('sidebarActive ')
    }else{
        $('html').addClass('sidebarActive ')
    }
}

//Swap Active Panel View
$(document).on('tap', '.menu:not(.outlinks) a:not(#mobileMenuToggle), #showTerms', swapPanels);

function swapPanels(){

    var panelLink = $(this).attr('data-href');
    if(!$(panelLink).hasClass('shown')){
        $('.shown').addClass('hiding');
        $(panelLink).addClass('shown');
        $('#currentPage h3').text(panelLink);
        setTimeout(function(){$('.shown.hiding').removeClass('shown hiding');}, 350);
    } 
}

$(document).on('click', '.chkbx', preventLabelDoubleEvent);

function preventLabelDoubleEvent(event){
    event.preventDefault();
    event.stopPropagation();      
}

//Fullscreen Controls
$('#toggleFullscreen').on('tap', toggleFullscreen);

function toggleFullscreen(){              
    if (StatusBar.isVisible) {
        $('#toggleFullscreen').find('input').prop('checked', true); 
        StatusBar.hide();
    }else{
        $('#toggleFullscreen').find('input').prop('checked', false);
        StatusBar.show();
    }     
}


//Wifi Controls
$('#toggleWifi').on('click', function(){window.wificontrols.toggle({callback:'wifiSignal', success:toggleWifi});});

function toggleWifi(returnVal){
    $('#wifiSignal').remove();
    if(returnVal.returnVal === 'enabled'){
        $('#footerCallouts').append('<li id="wifiSignal"><h3>wifi - 100%</h3></li>');
        $('#toggleWifi').find('input').prop('checked', true);  
    }else if(returnVal.returnVal === 'disabled'){
        $('#toggleWifi').find('input').prop('checked', false); 
        setTimeout(function(){window.mobiledata.check({success:toggleData});}, 10000);
    }
}

//Callback for Java Called Wifi Signal Listener
function wifiSignal(returnVal){
	var maxStrength = -50; 
	var minStrength = -120; 
	var percentage = Math.round(100 - Math.max(0, Math.min((returnVal - maxStrength) / (minStrength - maxStrength), 1) * 100));	
	/*Begin Theme Specific Editible Code*/
		$('#wifiSignal h3').text('wifi - '+percentage+'%');
	/*End Theme Specific Editible Code*/
}


//Bluetooth Controls
$('#toggleBluetooth').on('click', function(){window.bluetoothcontrols.toggle({success:toggleBluetooth});});

function toggleBluetooth(returnVal){
    if(returnVal.returnVal === 'enabled'){
        $('#toggleBluetooth').find('input').prop('checked', true);  
    }else if(returnVal.returnVal === 'disabled'){
        $('#toggleBluetooth').find('input').prop('checked', false);  
    }     
}


//Data Controls
$('#toggleData').on('click', function(){window.mobiledata.toggle({success:toggleData});});

function toggleData(returnVal){  
    if(returnVal.returnVal === 'enabled'){
        $('#toggleData').find('input').prop('checked', true);  
    }else if(returnVal.returnVal === 'disabled'){
        $('#toggleData').find('input').prop('checked', false);  
    }       
}


//Orientation Controls
$('#orientationRotate').on('click', function(){
    screen.unlockOrientation();  
});

$('#orientationPortrait').on('click', function(){
    screen.lockOrientation('portrait');  
});

$('#orientationLandscape').on('click', function(){
    screen.lockOrientation('landscape');  
});


//Volume Controls
$('#ringerNormal').on('click', function(){
    window.volumecontrols.normal({success:setVolVal});
});

$('#ringerSilent').on('click', function(){
    window.volumecontrols.silent({success:setVolVal});
});

$('#ringerVibrate').on('click', function(){
    window.volumecontrols.vibrate({success:setVolVal});
});

$('#ringerControls .increase').on('click', function(){ 
    window.volumecontrols.up({stream:'ringer', success:setVolVal});
});

$('#ringerControls .decrease').on('click', function(){
    window.volumecontrols.down({stream:'ringer', success:setVolVal});
});

$('#mediaControls .increase').on('click', function(){
    window.volumecontrols.up({stream:'media', success:setMediaVal});
});

$('#mediaControls .decrease').on('click', function(){
    window.volumecontrols.down({stream:'media', success:setMediaVal});
});

function setVolVal(returnVal){
    if(returnVal.returnVal > 0){
        $('#ringerNormal').find('input').prop('checked', true);
    }else{
        window.volumecontrols.check({stream:'ringer', flag:'mode', success:ringerModeCheck});
    }
    $('#ringerControls .text').text(returnVal.returnVal);
}

function setMediaVal(returnVal){
    $('#mediaControls .text').text(returnVal.returnVal);
}


//Brightness Controls
$('#brightnessControls .increase').on('click', function(){
    window.brightnesscontrols.up({step:25, success:setBrightVal});
});

$('#brightnessControls .decrease').on('click', function(){
    window.brightnesscontrols.down({step:25, success:setBrightVal});
});

function setBrightVal(returnVal){
    $('#brightnessControls .text').text(returnVal.returnVal);
}


$('#toggleAutoBright').on('click', function(){window.brightnesscontrols.toggle({success:toggleAutoBright});});

function toggleAutoBright(returnVal){
    if(returnVal.returnVal === 'enabled'){
        $('#toggleAutoBright').find('input').prop('checked', true);  
    }else if(returnVal.returnVal === 'disabled'){
        $('#toggleAutoBright').find('input').prop('checked', false);  
    }         
}


//Callback for Java Cell Signal Listener
function cellularSignal(returnVal){
	var maxStrength = -100; 
	var minStrength = -70; 
	var percentage = Math.round(100 - Math.max(0, Math.min((returnVal - maxStrength) / (minStrength - maxStrength), 1) * 100));

	/*Begin Theme Specific Editible Code*/
		$('#cellSignal h3').text('signal - '+percentage+'%');
	/*End Theme Specific Editible Code*/		
}

function ringerModeCheck(returnVal){
    if(returnVal.returnVal === 'silent'){
        $('#ringerSilent').find('input').prop('checked', true);
    }else if(returnVal.returnVal === 'vibrate'){
        $('#ringerVibrate').find('input').prop('checked', true);
    }else if(returnVal.returnVal === 'normal'){
        $('#ringerNormal').find('input').prop('checked', true);
    }
}

//Sets-Updates System Panel
function domlChecks(){
    window.missedcomm.check({callback:'missedCalls', flag:'calls'});
    window.missedcomm.check({callback:'missedSMS', flag:'sms'});
    window.volumecontrols.check({stream:'ringer', success:setVolVal});
    window.volumecontrols.check({stream:'media', success:setMediaVal});
    window.brightnesscontrols.check({flag:'value', success:setBrightVal});
    window.brightnesscontrols.check({flag:'mode', success:toggleAutoBright});
    window.bluetoothcontrols.check({success:toggleBluetooth});      
    window.volumecontrols.check({stream:'ringer', flag:'mode', success:ringerModeCheck});
    window.mobiledata.check({success:toggleData});
    window.wificontrols.check({callback:'wifiSignal', success:toggleWifi});
    
    if (StatusBar.isVisible) {
        $('#toggleFullscreen').find('input').prop('checked', false); 
    }else{
        $('#toggleFullscreen').find('input').prop('checked', true); 
    }
    
    //var orientation = screen.orientation; 
    //alert(orientation);
}


/*Handle the pause event*/
function onPause() {}

	
/*Handle the resume event*/
function onResume() {
    setTimeout(domlChecks, 1000);
}

function openExternal(elem) {
    window.open(elem.href, "_system");
    return false; // Prevent execution of the default onClick handler 
}

//Used to sort alphabetically within their array.
function compare(a,b) {
  if (a.name < b.name)
     return -1;
  if (a.name > b.name)
    return 1;
  return 0;
}
