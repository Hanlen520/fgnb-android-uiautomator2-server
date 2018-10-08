package com.macaca.android.testing.server.controllers;


import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;


public class PingController extends RouterNanoHTTPD.DefaultHandler {

    public static PingController ping;

    static {
        ping = new PingController() {

            @Override
            public NanoHTTPD.Response get(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
            return NanoHTTPD.newFixedLengthResponse(getStatus(), getMimeType(), "pong");
            }
        };
    }

    @Override
    public String getMimeType() {
        return "";
    }

    @Override
    public NanoHTTPD.Response.IStatus getStatus() {
        return NanoHTTPD.Response.Status.OK;
    }
}