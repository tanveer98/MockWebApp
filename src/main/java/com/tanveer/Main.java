package com.tanveer;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Main {
    private static final int SERVER_PORT = 8000;

    public static void main(String[] args) throws LifecycleException {
        String contextPath = "/";
        String appBase = ".";
        Tomcat tomcat = new Tomcat();

        tomcat.getHost().setAppBase(appBase);
        tomcat.setPort(SERVER_PORT);
        tomcat.addWebapp(contextPath, appBase);

        tomcat.start();
        tomcat.getServer().await();
    }
}
