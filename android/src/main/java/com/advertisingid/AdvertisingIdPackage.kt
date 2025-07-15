package com.advertisingid

import com.facebook.react.BaseReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider
import com.advertisingid.AdvertisingIdSpec

class AdvertisingIdPackage : BaseReactPackage() {
    override fun getModule(name: String, reactContext: ReactApplicationContext): NativeModule? =
        if (name == AdvertisingIdSpec.NAME) {
            AdvertisingIdModule(reactContext)
        } else {
            null
  }

    override fun getReactModuleInfoProvider() = ReactModuleInfoProvider {
        mapOf(
            AdvertisingIdSpec.NAME to ReactModuleInfo(
                name = AdvertisingIdSpec.NAME,
                className = AdvertisingIdModule::class.java.name,
                canOverrideExistingModule = false,
                needsEagerInit = false,
                isCxxModule = false,
                isTurboModule = true
            )
        )
  }
}
