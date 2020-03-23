import org.junit.Test;

public class CoreTest {
    @Test
    public void strTest(){
        String s = "33 ";
        int i = Integer.parseInt(s.replaceAll(" ",""));
        System.out.println(i);
    }
}
