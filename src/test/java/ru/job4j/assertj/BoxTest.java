package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .contains("phere")
                .startsWith("Sp")
                .isNotBlank()
                .isNotEmpty()
                .containsIgnoringCase("sph")
                .doesNotContain("www");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(3, 18);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .contains("know")
                .endsWith("ct")
                .isNotBlank()
                .containsPattern("n o");
    }

    @Test
    void numberOfVerticesIs8() {
        Box box = new Box(8, 1331);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(8)
                .isGreaterThan(5)
                .isLessThan(10)
                .isPositive()
                .isEven();
    }

    @Test
    void whenVertex8EdgeLessThen0NumberOfVerticesIsMinus1() {
        Box box = new Box(8, -1331);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(-1)
                .isOdd()
                .isLessThan(0)
                .isGreaterThan(-10);
    }

    @Test
    void whenVertex7NumberOfVerticesIsMinus1() {
        Box box = new Box(7, 6);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(-1)
                .isNotEqualTo(5)
                .isNegative();
    }

    @Test
    void whenVertex4Edge876IsExist() {
        Box box = new Box(4, 876);
        boolean result = box.isExist();
        assertThat(result).isTrue()
                .isNotEqualTo(false);
    }

    @Test
    void whenVertex14Edge32DoesNotExist() {
        Box box = new Box(14, 32);
        boolean result = box.isExist();
        assertThat(result).isFalse()
                .isNotEqualTo(true);
    }

    @Test
    void whenSphereR10AreaIs314Point15() {
        Box box = new Box(0, 10);
        double result = box.getArea();
        assertThat(result).isCloseTo(1256.6d, withPrecision(0.1))
                .isCloseTo(1256.6d, Percentage.withPercentage(1.0d))
                .isGreaterThan(678);
    }

    @Test
    void whenCubeEdge10AreaIs600() {
        Box box = new Box(8, 10);
        double result = box.getArea();
        assertThat(result).isCloseTo(600d, withPrecision(0.1))
                .isPositive()
                .isEqualTo(600, withPrecision(0.1d))
                .isEqualTo(600, within(0.1d));
    }
}