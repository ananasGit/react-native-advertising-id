#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(AdvertisingId, NSObject)

RCT_EXTERN_METHOD(getAdvertisingId:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)

@end