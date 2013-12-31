package algorithm.gene.feature;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class LengthShould {

    @Test
    public void equal_when_values_are_the_same() {
        Length length_1 = new Length(10);
        Length length_2 = new Length(10);

        assertThat(length_1).isEqualTo(length_2);
        assertThat(length_1.hashCode()).isEqualTo(length_2.hashCode());
    }


    @Test
    public void store_the_intial_given_value() {
        int lengthValue = 16;
        Length length = new Length(lengthValue);

        assertThat(lengthValue).isEqualTo(Integer.parseInt(length.toString()));
    }

}
