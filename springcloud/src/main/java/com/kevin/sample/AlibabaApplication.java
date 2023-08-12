package com.kevin.sample;


import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication

public class AlibabaApplication {

	public static void main(String[] args) {
		initFlowQpsRule();
		SpringApplication.run(AlibabaApplication.class, args);
	}

	private static void initFlowQpsRule() {
		List<FlowRule> rules = new ArrayList<FlowRule>();
		FlowRule flowQps = new FlowRule();
		flowQps.setResource("retrieveAlerts");

		flowQps.setCount(10);
		flowQps.setGrade(RuleConstant.FLOW_GRADE_QPS);
		rules.add(flowQps);
		FlowRuleManager.loadRules(rules);
	}

}
