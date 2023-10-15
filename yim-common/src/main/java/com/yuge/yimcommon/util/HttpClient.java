package com.yuge.yimcommon.util;

import okhttp3.*;

import java.io.IOException;

public final class HttpClient {

    private static MediaType mediaType = MediaType.parse("application/json");

    /**
     *
     * @param okHttpClient Factory for calls, which can be used to send HTTP requests and read their responses
     * @param params
     * @param url
     * @return
     * @throws IOException
     */
    public static Response call(OkHttpClient okHttpClient, String params, String url) throws IOException {

        RequestBody requestBody = RequestBody.create(mediaType, params);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        //execute():Invokes the request immediately, and blocks until the response can be processed or is in err
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        return response;
    }
}
