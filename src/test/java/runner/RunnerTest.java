package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/feature"},
        glue = {"steps"},
        tags = "@test",
        plugin = {"pretty",
                "html:target/cucumber",
                "json:target/cucumber.json"},
        dryRun = false,
        strict = true,
        monochrome = false)

public class RunnerTest {
}
