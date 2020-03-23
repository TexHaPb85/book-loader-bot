import entities.Good;
import org.junit.Test;
import util.SettingsUtil;
import util.SovaBooksLoadUtil;

public class BackendTest {
    @Test
    public void loadTest(){
        SettingsUtil.setUpApplication("Mozila Firefox");
        Good good = SovaBooksLoadUtil
                .loadGoodByURL("https://sovabooks.com.ua/ted-talks-slova-menjajut-mir-pervoe-oficial-noe-rukovodstvo-po-publichnym-vystuplenijam/");
        Good good1 = SovaBooksLoadUtil
                .loadGoodByURL("https://sovabooks.com.ua/agressija-pri-rasstrojstvah-lichnosti-i-perversijah/");
        Good good2 = SovaBooksLoadUtil
                .loadGoodByURL("https://sovabooks.com.ua/big-money-principy-pervyh-otkrovenno-o-biznese-i-zhizni-uspeshnyh-predprinimatelej/");
        System.out.println(good+"\n");
        System.out.println(good1+"\n");
        System.out.println(good2+"\n");
    }
}
