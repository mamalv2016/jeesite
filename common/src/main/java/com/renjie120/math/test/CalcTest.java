package com.renjie120.math.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

import com.renjie120.math.CalcInput;
import com.renjie120.math.CalcStrategyManager;

public class CalcTest {
	CalcInput input;

	@Before
	public void setUp() throws Exception {
		input = new CalcInput();
		input.setBen(900000.0)  ;
		input.setAddon(5000.0);
		input.setFee(0.084);
		input.setMonth(60);
		input.setYear(5);

	}

	/**
	 * 计算银行默认的存款计算器
	 * 
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void testBankCalc() throws UnsupportedEncodingException, IOException {
		input.setCalcMethod( 1);
		CalcStrategyManager.saveHtmlFile(input, "d:\\1.html", input.getYear()
				+ "年银行存款");
	}

	//@Test
	public void testFuliAndZuijiaCacl() throws UnsupportedEncodingException,
			IOException {
		input.setCalcMethod(2);
		CalcStrategyManager.saveHtmlFile(input, "d:\\2.html", input.getBen()
				+ "复利投资" + input.getMonth() + "月"
				+ (input.getAddon() != 0 ? "每月追加" + input.getAddon() + "元" : ""));
	}

	//@Test
	public void testDengBenCacl() throws UnsupportedEncodingException,
			IOException {
		input.setCalcMethod( 3);
		CalcStrategyManager.saveHtmlFile(input, "d:\\3.html", input.getBen()
				+ "等本金还款" + input.getMonth() + "月");
	}

	//@Test
	public void testDengBenXiCacl() throws UnsupportedEncodingException,
			IOException {
		input.setCalcMethod( 4);
		CalcStrategyManager.saveHtmlFile(input, "d:\\4.html", input.getBen()
				+ "等额本息还款" + input.getMonth() + "月");
	}
}
