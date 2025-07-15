"use strict";

import { NativeModules, Platform } from 'react-native';
const LINKING_ERROR = `The package 'react-native-advertising-id' doesn't seem to be linked. Make sure: \n\n` + Platform.select({
  ios: "- You have run 'pod install'\n",
  default: ''
}) + '- You rebuilt the app after installing the package\n' + '- You are not using Expo Go\n';
const AdvertisingId = NativeModules.AdvertisingId ? NativeModules.AdvertisingId : new Proxy({}, {
  get() {
    throw new Error(LINKING_ERROR);
  }
});

/**
 * Get the advertising ID for the device
 *
 * On Android, this is the Google Advertising ID
 * On iOS, this is the IDFA (Identifier for Advertising)
 *
 * Note: On iOS 14+, this will prompt the user for permission to track
 *
 * @returns Promise<string> - Returns the advertising ID or empty string if not available
 */
export function getAdvertisingId() {
  return AdvertisingId.getAdvertisingId();
}
export default {
  getAdvertisingId
};
//# sourceMappingURL=index.js.map