package com.github.experion.toolpath.items.custom_lambdas;

import com.github.experion.toolpath.items.tool_lambdas.ExistsLambdas;
import com.github.experion.toolpath.items.tool_lambdas.TriggerLambdas;

public class FlintLambdas implements TriggerLambdas {

    @Override
    public ExistsLambdas exists() {
        return new ExistsLambdas().PostHit().PostMine().UseBlock();
    }

}
