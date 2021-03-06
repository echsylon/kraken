package com.echsylon.kraken.request;

import com.echsylon.kraken.dto.OrderAddReceipt;
import com.echsylon.kraken.internal.CallCounter;

import static com.echsylon.kraken.internal.Utils.asString;
import static com.echsylon.kraken.internal.Utils.join;

/**
 * This class can build a request that adds a standard sell or buy order.
 * <p>
 * For further technical details see Kraken API documentation at:
 * https://www.kraken.com/help/api
 */
@SuppressWarnings("WeakerAccess")
public class AddOrderRequestBuilder extends RequestBuilder<OrderAddReceipt, AddOrderRequestBuilder> {

    /**
     * Creates a new request builder.
     *
     * @param callCounter The request call counter. May be null.
     * @param baseUrl     The base url of the request.
     * @param key         The user API key.
     * @param secret      The corresponding secret.
     */
    public AddOrderRequestBuilder(final CallCounter callCounter,
                                  final String baseUrl,
                                  final String key,
                                  final byte[] secret) {

        super(0, callCounter, key, secret, baseUrl,
                "POST", "/0/private/AddOrder",
                OrderAddReceipt.class);
    }


    /**
     * Sets the one time password to use when performing the request.
     *
     * @param oneTimePassword The password.
     * @return This request builder instance allowing method call chaining.
     */
    public AddOrderRequestBuilder useOneTimePassword(final String oneTimePassword) {
        data.put("otp", oneTimePassword);
        return this;
    }

    /**
     * Sets the asset pair request property.
     *
     * @param assetPair Asset pair of the new order.
     * @return This request builder instance allowing method call chaining.
     */
    public AddOrderRequestBuilder useAssetPair(final String assetPair) {
        data.put("pair", assetPair);
        return this;
    }

    /**
     * Sets the type request property.
     *
     * @param type Nature of the order. Options: "buy", "sell".
     * @return This request builder instance allowing method call chaining.
     */
    public AddOrderRequestBuilder useType(final String type) {
        data.put("type", type);
        return this;
    }

    /**
     * Sets the order type request property.
     *
     * @param orderType The type of the order. Options: "market", "limit",
     *                  "stop-loss", "take-profit", "stop-loss-profit",
     *                  "stop-loss-profit-limit", "stop-loss-limit",
     *                  "take-profit-limit", "trailing-stop", "trailing-stop-limit",
     *                  "stop-loss-and-limit", "settle-position".
     * @return This request builder instance allowing method call chaining.
     */
    public AddOrderRequestBuilder useOrderType(final String orderType) {
        data.put("ordertype", orderType);
        return this;
    }

    /**
     * Sets the price request property.
     *
     * @param price The price (depends on order type).
     * @return This request builder instance allowing method call chaining.
     */
    public AddOrderRequestBuilder usePrice(final String price) {
        data.put("price", price);
        return this;
    }

    /**
     * Sets the secondary price request property.
     *
     * @param secondaryPrice The secondary price (depends on order type).
     * @return This request builder instance allowing method call chaining.
     */
    public AddOrderRequestBuilder useSecondaryPrice(String secondaryPrice) {
        data.put("price2", secondaryPrice);
        return this;
    }

    /**
     * Sets the volume request property.
     *
     * @param volume Order volume in lots.
     * @return This request builder instance allowing method call chaining.
     */
    public AddOrderRequestBuilder useVolume(final String volume) {
        data.put("volume", volume);
        return this;
    }

    /**
     * Sets the leverage request property.
     *
     * @param leverage Amount of desired leverage. Defaults to none.
     * @return This request builder instance allowing method call chaining.
     */
    public AddOrderRequestBuilder useLeverage(final String leverage) {
        data.put("leverage", leverage);
        return this;
    }

    /**
     * Sets the order flags request property.
     *
     * @param flags List of order flags. Options viqc, fcib, fciq, nompp, post.
     *              See API documentation for details.
     * @return This request builder instance allowing method call chaining.
     */
    public AddOrderRequestBuilder useOrderFlags(final String... flags) {
        data.put("oflags", join(flags));
        return this;
    }

    /**
     * Sets the start time request property.
     *
     * @param startTime Scheduled start time as epoch seconds. Options: 0
     *                  (default), +[n], [n].
     * @return This request builder instance allowing method call chaining.
     */
    public AddOrderRequestBuilder useStartTime(final String startTime) {
        data.put("starttm", startTime);
        return this;
    }

    /**
     * Sets the expire time request property.
     *
     * @param expireTime Expiration time expressed in epoch seconds. Options: 0
     *                   (no exp. default), +[n], [n].
     * @return This request builder instance allowing method call chaining.
     */
    public AddOrderRequestBuilder useExpireTime(final String expireTime) {
        data.put("expiretm", expireTime);
        return this;
    }

    /**
     * Sets the user reference request property.
     *
     * @param userReference User reference id. 32-bit, signed.
     * @return This request builder instance allowing method call chaining.
     */
    public AddOrderRequestBuilder useReference(final String userReference) {
        data.put("userref", userReference);
        return this;
    }

    /**
     * Sets the closing order type request property.
     *
     * @param closeOrderType Closing order type.
     * @return This request builder instance allowing method call chaining.
     */
    public AddOrderRequestBuilder useCloseOrderType(final String closeOrderType) {
        data.put("close[ordertype]", closeOrderType);
        return this;
    }

    /**
     * Sets the closing price request property.
     *
     * @param closePrice Closing order price.
     * @return This request builder instance allowing method call chaining.
     */
    public AddOrderRequestBuilder useClosePrice(final String closePrice) {
        data.put("close[price]", closePrice);
        return this;
    }

    /**
     * Sets the secondary closing price request property.
     *
     * @param closeSecondaryPrice Closing order secondary price.
     * @return This request builder instance allowing method call chaining.
     */
    public AddOrderRequestBuilder useSecondaryClosePrice(final String closeSecondaryPrice) {
        data.put("close[price2]", closeSecondaryPrice);
        return this;
    }

    /**
     * Sets the secondary closing price request property.
     *
     * @param validateOnly Validate only, do not submit order.
     * @return This request builder instance allowing method call chaining.
     */
    public AddOrderRequestBuilder useValidateOnlyFlag(final boolean validateOnly) {
        if(validateOnly) data.put("validate", asString(validateOnly));
        return this;
    }

}
