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



