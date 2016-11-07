var exec = require('cordova/exec');

window.isNotificationEnabled = function(arg0, success, error) {
    console.log("isNotificationEnabled called with args :: " + arg0);
    exec(success, error, "NotificationsManager", "isNotificationEnabled", arg0);
};