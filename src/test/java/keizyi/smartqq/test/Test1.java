package keizyi.smartqq.test;

import com.keizyi.smartqq.bean.Message;
import com.keizyi.smartqq.bean.MessageValue;
import com.keizyi.smartqq.bean.Pull2FormData;
import com.keizyi.smartqq.bean.response.HttpListResult;
import com.keizyi.smartqq.core.RequestHelper;
import com.keizyi.smartqq.core.SmartQQClient;
import com.keizyi.smartqq.kit.JsonKit;
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
        RequestHelper requestHelper = SmartQQClient.Request.$(RequestPathKit.PULL_MSG)
                .addHeader("cookie","RK=BL3KzPNvMB; cuid=3965642384; pgv_pvi=8821518336; pac_uid=1_467146659; OUTFOX_SEARCH_USER_ID_NCOO=801402530.8613921; tvfe_boss_uuid=65c75cc72cfeead4; eas_sid=01c5v3F1z1w3E0j9z2m0u3s4Z8; o_cookie=467146659; pgv_pvid=2084067210; ptcz=08aa117c109be7322a0f6b41899b582491bd185e43373b625e24e073b5e0cfa1; pgv_si=s5086739456; pt2gguin=o1667675184; uin=o1667675184; skey=@KWXSop25y; ptisp=cm; p_uin=o1667675184; pt4_token=QjxBygWrgj8lOJ-v3UKPxXHzF61bAqgieRQPcx5O2Gs_; p_skey=FMIp8cFsnNCFj9MG4KaHzKqY3kNAn2lxSQ0nMi9hpA4_")
                .addHeader("origin","https://d1.web2.qq.com")
                .addHeader("content-type","application/x-www-form-urlencoded");
        Pull2FormData data = new Pull2FormData("8368046764001d636f6e6e7365727665725f77656271714031302e3133332e34312e383400001ad00000066b026e040015808a206d0000000a406172314338344a69526d0000002859185d94e66218548d1ecb1a12513c86126b3afb97a3c2955b1070324790733ddb059ab166de6857");

        String s = requestHelper.sendPost(JsonKit.toFeatureJson(data)).get();
        System.out.println(s);
    }

    @Test
    public void test5() throws IOException {
        //long i = 2629709360;
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
        String s = "{\"result\":[{\"poll_type\":\"message\",\"value\":{\"content\":[[\"font\",{\"color\":\"000000\",\"name\":\"微软雅黑\",\"size\":10,\"style\":[0,0,0]}],\"5\"],\"from_uin\":2629709360,\"msg_id\":13180,\"msg_type\":1,\"time\":1541647597,\"to_uin\":1667675184}}],\"retcode\":0,\"retmsg\":\"ok\"}\n";


        HttpListResult result = JsonKit.parse(s , HttpListResult.class);
        System.out.println(result.getRetcode());
        System.out.println(result.getResult());


        String json = JsonKit.toFeatureJson(result.getResult().get(0));
        System.out.println(json);
        //System.out.println(value.getValue().get("msg_id"));

        //System.out.println(value.getPoll_type());
        //System.out.println(value.getValue());
    }

    @Test
    public void test8(){
        String s = "{\"result\":\"error\",\"retcode\":0,\"retmsg\":\"ok\"}\n";
        HttpListResult result = JsonKit.parse(s , HttpListResult.class);
        System.out.println(result.getRetcode());
        System.out.println(result.getResult());
        try {

            String json = JsonKit.toFeatureJson(result.getResult().get(0));
            System.out.println(json);
        }catch (Exception e){
            System.out.println(111);
        }

    }


}
