package com.step.nova.security;

import java.sql.Connection;
import java.sql.DriverManager;

public class WalletTest {
    public static void main(String[] args) throws Exception {

        String url =
            "jdbc:oracle:thin:@(description="
          + "(address=(protocol=tcps)(host=adb.ap-singapore-1.oraclecloud.com)(port=1522))"
          + "(connect_data=(service_name=g4cbc0ce0f22fd9_ns_high.adb.oraclecloud.com))"
          + ")";

        try (Connection conn = DriverManager.getConnection(
                url,
                "nsappuser",
                "Nova@Step@Oracle@26ai")) {

            System.out.println("Connected successfully!");
        }
    }
}