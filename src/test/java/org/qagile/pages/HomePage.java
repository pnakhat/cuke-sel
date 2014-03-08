package org.qagile.pages;

import org.qagile.selenium.DriverProvider;
import org.qagile.utils.PropReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

public class HomePage implements PageStructure {

    @Autowired
    private DriverProvider driverProvider;

    public void navigate() {
        driverProvider.get(PropReader.getPropValue("app.url"));
    }

}
