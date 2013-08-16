package com.pramati.pool.client.clientimpl;

import java.io.InputStream;

public class Response {
   final private InputStream body;

    public Response(InputStream body) {
        this.body = body;
    }

    public InputStream getBody() {
        return body;
    } 
}