package com.derbygras.shavepoller;

import javax.inject.Inject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ShavePollerHandler implements RequestHandler<Object, String> {

    private final DropMonsterComponent component;

    @Inject
    public ShavePollerHandler() {
        this.component = DaggerDropMonsterComponent.builder().build();
    }

    @Override
    public String handleRequest(Object input, Context context) {
        LambdaLogger logger = context.getLogger();

        try {
            component.dropMonster().executeLogic();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "COMPLETE";
    }

}
