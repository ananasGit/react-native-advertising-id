package com.advertisingid

import android.content.Context
import android.os.AsyncTask
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import java.io.IOException

class AdvertisingIdModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  fun getAdvertisingId(promise: Promise) {
    try {
      object : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void): String {
          val context: Context = reactApplicationContext
          var advertisingId = ""
          
          try {
            val adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context)
            
            // Check if user has disabled ad tracking/personalized ads
            if (adInfo.isLimitAdTrackingEnabled()) {
              // Return empty string if user has disabled ad tracking
              advertisingId = ""
            } else {
              // Only return the actual ID if user allows ad tracking
              advertisingId = adInfo.id ?: ""
            }
          } catch (e: IOException) {
            promise.reject("ERROR", e.message)
          } catch (e: GooglePlayServicesNotAvailableException) {
            promise.reject("ERROR", "Google Play Services not available")
          } catch (e: GooglePlayServicesRepairableException) {
            promise.reject("ERROR", "Google Play Services needs repair")
          } catch (e: Exception) {
            promise.reject("ERROR", e.message)
          }
          
          return advertisingId
        }

        override fun onPostExecute(advertisingId: String) {
          promise.resolve(advertisingId)
        }
      }.execute()
    } catch (e: Exception) {
      promise.reject("ERROR", e.message)
    }
  }

  companion object {
    const val NAME = "AdvertisingId"
  }
}