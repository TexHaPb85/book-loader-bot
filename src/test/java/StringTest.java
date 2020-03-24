import org.junit.Test;

import java.util.Arrays;

public class StringTest {
    @Test
    public void strUnionTest() {
        String description = "«Кто не хочет, ищет причины. Кто хочет, ищет возможности — фраза моего Отца, главная фраза " +
                "в моем воспитании». Эта книга о людях, которые всегда ищут возможности и находят их, её и многие другие" +
                " книги вы можете приобрести на нашем сайте sovabooks.com.ua. Всё хорошее в этом Мире придумано людьми, " +
                "которые хотели заработать. Предпринимателями и Бизнесменами. Эта книга о них. О тех, кто каждый день делает" +
                " этот мир лучше, развивая свой бизнес, создавая рабочие места и платя налоги. Вопреки стереотипам, это не скучные," +
                " угрюмые капиталисты, у которых в голове только деньги. Это интересные, умные, харизматичные люди, которые," +
                " как и каждый из нас, каждый день нуждаются в успехе и сожалеют о том, что времени для этого всегда не достаточно. " +
                "Их отличие только в том, что все они одержимые своей идеей перфекционисты. А именно таким будет принадлежать " +
                "Мир будущего. Я очень волнуюсь. В твоих руках, уважаемый читатель, моя первая книга, состоящая из интервью " +
                "успешных предпринимателей в YouTube — проекте Big Money. Книга о людях и их историях побед. Эта книга поможет " +
                "тебе увеличить скорость и быстрее достигнуть своей мечты. Именно для этого эта книга и создана. Евгений Черняк";

        String s3 = Arrays.stream(description.split("[.]"))
                .peek(s -> System.out.println("---->" + s))
                .filter(sentence -> !sentence.contains("sovabook") && !sentence.contains("com") && !sentence.contains("ua"))
                .peek(s -> System.out.println("<----" + s))
                .reduce((s1, s2) -> s1 + "." + s2)
                .get();
        System.out.println("\n\n"+s3);


    }
}
