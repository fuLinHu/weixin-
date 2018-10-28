package com.fulinhu.controll;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fulinhu.po.Menu;
import com.fulinhu.po.Token;
import com.fulinhu.util.CommonUtil;
import com.fulinhu.util.MeanUtil;
import com.fulinhu.util.quarz.TokenJob;
import com.fulinhu.util.quarz.TokenSchedule;

public class InitMeanuServlet extends HttpServlet {
     public void init() throws ServletException {
    	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
