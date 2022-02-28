package mall.web.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import mall.common.Result;
import mall.pojo.Product;
import mall.service.OrderService;
import mall.service.impl.OrderServiceImpl;
import mall.utils.AlipayConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@WebServlet("/pay/*")
public class PayServlet extends BaseServlet {
    OrderService orderService = new OrderServiceImpl();

    public void createPayForm(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {

        //接收支付方式
        String checkway = request.getParameter("checkway");

        //接收订单编号
        String orderNumber = request.getParameter("orderNumber");

        //接收支付金额
        String payMoney = request.getParameter("payMoney");

        if("alipay".equals(checkway)){
            // 商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = new String(orderNumber.getBytes("ISO-8859-1"),"UTF-8");
            // 订单名称，必填
            String subject = new String(orderNumber.getBytes("ISO-8859-1"),"UTF-8");
            System.out.println(subject);


            // 付款金额，必填
            String total_amount=new String(payMoney.getBytes("ISO-8859-1"),"UTF-8");
            // 商品描述，可空
            String body = "";
            // 超时时间 可空
            String timeout_express="2m";
            // 销售产品码 必填
//            QUICK_WAP_WAY快速移动的方式，生成支付宝登录页面
            String product_code="FAST_INSTANT_TRADE_PAY";
            /**********************/
            // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
            //调用RSA签名方式
            AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
            AlipayTradePagePayRequest alipay_request=new AlipayTradePagePayRequest();

            // 封装请求支付信息
            AlipayTradePagePayModel model=new AlipayTradePagePayModel();
            model.setOutTradeNo(out_trade_no);
            model.setSubject(subject);
            model.setTotalAmount(total_amount);
            model.setBody(body);
            model.setTimeoutExpress(timeout_express);
            model.setProductCode(product_code);
            alipay_request.setBizModel(model);
            // 设置异步通知地址
            alipay_request.setNotifyUrl(AlipayConfig.notify_url);
            // 设置同步地址
            alipay_request.setReturnUrl(AlipayConfig.return_url);

            // form表单生产
            String form = "";
            try {
                // 调用SDK生成表单
                form = client.pageExecute(alipay_request).getBody();
                response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
                response.getWriter().write(form);//直接将完整的表单html输出到页面
                System.out.print(form);
                response.getWriter().flush();
                response.getWriter().close();
            } catch (AlipayApiException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }else{

        }

    }

    //接收支付宝的返回通知
    public void alipayReturnNotice(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {

        PrintWriter out = response.getWriter();
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //支付宝交易号

        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        //验证上面的信息是不是由支付宝给我们返回的
        boolean verify_result = false;
        try {
            verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        if(verify_result){//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码
            //该页面可做页面美工编辑
            //1.将订单的状态修改为1（已支付）
            orderService.update(1,out_trade_no);
            //2.将产品的销售数量加1

            //3.会员的积分要增加
            //4.通知物流模块或系统去发货
            out.println("验证成功<br />");
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

            //////////////////////////////////////////////////////////////////////////////////////////
        }else{
            //该页面可做页面美工编辑
            out.println("验证失败");
        }

    }


}
