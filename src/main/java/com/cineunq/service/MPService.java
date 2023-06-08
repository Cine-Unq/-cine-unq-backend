package com.cineunq.service;

import com.cineunq.dominio.Compra;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MPService {

    @Value("${MP.ACCESSTOKEN}")
    private String token;

    public String generarCompra(Compra compra) throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken(token);

        final Gson gson = new GsonBuilder().setPrettyPrinting().create();

        PreferenceItemRequest itemRequest =
                PreferenceItemRequest.builder()
                        .id("1")
                        .title("Entradas Funcion " + compra.getFuncion().getPeliculaEnFuncion().getNombre())
                        .description("Entradas")
                        .categoryId("Entretenimiento")
                        .quantity(compra.cantidadAsientosComprados())
                        .currencyId("ARS")
                        .unitPrice(BigDecimal.valueOf(compra.getPrecioUnitario()))
                        .build();

        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);

        PreferenceBackUrlsRequest urls = PreferenceBackUrlsRequest.builder().success("http://localhost:3000/movie/purchase/success/" + compra.getId()).failure("http://localhost:3000/movie/purchase/failure").pending("http://localhost:3000/movie/purchase/pending").build();

        PreferenceRequest preferenceRequest = PreferenceRequest.builder().backUrls(urls).items(items).build();

        PreferenceClient client = new PreferenceClient();

        Preference preference = client.create(preferenceRequest);

        JSONObject obj = new JSONObject(preference.getResponse().getContent());

        return obj.getString("init_point");
    }
}
