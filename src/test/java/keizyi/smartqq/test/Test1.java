package keizyi.smartqq.test;

import com.keizyi.smartqq.core.SmartQQClient;
import com.keizyi.smartqq.kit.SmartQQKit;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        SmartQQKit.ptuiCB("ptuiCB('0','0','https://ptlogin2.web2.qq.com/check_sig?pttype=1&uin=467146659...','0','登录成功！', 'lamkeizyi')");
    }

    @Test
    public void test3(){
        SmartQQClient smartQQClient = new SmartQQClient();
    }

    @Test
    public void test4(){
    }
}
