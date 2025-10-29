package com.rasha.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Hjälpklass för loggning med SLF4J. Där används för att logga information, varningar och felmeddelanden
 * Separat ansvar för loggning – följer SRP.
 */

public class LoggerUtil {

    // Logger kopplad till denna klass
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    // Loggar ett informationsmeddelande.
    public static void logInfo(String message) {
        logger.info(message);
    }

    // Loggar ett felmeddelande.
    public static void logError(String message) {
        logger.error(message);
    }

    // Loggar en varning.
    public static void logWarn(String message) {
        logger.warn(message);
    }

}
