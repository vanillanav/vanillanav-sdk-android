[![](https://jitpack.io/v/rosoftlab/vanillanav-sdk-android.svg)](https://jitpack.io/#rosoftlab/vanillanav-sdk-android/1.0)
# vanillaNAV SDK for android

To start using vanillaNav indoor navigation in your android app:

* Sign up for a free account on www.vanillanav.com
* Create an online venue using [vanillaNAV Manager](vanillanav.com/admin)
* Include the vanillanav-sdk maven package
* Trigger the navigation screen using the sdk button or programatically


Add the JitPack repository to your build file
```gradle
allprojects {
  repositories { 
    maven { url "https://jitpack.io" }
  }
}
```

Add the dependency
```gradle
dependencies {
  compile 'com.github.rosoftlab:vanillanav-sdk-android:1.0'
}
```

#### Trigger navigation using NavigationButton

The parameters vn_venue_id and vn_destination_id are mandatory, they are generated using vanillaNAV Manger.

Include the following button into the android xml layout. 
```xml
<net.rosoftlab.nav.NavigationButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:vn_icon_size="big"
        android:padding="16dp"
        app:vn_destination_id="8"
        app:vn_venue_id="3870" />
```
#### Trigger navigation programmatically
Use the the static method provided by the sdk, the venueId and destinationId are generated using vanillaNAV Manager.
```java
VanillaNav.navigate(activity, venueId, destinationId);
```

#### Sample app

This repository contains a [sample app](https://github.com/rosoftlab/vanillanav-sdk-android/tree/develop/sample) that demonstrates the functionality described above.
