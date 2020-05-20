package com.example.demo;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@RestController
@RequestMapping(value = "/v1")
public class StreamingWebServer {

    @RequestMapping(value = "/import/asm-reports/{assembly}", method = RequestMethod.GET)
    public StreamingResponseBody getVariantsByRegionStreamingOutput(
            @PathVariable String assembly,
            HttpServletRequest request,
            HttpServletResponse response) {
        return outputStream -> {
            response.setContentType("text/plain;charset=UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write("first message about '" + assembly + "'. request: {isAsyncSupported:" + request
                    .isAsyncSupported() + " }. sleeping...");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            sleepSeconds(bufferedWriter, 4);

            bufferedWriter.write("second message. sleeping...");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            sleepSeconds(bufferedWriter, 1);

            bufferedWriter.write("progress update 1. sleeping...");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            sleepSeconds(bufferedWriter, 1);
            bufferedWriter.write("progress update 2. sleeping...");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            sleepSeconds(bufferedWriter, 1);
            bufferedWriter.write("progress update 3. sleeping...");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            sleepSeconds(bufferedWriter, 2);

            bufferedWriter.write("final message.");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
        };
    }

    private void sleepSeconds(BufferedWriter bufferedWriter, int seconds) throws IOException {
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            bufferedWriter.write("interrupted!");
            bufferedWriter.flush();
            throw new RuntimeException(e);
        }
    }
}
