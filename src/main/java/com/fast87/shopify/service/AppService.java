package com.fast87.shopify.service;

import com.fast87.shopify.core.ShopifyEnv;
import com.fast87.shopify.utils.Commons;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    public String compareHmac(String query) throws Exception {
        query = query.substring(query.indexOf("shop="));
        String str_hmac = Commons.makeHmac(query);

        return str_hmac;
    }

    public String makeRedirectUrl(String shop_host) {
        StringBuilder sb = new StringBuilder()
                .append("redirect:")
                .append("https://")
                .append(shop_host)
                .append("/admin/oauth/authorize")
                .append("?client_id=").append(ShopifyEnv.APP_API_KEY)
                .append("&scope=").append(ShopifyEnv.APP_SCOPE)
                .append("&redirect_uri=").append(ShopifyEnv.APP_REDIRECT_URI)
                .append("&state=").append("teststate")
                .append("&grant_options[]=").append(ShopifyEnv.APP_GRANT_OPTIONS);


        return sb.toString();
    }
}
