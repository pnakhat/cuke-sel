package org.qagile.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.lift.find.Finder;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.qagile.selenium.DriverProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class WebForm {

    protected List<WebFormFields> webFormFieldsList = new ArrayList<WebFormFields>();

    protected <T extends WebForm> T initialize(T webForm, DriverProvider driverProvider) {
        Field[] fields = webForm.getClass().getFields();
        try {
            for (Field field : fields) {
                if (field.getType().equals(By.class)) {
                    setWebField(field, driverProvider);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to set webField: \n" + e.getMessage() + e.getStackTrace());
        }
        return webForm;
    }

    private void setWebField(Field field, DriverProvider driverProvider) {
        String defaultValue = field.isAnnotationPresent(DefaultValue.class) ? field.getAnnotation(DefaultValue.class).value() : null;
        String actionType = field.isAnnotationPresent(Action.class) ? field.getAnnotation(Action.class).value() : null;
        String name =  field.isAnnotationPresent(Locator.class) ? field.getAnnotation(Locator.class).name() : null;
        By by = getByFrom(field.getAnnotation(Locator.class).how(), field.getAnnotation(Locator.class).using());
        WebElement element = null;
        try {
            element = driverProvider.findElement(by);
        } catch (Exception e) {
            e.printStackTrace();
        }

        webFormFieldsList.add(new WebFormFields().setWebElement(element)
                .setDefaultvalue(defaultValue)
                .setAction(actionType)
                .setName(name));
    }

    public void fillForm() {
        for (WebFormFields webField : webFormFieldsList) {
            webField.takeDefaultAction();
        }
    }

    private static By getByFrom(How how, String using) {
        By by;
        switch (how) {
            case ID:
                by = By.id(using);
                break;
            case CSS:
                by = By.cssSelector(using);
                break;
            case CLASS_NAME:
                by = By.className(using);
                break;
            case XPATH:
                by = By.xpath(using);
                break;
            default:
                by = By.id(using);
                break;
        }
        return by;
    }


}

