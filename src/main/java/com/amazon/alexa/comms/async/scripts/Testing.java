package com.amazon.alexa.comms.async.scripts;

import com.amazon.alexa.comms.async.driver.DriverConfiguration;

import dev.failsafe.internal.util.Assert;
import lombok.extern.java.Log;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.amazon.alexa.comms.async.constants.StringConstants.*;
import static com.amazon.alexa.comms.async.constants.StringConstants.PAGE_SOURCE;

@Log
public class Testing extends DriverConfiguration {
    public static void main(String[] args) throws InterruptedException {
        List<String> firstName = Arrays.asList("John", "William", "James", "Thomas", "George", "Joseph", "Samuel", "Henry", "David", "Daniel",
                "Mary", "Elizabeth", "Sarah", "Nancy", "Catherine", "Margaret", "Jane", "Susan", "Hannah", "Martha");
        Collections.shuffle(firstName);
        System.out.println(firstName.get(0));
        List<String> lastName = Arrays.asList("Smith", "Brown", "Miller", "Johnson", "Jones", "Davis", "Wilson", "Clark", "Taylor");
        Collections.shuffle(lastName);
        System.out.println(lastName.get(1));
    }
}
