package com.example.demo.provider;

import com.alibaba.fastjson.JSON;
import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.dto.GithubUser;

import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by BJB139 on 2019/7/18.
 */
@Component
public class GithubProvider {

    public String  GetAccessToken(AccessTokenDTO accessTokenDTO){
       MediaType mediaType = MediaType.get("application/json; charset=utf-8");
       OkHttpClient client = new OkHttpClient();
       RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
       Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
       try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                String split[] = string.split("&");
                String tokenstr = split[0];
                String token = tokenstr.split("=")[1];
                return token;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
    }

    public GithubUser getUser(String accessToken)  {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
           GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
           return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
