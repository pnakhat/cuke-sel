package org.qagile;


import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(features = "classpath:features", format = { "pretty", "html:target/cucumber" },
        tags = "@registration,@login")

public class RunCukeTest {
}
