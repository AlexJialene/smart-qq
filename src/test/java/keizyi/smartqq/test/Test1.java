package keizyi.smartqq.test;

import com.keizyi.smartqq.bean.Pull2FormData;
import com.keizyi.smartqq.core.RequestHelper;
import com.keizyi.smartqq.core.SmartQQClient;
import com.keizyi.smartqq.bean.SelfInfo;
import com.keizyi.smartqq.bean.response.HttpResult;
import com.keizyi.smartqq.kit.JsonMapperKit;
import com.keizyi.smartqq.kit.RequestPathKit;
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
        SmartQQClient smartQQClient = new SmartQQClient(null);
    }

    @Test
    public void test2(){
        SmartQQKit.ptuiCB("ptuiCB('0','0','https://ptlogin2.web2.qq.com/check_sig?pttype=1&uin=467146659...','0','登录成功！', 'lamkeizyi')");
    }

    @Test
    public void test3(){
        //SmartQQClient smartQQClient = new SmartQQClient();
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
        RequestHelper requestHelper = SmartQQClient.Request.$(RequestPathKit.PULL_MSG)
                .addHeader("origin" , "https://d1.web2.qq.com")
                .addHeader("cookie" , "pgv_pvid=7826173400; tvfe_boss_uuid=254cfa023ba2ee61; pgv_pvi=9176148992; RK=ADzA3vNlbD; ptcz=667dab4be65224e408d7b10ba718e1ce99c27ae9ee09baba03c10cc6b92cb930; ptui_loginuin=1667675184; eas_sid=O195d3f4A884P2X8n474J822O9; o_cookie=467146659; pac_uid=1_467146659; pgv_si=s7035848704; _qpsvr_localtk=0.5447120785336257; pt2gguin=o1667675184; uin=o1667675184; skey=@8TGRDwZrB; p_uin=o1667675184; pt4_token=oCdvfnyMzG*OCcSgnpeTQhk7sB8ya1FuQSeXSxtRBiM_; p_skey=wg3opSiXOOHqyzR8YMMwLqfvYt4BqKYKoRS5z7PzFWc_")
                .addHeader("content-type" , "application/x-www-form-urlencoded");

        //ins param
        Pull2FormData data = new Pull2FormData("8368046764001d636f6e6e7365727665725f77656271714031302e3133332e34312e383400001ad00000066b026e040015808a206d0000000a406172314338344a69526d0000002859185d94e66218548d1ecb1a12513c86126b3afb97a3c2955b1070324790733ddb059ab166de6857");

        String result = requestHelper.sendPost("r={\"ptwebqq\":\"\",\"clientid\":53999199,\"psessionid\":\"8368046764001d636f6e6e7365727665725f77656271714031302e3133332e34312e383400001ad00000066b026e040015808a206d0000000a406172314338344a69526d0000002859185d94e66218548d1ecb1a12513c86126b3afb97a3c2955b1070324790733ddb059ab166de6857\",\"key\":\"\"}");
        System.out.println(result);
    }


}
