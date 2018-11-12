# smart-qq

> In development

## preface

Because the official website protocol changes frequently, So write a project and keep updating.

environment：
* java 8
* maven

## Sample

``` java
new SmartQQClient(new Callback() {
    @Override
    public void onMessage(Message message) {
        System.out.println("用户信息");
    }

    @Override
    public void groupMessage(GroupMessage message) {
        System.out.println("群消息");
    }

    @Override
    public void discuMessage(DiscuMessage message) {
        System.out.println("讨论组消息");
    }
}).startReceive();

```
> If you start to prompt to receive a message error: `pull message json string error!`.
The reason for this error is that the /poll2 interface returns an error：
> {"errmsg":"error","retcode":0,"retmsg":"ok"}

* 1.Log on to [web/smart QQ](https://web2.qq.com) and ensure that the message is successful.
* 2.Start the project (before closing smart/web QQ).




