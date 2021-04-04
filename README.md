# HelloSensors - MAMN01
Kurs: MAMN01 Avancerad interaktionsdesign 

## En kort beskrivning av hur du byggt din app

[Create an Android project](https://developer.android.com/training/basics/firstapp/creating-project) och [Build your first app](https://developer.android.com/training/basics/firstapp/index.html) användes för att förstå hur man ska bygga ett projekt i Android Studio. Appen som gjorts består av 3 olika aktiviteter: [MainActivity](https://github.com/AmandaMitic/HelloSensors/blob/master/app/src/main/java/com/example/hellosensors/MainActivity.java), [AccelerometerActivity](https://github.com/AmandaMitic/HelloSensors/blob/master/app/src/main/java/com/example/hellosensors/AccelerometerActivity.java) och [CompassActivity](https://github.com/AmandaMitic/HelloSensors/blob/master/app/src/main/java/com/example/hellosensors/CompassActivity.java). Där det i MainActivity finns två knappar som länkar vidare till AccelerometerActivity och CompassActivity. 

### Accelerometer
Jag har använt mig av kod från [Android Accelerometer Tutorial 1](https://www.youtube.com/watch?v=pkT7DU1Yo9Q) och [Android Accelerometer Tutorial 2](https://www.youtube.com/watch?v=Rda_5s4rObQ). 

Jag valde att skriva ut en text när telefonen är vinklad åt vänster eller höger (alltså enbart i x-riktning). Men dessutom lades det till en vibration som också visade på att telefonen var riktad antingen åt vänster eller höger. Detta gjordes på grund av att det existerar användare som kanske inte har perspekt syn eller någon synskada som istället kan känna av vibrationen i handen som en feedback. 

### Kompass
Jag har använt mig av kod från [Android compass code example](https://www.javacodegeeks.com/2013/09/android-compass-code-example.html), men dessutom utgått rätt mycket från hur jag gjorde i AccelerometerActivity. 

Jag valde att ändra färg till rött på bakgrunden när telefonen är riktad åt Norr. Men dessutom lades det till en vibration som också visade på att telefonen var riktad åt Norr. 

Detta gjordes med samma avsikt som för accelerometern,för att vissa användare kanske har en synskada, då vibrationen kan användas som feedback istället. 

### Tillagda saker 
- Jag la till en egen bild på kompassen. 
- Vibration, för Haptisk interaktion både för kompassen då telefonen är riktat mot Norr och accelerometern då telefonen är lutad åt vänster och höger. 
- Färg på bakgrunden för kompassen när telefonen är i riktning mot Norr. 
- Text, i accelerometern då telefonen är lutad mot höger, vänster eller upp och ner så kommer det information till användaren på skärmen. 
- Lågpass filtrering, som filtrerar sensorerna enligt koden [Lågpass filrering](https://www.built.io/blog/applying-low-pass-filter-to-android-sensor-s-readings). 

#### Sources and information from: 
 - https://developer.android.com/training/basics/firstapp/index.html
 - https://developer.android.com/training/basics/firstapp/building-ui
 - https://developer.android.com/guide/topics/sensors/sensors_motion.html
 - https://developer.android.com/reference/android/hardware/SensorManager.html
 - https://developer.android.com/reference/android/hardware/SensorEvent.html
 - https://developer.android.com/reference/android/hardware/Sensor#TYPE_ACCELEROMETER
 - https://developer.android.com/training/constraint-layout 
 - https://www.techrepublic.com/article/pro-tip-create-your-own-magnetic-compass-using-androids-internal-sensors/
 - https://stackoverflow.com/questions/13950338/how-to-make-an-android-device-vibrate-with-different-frequency  
 - https://www.built.io/blog/applying-low-pass-filter-to-android-sensor-s-readings
