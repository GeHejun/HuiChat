package com.ghj.access.umps;

import com.alibaba.fastjson.JSON;
import com.ghj.access.umps.dto.UserDTO;
import com.ghj.common.HSStatusEnum;
import com.ghj.common.HSession;
import com.ghj.protocol.Msg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import static com.ghj.common.constant.Constant.LOGIN_USER;

@Service
public class UserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public Boolean login(String channelId, String host, Msg.Login login) {
        String user = stringRedisTemplate.opsForValue().get(login.getForm());
        if (StringUtils.isEmpty(user)) {
            return false;
        }
        UserDTO userDTO = JSON.parseObject(user, UserDTO.class);
        HSession hSession = new HSession()
                .setChannelId(channelId)
                .setCreateTime(Instant.now().toEpochMilli())
                .setStatus(HSStatusEnum.ON_LINE.getCode())
                .setUId(login.getForm())
                .setUName(userDTO.getName())
                .setIp(host);
        String sessionStr = JSON.toJSONString(hSession);
        stringRedisTemplate.opsForValue().set(LOGIN_USER + login.getForm(), sessionStr, 5, TimeUnit.MINUTES);
        //TODO 登录消息是否需要存储
        return true;
    }


    public void logout(Msg.Logout logout) {
        stringRedisTemplate.delete(LOGIN_USER + logout.getForm());
        //TODO 登出消息是否需要存储
    }
}
