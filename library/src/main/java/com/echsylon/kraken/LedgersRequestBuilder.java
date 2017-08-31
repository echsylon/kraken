package com.echsylon.kraken;

import com.echsylon.kraken.dto.Ledger;
import com.google.gson.reflect.TypeToken;

import static com.echsylon.kraken.Utils.asString;
import static com.echsylon.kraken.Utils.join;

/**
 * This class can build a request that retrieves information about ledgers.
 * <p>
 * For further technical details see Kraken API documentation at:
 * https://www.kraken.com/help/api
 */
@SuppressWarnings("WeakerAccess")
public class LedgersRequestBuilder extends RequestBuilder<Dictionary<Ledger>> {

    /**
     * Constructor, intentionally hidden from public use.
     *
     * @param callCounter The request call counter. May be null.
     * @param baseUrl     The base url of the request.
     * @param key         The user API key.
     * @param secret      The corresponding secret.
     */
    LedgersRequestBuilder(final CallCounter callCounter,
                          final String baseUrl,
                          final String key,
                          final byte[] secret) {

        super(2, callCounter, key, secret, baseUrl,
                "POST", "/0/private/Ledgers",
                TypeToken.getParameterized(
                        Dictionary.class,
                        Ledger.class).getType());
    }


    /**
     * Sets the asset class request property.
     *
     * @param assetClass The type of asset. Defaults to "currency".
     * @return This request builder instance allowing method call chaining.
     */
    public LedgersRequestBuilder useAssetClass(String assetClass) {
        data.put("aclass", assetClass);
        return this;
    }

    /**
     * Sets the trade type request property.
     *
     * @param type Type of ledger to get. Options: "all" (default), "deposit",
     *             "withdrawal", "trade", "margin".
     * @return This request builder instance allowing method call chaining.
     */
    public LedgersRequestBuilder useType(String type) {
        data.put("type", type);
        return this;
    }

    /**
     * Sets the start request property.
     *
     * @param start Start time or ledger id of results. Exclusive.
     * @return This request builder instance allowing method call chaining.
     */
    public LedgersRequestBuilder useStartTag(String start) {
        data.put("start", start);
        return this;
    }

    /**
     * Sets the end request property.
     *
     * @param end End time or ledger id of results. Inclusive.
     * @return This request builder instance allowing method call chaining.
     */
    public LedgersRequestBuilder useEndTag(String end) {
        data.put("end", end);
        return this;
    }

    /**
     * Sets the offset request property.
     *
     * @param offset Result offset.
     * @return This request builder instance allowing method call chaining.
     */
    public LedgersRequestBuilder useOffset(int offset) {
        data.put("ofs", asString(offset));
        return this;
    }

    /**
     * Sets the assets request property.
     *
     * @param assets Assets to restrict result to. Defaults to all.
     * @return This request builder instance allowing method call chaining.
     */
    public LedgersRequestBuilder useAsset(String... assets) {
        data.put("asset", join(assets));
        return this;
    }

}