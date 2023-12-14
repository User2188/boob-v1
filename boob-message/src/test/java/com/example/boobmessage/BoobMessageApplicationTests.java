package com.example.boobmessage;

import com.example.boobmessage.model.Message;
import com.example.boobmessage.repository.MessageRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BoobMessageApplicationTests {
    @Autowired
    private MessageRepository messageRepository;

    @Test
    void contextLoads() {
    }

    /**
     * 添加操作
     */
    @Test
    void create() {

        for (int i = 0; i < 5; i++) {
            Message message = new Message();
            message.setUserName1("zhangsan" + i);
            message.setUserName2("zhuli");
            message.setObjId(1);
            message.setType(1);
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            System.out.println(dateFormat.format(date));
            message.setTime(date);

            Message save = messageRepository.save(message);

            System.out.println(save);
        }
    }

    /**
     * 查找操作
     */
    @Test
    void getMessagesByUserName2() {
        List<Message> find = messageRepository.getMessagesByUserName2("zhuli");
        find.forEach(System.out::println);
    }

    /**
     * 删除
     */
    @Test
    void deleteMessage() {
        String id = "6579cbdedea03616f3f07eaa";
        messageRepository.deleteById(id);
        System.out.println("delete " + id.toString() + " sucess");
    }

    @Test
    void deleteMessagesByUserName2() {
        String userName2 = "bob";
        messageRepository.deleteMessagesByUserName2(userName2);
        System.out.println("delete " + userName2 + " sucess");
    }

}
