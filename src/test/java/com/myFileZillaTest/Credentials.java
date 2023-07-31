package com.myFileZillaTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Credentials {
	private static final String PROPERTIES_FILE = "C:\\Users\\{Username}\\eclipse-workspace\\FilezillaAutomation\\src\\test\\java\\com\\myFileZillaTest\\application.properties";

    final private static String hostFtp;
    final private static String portFtp;
    final private static String usernameFtp;
    final private static String passwordFtp;

    static {
        try (FileInputStream input = new FileInputStream(PROPERTIES_FILE)) {
            Properties prop = new Properties();
            prop.load(input);

            hostFtp = prop.getProperty("hostFtp");
            portFtp = prop.getProperty("portFtp");
            usernameFtp = prop.getProperty("usernameFtp");
            passwordFtp = prop.getProperty("passwordFtp");


        } catch (IOException ex) {
            throw new RuntimeException("Could not read properties file: " + PROPERTIES_FILE, ex);
        }
    }

    public static String gethostFtp() {
        return hostFtp;
    }

    public static String getportFtp() {
        return portFtp;
    }

    public static String getusernameFtp() {
        return usernameFtp;
    }

    public static String getpasswordFtp() {
        return passwordFtp;
    }
}
