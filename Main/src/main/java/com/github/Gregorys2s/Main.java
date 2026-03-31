package com.github.Gregorys2s;

import java.sql.*;
import javax.sql.*;

public class Main {
    public static void main(String[] args) {
    DriverManager.drivers().forEach(
            driver -> System.out.println(driver.toString())
    );

    for (Driver d : DriverManager.drivers().toList())
    {
        System.out.println(d.toString());
    }

    }
}