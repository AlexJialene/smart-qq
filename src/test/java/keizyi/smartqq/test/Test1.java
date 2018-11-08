package keizyi.smartqq.test;

import com.keizyi.smartqq.bean.Message;
import com.keizyi.smartqq.bean.MessageValue;
import com.keizyi.smartqq.bean.response.HttpListResult;
import com.keizyi.smartqq.bean.response.HttpMapResult;
import com.keizyi.smartqq.core.SmartQQClient;
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
        String path = "https://ptlogin2.web2.qq.com/check_sig";
        int i = path.indexOf("?");
        String name = path.substring(path.lastIndexOf("/") , i==-1?path.length():i);
        System.out.println(
                String
                        .format("the request [%s] result: %s" ,
                                name,
                                "123123"));

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
        String s = "{\"result\":[{\"poll_type\":\"message\",\"value\":{\"content\":[[\"font\",{\"color\":\"000000\",\"name\":\"微软雅黑\",\"size\":10,\"style\":[0,0,0]}],\"5\"],\"from_uin\":2629709360,\"msg_id\":13180,\"msg_type\":1,\"time\":1541647597,\"to_uin\":1667675184}}],\"retcode\":0,\"retmsg\":\"ok\"}\n";

        JsonMapperKit jsonMapperKit = JsonMapperKit.nonNullMapper();
        HttpListResult result = jsonMapperKit.fromJson(s , HttpListResult.class);
        System.out.println(result.getRetcode());
        MessageValue<Message> value = (MessageValue<Message>) result.asClass(MessageValue.class);
        System.out.println(value.getPoll_type());
        System.out.println();
    }


}
