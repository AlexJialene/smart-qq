package keizyi.smartqq.test;

import com.keizyi.smartqq.core.SmartQQClient;
import com.keizyi.smartqq.bean.SelfInfo;
import com.keizyi.smartqq.bean.response.HttpResult;
import com.keizyi.smartqq.kit.JsonMapperKit;
import com.keizyi.smartqq.kit.SmartQQKit;
import org.junit.Test;

import java.io.IOException;

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
        //smartQQClient.insQRCode();
        //System.out.println(smartQQClient.getQrsig());

    }

    @Test
    public void test2(){
        SmartQQKit.ptuiCB("ptuiCB('0','0','https://ptlogin2.web2.qq.com/check_sig?pttype=1&uin=467146659...','0','登录成功！', 'lamkeizyi')");
    }

    @Test
    public void test3(){
        SmartQQClient smartQQClient = new SmartQQClient();
        //System.out.println(smartQQClient.getCheckSigHeaders().toString());
    }

    @Test
    public void test4(){
    }

    @Test
    public void test5() throws IOException {
    }

    @Test
    public void test6(){
        String s = "pt4_token=;Expires=Thu, 01 Jan 1970 00:00:00 GMT;";
        String[] array = s.split(";");
        System.out.println(array[0].length());
        System.out.println(array[0].lastIndexOf("="));


    }

    @Test
    public void test7(){

    }


}
