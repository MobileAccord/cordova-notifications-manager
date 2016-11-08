# cordova-notifications-manager
Cordova plugin that checks whether push Notifications for the app has been enabled/disabled through settings

# Usage
```javascript
  window.isNotificationEnabled (
    function(sucess){ console.log('sucess : ' + sucess)}, 
    function(error){ console.log('failure : ' + error) }
  );
```
