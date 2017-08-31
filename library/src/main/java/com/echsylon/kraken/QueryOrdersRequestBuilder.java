package com.echsylon.kraken;

import com.echsylon.kraken.dto.Order;
import com.google.gson.reflect.TypeToken;

import static com.echsylon.kraken.Utils.asString;
import static com.echsylon.kraken.Utils.join;

/**
 * This class can build a request that retrieves information about any
 * particular order(s).
 * <p>
 * For further technical details see Kraken API documentation at:
 * https://www.kraken.com/help/api
 */
@SuppressWarnings("WeakerAccess")
public class QueryOrdersRequestBuilder extends RequestBuilder<Dictionary<Order>> {

    /**
     * Constructor, intentionally hidden from public use.
     *
     * @param callCounter The request call counter. May be null.
     * @param baseUrl     The base url of the request.
     * @param key         The user API key.
     * @param secret      The corresponding secret.
     */
    QueryOrdersRequestBuilder(final CallCounter callCounter,
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
     * Sets the include trades request property.
     *
     * @param includeTrades Whether to include trades. Defaults to false.
     * @return This request builder instance allowing method call chaining.
     */
    public QueryOrdersRequestBuilder useTradesFlag(boolean includeTrades) {
        data.put("trades", asString(includeTrades));
        return this;
    }

    /**
     * Sets the user reference request property.
     *
     * @param userReferenceId Restrict results to given user reference id.
     * @return This request builder instance allowing method call chaining.
     */
    public QueryOrdersRequestBuilder useReference(String userReferenceId) {
        data.put("userref", userReferenceId);
        return this;
    }

    /**
     * Sets the transaction ids request property.
     *
     * @param transactionIds Transaction ids of the orders to get. 20 max, at
     *                       least one is required.
     * @return This request builder instance allowing method call chaining.
     */
    public QueryOrdersRequestBuilder useTransactions(String... transactionIds) {
        data.put("txid", join(transactionIds));
        return this;
    }

}