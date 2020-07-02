package com.boot.admin.notify;

import com.alibaba.fastjson.JSONObject;
import com.boot.admin.notify.webhook.WebhookUtil;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Macro
 * @Description: 服务自定义通知
 * @Date: 2020/2/28 上午11:50
 * @Version: 1.0
 */
@Component
@Slf4j
public class CustomNotifier  extends AbstractStatusChangeNotifier {

    public CustomNotifier(InstanceRepository repository) {
        super(repository);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        return Mono.fromRunnable(() -> {
            if (event instanceof InstanceStatusChangedEvent) {
                log.error("[ monitor ] 服务集群：{} 发生异常,实例信息：{}, 状态：{}",
                        instance.getRegistration().getName(),
                        event.getInstance(),
                        ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus());
                //状态
                String status = ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus();
                // 通知消息内容
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("servieName", instance.getRegistration().getName());
                jsonObject.put("detail", ((InstanceStatusChangedEvent) event).getStatusInfo().getDetails());
                jsonObject.put("status", status);

                switch (status) {
                    // 健康检查没通过
                    case "DOWN":
                        System.out.println("发送 健康检查没通过 的通知！");
                        break;
                    // 服务离线
                    case "OFFLINE":
                        System.out.println("发送 服务离线 的通知！");
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("【 系统服务 】");
                        stringBuilder.append("\n 服务名：").append(instance.getRegistration().getName());
                        stringBuilder.append("\n 事件：").append("服务离线");
                        stringBuilder.append("\n 详情：").append(((InstanceStatusChangedEvent) event).getStatusInfo().getDetails());
                        Map param = new HashMap();
                        param.put("content", stringBuilder.toString());
                        WebhookUtil.sendText("",
                                param);
                        break;
                    //服务上线
                    case "UP":
                        System.out.println("发送 服务上线 的通知！");
                        break;
                    // 服务未知异常
                    case "UNKNOWN":
                        System.out.println("发送 服务未知异常 的通知！");
                        break;
                    default:
                        break;
                }

            } else {
                log.error("[ monitor ] 服务集群：{} 发生异常,实例信息：{}, 事件类型：{}",
                        instance.getRegistration().getName(),
                        event.getInstance(),
                        event.getType());
            }
        });
    }
}
