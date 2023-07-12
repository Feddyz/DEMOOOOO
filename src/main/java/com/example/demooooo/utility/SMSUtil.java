package com.example.demooooo.utility;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.ICredential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponseBody;
import com.google.gson.Gson;
import darabonba.core.client.ClientOverrideConfiguration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SMSUtil {

    public static SendSmsResponseBody sendSMS(String phone, String code) throws ExecutionException, InterruptedException {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId("LTAI5tLb7G9LK5B3BT2QAx5L")
                .accessKeySecret("p5E8IL0bgnfnTtLskKAX3qIGncykke")
                //.securityToken("<your-token>") // use STS token
                .build());

        ICredential credentials = provider.getCredentials();

        // Configure the Client
        AsyncClient client = AsyncClient.builder()
                .region("cn-hangzhou") // Region ID
                //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
                .credentialsProvider(provider)
                //.serviceConfiguration(Configuration.create()) // Service-level configuration
                // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                        //.setConnectTimeout(Duration.ofSeconds(30))
                )
                .build();

        // Parameter settings for API request
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName("韩帅鹏的博客") // 签名的名称
                .templateCode("SMS_461870177")//模板的名称
                .phoneNumbers(phone)
                .templateParam("{\"code\":\"" + code + "\"}")
                // Request-level configuration rewrite, can set Http request parameters, etc.
                // .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))
                .build();

        // Asynchronously get the return value of the API request
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        // Synchronously get the return value of the API request
        SendSmsResponse resp = response.get();
        System.out.println(new Gson().toJson(resp));
        // Asynchronous processing of return values
    /*response.thenAccept(resp -> {
        System.out.println(new Gson().toJson(resp));
    }).exceptionally(throwable -> { // Handling exceptions
        System.out.println(throwable.getMessage());
        return null;
    });*/

        // Finally, close the client
        client.close();
        return resp.getBody();
    }

}
