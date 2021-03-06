package com.echsylon.kraken.request;

import com.echsylon.kraken.Dictionary;
import com.echsylon.kraken.dto.Order;
import com.echsylon.kraken.internal.CallCounter;
import com.google.gson.reflect.TypeToken;

import static com.echsylon.kraken.internal.Utils.asString;
import static com.echsylon.kraken.internal.Utils.join;

/**
 * This class can build a request that retrieves information about any
 * particular order(s).
 * <p>
 * For further technical details see Kraken API documentation at:
 * https://www.kraken.com/help/api
 */
@SuppressWarnings("WeakerAccess")
public class QueryOrdersRequestBuilder extends RequestBuilder<Dictionary<Order>, QueryOrdersRequestBuilder> {

    /**
     * Creates a new request builder.
     *
     * @param callCounter The request call counter. May be null.
     * @param baseUrl     The base url of the request.
     * @param key         The user API key.
     * @param secret      The corresponding secret.
     */
    public QueryOrdersRequestBuilder(final CallCounter callCounter,
                                     final String baseUrl,
                                     final String key,
                                     final byte[] secret) {

        super(1, callCounter, key, secret, baseUrl,
                "POST", "/0/private/QueryOrders",
                TypeToken.getParameterized(
                        Dictionary.class,
                        Order.class).getType());
    }


    /**
     * Sets the one time password to use when performing the request.
     *
     * @param oneTimePassword The password.
     * @return This request builder instance allowing method call chaining.
     */
    public QueryOrdersRequestBuilder useOneTimePassword(final String oneTimePassword) {
        data.put("otp", oneTimePassword);
        return this;
    }

    /**
     * Sets the include trades request property.
     *
     * @param includeTrades Whether to include trades. Defaults to false.
     * @return This request builder instance allowing method call chaining.
     */
    public QueryOrdersRequestBuilder useTradesFlag(final boolean includeTrades) {
        data.put("trades", asString(includeTrades));
        return this;
    }

    /**
     * Sets the user reference request property.
     *
     * @param userReferenceId Restrict results to given user reference id.
     * @return This request builder instance allowing method call chaining.
     */
    public QueryOrdersRequestBuilder useReference(final String userReferenceId) {
        data.put("userref", userReferenceId);
        return this;
    }

    /**
     * Sets the transaction ids request property.
     *
     * @param transactionIds Transaction ids of the orders to get. 20 max, at least one is
     *                       required.
     * @return This request builder instance allowing method call chaining.
     */
    public QueryOrdersRequestBuilder useTransactions(final String... transactionIds) {
        data.put("txid", join(transactionIds));
        return this;
    }

}
