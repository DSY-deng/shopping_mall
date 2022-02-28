package mall.utils;

public class AlipayConfig {
    // 商户appid
    public static String APPID = "2016102400750212";  //配置收款人的信息
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCfyh/4UduPWkL4Xea9PWs8I6p77WXdftM4X7gukkxAK/fsRdUQHfK58ugtShSSlGK3KrcGibSCqo9ZauZ4sEvZ0lTiK4MPKs4HJI1cHL30UT01vN1w/b8FlfvWrDnIy20LoDq+OpYrpgQ0rvRjrdYwUHeZ73y0BLz1xKl5CXKNEOD0lTXMPnq8GgCmYD6sw2fBwmuMjBuX53vB2j4fmLh/rzA8ggKqv4uRRsxMDToQvwAjMLtTXQfiDgFCPQ8F/3CZJakHBsAOGh/vHX805JENIwlDRk2K7f80icoNKDZieg8M1TSV3kopnkbLTDv2to4yjnlIDkkP4kGiZaisTqQ5AgMBAAECggEAIgeEuXiNXsElRAmTazfaT4t08kGDkSFCUF4qNljXvk7e1MWmc3E9URaHFaYSLYlGYKXLJqxK8gzqzGs0u/0nmjLt+uCzX5aJHAJPzI8ecRqgA4K+UiLn/IONh5FUGQwho/kyqXKGqrMHcETWLShHVjC8196l2xyrNVf1k55Mc/gOw4m1WwY/uQvLLiwCvgrjDE3zgeM7sty9t2PpUPb+6GVZm9wjtU6lgh4bL5n3u+zR8ZZ77EYZM5k+CgGsoMXfKPw/upfMXe78Tv+oyqApuVTkpV+WLlD3DpGCsnd6sD5w8j4Mjzd0xtwMq5LGIumDAan+HxPibjMiC3u4+51rdQKBgQDdFPQ39iqqCQiu4Bac1DpMllKKcmHUz+nrp9N/FemW20Gc4oaBWpb59DegYJXpg3OfWMhddXXSTZdpnImdY49yqzRqnQVvAf7LTWsbPD379DecCpzC/Updn7ZeAGSydZKOK6AoVE3HCsD/4oykCiWQ/7+TciUcOoSB4pVUOBHGuwKBgQC5Bu1eqzgjGtsYnxGd/jeJkSTKV7nPhPqZZg+cX6XHgJU7JAZVuWYOA30EO6LoVZkKbcXBSQCDOjZUFsRAfOs3DJpOxzEBtFoXt8yyZYxdflcA0J80WPCWa2reFTKln/T/7C0Eh3vuukLrPhX4s9dCSi0NLZFAmsoqFc46+INjmwKBgHJQZmMVT0QyfaHmzupytl5pj+50bG+cviiS08Ivs2PEpW2Yl9IE5HIEPEsaBgoDZWKshpYEqVJ/ul3qiiGunDq/mc/Stz+J+ZRnyI0Bnk9MHQD7X8lXLP+yGJsMewTn1Erc9ehEhx73SYpGO1CKypfqFcYIzHWHMIL0hSBSCacHAoGAPxL6diw+DdGI4OLQ+aAAxLei6Atxvylg2EwvhCoi9FiEBs4oDq8N+Db+NiQyS6tqaWC0akh9kT6V6KJ+MlGjSXfVXoeO0Drv/2fbsvDlIbYA6Lk1nH25Pj3OB3kIc+eMeHXFfubEu8PpLhJTIR4FgoaQxw5ClthtlF0smXPALoMCgYBJwTtWCfKUn7kSYi+IGFlTdwCRm5x06eirimXGHPX/MK/4RWqBGT2LiEkwoFW+XyW+GXYHEG0yYADoQBiwH1g2SPE6LgaYzIzqNddTOx7KGGc5RgfjBKRQBpGz7LIrtM3LYdES5Vfr5BAlpKFSyG2X3ALTcJ0GmY7M6onCwLLDbw==";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://商户网关地址/alipay.trade.wap.pay-JAVA-UTF-8/return_url.jsp";


    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    //当支付完成之后，通知我们服务（服务器必须定义一个servlet或jsp来接收apli的通知）

    public static String return_url = "http://127.0.0.1/pay/alipayReturnNotice";
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj1ZKL2HKklfPsjlrdkZkxEeNI0BVO49nsKh0gtEVEoxLcwIbyUzB3QsjqN2Q0GlHP7MVY7s5xB+3+RbfUBId4qUSJihqYxmO5FcVjdhxhBdzjxHOQeCjfavXKP/dqInyvMJxGmTCaLerjjbAgg32sz3Py7TwhstRo4+/YFJ610VeIJneoQizIYuPfSN5DUX8SwwbKubxpfRc6SMMWbn1KBtSL1b1DYgdS+jwvjg4DjpHb57dF60MwERd5vwQIxmppZwe5fhXnED0tRJ0baFzzhpFQE73G36J3S3zhkQuwvi2/1a6ya6257L3Gw/QtlLRRfEG1W/dtFr5sn+GX2AZnQIDAQAB";
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}
