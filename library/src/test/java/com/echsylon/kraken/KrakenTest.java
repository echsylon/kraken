package com.echsylon.kraken;

import com.echsylon.atlantis.Atlantis;
import com.echsylon.blocks.callback.DefaultRequest;
import com.echsylon.kraken.dto.Time;
import com.echsylon.kraken.exception.KrakenRequestException;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.concurrent.ExecutionException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * These test cases will test the core capabilities of the Android Kraken SDK.
 * <p>
 * The tests will take advantage of the fact that the Kraken implementation
 * returns a {@code DefaultRequest} object. Since the {@code DefaultRequest}
 * class extends {@code FutureTask} we can block the test thread until a result
 * is produced.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, sdk = 25)
public class KrakenTest {

    private Atlantis atlantis;

    @After
    public void after() {
        atlantis.stop();
        atlantis = null;
    }


    @Test
    public void responseWithError_shouldThrowException() throws Exception {
        atlantis = MockHelper.start("GET", "/0/public/Time",
                "{'error': ['Some:Error:Structure']}");

        DefaultRequest<Time> request =
                (DefaultRequest<Time>) new Kraken("http://localhost:8080")
                        .getServerTime()
                        .enqueue();

        assertThatThrownBy(() -> request.get(1, SECONDS))
                .isInstanceOf(ExecutionException.class)
                .hasCauseInstanceOf(KrakenRequestException.class);
    }

    @Test
    public void responseWithSuccess_shouldNotThrowException() throws Exception {
        atlantis = MockHelper.start("GET", "/0/public/Time",
                "{'error': [], 'result': {" +
                        " 'unixtime': 0," +
                        " 'rfc1123': 'some_rfc1123_time'}}");

        DefaultRequest<Time> request =
                (DefaultRequest<Time>) new Kraken("http://localhost:8080")
                        .getServerTime()
                        .enqueue();

        assertThat(request.get(1, SECONDS), is(notNullValue()));
    }

    @Test
    public void requestingPrivateResource_shouldThrowExceptionIfNoCredentialsProvided() throws Exception {
        atlantis = MockHelper.start("POST", "/0/private/Balance",
                "{'error': [], 'result': {}}");

        DefaultRequest<?> request =
                (DefaultRequest<?>) new Kraken("http://localhost:8080")
                        .getAccountBalance()
                        .enqueue();

        assertThatThrownBy(() -> request.get(1, SECONDS))
                .isInstanceOf(ExecutionException.class)
                .hasCauseInstanceOf(IllegalStateException.class);
    }

    @Test
    public void requestingPrivateResource_shouldNotThrowExceptionIfCredentialsProvided() throws Exception {
        atlantis = MockHelper.start("POST", "/0/private/Balance",
                "{'error': [], result: {}}");

        String key = "key";
        String secret = "c2VjcmV0";

        DefaultRequest<?> request =
                (DefaultRequest<?>) new Kraken("http://localhost:8080", key, secret)
                        .getAccountBalance()
                        .enqueue();

        assertThat(request.get(1, SECONDS), is(notNullValue()));
    }

}