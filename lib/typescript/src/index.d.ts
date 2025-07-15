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
export declare function getAdvertisingId(): Promise<string>;
declare const _default: {
    getAdvertisingId: typeof getAdvertisingId;
};
export default _default;
//# sourceMappingURL=index.d.ts.map