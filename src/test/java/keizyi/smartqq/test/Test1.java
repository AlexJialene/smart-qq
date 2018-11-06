package keizyi.smartqq.test;

import com.keizyi.smartqq.core.SmartQQClient;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/6
 * Time: 13:16
 */
public class Test1 {

    @Test
    public void test1(){
        SmartQQClient smartQQClient = new SmartQQClient();
        smartQQClient.insQRCode();
        System.out.println(smartQQClient.getQrsig());

    }

    @Test
    public void test2(){
        SmartQQClient smartQQClient = new SmartQQClient();
        int i = smartQQClient.hash33("grsig=bgCf*vqxYKbLEyx5SQdhKgriUMw9ejm0qCRi50o7M0cwURg*D2h5YedsCm0VbpDZ");
        System.out.println(i);
    }
}
