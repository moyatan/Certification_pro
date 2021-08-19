package Certification.app.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;

@Controller
public class ContactusController {

	@RequestMapping(value={"/contactus"},method=RequestMethod.GET)
	@ResponseBody
	public String postContactus(@RequestParam("text")String textValue,@RequestParam("select")String selectValue) {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分ss秒SSSミリ秒 E曜日");
		String datenow = dtformat.format(date);
		String message = "カテゴリ:" + selectValue + "\n" + "お問い合わせ内容:" + textValue + "\n" + "お問い合わせ日時:" + datenow;
		String returnMessage = "";
		try {
			SlackSession session = SlackSessionFactory.createWebSocketSlackSession("xoxb-1884296775441-2393416508369-G46LgtyZbV9tAfxoX6feiKcI");
			session.connect(); //slackに接続
			SlackChannel channel = session.findChannelByName("general");
			session.sendMessage(channel,message);
			session.disconnect();
			returnMessage = "お問い合わせできました";
		}catch(Exception e) {
			System.out.println(e);
			returnMessage = "お問い合わせ内容の送信に失敗しました";
		}
		
		return returnMessage;
	}
}
