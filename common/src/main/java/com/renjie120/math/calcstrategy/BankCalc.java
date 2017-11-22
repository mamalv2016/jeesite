package com.renjie120.math.calcstrategy;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.renjie120.math.CalcInput;
import com.renjie120.math.CalcResult;
import com.renjie120.math.ComeInPlan;
import com.renjie120.math.ICaclSumMethod;
import com.renjie120.math.tool.MathTool;

/**
 * 银行默认存款计算器 calcMethod ==1
 * 
 * @author Administrator
 * 
 */
public class BankCalc implements ICaclSumMethod { 

	/**
	 * 计算银行默认存款的利息，只使用年份参数，不使用月份参数. 且不使用追加投资金额，就是简单的计算利息
	 * 
	 * @param ben
	 * @param fee
	 * @param year
	 * @return
	 */
	private double calcLixi(double ben, double fee, int year) {
		return MathTool.multiply(MathTool.multiply(ben, fee), year);
	}

	private void checkArgument(CalcInput input) {
		Preconditions.checkArgument(input.getYear() > 0, "年份数必须大于0");
	}

	@Override
	public CalcResult calc(CalcInput input) {
		checkArgument(input);

		CalcResult result = new CalcResult();
		result.benjin = input.getBen();

		result.lixi = calcLixi(input.getBen(), input.getFee(), input.getYear());

		result.rate = MathTool.multiply(input.getFee(), input.getYear());

		return result;
	}

	@Override
	public boolean accept(CalcInput input) {
		return input.getCalcMethod() == 1;
	}

	@Override
	public List<ComeInPlan> plan(CalcInput input) {
		checkArgument(input);
		List<ComeInPlan> result = Lists.newArrayList();

		double lastBen = input.getBen();
		for (int i = 0; i < input.getYear(); i++) {
			ComeInPlan p1 = new ComeInPlan();
			p1.periodic = i + 1;
			p1.initMoney = lastBen;
			p1.profit = MathTool.multiply(input.getBen(), input.getFee());
			p1.endMoney = MathTool.add(lastBen, p1.profit);
			p1.rate = MathTool.multiply(input.getFee(), p1.periodic);

			lastBen = p1.endMoney;

			result.add(p1);
		}
		return result;
	}

}
