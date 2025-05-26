# react-native-advertising-id

A React Native library to fetch advertising IDs for both Android and iOS platforms.

## Installation

```sh
npm install react-native-advertising-id
# or
yarn add react-native-advertising-id
```

### iOS Setup

For iOS, you need to add two entries to your `Info.plist` file:

`NSUserTrackingUsageDescription` - A message that tells the user why the app is requesting permission to use data for tracking the user or the device.

In your `Info.plist`, add:

```xml
<key>NSUserTrackingUsageDescription</key>
<string>This identifier will be used to deliver personalized ads to you.</string>
```

The required frameworks (`AppTrackingTransparency` and `AdSupport`) will be automatically linked when you install the pods.

Then run:

```sh
cd ios && pod install
```

### Android Setup

For Android, the library requires Google Play Services Ads Identifier. This is automatically included as a dependency when you install the package.

Make sure that your `AndroidManifest.xml` includes the following permission:

```xml
<uses-permission android:name="com.google.android.gms.permission.AD_ID" />
```

## Usage

```javascript
import { getAdvertisingId } from 'react-native-advertising-id';

// ...

const fetchAdvertisingId = async () => {
  try {
    const adId = await getAdvertisingId();
    console.log('Advertising ID:', adId);
    // If the user denied permission or ID is not available, adId will be an empty string
  } catch (error) {
    console.error('Error getting advertising ID:', error);
  }
};
```

## How It Works

### iOS

- For iOS 14 and later, this package uses the App Tracking Transparency framework to request permission from the user before accessing the IDFA (Identifier For Advertising).
- For iOS 13 and earlier, it checks if advertising tracking is enabled and returns the IDFA if available.
- If the user denies permission or advertising tracking is disabled, an empty string is returned.

### Android

- This package uses Google Play Services to retrieve the Google Advertising ID.
- If Google Play Services is not available or there's an error retrieving the ID, an empty string is returned.

## License

MIT
