package com.nosbielc.mixed.salad.bancocentral.componentes;

import com.nosbielc.mixed.salad.bancocentral.BancoCentralServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.util.SocketUtils;
import org.springframework.util.StringUtils;

@Component
public class ServerPortCustomizer
        implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    private static final Logger log = LoggerFactory.getLogger(ServerPortCustomizer.class);

    @Value("${range.port.min}")
    private static int rangePortMin;

    @Value("${range.port.max}")
    private static int rangePortMax;

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
//        factory.setPort(getRandomPort(rangePortMin, rangePortMax));
    }

    public static void setRandomPort() {
        setRandomPort(8001, 8020);
    }

    private static void setRandomPort(int minPort, int maxPort) {
        try {
            String userDefinedPort = System.getProperty("server.port", System.getenv("SERVER_PORT"));
            if (StringUtils.isEmpty(userDefinedPort)) {
                int port = SocketUtils.findAvailableTcpPort(minPort, maxPort);
                System.setProperty("server.port", String.valueOf(port));
                log.info("Server port set to {}.", port);
            }
        } catch (IllegalStateException var4) {
            log.warn("No port available in range %s-%s. Default embedded server configuration will be used.", minPort, maxPort);
        }
    }

    private static int getRandomPort(int minPort, int maxPort) {
        try {
            String userDefinedPort = System.getProperty("server.port", System.getenv("SERVER_PORT"));
            if (StringUtils.isEmpty(userDefinedPort)) {
                return SocketUtils.findAvailableTcpPort(minPort, maxPort);
            }

            return Integer.parseInt(userDefinedPort);
        } catch (IllegalStateException var4) {
            log.warn("No port available in range %s-%s. Default embedded server configuration will be used.", minPort, maxPort);
        }

        return 6565;
    }

}

