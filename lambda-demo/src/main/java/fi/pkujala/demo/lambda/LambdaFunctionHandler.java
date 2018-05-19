package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.demo.vo.RequestVO;
import com.amazonaws.lambda.demo.vo.ResponseVO;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<RequestVO, ResponseVO> {

    @Override
    public ResponseVO handleRequest(RequestVO input, Context context) {
        context.getLogger().log("Input: " + input);

        return new ResponseVO("Hello mr. " + input.getFirstName() + " " + input.getLastName() + "!");
    }

}
