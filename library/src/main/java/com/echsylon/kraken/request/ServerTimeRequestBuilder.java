package com.echsylon.kraken.request;

import com.echsylon.kraken.dto.Time;
import com.echsylon.kraken.internal.CallCounter;

/**
 * This class can build a request that retrieves the current server time. This
 * is to aid in approximating the skew time between the server and client.
 * <p>
 * For further technical details see Kraken API documentation at:
 * https://www.kraken.com/help/api
 */
@SuppressWarnings("WeakerAccess")
public class ServerTimeRequestBuilder extends RequestBuilder<Time, ServerTimeRequestBuilder> {

    /**
     * Creates a new request builder.
     *
     * @param callCounter The request call counter. May be null.
     * @param baseUrl     The base url of the request.
     * @param key         The user API key.
     * @param secret      The corresponding secret.
     */
    public ServerTimeRequestBuilder(final CallCounter callCounter,
                                    final String baseUrl,
                                    final String key,
                                    final byte[] secret) {

        super(1, callCounter, key, secret, baseUrl,
                "GET", "/0/public/Time", Time.class);
    }

}
