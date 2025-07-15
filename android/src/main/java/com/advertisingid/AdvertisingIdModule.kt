package com.advertisingid

import android.content.Context
import android.os.AsyncTask
import com.facebook.react.bridge.Promise
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.bridge.ReactApplicationContext
import com.advertisingid.AdvertisingIdSpec
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import java.io.IOException

@ReactModule(name = AdvertisingIdSpec.NAME)
class AdvertisingIdModule(reactContext: ReactApplicationContext) : AdvertisingIdSpec(reactContext) {
    override fun getAdvertisingId(promise: Promise) {
        try {
            object : AsyncTask<Void, Void, String>() {
                override fun doInBackground(vararg params: Void): String {
                    val context: Context = reactApplicationContext
                    var advertisingId = ""
                    try {
                        val adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context)
                        if (adInfo.isLimitAdTrackingEnabled()) {
                            advertisingId = ""
                        } else {
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
}
