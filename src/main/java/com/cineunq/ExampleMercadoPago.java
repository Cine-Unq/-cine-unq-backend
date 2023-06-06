//package com.cineunq;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.mercadopago.MercadoPagoConfig;
//import com.mercadopago.client.payment.PaymentClient;
//import com.mercadopago.client.payment.PaymentCreateRequest;
//import com.mercadopago.client.payment.PaymentPayerRequest;
//import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
//import com.mercadopago.client.preference.PreferenceClient;
//import com.mercadopago.client.preference.PreferenceItemRequest;
//import com.mercadopago.client.preference.PreferenceRequest;
//import com.mercadopago.exceptions.MPApiException;
//import com.mercadopago.exceptions.MPException;
//import com.mercadopago.resources.payment.Payment;
//import com.mercadopago.resources.preference.Preference;
//import com.mercadopago.resources.preference.PreferenceBackUrls;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ExampleMercadoPago {
//
//    public static void main(String[] args) throws MPException, MPApiException {
//
//        MercadoPagoConfig.setAccessToken("");
//
//        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//
//        PreferenceItemRequest itemRequest =
//                PreferenceItemRequest.builder()
//                        .id("1234")
//                        .title("Games")
//                        .description("PS5")
//                        .pictureUrl("https://arsonyb2c.vtexassets.com/arquivos/ids/361930/711719555360_003.jpg?v=638143176509630000")
//                        .categoryId("games")
//                        .quantity(1)
//                        .currencyId("ARS")
//                        .unitPrice(new BigDecimal("10"))
//                        .build();
//
//        List<PreferenceItemRequest> items = new ArrayList<>();
//        items.add(itemRequest);
//
//        PreferenceBackUrlsRequest urls = PreferenceBackUrlsRequest.builder().success("http://localhost:8080/success").failure("http://localhost:8080/failure").pending("http://localhost:8080/pending").build();
//
//        PreferenceRequest preferenceRequest = PreferenceRequest.builder().backUrls(urls).items(items).build();
//
//        PreferenceClient client = new PreferenceClient();
//
//        Preference preference = client.create(preferenceRequest);
//
//        System.out.println(preference.getResponse().getContent());
//
//        JSONObject obj = new JSONObject(preference.getResponse().getContent());
//
//        String urlInit = obj.getString("init_point");
//
//        System.out.println(urlInit);
//
//                //+ preference.getResponse().getContent().
//
//        //return "redirect" + gson.fromJson(preference.getResponse().getContent(),)
//        //System.out.println(gson.toJson(preference));
//    }
//}
