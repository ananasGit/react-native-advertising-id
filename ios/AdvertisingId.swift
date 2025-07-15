import Foundation
import AdSupport
import AppTrackingTransparency

@objc(AdvertisingId)
class AdvertisingId: NSObject {
    
    @objc(getAdvertisingId:rejecter:)
    func getAdvertisingId(_ resolve: @escaping RCTPromiseResolveBlock, rejecter reject: @escaping RCTPromiseRejectBlock) {
        
        if #available(iOS 14, *) {
            // For iOS 14 and later, we need to request permission
            ATTrackingManager.requestTrackingAuthorization { status in
                DispatchQueue.main.async {
                    switch status {
                    case .authorized:
                        // User has granted permission
                        let idfa = ASIdentifierManager.shared().advertisingIdentifier.uuidString
                        resolve(idfa)
                    case .denied, .restricted, .notDetermined:
                        // User has denied permission or restricted
                        resolve("")
                    @unknown default:
                        resolve("")
                    }
                }
            }
        } else {
            // For iOS 13 and earlier
            if ASIdentifierManager.shared().isAdvertisingTrackingEnabled {
                let idfa = ASIdentifierManager.shared().advertisingIdentifier.uuidString
                resolve(idfa)
            } else {
                resolve("")
            }
        }
    }
    
    @objc
    static func requiresMainQueueSetup() -> Bool {
        return false
    }
}