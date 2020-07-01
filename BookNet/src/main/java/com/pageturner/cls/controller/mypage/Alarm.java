package com.pageturner.cls.controller.mypage;

/**
 * 	이 클래스는 알람리스트를 호출하는 클래스이다.
 * @author	박기윤
 * @since	2020.07.01
 * @version	v.1.0
 * 
 */

import java.util.*;
import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import com.pageturner.cls.service.*;
import com.pageturner.cls.vo.*;

@Controller
public class Alarm {
	@Autowired
	MyPageService mpSrvc;
	@Autowired
	AlarmService alSrvc;
	
	@RequestMapping("/alarm/alarmPage.cls")
	public ModelAndView showAlarmList(ModelAndView mv, HttpSession session) {
		String view = "mypage/alarmPage";
		String sid = (String)session.getAttribute("SID");
		MemberVO mVO = new MemberVO();
		ArrayList<AlarmVO> list = new ArrayList<AlarmVO>();
		
		mVO.setId(sid);
		mVO = mpSrvc.getInfo(mVO);
		try {
			list = alSrvc.getAlarmList(mVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("LIST", list);
		mv.setViewName(view);
		return mv;
	}
}