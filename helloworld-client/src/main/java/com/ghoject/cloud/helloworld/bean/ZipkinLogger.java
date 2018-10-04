package com.ghoject.cloud.helloworld.bean;

import brave.Tracer;

public class ZipkinLogger {

    private Tracer tracer;

    private Class clazz;

    public ZipkinLogger(Class clazz) {
        this.tracer = SpringContext.getBean(Tracer.class);
        this.clazz = clazz;
    }

    public ZipkinLogger debug (String log) {
        tracer.currentSpan().tag(clazz.getName() + " debug", log);
        return this;
    }
}
