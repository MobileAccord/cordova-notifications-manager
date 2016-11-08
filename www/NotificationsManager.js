var exec = require('cordova/exec');

window.isNotificationEnabled = function(success, error) {
    exec(success, error, "NotificationsManager", "isNotificationEnabled", null);
};