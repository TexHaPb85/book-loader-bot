import org.junit.Test;

public class CoreTest {
    @Test
    public void strTest() {
        String s = "33 ";
        int i = Integer.parseInt(s.replaceAll(" ", ""));
        System.out.println(i);
    }

    @Test
    public void referenceTest() {
        nyClass n = new nyClass();
        n.v1=13;
        n.v2="asf";
        nyClass n1 = n;
        System.out.println(n1.v2);
        System.out.println(n1.v1);
        n.v1++;
        n.v2="aaaa";
        System.out.println(n1.v2);
        System.out.println(n1.v1);
    }

    @Test
    public void pathTest(){
        System.out.println(this.getClass().getPackage());
    }
}

class nyClass{
    public Integer v1;
    public String v2;


}
