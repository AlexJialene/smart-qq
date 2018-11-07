package keizyi.smartqq.test;

import com.keizyi.smartqq.core.SmartQQClient;
import com.keizyi.smartqq.dto.XLoginDto;
import com.keizyi.smartqq.dto.response.HttpResult;
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
        //System.out.println(smartQQClient.getCheckSigHeaders().toString());
    }

    @Test
    public void test4(){
        String path = "https://ssl.ptlogin2.qq.com/ptqrlogin?u1=https%3A%2F%2Fweb2.qq.com%2Fproxy.html&ptqrtoken={}&ptredirect={}&h=1&t=1&g=1&from_ui=1&ptlang=2052&action=0-0-1541497137149&js_ver=10284&js_type=1&login_sig=&pt_uistyle=40&aid=501004106&daid=164&mibao_css=m_webqq&";
        System.out.println(SmartQQKit.urlAssembly(path ));
        System.out.println(path);
    }

    @Test
    public void test5() throws IOException {
        String s = "{\"result\":{\"cip\":23600812,\"f\":0,\"index\":1075,\"port\":47450,\"psessionid\":\"8368046764001d636f6e6e7365727665725f77656271714031302e3133332e34312e383400001ad00000066b026e040015808a206d0000000a406172314338344a69526d0000002859185d94e66218548d1ecb1a12513c86126b3afb97a3c2955b1070324790733ddb059ab166de6857\",\"status\":\"online\",\"uin\":467146659,\"user_state\":0,\"vfwebqq\":\"59185d94e66218548d1ecb1a12513c86126b3afb97a3c2955b1070324790733ddb059ab166de6857\"},\"retcode\":0}";
        JsonMapperKit jsonMapperKit = JsonMapperKit.nonNullMapper();
        HttpResult res = jsonMapperKit.fromJson(s , HttpResult.class);
        XLoginDto dto = (XLoginDto) res.asClass(XLoginDto.class);
        System.out.println(dto.getCip());

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
