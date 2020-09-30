package com.superzyen.dsltool.offline;

import com.superzyen.common.InOutFile;

import java.io.*;

public class GenDslTool {

    public void gen() throws IOException {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {

            File dslFile = InOutFile.getInputFile();
            File convertFile = InOutFile.getOutputFile();
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(dslFile)));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(convertFile)));
            String dsl = null;
            while ((dsl = bufferedReader.readLine()) != null) {
                System.out.println(dsl);
                dsl = this.processDsl(dsl);
                String convertDsl = String.format("builder.append(\"%s\");", dsl);
                bufferedWriter.write(convertDsl);
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bufferedReader.close();
            bufferedWriter.close();
        }
    }

    public String gen(String dsl) {
        StringBuilder builder = new StringBuilder();
        try {
            dsl = this.processDsl(dsl);
            String[] dslSplits = null;
            if (dsl.contains("\r\n")) {
                dslSplits = dsl.split("\\r\\n");
            } else {
                dslSplits = dsl.split("\\n");
            }
            for (int i = 0, j = dslSplits.length; i < j; i++) {
                String convertDsl = String.format("builder.append(\"%s\");\r\n", dslSplits[i]);
                builder.append(convertDsl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private String processDsl(String dsl) {
        return dsl.replace("\"", "\\\"");
    }
}
