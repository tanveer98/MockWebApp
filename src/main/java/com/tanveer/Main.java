package com.tanveer;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    private static final String PROP_FILE_NAME = "config.properties";
    private static final Properties PROPS = new Properties();
    private static int SERVER_PORT = 9000;
    private static String SERVER_HOST = "localhost";

    public static void main(String[] args) throws LifecycleException, IOException {
        readProperties();

        String contextPath = "/";
        String appBase = ".";
        Tomcat tomcat = new Tomcat();

        tomcat.setHostname(SERVER_HOST);
        tomcat.setPort(SERVER_PORT);
        tomcat.getHost().setAppBase(appBase);
        tomcat.addWebapp(contextPath, appBase);

        tomcat.start();
        tomcat.getServer().await();
    }

    private static void readProperties() throws IOException {
        InputStream stream = Main.class.getClassLoader().getResourceAsStream(PROP_FILE_NAME);
        String s;

        if (stream == null) {
            System.err.println("Could not find properties file, reverting back to default settings");
            return;
        }

        PROPS.load(stream);

        s = PROPS.getProperty("server.hostname");
        if (s != null && !s.isEmpty()) {
            SERVER_HOST = s;
        }

        s = PROPS.getProperty("server.port");
        if (s != null && !s.isEmpty()) {
            try {
                SERVER_PORT = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        }
    }


}
