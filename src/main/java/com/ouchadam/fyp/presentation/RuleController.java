package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.presentation.tab.rule.RuleContainer;

import java.util.List;

public interface RuleController {
    List<RuleContainer<Member>> get();
}
