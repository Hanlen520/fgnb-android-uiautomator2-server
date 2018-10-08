package com.macaca.android.testing.server.controllers;

import android.support.test.uiautomator.UiDevice;

import com.macaca.android.testing.server.common.Elements;
import com.macaca.android.testing.server.listeners.AccessibilityEventListener;
import com.macaca.android.testing.server.models.Response;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;


public class ToastController extends RouterNanoHTTPD.DefaultHandler {

    public static ToastController toast;
    UiDevice mDevice = Elements.getGlobal().getmDevice();

    static {
        toast = new ToastController() {

            @Override
            public NanoHTTPD.Response get(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
                String sessionId = urlParams.get("sessionId");
                return NanoHTTPD.newFixedLengthResponse(getStatus(), getMimeType(), new Response(AccessibilityEventListener.toast, sessionId).toString());
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