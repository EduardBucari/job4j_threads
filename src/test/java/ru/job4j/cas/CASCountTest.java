package ru.job4j.cas;

import org.junit.Assert;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class CASCountTest {

    @Test
    public void whenCASCountLogic() {
        CASCount casCount = new CASCount(0);

        casCount.increment();
        casCount.get();
        casCount.increment();

        Assert.assertThat(casCount.get(), is(2));
    }

    @Test
    public void whenCASCountLogicTwo() {
        CASCount casCount = new CASCount(2);

        casCount.increment();
        casCount.get();
        casCount.increment();
        casCount.get();
        casCount.increment();
        casCount.get();
        casCount.increment();
        casCount.get();
        casCount.increment();

        Assert.assertThat(casCount.get(), is(7));
    }

    @Test
    public void whenCASCountLogicThree() {
        CASCount casCount = new CASCount(0);

        casCount.increment();
        casCount.increment();
        casCount.increment();

        Assert.assertThat(casCount.get(), is(3));
    }
}