package com.coffee.coffeeweb.email;

import com.coffee.coffeeweb.email.services.EmailService;
import com.coffee.coffeeweb.email.vo.MailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    /**
     * 发送邮件的主界面
     */
    @GetMapping("/email")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("email/email");//打开发送邮件的页面
        mv.addObject("from", emailService.getMailSendFrom());//邮件发信人
        return mv;
    }
    /**
     * 发送邮件
     */
    @PostMapping("/mail/send")
    public MailVo sendMail(MailVo mailVo, MultipartFile[] files) {
        mailVo.setMultipartFiles(files);
        return emailService.sendMail(mailVo);//发送邮件和附件
    }
}
