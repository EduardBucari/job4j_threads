package ru.job4j.cas;

import org.junit.Assert;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class CASCount2Test {

    @Test
    public void whenCASCountLogic() {
        CASCount2 casCount2 = new CASCount2(0);

        casCount2.increment();
        casCount2.get();
        casCount2.increment();

        Assert.assertThat(casCount2.get(), is(2));
    }

    @Test
    public void whenCASCountLogicTwo() {
        CASCount2 casCount2 = new CASCount2(2);

        casCount2.increment();
        casCount2.get();
        casCount2.increment();
        casCount2.get();
        casCount2.increment();
        casCount2.get();
        casCount2.increment();
        casCount2.get();
        casCount2.increment();

        Assert.assertThat(casCount2.get(), is(7));
    }

    @Test
    public void whenCASCountLogicThree() {
        CASCount2 casCount2 = new CASCount2(0);

        casCount2.increment();
        casCount2.increment();
        casCount2.increment();

        Assert.assertThat(casCount2.get(), is(3));
    }
}