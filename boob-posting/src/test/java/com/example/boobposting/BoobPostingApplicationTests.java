package com.example.boobposting;

import com.example.boobposting.dao.PostingDAO;
import com.example.boobposting.dao.ReplyDAO;
import com.example.boobposting.model.Posting;
import com.example.boobposting.model.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static java.lang.System.*;

@SpringBootTest
class BoobPostingApplicationTests {

    @Autowired
    ReplyDAO replyDAO;

    @Autowired
    PostingDAO postingDAO;

    @Test
    void contextLoads() {
    }

    @Test
    public void jpaTestReplyController() throws Exception {
//        Reply entity = new Reply();
//        entity.setUserName("无境");
//        entity.setContent("不错");
////        entity.setObjId(1);
//        entity.setReplyTime(new Date());
//        entity.setReplyLevel(1);
//        replyDAO.save(entity);
//        List<Reply> replyList = replyDAO.findAll();
//        for (Reply reply : replyList) {
//            out.println(reply.toString());
//        }
//        out.println("================================================");

//        for (int j = 152; j < 155; j++) {
//            // 循环插入（测试page）
//            for (int i = 0; i < 5; i++) {
//                Reply entity = new Reply(); // 在每次循环迭代时创建新的对象
//                entity.setUserName("mary");
//                entity.setObjId(j); // 回复目标id为j
//                entity.setReplyLevel(0); // 回复的是帖子
//                entity.setContent("reply num " + i);
//                entity.setReplyTime(new Date());
//
//                replyDAO.save(entity);
//            }
//        }

//        List<Reply> replyList = replyDAO.findByObjIdAndReplyLevel(152,0);
//        out.println(replyList.isEmpty());
//        for (Reply reply : replyList) {
//            out.println(reply.toString());
//        }


//        // 测试分页
//        int obgId = 152, replyLevel = 0, page = 0, size = 2;
//        Pageable pageable = PageRequest.of(page, size, Sort.by("replyTime").descending());
//        Page<Reply> replyPage = replyDAO.findByObjIdAndReplyLevel(obgId, replyLevel, pageable);
//        out.println(replyPage == null);
//
//        out.println(replyPage.getContent().isEmpty());
//        for (Reply reply : replyPage.getContent()) {
//            out.println(reply.toString());
//        }


    }

    @Test
    public void jpaTestPostingController() throws Exception {

//        //测试save
//        Posting entity = new Posting();
//        entity.setUserName("bob");
//        entity.setContent("is what?");
//        entity.setTitle("java jpa 和 mybatis 区别");
//        entity.setCreateTime(new Date());
//
//        postingDAO.save(entity);

//        //测试findByUserName
//        List<Posting> postingList = postingDAO.findByUserName("bob");
//        for (Posting posting : postingList) {
//            out.println(posting.toString());
//        }
//        out.println("================================================");

//        // 循环插入（测试page）
//        for (int i = 0; i < 20; i++) {
//            Posting entity = new Posting(); // 在每次循环迭代时创建新的对象
//            entity.setUserName("mary");
//            entity.setTitle("page test");
//            entity.setContent("page test " + i);
//            entity.setCreateTime(new Date());
//            postingDAO.save(entity);
//        }

        // 测试分页
        int page=1,size=5;
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Page<Posting> postingPage = postingDAO.findByUserName("111", pageable);
        out.println(postingPage == null);
//        out.println(postingPage.getContent().isEmpty());
//        for (Posting posting : postingPage.getContent()) {
//            out.println(posting.toString());
//        }
    }

}
