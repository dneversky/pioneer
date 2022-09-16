package dev.dneversky.pioneer.user.api.kafka;

import dev.dneversky.pioneer.user.MessageModel;
import dev.dneversky.pioneer.user.service.impl.DefaultUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TeamListener {

    private final DefaultUserService userService;

    @Autowired
    public TeamListener(DefaultUserService userService) {
        this.userService = userService;
    }

    @KafkaListener(topics = "topic.user", groupId = "group.team")
    public void listenGroupFoo(MessageModel message) {
        log.info("Received Message in group team: {}", message);
        userService.changeTeam(message.getUserId(), message.getTeamId());
    }
}
