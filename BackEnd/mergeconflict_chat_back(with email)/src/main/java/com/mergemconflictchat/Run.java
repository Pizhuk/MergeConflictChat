package com.mergemconflictchat;

import com.mergemconflictchat.config.ServerConfiguration;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;

public class Run {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(Run.class);
        ServerConfiguration serverConfiguration = new ServerConfiguration();
        Server server = serverConfiguration.buildServer();
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
