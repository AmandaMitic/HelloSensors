# HelloSensors - MAMN01
Kurs: MAMN01 Avancerad interaktionsdesign 

## En kort beskrivning av hur du byggt din app

[Build your first app](https://developer.android.com/training/basics/firstapp/index.html) användes för att förstå hur man ska bygga ett projekt i Android Studio. Appen som gjorts består av 3 olika aktiviteter: [MainActivity](https://github.com/AmandaMitic/HelloSensors/blob/master/app/src/main/java/com/example/hellosensors/MainActivity.java),[AccelerometerActivity](https://github.com/AmandaMitic/HelloSensors/blob/master/app/src/main/java/com/example/hellosensors/AccelerometerActivity.java) och [CompassActivity](https://github.com/AmandaMitic/HelloSensors/blob/master/app/src/main/java/com/example/hellosensors/CompassActivity.java). I MainActivity finns det två knappar som länka till AccelerometerActivity och CompassActivity. 

### Kompass
Jag valde att ändra färg till rött på bakgrunden när telefonen är riktad åt Norr. Men dessutom lades det till en vibration som också visade på att telefonen var riktad åt Norr. 

Detta gjordes för att vissa användare kanske inte inte kan se, då vibrationen kan användas som feedback. 

### Accelerometer
Jag valde att skriva ut en text när telefonen är vinklad åt vänster eller höger (alltså enbart i x-riktning). Men dessutom lades det till en vibration som också visade på att telefonen var riktad antingen åt vänster eller höger. Detta gjordes med samma avsikt som för kompassen, då vissa användare kanske inte kan se men istället kan känna av vibrationen i handen. 

#### Sources and information from: 
 - https://developer.android.com/guide/topics/sensors/sensors_motion.html
 - https://developer.android.com/reference/android/hardware/SensorManager.html
 - https://stackoverflow.com/questions/13950338/how-to-make-an-android-device-vibrate-with-different-frequency  
 - https://developer.android.com/training/constraint-layout 
 - https://www.techrepublic.com/article/pro-tip-create-your-own-magnetic-compass-using-androids-internal-sensors/
